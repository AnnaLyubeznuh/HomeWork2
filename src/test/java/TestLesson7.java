import org.example.Lesson7;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestLesson7 {

    @Test
    @DisplayName("Факториал числа")
    public void factorialTest() {
        assertEquals(120, Lesson7.factorial(5));
    }

    @Test
    @DisplayName("Площадь треугольника")
    public void testAreaTriangle() {
        double expectedResult = 6;
        double result = Lesson7.areaTriangle(3, 4, 5);
        assertEquals(expectedResult, result);
    }

    @Test
    @DisplayName("Сумма двух целых чисел")
    public void testAddition() {
        int expectedResult = 8;
        double result = Lesson7.addition(7, 1);
        assertEquals(expectedResult, result);
    }

    @Test
    @DisplayName("Вычетание двух целых чисел")
    public void testSubtraction() {
        int expectedResult = 4;
        double result = Lesson7.subtraction(9, 5);
        assertEquals(expectedResult, result);
    }

    @Test
    @DisplayName("Деление двух целых чисел")
    public void testDivision() {
        int expectedResult = 8;
        double result = Lesson7.division(24, 3);
        assertEquals(expectedResult, result);
    }

    @Test
    @DisplayName("Умножение двух целых чисел")
    public void testMultiplication() {
        int expectedResult = 12;
        double result = Lesson7.multiplication(3, 4);
        assertEquals(expectedResult, result);
    }

    @Test
    @DisplayName("Сравнение двух целых равных чисел")
    public void testComparisonEqual() {
        assertEquals(0, Lesson7.comparison(5, 5));
    }

    @Test
    @DisplayName("Сравнение двух целых чисел, первое число больше второго")
    public void testComparisonMore() {
        assertEquals(1, Lesson7.comparison(10, 8));
    }

    @Test
    @DisplayName("Сравнение двух целых чисел, первое число меньше второго")
    public void testComparisonLess() {
        assertEquals(-1, Lesson7.comparison(5, 7));
    }
}
