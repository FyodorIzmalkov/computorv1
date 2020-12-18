package com.compuctor;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;
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
            System.out.println("ploho");
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
        long maxDegree = 0;
        for (Long degree : degreeSet) {
            if (degree > 2) {
                System.out.println("NIMOGU RESHIT");
                System.exit(1);
            }
            if (degree > maxDegree) {
                maxDegree = degree;
            }
        }

        System.out.println(map.toString());

        map.forEach((key, val) -> {
            Double sum = val.stream()
                    .mapToDouble(Double::doubleValue).sum();
            finalMap.put(key, sum);
        });

        System.out.println(map.toString());
        System.out.println(finalMap.toString());

        //"5 * X^0 + 4 * X^1 - 9.3 * X^2 = 1 * X^0"
        //TODO REDUCED FORM
        String reducedForm = "";
        for (Map.Entry<Long, Double> entry : finalMap.entrySet()) {
            Double val = entry.getValue();

            if (val < 0) {
                reducedForm += "- ";
                val *= -1;
            } else if (!reducedForm.isEmpty()) {
                reducedForm += "+ ";
            }

            if (val % 1 == 0) {
                reducedForm += val.longValue();
            } else {
                reducedForm += val;
            }
            reducedForm += " * X^";
            reducedForm += entry.getKey();
            reducedForm += " ";
        }
        reducedForm += "= 0";
        System.out.println("Reduced form: " + reducedForm);
        System.out.println("Polynomial degree: " + maxDegree);

        if (maxDegree == 0) {
            System.out.println("VSE PLOHO JOPA");
        } else if (maxDegree == 1) {
            Double coeffZeroDegree = finalMap.get(ZERO_DEGREE) * -1;
            Double coeffFirstDegree = finalMap.get(FIRST_DEGREE);
            System.out.println("The solution is:");
            System.out.println(coeffZeroDegree / coeffFirstDegree);
        } else if (maxDegree == 2) {
            // a - SECOND b - FIRST c = ZERO
            // D = b * b - 4 * a * c
            Double c = finalMap.get(ZERO_DEGREE);
            Double a = finalMap.get(FIRST_DEGREE);
            Double b = finalMap.get(SECOND_DEGREE);

            double discr = b * b - 4 * a * c;
            if (discr < 0){
                System.out.println("КОРНЕЙ НЕТ НО НАДО ЗАПИЛИТЬ НАХОЖЖДЕНИЕ ХРЕНОВЫХ ЧИСЕЛ");
            } else if (discr == 0){
                System.out.println("EST ROVNO ODIN KOREN");
            }
        }

//        Pattern pattern = Pattern.compile("[0-9X\s\^\-\+\*\=\\.\\/]+$");
//        Matcher matcher = pattern.matcher(strToParse);
//        equationRegex = re.compile('[0-9X\s\^\-\+\*\=\.\/]+$')
        // Find all matches
//        while (matcher.find()) {
//            // Get the matching string
//            String match = matcher.group();
//            System.out.println(match);
//        }

//        Scanner scanner = new Scanner(System.in);
//        while(true){
//            String line = scanner.nextLine();
//            System.out.println(line);
//        }
    }

//              "5123 * X^0 + 4 * X^1 - 9.3 * X^2 = 1 * X^0"
// Reduced form: 4 * X^0 + 4 * X^1 - 9.3 * X^2 = 0
// "5 + 4 * X + X^2= X^2"
// Reduced form: 5 + 4 * X = 0


    // poluchaem {0=[5.0, -1.0], 1=[4.0], 2=[-9.3]}
    public static void splitAndParse(String strToParse) {
        String[] strArr = strToParse.split("=");
        String leftPart = strArr[0];
        String rightPart = strArr[1];

        parseString(leftPart, LEFT_PART);
        parseString(rightPart, RIGHT_PART);
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
            System.exit(1);
        }

        long degreeLong = 0;
        try {
            degreeLong = Long.parseLong(degree.toString());
        } catch (Exception e) {
            System.out.println("error long parsing");
            System.exit(1);
        }

        map.computeIfAbsent(degreeLong, k -> new ArrayList<>()).add(numDouble);
        return i;
    }

    // "5 * X^0 + 4 * X^1 - 9.3 * X^2 = 1 * X^0"
    public static int getMaxDegree(String str) {
        String[] strArr = str.split("=");
        String leftPart = strArr[0];
        String rightPart = strArr[1];


        System.out.println(leftPart);
        System.out.println(rightPart);
        return 1;
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
