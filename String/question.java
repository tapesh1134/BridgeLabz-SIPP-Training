
public class question {

    public static void main(String[] args) {
        String s = "my name is tapesh";
        for (String str : s.split(" ")) {
            System.out.println(str + " " + str.length());
        }
        // Scanner sc = new Scanner(System.in);
        // int n = sc.nextInt();
        // int[] arr = new int[n];
        // int high = Integer.MIN_VALUE;
        // int low = Integer.MAX_VALUE;
        // int sum = 0;
        // for (int i = 0; i < n; i++) {
        //     int val;
        //     while (!sc.hasNextInt()) {
        //         System.out.println("Enter a numeric value: ");
        //         sc.next();
        //     }
        //     val = sc.nextInt();
        //     if (val < 0) {
        //         System.out.println("Enter valid input: ");
        //         val = sc.nextInt();
        //     }
        //     arr[i] = val;
        //     sum += val;
        //     if (val > high) {
        //         high = val;
        //     }
        //     if (val < low) {
        //         low = val;
        //     }
        // }
        // System.out.println("Average: " + sum / arr.length);
        // System.out.println("Lowest: " + low);
        // System.out.println("Highest: " + high);
        // for (int T : arr) {
        //     if (T > sum / arr.length) {
        //         System.out.print(T + " ");
        //     }
        // }
    }
}
