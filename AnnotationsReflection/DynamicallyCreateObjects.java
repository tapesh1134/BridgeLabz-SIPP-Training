import java.lang.reflect.Constructor;

public class DynamicallyCreateObjects {
    public static void main(String[] args) {
        try {
            Class<?> studentClass = Student.class;
            
            Constructor<?> defaultConstructor = studentClass.getDeclaredConstructor();
            Student student1 = (Student) defaultConstructor.newInstance();
            System.out.println("Default constructor: " + student1);
            
            Constructor<?> parameterizedConstructor = studentClass.getDeclaredConstructor(String.class, int.class, String.class);
            Student student2 = (Student) parameterizedConstructor.newInstance("John Doe", 101, "Computer Science");
            System.out.println("Parameterized constructor: " + student2);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
