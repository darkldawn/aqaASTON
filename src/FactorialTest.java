package src;

import org.testng.Assert;
import org.testng.annotations.Test;

public class FactorialTest {

    @Test
    public void testFactorialPositive() {
        int number = 5;
        long expectedFactorial = 120;
        long actualFactorial = FactorialCalculator.calculateFactorial(number);
        Assert.assertEquals(actualFactorial, expectedFactorial, "Факториал положительного числа должен быть корректным.");
    }

    @Test
    public void testFactorialZero() {
        int number = 0;
        long expectedFactorial = 1;
        long actualFactorial = FactorialCalculator.calculateFactorial(number);
        Assert.assertEquals(actualFactorial, expectedFactorial, "Факториал нуля должен быть равен 1.");
    }

    @Test
    public void testFactorialOne() {
        int number = 1;
        long expectedFactorial = 1;
        long actualFactorial = FactorialCalculator.calculateFactorial(number);
        Assert.assertEquals(actualFactorial, expectedFactorial, "Факториал единицы должен быть равен 1.");
    }

    @Test
    public void testFactorialLargeNumber() {
        int number = 10;
        long expectedFactorial = 3628800;
        long actualFactorial = FactorialCalculator.calculateFactorial(number);
        Assert.assertEquals(actualFactorial, expectedFactorial, "Факториал большого числа должен быть корректным.");
    }

    @Test
    public void testFactorialEdgeCase() {
        int number = 15;
        long expectedFactorial = 1307674368000L;
        long actualFactorial = FactorialCalculator.calculateFactorial(number);
        Assert.assertEquals(actualFactorial, expectedFactorial, "Факториал числа на границе типа данных должен быть корректным.");
    }

}

