import java.lang.reflect.Field;
import java.util.Map;

public class CustomObjectMapper {
    
    public static <T> T toObject(Class<T> clazz, Map<String, Object> properties) {
        try {
            T instance = clazz.getDeclaredConstructor().newInstance();
            
            for (Map.Entry<String, Object> entry : properties.entrySet()) {
                String fieldName = entry.getKey();
                Object value = entry.getValue();
                
                try {
                    Field field = clazz.getDeclaredField(fieldName);
                    field.setAccessible(true);
                    
                    if (value instanceof String && field.getType() == int.class) {
                        field.set(instance, Integer.parseInt((String) value));
                    } else if (value instanceof String && field.getType() == double.class) {
                        field.set(instance, Double.parseDouble((String) value));
                    } else if (value instanceof String && field.getType() == boolean.class) {
                        field.set(instance, Boolean.parseBoolean((String) value));
                    } else {
                        field.set(instance, value);
                    }
                    
                } catch (NoSuchFieldException e) {
                    System.out.println("Field not found: " + fieldName);
                }
            }
            
            return instance;
            
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static void main(String[] args) {
        Map<String, Object> studentData = Map.of(
            "name", "Jane Smith",
            "rollNumber", "102",
            "course", "Mathematics"
        );
        
        Student student = toObject(Student.class, studentData);
        System.out.println("Created student: " + student);
        
        Map<String, Object> personData = Map.of(
            "name", "Bob Johnson",
            "age", "28"
        );
        
        Person person = toObject(Person.class, personData);
        System.out.println("Created person: " + person);
    }
}
