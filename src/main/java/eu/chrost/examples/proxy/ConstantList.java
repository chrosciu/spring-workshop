package eu.chrost.examples.proxy;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Proxy;
import java.util.List;

@Slf4j
public class ConstantList {
    public static void main(String[] args) {
        List list = (List)Proxy.newProxyInstance(
                ConstantList.class.getClassLoader(),
                new Class[]{List.class},
                (proxy, method, arguments) -> {
                    if ("get".equals(method.getName())) {
                        return 1;
                    } else {
                        throw new UnsupportedOperationException();
                    }
                }
        );
        log.info("{}", list.get(3));
    }
}
