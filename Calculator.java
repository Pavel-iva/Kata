import java.util.*;

public class Calculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        scanner.close();
        String[] inputs = input.split(" ");
        if (inputs.length != 3) {
            throw new IllegalArgumentException("throws Exception");
        }
        String num1 = inputs[0];
        String num2 = inputs[2];
        String operator = inputs[1];
        if (!isValidInput(num1) || !isValidInput(num2)) {
            throw new IllegalArgumentException("throws Exception");
        }
        boolean isNum1Roman = isRomanNumeral(num1);
        boolean isNum2Roman = isRomanNumeral(num2);
        if (isNum1Roman != isNum2Roman) {
            throw new IllegalArgumentException("throws Exception");
        }
        int intNum1 = isNum1Roman ? romanToInt(num1) : Integer.parseInt(num1);
        int intNum2 = isNum2Roman ? romanToInt(num2) : Integer.parseInt(num2);
        int result;
        switch (operator) {
            case "+":
                result = intNum1 + intNum2;
                break;
            case "-":
                result = intNum1 - intNum2;
                break;
            case "*":
                result = intNum1 * intNum2;
                break;
            case "/":
                if (intNum2 == 0) {
                    throw new IllegalArgumentException("throws Exception");
                }
                result = intNum1 / intNum2;
                break;
            default:
                throw new IllegalArgumentException("throws Exception");
        }
        if (isNum1Roman && (result <= 0 || result > 10)) {
            throw new IllegalArgumentException("throws Exception");
        }
        String output = isNum1Roman ? intToRoman(result) : String.valueOf(result);
        System.out.println(output);
    }
    private static boolean isValidInput(String num) {
        int intNum;
        try {
            intNum = Integer.parseInt(num);
        } catch (NumberFormatException e) {
            return isRomanNumeral(num);
        }
        return intNum >= 1 && intNum <= 10;
    }

    private static boolean isRomanNumeral(String str) {
        return str.matches("^[IVX]+$");
    }

    private static int romanToInt(String str) {
        Map<Character, Integer> romanToDecimal = new HashMap<>();
        romanToDecimal.put('I', 1);
        romanToDecimal.put('V', 5);
        romanToDecimal.put('X', 10);
        int decimal = 0;
        int prevValue = 0;
        for (int i = str.length() - 1; i >= 0; i--) {
            char ch = str.charAt(i);
            int currValue = romanToDecimal.get(ch);
            if (currValue < prevValue) {
                decimal -= currValue;
            } else {
                decimal += currValue;
            }
            prevValue = currValue;
        }
        return decimal;
    }

    private static String intToRoman(int num) {
        StringBuilder sb = new StringBuilder();
        while (num >= 1000) {
            sb.append("M");
            num -= 1000;
        }
        while (num >= 900) {
            sb.append("CM");
            num -= 900;
        }
        while (num >= 500) {
            sb.append("D");
            num -= 500;
        }
        while (num >= 400) {
            sb.append("CD");
            num -= 400;
        }
        while (num >= 100) {
            sb.append("C");
            num -= 100;
        }
        while (num >= 90) {
            sb.append("XC");
            num -= 90;
        }
        while (num >= 50) {
            sb.append("L");
            num -= 50;
        }
        while (num >= 40) {
            sb.append("XL");
            num -= 40;
        }
        while (num >= 10) {
            sb.append("X");
            num -= 10;
        }
        while (num >= 9) {
            sb.append("IX");
            num -= 9;
        }
        while (num >= 5) {
            sb.append("V");
            num -= 5;
        }
        while (num >= 4) {
            sb.append("IV");
            num -= 4;
        }
        while (num >= 1) {
            sb.append("I");
            num -= 1;
        }
        return sb.toString();
    }
}