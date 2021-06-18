package bulchalka;

public class Factorial {

    public static int iterative(int num) {
        if (num == 0) {
            return 1;
        }
        int factorial = 1;
        for (int i = 1; i <= num; i++) {
            factorial *= i;
        }
        return factorial;
    }

    public static int recursive(int num) {
        if (num == 0) { //base case
            return 1;
        }
        return num * recursive(num - 1);
    }

    public static void main(String[] args) {
        System.out.println(recursive(3));
    }
}
