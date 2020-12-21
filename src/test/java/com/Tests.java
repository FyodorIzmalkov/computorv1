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
    public void test2FromSubject() {
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

    @Test
    public void customTest1() {
        String[] testStr = {"4 * X^0 - 10 * X^1 + 4 * X^2 = 0 * X^0 + 0 * X^1 + 0 * X^2"};

        String reducedForm = "Reduced form: 4 * X^0 - 10 * X^1 + 4 * X^2 = 0";
        String polynomialDegree = "Polynomial degree: 2";
        String discriminant = "Discriminant is strictly positive, the two solutions are: ";
        String answer1 = "0.500000";
        String answer2 = "2.000000";

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
    public void customTest2() {
        String[] testStr = {"2 * X^0 + 3 * X^1 - 4 * X^2 = 0 * X^0 + 0 * X^1 + 0 * X^2"};

        String reducedForm = "Reduced form: 2 * X^0 + 3 * X^1 - 4 * X^2 = 0";
        String polynomialDegree = "Polynomial degree: 2";
        String discriminant = "Discriminant is strictly positive, the two solutions are: ";
        String answer1 = "1.175391";
        String answer2 = "-0.425391";

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
    public void customTest3() {
        String[] testStr = {"0 * X^0 + 3 * X^1 - 4 * X^2 = 0 * X^0 + 0 * X^1 + 0 * X^2"};

        String reducedForm = "Reduced form: 0 * X^0 + 3 * X^1 - 4 * X^2 = 0";
        String polynomialDegree = "Polynomial degree: 2";
        String discriminant = "Discriminant is strictly positive, the two solutions are: ";
        String answer1 = "0.750000";
        String answer2 = "-0.000000";

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
    public void customTest4() {
        String[] testStr = {"0 * X^0 + 0 * X^1 - 4 * X^2 = 0 * X^0 + 0 * X^1 + 0 * X^2"};

        String reducedForm = "0 * X^0 + 0 * X^1 - 4 * X^2 = 0";
        String polynomialDegree = "Polynomial degree: 2";
        String discriminant = "Discriminant is equal to zero, the solution is: ";
        String answer1 = "0.000000";

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
    public void customTest5() {
        String[] testStr = {"0 * X^0 + 0 * X^1 - 0 * X^2 = 0 * X^0 + 0 * X^1 + 0 * X^2"};

        String reducedForm = "Reduced form: 0 * X^0 + 0 * X^1 + 0 * X^2 = 0";
        String polynomialDegree = "Polynomial degree: 0";
        String answer1 = "Each real number is a solution.";

        Main.solveEquation(testStr);
        String output = outputStreamCaptor.toString();
        Assert.assertTrue(output.contains(reducedForm));
        Assert.assertTrue(output.contains(polynomialDegree));
        Assert.assertTrue(output.contains(answer1));

        System.setOut(standardOut);
        System.out.println(output);
    }

    @Test
    public void customTest6() {
        String[] testStr = {"1 * X^0 + 0 * X^1 - 4 * X^2 = 0 * X^0 + 0 * X^1 + 0 * X^2"};

        String reducedForm = "Reduced form: 1 * X^0 + 0 * X^1 - 4 * X^2 = 0";
        String polynomialDegree = "Polynomial degree: 2";
        String discriminant = "Discriminant is strictly positive, the two solutions are: ";
        String answer1 = "0.500000";
        String answer2 = "-0.500000";

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
    public void customTest7() {
        String[] testStr = {"1 * X^0 + 2 * X^1 - 0 * X^2 = 0 * X^0 + 0 * X^1 + 0 * X^2"};

        String reducedForm = "Reduced form: 1 * X^0 + 2 * X^1 + 0 * X^2 = 0";
        String polynomialDegree = "Polynomial degree: 1";
        String discriminant = "The solution is: ";
        String answer1 = "-0.500000";

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
    public void customTest8() {
        String[] testStr = {"-2.5 * X^0 + 4.5 * X^1 - 1.5 * X^2 = 0 * X^0 + 0 * X^1 + 0 * X^2"};

        String reducedForm = "Reduced form: - 2.5 * X^0 + 4.5 * X^1 - 1.5 * X^2 = 0";
        String polynomialDegree = "Polynomial degree: 2";
        String discriminant = "Discriminant is strictly positive, the two solutions are: ";
        String answer1 = "2.263763";
        String answer2 = "0.736237";

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
    public void complexTest1() {
        String[] testStr = {"-2.5 * X^0 + 2.5 * X^1 - 1.5 * X^2 = 0 * X^0 + 0 * X^1 + 0 * X^2"};

        String reducedForm = "Reduced form: - 2.5 * X^0 + 2.5 * X^1 - 1.5 * X^2 = 0";
        String polynomialDegree = "Polynomial degree: 2";
        String discriminant = "Discriminant is strictly negative, the two solutions are: ";
        String answer1 = "0.833333 - 0.986013i";
        String answer2 = "0.833333 + 0.986013i";

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
    public void checkDegree0_with_solutions() {
        String[] testStr = {"5 * X^0 = 5 * X^0"};

        String reducedForm = "Reduced form: 0 * X^0 = 0";
        String polynomialDegree = "Polynomial degree: 0";
        String discriminant = "Each real number is a solution.";

        Main.solveEquation(testStr);
        String output = outputStreamCaptor.toString();
        Assert.assertTrue(output.contains(reducedForm));
        Assert.assertTrue(output.contains(polynomialDegree));
        Assert.assertTrue(output.contains(discriminant));

        System.setOut(standardOut);
        System.out.println(output);
    }

    @Test
    public void checkDegree0_no_solutions() {
        String[] testStr = {"4 * X^0 = 8 * X^0"};

        String reducedForm = "Reduced form: - 4 * X^0 = 0";
        String polynomialDegree = "Polynomial degree: 0";
        String discriminant = "There are no solutions, the equation is incorrect.";

        Main.solveEquation(testStr);
        String output = outputStreamCaptor.toString();
        Assert.assertTrue(output.contains(reducedForm));
        Assert.assertTrue(output.contains(polynomialDegree));
        Assert.assertTrue(output.contains(discriminant));

        System.setOut(standardOut);
        System.out.println(output);
    }

    @Test
    public void checkDegree1() {
        String[] testStr = {"5.5 * X^0 = 4 * X^0 + 7.2 * X^1"};

        String reducedForm = "Reduced form: 1.5 * X^0 - 7.2 * X^1 = 0";
        String polynomialDegree = "Polynomial degree: 1";
        String discriminant = "The solution is: ";
        String answer1 = "0.208333";

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
    public void checkDegree2_positive_discriminant() {
        String[] testStr = {"5 * X^0 + 13.1 * X^1 + 3 * X^2 = 1 * X^0 + 1 * X^1"};

        String reducedForm = "Reduced form: 4 * X^0 + 12.1 * X^1 + 3 * X^2 = 0";
        String polynomialDegree = "Polynomial degree: 2";
        String discriminant = "Discriminant is strictly positive, the two solutions are: ";
        String answer1 = "-3.670030";
        String answer2 = "-0.363303";

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
    public void checkDegree2_negative_discriminant() {
        String[] testStr = {"5 * X^0 + 3 * X^1 + 3 * X^2 = 1 * X^0 + 0 * X^1"};

        String reducedForm = "Reduced form: 4 * X^0 + 3 * X^1 + 3 * X^2 = 0";
        String polynomialDegree = "Polynomial degree: 2";
        String discriminant = "Discriminant is strictly negative, the two solutions are: ";
        String answer1 = "-0.500000 + 1.040833i";
        String answer2 = "-0.500000 - 1.040833i";

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
    public void checkDegree2_zero_discriminant() {
        String[] testStr = {"1 * X^0 + 4 * X^1 + 4 * X^2 = 0 * X^0 + 0 * X^1"};

        String reducedForm = "Reduced form: 1 * X^0 + 4 * X^1 + 4 * X^2 = 0";
        String polynomialDegree = "Polynomial degree: 2";
        String discriminant = "Discriminant is equal to zero, the solution is: ";
        String answer1 = "-0.500000";

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
    public void checkDegree3_is_not_solved() {
        String[] testStr = {"1 * X^0 + 4 * X^1 + 4 * X^3 = 0 * X^0 + 0 * X^1"};

        String reducedForm = "Reduced form: 1 * X^0 + 4 * X^1 + 4 * X^3 = 0";
        String polynomialDegree = "Polynomial degree: 3";
        String discriminant = "The polynomial degree is strictly greater than 2, I can't solve.";

        Main.solveEquation(testStr);
        String output = outputStreamCaptor.toString();
        Assert.assertTrue(output.contains(reducedForm));
        Assert.assertTrue(output.contains(polynomialDegree));
        Assert.assertTrue(output.contains(discriminant));

        System.setOut(standardOut);
        System.out.println(output);
    }

    @Test
    public void checkDegree4_is_not_solved() {
        String[] testStr = {"1 * X^0 + 4 * X^1 + 4 * X^4 = 0 * X^0 + 0 * X^1"};

        String reducedForm = "Reduced form: 1 * X^0 + 4 * X^1 + 4 * X^4 = 0";
        String polynomialDegree = "Polynomial degree: 4";
        String discriminant = "The polynomial degree is strictly greater than 2, I can't solve.";

        Main.solveEquation(testStr);
        String output = outputStreamCaptor.toString();
        Assert.assertTrue(output.contains(reducedForm));
        Assert.assertTrue(output.contains(polynomialDegree));
        Assert.assertTrue(output.contains(discriminant));

        System.setOut(standardOut);
        System.out.println(output);
    }

    @Test
    public void checkDegree15_is_not_solved() {
        String[] testStr = {"1 * X^0 + 4 * X^1 + 4 * X^4 + 1 * X^15 = 0 * X^0 + 0 * X^1"};

        String reducedForm = "Reduced form: 1 * X^0 + 4 * X^1 + 4 * X^4 + 1 * X^15 = 0";
        String polynomialDegree = "Polynomial degree: 15";
        String discriminant = "The polynomial degree is strictly greater than 2, I can't solve.";

        Main.solveEquation(testStr);
        String output = outputStreamCaptor.toString();
        Assert.assertTrue(output.contains(reducedForm));
        Assert.assertTrue(output.contains(polynomialDegree));
        Assert.assertTrue(output.contains(discriminant));

        System.setOut(standardOut);
        System.out.println(output);
    }

    // bonus tests

    // A coefficient alone ("4") is treated as a factor of X^0.
    @Test
    public void check_bonus_positive_coefficient_alone() {
        String[] testStr = {"4 + 7 * X^1 + 2 * X^2 = 0"};

        String reducedForm = "Reduced form: 4 * X^0 + 7 * X^1 + 2 * X^2 = 0";
        String polynomialDegree = "Polynomial degree: 2";
        String discriminant = "Discriminant is strictly positive, the two solutions are: ";
        String answer1 = "-2.780776";
        String answer2 = "-0.719224";

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
    public void check_bonus_negative_coefficient_alone() {
        String[] testStr = {"-4 + 7 * X^1 + 2 * X^2 = 0"};

        String reducedForm = "Reduced form: - 4 * X^0 + 7 * X^1 + 2 * X^2 = 0";
        String polynomialDegree = "Polynomial degree: 2";
        String discriminant = "Discriminant is strictly positive, the two solutions are: ";
        String answer1 = "-4.000000";
        String answer2 = "0.500000";

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

    // An X alone is considered to have a coefficient of 1 and an exponent of 1.
    @Test
    public void check_bonus_positive_coefficient_x_alone() {
        String[] testStr = {"4 + X - 2 * X^2 = 0"};

        String reducedForm = "Reduced form: 4 * X^0 + 1 * X^1 - 2 * X^2 = 0";
        String polynomialDegree = "Polynomial degree: 2";
        String discriminant = "Discriminant is strictly positive, the two solutions are: ";
        String answer1 = "1.686141";
        String answer2 = "-1.186141";

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
    public void check_bonus_negative_coefficient_x_alone() {
        String[] testStr = {"5 - X - 2 * X^2 = 0"};

        String reducedForm = "Reduced form: 5 * X^0 - 1 * X^1 - 2 * X^2 = 0";
        String polynomialDegree = "Polynomial degree: 2";
        String discriminant = "Discriminant is strictly positive, the two solutions are: ";
        String answer1 = "1.350781";
        String answer2 = "-1.850781";

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

    // * A missing exponent ("4 * X") is considered to be 1.
    @Test
    public void check_bonus_positive_coefficient_x_missing_exponent() {
        String[] testStr = {"4 + 2 * X - 2 * X^2 = 0"};

        String reducedForm = "Reduced form: 4 * X^0 + 2 * X^1 - 2 * X^2 = 0";
        String polynomialDegree = "Polynomial degree: 2";
        String discriminant = "Discriminant is strictly positive, the two solutions are: ";
        String answer1 = "2.000000";
        String answer2 = "-1.000000";

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
    public void check_bonus_negative_coefficient_x_missing_exponent() {
        String[] testStr = {"3 - 2 * X - 2 * X^2 = 0"};

        String reducedForm = "Reduced form: 3 * X^0 - 2 * X^1 - 2 * X^2 = 0";
        String polynomialDegree = "Polynomial degree: 2";
        String discriminant = "Discriminant is strictly positive, the two solutions are: ";
        String answer1 = "0.822876";
        String answer2 = "-1.822876";

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

    // A missing coefficient ("X^6") is considered to be 1
    @Test
    public void check_bonus_positive_coefficient_x_with_exponent() {
        String[] testStr = {"3 - 4 * X + X^2 = 0"};

        String reducedForm = "Reduced form: 3 * X^0 - 4 * X^1 + 1 * X^2 = 0";
        String polynomialDegree = "Polynomial degree: 2";
        String discriminant = "Discriminant is strictly positive, the two solutions are: ";
        String answer1 = "1.000000";
        String answer2 = "3.000000";

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
    public void check_bonus_negative_coefficient_x_with_exponent() {
        String[] testStr = {"4 + 2 * X - X^2 = 0"};

        String reducedForm = "Reduced form: 4 * X^0 + 2 * X^1 - 1 * X^2 = 0";
        String polynomialDegree = "Polynomial degree: 2";
        String discriminant = "Discriminant is strictly positive, the two solutions are: ";
        String answer1 = "3.236068";
        String answer2 = "-1.236068";

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

    // Operands can be input in arbitrary order regardless of exponential power;
    // multiple operands of the same power may also appear in arbitrary order.
    @Test
    public void check_bonus_arbitrary_order_1() {
        String[] testStr = {"3 - 6 * X + X^2 - X + 2 * X^2= 0"};

        String reducedForm = "Reduced form: 3 * X^0 - 7 * X^1 + 3 * X^2 = 0";
        String polynomialDegree = "Polynomial degree: 2";
        String discriminant = "Discriminant is strictly positive, the two solutions are: ";
        String answer1 = "0.565741";
        String answer2 = "1.767592";

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
    public void check_bonus_arbitrary_order_2() {
        String[] testStr = {"-1 3 - 6 * X + X^2 - X + 2 * X^2= 0"};

        String reducedForm = "Reduced form: 3 * X^0 - 7 * X^1 + 3 * X^2 = 0";
        String polynomialDegree = "Polynomial degree: 2";
        String discriminant = "Discriminant is strictly positive, the two solutions are: ";
        String answer1 = "0.565741";
        String answer2 = "1.767592";

        Main.solveEquation(testStr);
        String output = outputStreamCaptor.toString();
        System.setOut(standardOut);
        System.out.println(output);
        Assert.assertTrue(output.contains(reducedForm));
        Assert.assertTrue(output.contains(polynomialDegree));
        Assert.assertTrue(output.contains(discriminant));
        Assert.assertTrue(output.contains(answer1));
        Assert.assertTrue(output.contains(answer2));

        System.setOut(standardOut);
        System.out.println(output);
    }

    @Test
    public void check_bonus_equation_not_valid_1() {
        String[] testStr = {"-1 a - 6 * X + X^2 - X + 2 * X^2= 0"};

        String errorMessage = "Equation is not valid, it contains wrong characters";

        Main.solveEquation(testStr);
        String output = outputStreamCaptor.toString();
        Assert.assertTrue(output.contains(errorMessage));

        System.setOut(standardOut);
        System.out.println(output);
    }

    @Test
    public void check_bonus_equation_not_valid_2() {
        String[] testStr = {"-1 - 6 * X + X^2 - [X 2] + 2 * X^2= 0"};

        String errorMessage = "Equation is not valid, it contains wrong characters";

        Main.solveEquation(testStr);
        String output = outputStreamCaptor.toString();
        Assert.assertTrue(output.contains(errorMessage));

        System.setOut(standardOut);
        System.out.println(output);
    }
}
