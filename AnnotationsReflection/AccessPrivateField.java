import java.lang.reflect.Field;

public class AccessPrivateField {
    public static void main(String[] args) {
        Person person = new Person("Alice", 25);
        
        try {
            Class<?> clazz = person.getClass();
            Field ageField = clazz.getDeclaredField("age");
            
            ageField.setAccessible(true);
            
            System.out.println("Original age: " + ageField.get(person));
            
            ageField.set(person, 30);
            
            System.out.println("Modified age: " + ageField.get(person));
            System.out.println("Person object: " + person);
            
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
