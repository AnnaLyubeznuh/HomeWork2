package org.example;

public class Lesson7 {

    public static int factorial(int a) {
        if (a < 0) {
            throw new IllegalArgumentException("Число не может быть отрицательным: " + a);
        }
        int result = 1;
        for (int i = 1; i <= a; i++) {
            result *= i;
        }
        return result;
    }

    public static double areaTriangle(int a, int b, int c) {
        if (a <= 0 || b <= 0 || c <= 0) {
            throw new IllegalArgumentException("Стороны треугольника должны быть больше нуля");
        }
        double s = (a + b + c) / 2.0;
        return Math.sqrt(s * (s - a) * (s - b) * (s - c));
    }

    public static int addition(int a, int b) {
        return a + b;
    }

    public static int subtraction(int a, int b) {
        return a - b;
    }

    public static double division(int a, int b) {
        if (b == 0) {
            throw new ArithmeticException("Деление на ноль невозможно");
        }
        return (double) a / b;
    }

    public static int multiplication(int a, int b) {
        return a * b;
    }

    public static int comparison(int a, int b) {
        return Integer.compare(a, b);
    }
}
