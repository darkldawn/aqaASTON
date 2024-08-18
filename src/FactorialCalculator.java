package src;
import java.util.Scanner;

public class FactorialCalculator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите число: ");
        int number = scanner.nextInt();

        long factorial = calculateFactorial(number);

        if (factorial == -1) {
            System.out.println("Факториал не определен для отрицательных чисел.");
        } else {
            System.out.println("Факториал " + number + " равен: " + factorial);
        }
    }
    public static long calculateFactorial(int number) {
        if (number < 0) {
            return -1; 
        } else if (number == 0) {
            return 1; 
        } else {
            long factorial = 1;
            for (int i = 1; i <= number; i++) {
                factorial *= i;
            }
            return factorial;
        }
    }
    public int factorial(int i) {
        return i;
    }
}
