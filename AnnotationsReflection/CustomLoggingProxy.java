import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class CustomLoggingProxy {
    
    public static <T> T createLoggingProxy(Class<T> interfaceClass, T target) {
        return (T) Proxy.newProxyInstance(
            interfaceClass.getClassLoader(),
            new Class[]{interfaceClass},
            new LoggingInvocationHandler(target)
        );
    }
    
    private static class LoggingInvocationHandler implements InvocationHandler {
        private final Object target;
        
        public LoggingInvocationHandler(Object target) {
            this.target = target;
        }
        
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("Method called: " + method.getName());
            System.out.println("Arguments: " + java.util.Arrays.toString(args));
            
            long startTime = System.currentTimeMillis();
            Object result = method.invoke(target, args);
            long endTime = System.currentTimeMillis();
            
            System.out.println("Method " + method.getName() + " executed in " + (endTime - startTime) + " ms");
            System.out.println("Result: " + result);
            System.out.println("---");
            
            return result;
        }
    }
    
    public static void main(String[] args) {
        GreetingImpl greetingImpl = new GreetingImpl();
        Greeting greetingProxy = createLoggingProxy(Greeting.class, greetingImpl);
        
        greetingProxy.sayHello("World");
        greetingProxy.sayGoodbye("Java");
        greetingProxy.sayWelcome("Reflection");
    }
}
