package eu.chrost.examples.proxybeanmethods;

public class Foo {

    private final Bar bar;

    public Foo(Bar bar) {

        this.bar = bar;
    }

    public Bar getBar() {
        return bar;
    }
}
