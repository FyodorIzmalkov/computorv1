package com;

import com.computor.Main;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class TestsWithFlag {

    private final PrintStream standardOut = System.out;
    private ByteArrayOutputStream outputStreamCaptor;
    private String testStr;


    @BeforeEach
    public void setUp() {
        outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
        Main.getFinalMap().clear();
        Main.getMap().clear();
        Main.setIrrFlag(true);
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
        System.out.println("Test string: " + testStr);
        System.out.println(outputStreamCaptor.toString());
    }

    @Test
    public void test1_withFlag() {
        testStr = "5 * X^0 + 4 * X^1 = 4 * X^0";

        String reducedForm = "Reduced form: 1 * X^0 + 4 * X^1 = 0";
        String polynomialDegree = "Polynomial degree: 1";
        String discriminant = "The solution is: ";
        String answer1 = "-1/4";

        Main.solveEquation(testStr);
        String output = outputStreamCaptor.toString();
        Assert.assertTrue(output.contains(reducedForm));
        Assert.assertTrue(output.contains(polynomialDegree));
        Assert.assertTrue(output.contains(discriminant));
        Assert.assertTrue(output.contains(answer1));
    }

    @Test
    public void test2_withFlag() {
        testStr = "5 + 4 * X + X^2= X^2";

        String reducedForm = "Reduced form: 5 * X^0 + 4 * X^1 + 0 * X^2 = 0";
        String polynomialDegree = "Polynomial degree: 1";
        String discriminant = "The solution is: ";
        String answer1 = "-1 * 1/4";

        Main.solveEquation(testStr);
        String output = outputStreamCaptor.toString();
        Assert.assertTrue(output.contains(reducedForm));
        Assert.assertTrue(output.contains(polynomialDegree));
        Assert.assertTrue(output.contains(discriminant));
        Assert.assertTrue(output.contains(answer1));
    }

    @Test
    public void test3_withFlag() {
        testStr = "1 * X^0 + 2 * X^1 - 0 * X^2 = 0 * X^0 + 0 * X^1 + 0 * X^2";

        String reducedForm = "Reduced form: 1 * X^0 + 2 * X^1 + 0 * X^2 = 0";
        String polynomialDegree = "Polynomial degree: 1";
        String discriminant = "The solution is: ";
        String answer1 = "-1/2";

        Main.solveEquation(testStr);
        String output = outputStreamCaptor.toString();
        Assert.assertTrue(output.contains(reducedForm));
        Assert.assertTrue(output.contains(polynomialDegree));
        Assert.assertTrue(output.contains(discriminant));
        Assert.assertTrue(output.contains(answer1));
    }

    @Test
    public void test4_withFlag() {
        testStr = "X = 0.0001";

        String reducedForm = "Reduced form: - 1.0E-4 * X^0 + 1 * X^1 = 0";
        String polynomialDegree = "Polynomial degree: 1";
        String discriminant = "The solution is: ";
        String answer1 = "1/10000";

        Main.solveEquation(testStr);
        String output = outputStreamCaptor.toString();
        Assert.assertTrue(output.contains(reducedForm));
        Assert.assertTrue(output.contains(polynomialDegree));
        Assert.assertTrue(output.contains(discriminant));
        Assert.assertTrue(output.contains(answer1));
    }

    @Test
    public void test5_withFlag() {
        testStr = "2 * X^0 + 2 * x = 0 + 0 * x^30";

        String reducedForm = "Reduced form: 2 * X^0 + 2 * X^1 + 0 * X^30 = 0";
        String polynomialDegree = "Polynomial degree: 1";
        String discriminant = "The solution is: ";
        String answer1 = "-1";

        Main.solveEquation(testStr);
        String output = outputStreamCaptor.toString();
        Assert.assertTrue(output.contains(reducedForm));
        Assert.assertTrue(output.contains(polynomialDegree));
        Assert.assertTrue(output.contains(discriminant));
        Assert.assertTrue(output.contains(answer1));
    }

    @Test
    public void test6_withFlag() {
        testStr = "5.5 * X^0 = 4 * X^0 + 7.2 * X^1";

        String reducedForm = "Reduced form: 1.5 * X^0 - 7.2 * X^1 = 0";
        String polynomialDegree = "Polynomial degree: 1";
        String discriminant = "The solution is: ";
        String answer1 = "5/24";

        Main.solveEquation(testStr);
        String output = outputStreamCaptor.toString();
        Assert.assertTrue(output.contains(reducedForm));
        Assert.assertTrue(output.contains(polynomialDegree));
        Assert.assertTrue(output.contains(discriminant));
        Assert.assertTrue(output.contains(answer1));
    }
}
