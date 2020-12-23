package com.computor;

import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;
import java.util.*;

import static com.computor.Constants.*;
import static java.lang.Character.isDigit;

public class Main {
    private static final char X = 'x';
    private static final char DOT = '.';
    private static final String irreducibleFlag = "-irr";
    @Setter
    private static boolean irrFlag = false;
    @Getter
    private static final Map<Long, List<Double>> map = new HashMap<>();
    @Getter
    private static final Map<Long, Double> finalMap = new HashMap<>();

    public static void main(String[] args) {
        if (args.length == 1 || args.length == 2) {
            if (irreducibleFlag.equals(args[0])) {
                irrFlag = true;
                solveEquation(args[1]);
            } else if (irreducibleFlag.equals(args[1])) {
                irrFlag = true;
                solveEquation(args[0]);
            } else {
                System.out.println("Wrong flag, correct flag is: -irr");
            }
        } else {
            System.out.println("Type only 1 or 2 arguments!");
        }
    }

    public static void solveEquation(String str) {
        try {
            String strToParse = validateAndApplyRegexp(str);
            validateInputString(strToParse);
            parseStringToMap(strToParse);
            calculateCoefficientsInMap();
            long maxDegree = getMaxPolynomialDegree();
            printReducedFormAndMaxDegree(maxDegree);
            checkMaxDegreeAndAllRealNumbersSolution(maxDegree);
            solveEquationAndPrintResult(maxDegree);
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
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
                reducedForm.append(String.format("%.0f", val));
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

    public static void parseStringToMap(String strToParse) {
        String[] strArr = strToParse.split("=");
        String leftPart = strArr[0];
        String rightPart = strArr[1];

        parseString(leftPart, LEFT_PART);
        if (!"0".equals(rightPart)) {
            parseString(rightPart, RIGHT_PART);
        }
    }

    public static void parseString(String str, int multiplicator) {
        Integer i = 0;
        while (isDigit(str.charAt(i)) || X == str.charAt(i) || '-' == str.charAt(i) || '+' == str.charAt(i)) {
            i = parsePartsAndAddToMap(str, i, str.length(), multiplicator);
            if (i == str.length()) {
                break;
            }
        }
    }

    private static Integer parsePartsAndAddToMap(String str, Integer i, int len, int multiplicator) {
        StringBuilder number = new StringBuilder();
        boolean appendX0 = false;
        while (i < len) {
            char curChar = str.charAt(i);
            if (isDigit(curChar) || DOT == curChar || '-' == curChar || '+' == curChar) {
                String tmpStr = number.toString();
                if (tmpStr.length() >= 1 && isDigit(tmpStr.charAt(tmpStr.length() - 1)) && ('-' == curChar || '+' == curChar)) {
                    appendX0 = true;
                    break;
                }
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
            if (appendX0) {
                degree.append(0);
                break;
            }

            char curChar = str.charAt(i);
            if (curChar == '+' || curChar == '-') {
                break;
            }

            if (isDigit(curChar)) {
                degree.append(curChar);
            }

            if (DOT == curChar) {
                throw new IllegalArgumentException("Degree must be integer");
            }

            i++;
        }

        double numDouble;
        try {
            String numberStr = number.toString();
            if ("-".equals(numberStr)) {
                numberStr = "-1";
            } else if ("+".equals(numberStr) || numberStr.isEmpty()) {
                numberStr = "1";
            }

            numDouble = Double.parseDouble(numberStr) * multiplicator;
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while parsing degree: " + e.getLocalizedMessage());
        }

        long degreeLong;
        try {
            String degreeStr = degree.toString();
            if (degreeStr.isEmpty()) {
                degreeStr = "0";
            }

            BigInteger bigInt = new BigInteger(degreeStr);
            if (bigInt.compareTo(BigInteger.valueOf(Long.MAX_VALUE)) > 0) {
                throw new IllegalArgumentException(degreeStr + ": value is too large");
            }

            degreeLong = Long.parseLong(degreeStr);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while parsing degree: " + e.getLocalizedMessage());
        }

        map.computeIfAbsent(degreeLong, k -> new ArrayList<>()).add(numDouble);
        return i;
    }

    public static String validateAndApplyRegexp(String strToParse) {
        return strToParse
                .trim()
                .replace(" ", "")
                .replace("--", "+")
                .replace("+-", "-")
                .replace("-+", "-")
                .toLowerCase()
                .replaceAll("(x(?!\\^))", "x^1");
    }

    public static double sqrt(double number) {
        if (number == 0.0) {
            return 0;
        }

        double t;

        double squareRoot = number / 2;

        do {
            t = squareRoot;
            squareRoot = (t + (number / t)) / 2;
        } while ((t - squareRoot) != 0);

        return squareRoot;
    }

    private static void calculateCoefficientsInMap() {
        map.forEach((key, val) -> {
            Double sum = val.stream()
                    .mapToDouble(Double::doubleValue).sum();
            finalMap.put(key, sum);
        });
    }

    private static long getMaxPolynomialDegree() {
        long maxDegree = 0;
        for (Map.Entry<Long, Double> entry : finalMap.entrySet()) {
            if (entry.getValue() != 0) {
                maxDegree = maxDegree > entry.getKey() ? maxDegree : entry.getKey();
            }
        }
        return maxDegree;
    }

    private static void printReducedFormAndMaxDegree(long maxDegree) {
        System.out.println("Reduced form: " + getReducedFormOfEquation());
        System.out.println("Polynomial degree: " + maxDegree);
    }

    private static void checkMaxDegreeAndAllRealNumbersSolution(long maxDegree) {
        if (maxDegree > 2) {
            throw new IllegalArgumentException("The polynomial degree is strictly greater than 2, I can't solve.");
        }

        if (finalMap.values().stream().allMatch(val -> val == 0)) {
            throw new IllegalArgumentException("Each real number is a solution.");
        }
    }

    private static void solveEquationAndPrintResult(long maxDegree) {
        if (maxDegree == 0) {
            System.out.println("There are no solutions, the equation is incorrect.");
        } else if (maxDegree == 1) {
            double c = Optional.ofNullable(finalMap.get(ZERO_DEGREE)).orElse(0.0D) * -1; // потому что перекидываем через равно
            double b = Optional.ofNullable(finalMap.get(FIRST_DEGREE)).orElse(0.0D);
            System.out.println("The solution is: ");
            if (!irrFlag) {
                System.out.printf("%.6f%n", (c / b));
            } else {
                printSolutionForOneDegree(c, b);
            }
        } else {
            // a - SECOND b - FIRST c = ZERO
            // D = b * b - 4 * a * c
            double a = Optional.ofNullable(finalMap.get(SECOND_DEGREE)).orElse(0.0D);
            double b = Optional.ofNullable(finalMap.get(FIRST_DEGREE)).orElse(0.0D);
            double c = Optional.ofNullable(finalMap.get(ZERO_DEGREE)).orElse(0.0D);

            double discriminant = b * b - 4 * a * c;
            System.out.println("Discriminant: " + discriminant);
            if (discriminant < 0) {
                System.out.println("Discriminant is strictly negative, the two solutions are: ");
                double minusB = -1 * b;
                double sqrtOfDiscriminant = sqrt(discriminant * -1);
                double twoA = 2 * a;
                System.out.println((String.format("%.6f", (minusB / twoA)) + " + " + String.format("%.6f", (sqrtOfDiscriminant / twoA)) + "i").replace(" + -", " - "));
                System.out.println((String.format("%.6f", (minusB / twoA)) + " - " + String.format("%.6f", (sqrtOfDiscriminant / twoA)) + "i").replace(" - -", " + "));
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

    private static void validateInputString(String str) {
        Map<Character, Long> charsMap = new HashMap<>();

        String availableCharacters = "0123456789^+=-*x. ";
        for (int i = 0; i < str.length(); i++) {
            char curChar = str.charAt(i);
            if (charsMap.containsKey(curChar)) {
                Long val = charsMap.get(curChar);
                val++;
                charsMap.put(curChar, val);
            } else {
                charsMap.put(curChar, 1L);
            }
            if (!availableCharacters.contains(String.valueOf(curChar))) {
                throw new IllegalArgumentException("Equation is not valid, it contains wrong characters");
            }
        }

        if (charsMap.get('=') == null || charsMap.get('=') != 1L) {
            throw new IllegalArgumentException("There are must be one character of '='");
        }
    }

    private static double gcdByEuclidsAlgorithm(double n1, double n2) {
        if (n2 == 0) {
            return n1;
        }

        return gcdByEuclidsAlgorithm(n2, n1 % n2);
    }

    private static double abs(double a) {
        return (a <= 0.0D) ? 0.0D - a : a;
    }

    private static long abs(long a) {
        return (a < 0) ? -a : a;
    }

    private static void printSolutionForOneDegree(double c, double b) {
        while (abs(c) % 1 != 0) {
            c *= 10;
            b *= 10;
        }
        double gcd = gcdByEuclidsAlgorithm(c, b);
        c /= gcd;
        b /= gcd;
        double tmp = c / b;
        boolean wasNegative = tmp < 0;
        long res = abs((long) tmp);
        c = abs(c);
        b = abs(b);
        if (res >= 1) {
            c -= b * res;
            if (c == 0.0D) {
                System.out.println((wasNegative ? "-" : "") + res);
                return;
            }
            System.out.println((wasNegative ? "-" : "") + res + " * " + String.format("%.0f", c) + "/" + String.format("%.0f", b));
        } else {
            System.out.println((wasNegative ? "-" : "") + String.format("%.0f", c) + "/" + String.format("%.0f", b));
        }
    }
}
