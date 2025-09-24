import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class DependencyInjectionUsingReflection {
    
    private static Map<Class<?>, Object> container = new HashMap<>();
    
    public static void register(Class<?> clazz, Object instance) {
        container.put(clazz, instance);
    }
    
    public static <T> T getInstance(Class<T> clazz) {
        Object instance = container.get(clazz);
        if (instance == null) {
            try {
                instance = clazz.getDeclaredConstructor().newInstance();
                container.put(clazz, instance);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        return clazz.cast(instance);
    }
    
    public static void injectDependencies(Object target) {
        Class<?> clazz = target.getClass();
        Field[] fields = clazz.getDeclaredFields();
        
        for (Field field : fields) {
            if (field.isAnnotationPresent(Inject.class)) {
                try {
                    field.setAccessible(true);
                    Object dependency = getInstance(field.getType());
                    field.set(target, dependency);
                    System.out.println("Injected " + field.getType().getSimpleName() + " into " + clazz.getSimpleName());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    public static void main(String[] args) {
        UserService userService = new UserService();
        
        System.out.println("Before injection:");
        System.out.println("DatabaseService: " + userService.getDatabaseService());
        System.out.println("EmailService: " + userService.getEmailService());
        
        injectDependencies(userService);
        
        System.out.println("\nAfter injection:");
        System.out.println("DatabaseService: " + userService.getDatabaseService());
        System.out.println("EmailService: " + userService.getEmailService());
        
        if (userService.getDatabaseService() != null) {
            userService.getDatabaseService().connect();
        }
        
        if (userService.getEmailService() != null) {
            userService.getEmailService().sendEmail("test@example.com", "Test", "Hello World");
        }
    }
}
