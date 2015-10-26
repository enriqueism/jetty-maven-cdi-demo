package rmohr.examples.cdi;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class DefaultGreeting implements Greeting {

    private int counter = 0;

    @Override
    public String getText() {
        return String.format("Hello %d!", counter++);
    }
}
