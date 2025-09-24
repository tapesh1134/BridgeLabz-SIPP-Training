import java.lang.reflect.Method;
import java.util.Scanner;

public class DynamicMethodInvocation {
    public static void main(String[] args) {
        MathOperations mathOps = new MathOperations();
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Available methods: add, subtract, multiply, divide, power, factorial");
        System.out.print("Enter method name: ");
        String methodName = scanner.nextLine();
        
        try {
            Class<?> clazz = mathOps.getClass();
            Method method = null;
            Object result = null;
            
            if (methodName.equals("add") || methodName.equals("subtract") || methodName.equals("multiply")) {
                System.out.print("Enter first number: ");
                int a = scanner.nextInt();
                System.out.print("Enter second number: ");
                int b = scanner.nextInt();
                
                method = clazz.getMethod(methodName, int.class, int.class);
                result = method.invoke(mathOps, a, b);
                
            } else if (methodName.equals("divide")) {
                System.out.print("Enter first number: ");
                double a = scanner.nextDouble();
                System.out.print("Enter second number: ");
                double b = scanner.nextDouble();
                
                method = clazz.getMethod(methodName, double.class, double.class);
                result = method.invoke(mathOps, a, b);
                
            } else if (methodName.equals("power")) {
                System.out.print("Enter base: ");
                int base = scanner.nextInt();
                System.out.print("Enter exponent: ");
                int exponent = scanner.nextInt();
                
                method = clazz.getMethod(methodName, int.class, int.class);
                result = method.invoke(mathOps, base, exponent);
                
            } else if (methodName.equals("factorial")) {
                System.out.print("Enter number: ");
                int n = scanner.nextInt();
                
                method = clazz.getMethod(methodName, int.class);
                result = method.invoke(mathOps, n);
                
            } else {
                System.out.println("Method not found: " + methodName);
                return;
            }
            
            System.out.println("Result: " + result);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
