package com;

import com.computor.Main;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class Tests {

    private final PrintStream standardOut = System.out;
    private ByteArrayOutputStream outputStreamCaptor;


    @BeforeEach
    public void setUp() {
        outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
        Main.getFinalMap().clear();
        Main.getMap().clear();
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

    @Test
    public void test1FromSubject() {
        String[] testStr = {"5 * X^0 + 4 * X^1 - 9.3 * X^2 = 1 * X^0"};

        String reducedForm = "Reduced form: 4 * X^0 + 4 * X^1 - 9.3 * X^2 = 0";
        String polynomialDegree = "Polynomial degree: 2";
        String discriminant = "Discriminant is strictly positive, the two solutions are: ";
        String answer1 = "0.905239";
        String answer2 = "-0.475131";

        Main.solveEquation(testStr);
        String output = outputStreamCaptor.toString();
        Assert.assertTrue(output.contains(reducedForm));
        Assert.assertTrue(output.contains(polynomialDegree));
        Assert.assertTrue(output.contains(discriminant));
        Assert.assertTrue(output.contains(answer1));
        Assert.assertTrue(output.contains(answer2));

        System.setOut(standardOut);
        System.out.println(output);
    }


    @Test
    public void test2FromSubject() throws InterruptedException {
        String[] testStr = {"5 * X^0 + 4 * X^1 = 4 * X^0"};

        String reducedForm = "Reduced form: 1 * X^0 + 4 * X^1 = 0";
        String polynomialDegree = "Polynomial degree: 1";
        String discriminant = "The solution is: ";
        String answer1 = "-0.250000";

        Main.solveEquation(testStr);
        String output = outputStreamCaptor.toString();
        Assert.assertTrue(output.contains(reducedForm));
        Assert.assertTrue(output.contains(polynomialDegree));
        Assert.assertTrue(output.contains(discriminant));
        Assert.assertTrue(output.contains(answer1));

        System.setOut(standardOut);
        System.out.println(output);
    }

    @Test
    public void test3FromSubject() {
        String[] testStr = {"8 * X^0 - 6 * X^1 + 0 * X^2 - 5.6 * X^3 = 3 * X^0"};

        String reducedForm = "Reduced form: 5 * X^0 - 6 * X^1 + 0 * X^2 - 5.6 * X^3 = 0";
        String polynomialDegree = "Polynomial degree: 3";
        String discriminant = "The polynomial degree is strictly greater than 2, I can't solve.";

        Main.solveEquation(testStr);
        String output = outputStreamCaptor.toString();
        Assert.assertTrue(output.contains(reducedForm));
        Assert.assertTrue(output.contains(polynomialDegree));
        Assert.assertTrue(output.contains(discriminant));

        System.setOut(standardOut);
        Main.solveEquation(testStr);
    }

    @Test
    public void test4Bonus() {
        String[] testStr = {"5 + 4 * X + X^2= X^2"};

        String reducedForm = "Reduced form: 5 * X^0 + 4 * X^1 + 0 * X^2 = 0";
        String polynomialDegree = "Polynomial degree: 1";
        String discriminant = "The solution is: ";
        String answer1 = "-1.250000";

        Main.solveEquation(testStr);
        String output = outputStreamCaptor.toString();
        Assert.assertTrue(output.contains(reducedForm));
        Assert.assertTrue(output.contains(polynomialDegree));
        Assert.assertTrue(output.contains(discriminant));
        Assert.assertTrue(output.contains(answer1));

        System.setOut(standardOut);
        System.out.println(output);
    }
}
