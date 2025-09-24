import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Map;

public class GenerateJsonRepresentation {
    
    public static String toJson(Object obj) {
        if (obj == null) {
            return "null";
        }
        
        Class<?> clazz = obj.getClass();
        
        if (clazz == String.class) {
            return "\"" + obj + "\"";
        }
        
        if (clazz.isPrimitive() || Number.class.isAssignableFrom(clazz) || 
            Boolean.class.isAssignableFrom(clazz)) {
            return obj.toString();
        }
        
        if (Collection.class.isAssignableFrom(clazz)) {
            Collection<?> collection = (Collection<?>) obj;
            StringBuilder sb = new StringBuilder("[");
            boolean first = true;
            for (Object item : collection) {
                if (!first) sb.append(", ");
                sb.append(toJson(item));
                first = false;
            }
            sb.append("]");
            return sb.toString();
        }
        
        if (Map.class.isAssignableFrom(clazz)) {
            Map<?, ?> map = (Map<?, ?>) obj;
            StringBuilder sb = new StringBuilder("{");
            boolean first = true;
            for (Map.Entry<?, ?> entry : map.entrySet()) {
                if (!first) sb.append(", ");
                sb.append("\"").append(entry.getKey()).append("\": ");
                sb.append(toJson(entry.getValue()));
                first = false;
            }
            sb.append("}");
            return sb.toString();
        }
        
        StringBuilder sb = new StringBuilder("{");
        Field[] fields = clazz.getDeclaredFields();
        boolean first = true;
        
        for (Field field : fields) {
            try {
                field.setAccessible(true);
                Object value = field.get(obj);
                
                if (!first) sb.append(", ");
                sb.append("\"").append(field.getName()).append("\": ");
                sb.append(toJson(value));
                first = false;
                
            } catch (IllegalAccessException e) {
                System.out.println("Cannot access field: " + field.getName());
            }
        }
        
        sb.append("}");
        return sb.toString();
    }
    
    public static void main(String[] args) {
        Student student = new Student("Alice", 103, "Physics");
        System.out.println("Student JSON: " + toJson(student));
        
        Person person = new Person("Charlie", 35);
        System.out.println("Person JSON: " + toJson(person));
        
        Calculator calculator = new Calculator();
        System.out.println("Calculator JSON: " + toJson(calculator));
    }
}
