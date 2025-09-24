import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class CacheResultExample {
    
    private static Map<String, Object> cache = new HashMap<>();
    
    @CacheResult
    public int fibonacci(int n) {
        System.out.println("Computing fibonacci(" + n + ")");
        if (n <= 1) {
            return n;
        }
        return fibonacci(n - 1) + fibonacci(n - 2);
    }
    
    @CacheResult
    public int factorial(int n) {
        System.out.println("Computing factorial(" + n + ")");
        if (n <= 1) {
            return 1;
        }
        return n * factorial(n - 1);
    }
    
    @CacheResult
    public int power(int base, int exponent) {
        System.out.println("Computing power(" + base + ", " + exponent + ")");
        int result = 1;
        for (int i = 0; i < exponent; i++) {
            result *= base;
        }
        return result;
    }
    
    public int simpleAdd(int a, int b) {
        System.out.println("Computing add(" + a + ", " + b + ")");
        return a + b;
    }
    
    public static void main(String[] args) {
        CacheResultExample example = new CacheResultExample();
        
        System.out.println("=== Testing Cached Methods ===");
        
        System.out.println("\n1. Testing fibonacci(5) - first call:");
        long startTime = System.currentTimeMillis();
        int result1 = invokeWithCache(example, "fibonacci", 5);
        long endTime = System.currentTimeMillis();
        System.out.println("Result: " + result1 + " (Time: " + (endTime - startTime) + "ms)");
        
        System.out.println("\n2. Testing fibonacci(5) - second call (should use cache):");
        startTime = System.currentTimeMillis();
        int result2 = invokeWithCache(example, "fibonacci", 5);
        endTime = System.currentTimeMillis();
        System.out.println("Result: " + result2 + " (Time: " + (endTime - startTime) + "ms)");
        
        System.out.println("\n3. Testing factorial(6) - first call:");
        startTime = System.currentTimeMillis();
        int result3 = invokeWithCache(example, "factorial", 6);
        endTime = System.currentTimeMillis();
        System.out.println("Result: " + result3 + " (Time: " + (endTime - startTime) + "ms)");
        
        System.out.println("\n4. Testing factorial(6) - second call (should use cache):");
        startTime = System.currentTimeMillis();
        int result4 = invokeWithCache(example, "factorial", 6);
        endTime = System.currentTimeMillis();
        System.out.println("Result: " + result4 + " (Time: " + (endTime - startTime) + "ms)");
        
        System.out.println("\n5. Testing power(2, 10) - first call:");
        startTime = System.currentTimeMillis();
        int result5 = invokeWithCache(example, "power", 2, 10);
        endTime = System.currentTimeMillis();
        System.out.println("Result: " + result5 + " (Time: " + (endTime - startTime) + "ms)");
        
        System.out.println("\n6. Testing power(2, 10) - second call (should use cache):");
        startTime = System.currentTimeMillis();
        int result6 = invokeWithCache(example, "power", 2, 10);
        endTime = System.currentTimeMillis();
        System.out.println("Result: " + result6 + " (Time: " + (endTime - startTime) + "ms)");
        
        System.out.println("\n7. Testing simpleAdd(5, 3) - not cached:");
        startTime = System.currentTimeMillis();
        int result7 = invokeWithCache(example, "simpleAdd", 5, 3);
        endTime = System.currentTimeMillis();
        System.out.println("Result: " + result7 + " (Time: " + (endTime - startTime) + "ms)");
        
        System.out.println("\n8. Testing simpleAdd(5, 3) - second call (not cached):");
        startTime = System.currentTimeMillis();
        int result8 = invokeWithCache(example, "simpleAdd", 5, 3);
        endTime = System.currentTimeMillis();
        System.out.println("Result: " + result8 + " (Time: " + (endTime - startTime) + "ms)");
        
        System.out.println("\nCache contents: " + cache);
    }
    
    private static Object invokeWithCache(Object obj, String methodName, Object... args) {
        try {
            Class<?> clazz = obj.getClass();
            Method method = null;
            
            if (args.length == 1) {
                method = clazz.getMethod(methodName, int.class);
            } else if (args.length == 2) {
                method = clazz.getMethod(methodName, int.class, int.class);
            }
            
            if (method != null && method.isAnnotationPresent(CacheResult.class)) {
                String cacheKey = methodName + "_" + java.util.Arrays.toString(args);
                
                if (cache.containsKey(cacheKey)) {
                    System.out.println("Cache hit for " + cacheKey);
                    return cache.get(cacheKey);
                }
                
                Object result = method.invoke(obj, args);
                cache.put(cacheKey, result);
                System.out.println("Result cached for " + cacheKey);
                return result;
            } else {
                return method.invoke(obj, args);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
