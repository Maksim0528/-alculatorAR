import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите математическое выражение (+, -, /, *) двух чисел (арабских или римских) от 1 до 10 включительно и нажмите enter");
        String str = scanner.nextLine();
        System.out.println(calc(str));
    }

    public static String calc(String input) {
        int number1, number2;
        String operation;
        int result;
        String[] arr = input.trim().toUpperCase().replaceAll("\\s+", " ").split(" ");

        if (arr.length != 3) {
            throw new IllegalArgumentException("Формат математической операции не удовлетворяет заданию - два операнда и один оператор");
        }

        List<String> romanNumerals = new ArrayList<>();
        romanNumerals.add("I");
        romanNumerals.add("II");
        romanNumerals.add("III");
        romanNumerals.add("IV");
        romanNumerals.add("V");
        romanNumerals.add("VI");
        romanNumerals.add("VII");
        romanNumerals.add("VIII");
        romanNumerals.add("IX");
        romanNumerals.add("X");

        String numStr1 = arr[0];
        String numStr2 = arr[2];

        boolean isRoman = false;
        if (romanNumerals.contains(numStr1) || romanNumerals.contains(numStr2)) {
            isRoman = romanNumerals.contains(numStr1) && romanNumerals.contains(numStr2);
            if (!isRoman)
                throw new IllegalArgumentException("Используются одновременно разные системы счисления или одно из римских чисел не входит в диапазон от 1 до 10");
        }

        if (isRoman) {
            number1 = convertRomanToArabic(numStr1);
            number2 = convertRomanToArabic(numStr2);
        } else {
            number1 = Integer.parseInt(numStr1);
            number2 = Integer.parseInt(numStr2);
        }

        if ((number1 < 1 || number1 > 10) || (number2 < 1 || number2 > 10)) {
            throw new IllegalArgumentException("Вы ввели число, которое не входит в диапазон от 1 до 10");
        }

        operation = arr[1];
        switch (operation) {
            case "+" -> result = number1 + number2;
            case "-" -> result = number1 - number2;
            case "*" -> result = number1 * number2;
            case "/" -> result = number1 / number2;
            default ->
                    throw new IllegalArgumentException("Не правильно введен символ операции, используйте только +, -, *, /");
        }

        if (isRoman && result < 1) {
            throw new ArithmeticException("в римской системе счисления отсутсвуют отрицательные чисела и ноль");
        }

        String resultStr = Integer.toString(result);
        int i = resultStr.length();
        if (isRoman) {
            if (i == 3)
                resultStr = "C";
            else if (i == 2) {
                resultStr = (convertArabicToRomanTen(resultStr.substring(0, 1)) + convertArabicToRomanUnits(resultStr.substring(1)));
            } else if (i == 1) {
                resultStr = convertArabicToRomanUnits(resultStr);
            }
        }
        return resultStr;
    }

    private static int convertRomanToArabic(String romanNumber) {
        switch (romanNumber) {
            case ("I") -> {
                return 1;
            }
            case ("II") -> {
                return 2;
            }
            case ("III") -> {
                return 3;
            }
            case ("IV") -> {
                return 4;
            }
            case ("V") -> {
                return 5;
            }
            case ("VI") -> {
                return 6;
            }
            case ("VII") -> {
                return 7;
            }
            case ("VIII") -> {
                return 8;
            }
            case ("IX") -> {
                return 9;
            }
            case ("X") -> {
                return 10;
            }
            default -> throw new IllegalArgumentException("Вы ввели число, которое не входит в диапазон от I до X");
        }
    }

    private static String convertArabicToRomanUnits(String arabicNumberUnits) {
        switch (arabicNumberUnits) {
            case ("1") -> {
                return "I";
            }
            case ("2") -> {
                return "II";
            }
            case ("3") -> {
                return "III";
            }
            case ("4") -> {
                return "IV";
            }
            case ("5") -> {
                return "V";
            }
            case ("6") -> {
                return "VI";
            }
            case ("7") -> {
                return "VII";
            }
            case ("8") -> {
                return "VIII";
            }
            case ("9") -> {
                return "IX";
            }
            default -> {
                return "";
            }
        }
    }

    private static String convertArabicToRomanTen(String arabicNumberTen) {
        switch (arabicNumberTen) {
            case ("1") -> {
                return "X";
            }
            case ("2") -> {
                return "XX";
            }
            case ("3") -> {
                return "XXX";
            }
            case ("4") -> {
                return "XL";
            }
            case ("5") -> {
                return "L";
            }
            case ("6") -> {
                return "LX";
            }
            case ("7") -> {
                return "LXX";
            }
            case ("8") -> {
                return "LXXX";
            }
            case ("9") -> {
                return "XC";
            }
            default -> {
                return "";
            }
        }
    }
}

