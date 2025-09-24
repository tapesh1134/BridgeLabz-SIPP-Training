public class GreetingImpl implements Greeting {
    @Override
    public String sayHello(String name) {
        return "Hello, " + name + "!";
    }
    
    @Override
    public String sayGoodbye(String name) {
        return "Goodbye, " + name + "!";
    }
    
    @Override
    public String sayWelcome(String name) {
        return "Welcome, " + name + "!";
    }
}
