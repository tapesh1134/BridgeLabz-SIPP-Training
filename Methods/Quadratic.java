import java.util.Scanner;

public class Quadratic {

    // Method to find roots of quadratic equation
    public static double[] findRoots(double a, double b, double c) {
        double delta = Math.pow(b, 2) - 4 * a * c;
        if (delta > 0) {
            double root1 = (-b + Math.sqrt(delta)) / (2 * a);
            double root2 = (-b - Math.sqrt(delta)) / (2 * a);
            return new double[]{root1, root2};
        } else if (delta == 0) {
            double root = -b / (2 * a);
            return new double[]{root};
        } else {
            // No real roots
            return new double[]{};
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter coefficients a, b, and c:");

        double a = scanner.nextDouble();
        double b = scanner.nextDouble();
        double c = scanner.nextDouble();

        double[] roots = findRoots(a, b, c);

        if (roots.length == 2) {
            System.out.println("Two roots: " + roots[0] + " and " + roots[1]);
        } else if (roots.length == 1) {
            System.out.println("One root: " + roots[0]);
        } else {
            System.out.println("No real roots.");
        }

        scanner.close();
    }
}
