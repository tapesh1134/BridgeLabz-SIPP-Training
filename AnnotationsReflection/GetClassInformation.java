import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Scanner;

public class GetClassInformation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter class name: ");
        String className = scanner.nextLine();
        
        try {
            Class<?> clazz = Class.forName(className);
            
            System.out.println("\n=== CLASS INFORMATION ===");
            System.out.println("Class Name: " + clazz.getName());
            System.out.println("Simple Name: " + clazz.getSimpleName());
            System.out.println("Package: " + clazz.getPackage());
            
            System.out.println("\n=== CONSTRUCTORS ===");
            Constructor<?>[] constructors = clazz.getDeclaredConstructors();
            for (Constructor<?> constructor : constructors) {
                System.out.println("Constructor: " + constructor);
            }
            
            System.out.println("\n=== FIELDS ===");
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                System.out.println("Field: " + field);
            }
            
            System.out.println("\n=== METHODS ===");
            Method[] methods = clazz.getDeclaredMethods();
            for (Method method : methods) {
                System.out.println("Method: " + method);
            }
            
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found: " + className);
        }
    }
}
