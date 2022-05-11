
//import java.io.*;
//import java.util.Arrays;
//import java.util.Comparator;
//import java.util.List;
//import java.util.stream.Collectors;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//import java.util.Locale;
import static com.sun.tools.javac.util.StringUtils.toUpperCase;
import java.util.Scanner;

class Main {
    //public static String calc(String input) { }
    private static String convertToRoman(int mInt) {
        String[] rnChars = { "M",  "CM", "D", "C",  "XC", "L",  "X", "IX", "V", "I" };
        int[] rnVals = {  1000, 900, 500, 100, 90, 50, 10, 9, 5, 1 };
        String retVal = "";

        for (int i = 0; i < rnVals.length; i++) {
            int numberInPlace = mInt / rnVals[i];
            if (numberInPlace == 0) continue;
            retVal += numberInPlace == 4 && i > 0? rnChars[i] + rnChars[i - 1]:
                    new String(new char[numberInPlace]).replace("\0",rnChars[i]);
            mInt = mInt % rnVals[i];
        }
        return retVal;
    }
    private static int convertToNumber(String str) {
        switch (str) {
            case "I":
                return 1;
            case "II":
                return 2;
            case "III":
                return 3;
            case "IV":
                return 4;
            case "V":
                return 5;
            case "VI":
                return 6;
            case "VII":
                return 7;
            case "VIII":
                return 8;
            case "IX":
                return 9;
            case "X":
                return 10;
            default:
                return 0;
        }
    }
    static boolean isCheck(String str) {
        int count = 0;
        for(int i = 0; i < str.length(); ++i) {
            if(str.charAt(i) == ' ') {
                ++count;
            }
        }
        return count == 2;
    }
    static  boolean isNumeric(String str) {
        for(int i = 0; i < str.length(); ++i) {
            if(str.charAt(i) >= '0' && str.charAt(i) <= '9') {
                return true;
            }
        } return false;
    }
    static  boolean isRoman(String str) {
        for(int i = 0; i < str.length(); ++i) {
            if(str.charAt(i) == 'I' || str.charAt(i) == 'V' || str.charAt(i) == 'X' ||
                    str.charAt(i) == 'D' || str.charAt(i) == 'C' || str.charAt(i) == 'M' ||
                    str.charAt(i) == 'L') {
                return  true;
            }
        } return false;
    }
    static boolean isOperator(char c) {
        return  (c == '+' || c == '-' || c == '*' || c == '/');
    }
    static  boolean isCorrect(String str) {
        return (isNumeric(str) && isRoman(str));
    }
    static String symbolDelete(String str) {  // удаление ненужных символов кроме ([])
        return str.replaceAll("[^IVXCDML0123456789]", "");
    }
    static int summ(int a, int b) {
        return  a + b;
    }
    static int difference(int a, int b) {
        return  a - b;
    }
    static  int work(int a, int b) {
        return  a * b;
    }
    static  int division(int a, int b) {
        return a / b;
    }
    static int getOperation(int a, int b, char c) {
        switch (c) {
            case '+':
                return summ(a, b);
            case '-':
                return difference(a, b);
            case '*':
                return work(a, b);
            case '/':
                return division(a, b);
        }
        return 0;
    }

    public  static  void main(String[] args) throws Exception {
        Scanner input = new Scanner(System.in);
        System.out.print("Введите пример: ");
        String example = input.nextLine();
        if(!isCheck(example)) {
            throw new Exception("формат математической операции не удовлетворяет заданию" +
                    "- два операнда и один оператор или строка не является математической операцией");
        }
            String[] subStr;
            String delimeter = " ";
            subStr = example.split(delimeter, 3);
            String operand1 = symbolDelete(subStr[0]);
            String operand2 = symbolDelete(subStr[2]);
            String operator = subStr[1];
            char ch = operator.charAt(0);
            if (!isOperator(ch)) {
                throw new Exception("Оператор не соответствует математичекой операции");
            }
                if (isCorrect(operand1) && isCorrect(operand2)) {
                    throw new Exception("Нельзя спользовать одновременно разные системы счисления");
                }
                    if (isNumeric(operand1)) {
                        Integer inumber1 = Integer.valueOf(operand1);
                        Integer inumber2 = Integer.valueOf(operand2);
                        if (operator.length() == 1) {
                            int result = getOperation(inumber1, inumber2, ch);
                            System.out.println(inumber1 + " " + ch + " " + inumber2 + " " + " = " + result);
                        } else System.out.println("false2");
                    } else if (isRoman(operand1)) {
                        int number1 = convertToNumber(operand1);
                        int number2 = convertToNumber(operand2);
                        if (operator.length() == 1) {
                            int result = getOperation(number1, number2, ch);
                            if (!(result > 0)) {
                                throw new Exception("В римской системе нет отрицательных чисел");
                            }
                                System.out.println(convertToRoman(number1) + " " + ch + " "
                                        + convertToRoman(number2) + " = " + convertToRoman(result));
                            //} else System.out.println("res < 0");
                        }
                    }/**/
                //} else System.out.println("One or two operands are invalid");
            //} else System.out.println("Operator not eligible");
        //} else System.out.println("The input format is incorrect");
    }
}
