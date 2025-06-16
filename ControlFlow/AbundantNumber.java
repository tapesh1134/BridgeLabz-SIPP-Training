
import java.util.*;

public class AbundantNumber {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.err.print("Enter a number");
        int num = sc.nextInt();
        int sum = 0;
        for (int i = 1; i * i < num; i++) {
            if (num % i == 0) {
                sum += (i + num / i);
            }
        }
        if (sum > num) {
            System.out.println("Abundant number");
        } else {
            System.err.println("Not a abundant number");
        }
    }
}
