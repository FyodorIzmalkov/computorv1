package com.compuctor;

import lombok.Getter;

import java.util.*;

import static com.compuctor.Constants.*;
import static java.lang.Character.isDigit;

//              "5 * X^0 + 4 * X^1 - 9.3 * X^2 = 1 * X^0"
// Reduced form: 4 * X^0 + 4 * X^1 - 9.3 * X^2 = 0
// "5 + 4 * X + X^2= X^2"
// Reduced form: 5 + 4 * X = 0
public class Main {
    private static final char X = 'x';
    private static final char DOT = '.';
    @Getter
    private static final Map<Long, List<Double>> map = new HashMap<>();
    private static final Map<Long, Double> finalMap = new HashMap<>();

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Type in only 1 argument!");
            System.exit(1);
        }

        String strToParse = args[0];                // "'(x(?!\\^))"
        strToParse = strToParse.replace(" ", "")
                .toLowerCase()
                .replace("(x(?!\\^))", "x^1")
                .replace("--", "+")
                .replace("+-", "-")
                .replace("-+", "-");

        System.out.println(strToParse);
        splitAndParse(strToParse);

        // poluchaem {0=[5.0, -1.0], 1=[4.0], 2=[-9.3]}
        Set<Long> degreeSet = map.keySet();
        long maxDegree = degreeSet.stream()
                .mapToLong(Long::longValue)
                .max()
                .orElse(0L);


        System.out.println(map.toString());

        map.forEach((key, val) -> {
            Double sum = val.stream()
                    .mapToDouble(Double::doubleValue).sum();
            finalMap.put(key, sum);
        });

        System.out.println(map.toString());
        System.out.println(finalMap.toString());


        System.out.println("Reduced form: " + getReducedFormOfEquation());
        System.out.println("Polynomial degree: " + maxDegree);

        if (maxDegree > 2) {
            System.out.println("The polynomial degree is strictly greater than 2, I can't solve.");
            System.exit(1);
        }

        if (maxDegree == 0) {
            if (finalMap.get(ZERO_DEGREE) == 0D) {
                System.out.println("Each real number is a solution.");
            } else {
                System.out.println("There are no solutions, the equation is incorrect.");
            }
        } else if (maxDegree == 1) {
            Double c = finalMap.get(ZERO_DEGREE) * -1; // потому что перекидываем через равно
            Double b = finalMap.get(FIRST_DEGREE);
            System.out.println("The solution is: ");
            System.out.printf("%.6f%n", (c / b));
        } else {
            // a - SECOND b - FIRST c = ZERO
            // D = b * b - 4 * a * c
            Double a = finalMap.get(SECOND_DEGREE);
            Double b = finalMap.get(FIRST_DEGREE);
            Double c = finalMap.get(ZERO_DEGREE);

            double discriminant = b * b - 4 * a * c;
            System.out.println("Discriminant: " + discriminant);
            if (discriminant < 0) {
                System.out.println("Discriminant is strictly negative, the two solutions are: ");
                double minusB = -1 * b;
                double sqrtOfDiscriminant = sqrt(discriminant * -1);
                double twoA = 2 * a;
                System.out.println(String.format("%.6f", (minusB / twoA)) + " + " + String.format("%.6f", (sqrtOfDiscriminant / twoA)) + "i");
                System.out.println(String.format("%.6f", (minusB / twoA)) + " - " + String.format("%.6f", (sqrtOfDiscriminant / twoA)) + "i");
            } else if (discriminant == 0) {
                System.out.println("Discriminant is equal to zero, the solution is: ");
                double x1 = ((-1 * b) - sqrt(discriminant)) / (2 * a);
                System.out.printf("%.6f%n", x1);
            } else {
                System.out.println("Discriminant is strictly positive, the two solutions are: ");
                double x1 = ((-1 * b) - sqrt(discriminant)) / (2 * a);
                double x2 = ((-1 * b) + sqrt(discriminant)) / (2 * a);
                System.out.printf("%.6f%n", x1);
                System.out.printf("%.6f%n", x2);
            }
        }
    }

    private static String getReducedFormOfEquation() {
        StringBuilder reducedForm = new StringBuilder();
        for (Map.Entry<Long, Double> entry : finalMap.entrySet()) {
            Double val = entry.getValue();

            if (val < 0) {
                reducedForm.append("- ");
                val *= -1;
            } else if (reducedForm.length() > 0) {
                reducedForm.append("+ ");
            }

            if (val % 1 == 0) {
                reducedForm.append(val.longValue());
            } else {
                reducedForm.append(val);
            }
            reducedForm.append(" * X^");
            reducedForm.append(entry.getKey());
            reducedForm.append(" ");
        }
        reducedForm.append("= 0");

        return reducedForm.toString();
    }


    // poluchaem {0=[5.0, -1.0], 1=[4.0], 2=[-9.3]}
    public static void splitAndParse(String strToParse) {
        String[] strArr = strToParse.split("=");
        String leftPart = strArr[0];
        String rightPart = strArr[1];

        parseString(leftPart, LEFT_PART);
        if (!"0".equals(rightPart)) {
            parseString(rightPart, RIGHT_PART);
        }
    }

    //isLeftPart yes = 1, no - 1 multiplicator
    public static void parseString(String str, int multiplicator) {


        for (Integer i = 0; i < str.length(); i++) {
            char curChar = str.charAt(i);
            if (isDigit(curChar) || X == curChar || '-' == curChar || '+' == curChar) {
                while (isDigit(str.charAt(i)) || X == str.charAt(i) || '-' == str.charAt(i) || '+' == str.charAt(i)) {
                    i = getParam(str, i, str.length(), multiplicator);
                    if (i == str.length()) {
                        break;
                    }
                }
            }
        }
    }

    // 5123*X^0 or 4*X^1 or 9.3*X^2      5123*x^0+4*x^1-9.3*x^2=1*x^0
    private static Integer getParam(String str, Integer i, int len, int multiplicator) {
        StringBuilder number = new StringBuilder();
        while (i < len) {
            char curChar = str.charAt(i);
            if (isDigit(curChar) || DOT == curChar || '-' == curChar || '+' == curChar) {
                number.append(curChar);
                i++;
            } else if (X == curChar || '*' == curChar) {
                i++;
            } else if ('^' == curChar) {
                i++;
                break;
            }
        }

        StringBuilder degree = new StringBuilder();
        while (i < len) {
            char curChar = str.charAt(i);
            if (curChar == '+' || curChar == '-' || curChar == '=') {
                break;
            }
            if (isDigit(curChar)) { //TODO MB ERROR
                degree.append(curChar);
            }
            i++;
        }

        double numDouble = 0;
        try {
            numDouble = Double.parseDouble(number.toString()) * multiplicator;
        } catch (Exception e) {
            System.out.println("error double parsing");
            System.out.println(e.toString());
            System.exit(1);
        }

        long degreeLong = 0;
        try {
            degreeLong = Long.parseLong(degree.toString());
        } catch (Exception e) {
            System.out.println("error long parsing");
            System.out.println(e.toString());
            System.exit(1);
        }

        map.computeIfAbsent(degreeLong, k -> new ArrayList<>()).add(numDouble);
        return i;
    }

    public static double sqrt(double number) {
        double t;

        double squareRoot = number / 2;

        do {
            t = squareRoot;
            squareRoot = (t + (number / t)) / 2;
        } while ((t - squareRoot) != 0);

        return squareRoot;
    }
}
