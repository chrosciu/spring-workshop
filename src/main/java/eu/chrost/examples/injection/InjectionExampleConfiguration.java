package eu.chrost.examples.injection;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class InjectionExampleConfiguration {

    @Bean
    public OtherSingletonServiceUsingPrototype otherSingletonServiceUsingPrototype(PrototypeScopeService prototypeScopeService) {
        return new OtherSingletonServiceUsingPrototype(prototypeScopeService);
    }

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public SingletonServiceUsingPrototype singletonService(PrototypeScopeService prototypeScopeService) {
        return new SingletonServiceUsingPrototype(prototypeScopeService);
    }

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public SingletonServiceUsingPrototypeWithObjectProvider singletonServiceUsingPrototypeWithObjectProvider(
            ObjectFactory<PrototypeScopeService> prototypeScopeServiceObjectFactory) {
        return new SingletonServiceUsingPrototypeWithObjectProvider(prototypeScopeServiceObjectFactory);
    }

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public PrototypeScopeService prototypeScopeService() {
        return new PrototypeScopeService();
    }
}
