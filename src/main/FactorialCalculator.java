package src.main;
import java.util.Scanner;

public class FactorialCalculator {


    public static long calculateFactorial(int number) {
        if (number < 0) {
            throw new IllegalArgumentException("Факториал не определен для отрицательных чисел.");
        }
        long factorial = 1;
        for (int i = 1; i <= number; i++) {
            factorial *= i;
        }
        return factorial;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите число для вычисления факториала: ");
        int number = scanner.nextInt();

        try {
            long result = calculateFactorial(number);
            System.out.println("Факториал числа " + number + " равен: " + result);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        scanner.close();
    }
}