import org.example.Lesson7;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertThrows;

public class TestLesson7 {

    @Test
    public void factorialTest() {
        assertEquals(Lesson7.factorial(5), 120);

        assertThrows(IllegalArgumentException.class, () -> Lesson7.factorial(-5));
    }

    @Test
    public void testAreaTriangle() {
        double expectedResult = 6;
        double result = Lesson7.areaTriangle(3, 4, 5);
        assertEquals(result, expectedResult);

        assertThrows(IllegalArgumentException.class, () -> Lesson7.areaTriangle(-3, 4, 5));
    }

    @Test
    public void testAddition() {
        int expectedResult = 8;
        double result = Lesson7.addition(7, 1);
        assertEquals(result, expectedResult);
    }

    @Test
    public void testSubtraction() {
        int expectedResult = 4;
        double result = Lesson7.subtraction(9, 5);
        assertEquals(result, expectedResult);
    }

    @Test
    public void testDivision() {
        double expectedResult = 4.8;
        double result = Lesson7.division(24, 5);
        assertEquals(result, expectedResult, 0.0001);

        assertThrows(ArithmeticException.class, () -> Lesson7.division(5, 0));
    }

    @Test
    public void testMultiplication() {
        int expectedResult = 12;
        double result = Lesson7.multiplication(3, 4);
        assertEquals(result, expectedResult);
    }

    @Test
    public void testComparisonEqual() {
        assertEquals(Lesson7.comparison(5, 5), 0);
    }

    @Test
    public void testComparisonMore() {
        assertEquals(Lesson7.comparison(10, 8), 1);
    }

    @Test
    public void testComparisonLess() {
        assertEquals(Lesson7.comparison(5, 7), -1);
    }
}

