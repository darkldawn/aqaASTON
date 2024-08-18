package src.main;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FactorialCalculatorTest {

    @Test
    public void testFactorialOfZero() {
        long result = FactorialCalculator.calculateFactorial(0);
        Assertions.assertEquals(1, result, "Факториал 0 должен быть равен 1.");
    }

    @Test
    public void testFactorialOfOne() {
        long result = FactorialCalculator.calculateFactorial(1);
        Assertions.assertEquals(1, result, "Факториал 1 должен быть равен 1.");
    }

    @Test
    public void testFactorialOfPositiveNumber() {

        long result = FactorialCalculator.calculateFactorial(5);
        Assertions.assertEquals(120, result, "Факториал 5 должен быть равен 120.");

        result = FactorialCalculator.calculateFactorial(10);
        Assertions.assertEquals(3628800, result, "Факториал 10 должен быть равен 3628800.");
    }

    @Test
    public void testFactorialOfNegativeNumber() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            FactorialCalculator.calculateFactorial(-1);
        }, "Факториал не определен для отрицательных чисел.");
    }
    @Test
    public void testFactorialOfLargeNumber() {
        long result = FactorialCalculator.calculateFactorial(20);
        Assertions.assertEquals(2432902008176640000L, result, "Факториал 20 должен быть равен 2432902008176640000.");
    }
}
