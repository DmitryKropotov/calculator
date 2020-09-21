package com.company;

import java.util.IllegalFormatException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	// write your code here
       Scanner scanner = new Scanner(System.in);
       String userInput = scanner.nextLine();
       userInput = userInput.trim();
       if (!correctInput(userInput)) {
           throw new IllegalArgumentException("format of user input is incorrect");
       }
       String arithmeticalSign = defineArithmeticalSign(userInput);
       String[] numbers;
       switch (arithmeticalSign) {
           case "+":
             numbers = userInput.split("\\+");
             break;
           case "*":
             numbers = userInput.split("\\*");
             break;
           default:
             numbers = userInput.split(arithmeticalSign);
       }
       int number1, number2;
       boolean romans;
       if (numbers[0].matches("[0-9]*")) {
           number1 = Integer.parseInt(numbers[0]);
           number2 = Integer.parseInt(numbers[1]);
           romans = false;
       } else {
           number1 = RomanNumbers.convertRomanToArabic(numbers[0]);
           number2 = RomanNumbers.convertRomanToArabic(numbers[1]);
           romans = true;
       }
       double result = 0;
       if (arithmeticalSign.equals("+")) {
           result = number1+number2;
       }
       if (arithmeticalSign.equals("-")) {
           result = number1-number2;
       }
       if (arithmeticalSign.equals("*")) {
           result = number1*number2;
       }
       if (arithmeticalSign.equals("/")) {
           result = (double)number1/(double)number2;
       }
        System.out.println(romans ? RomanNumbers.convertArabicToRoman((int)result): result);
    }

    private static boolean correctInput(String userInput) {
        return userInput.matches("10[\\+\\-\\*\\//]10") ||
               userInput.matches("[1-9][\\+\\-\\*\\//]10") ||
               userInput.matches("10[\\+\\-\\*\\//][1-9]") ||
               userInput.matches("[1-9][\\+\\-\\*\\//][1-9]") ||

               userInput.matches("I{1,3}[\\+\\-\\*\\//]I{1,3}") ||
               userInput.matches("I{1,3}[\\+\\-\\*\\//]I?V") ||
               userInput.matches("I{1,3}[\\+\\-\\*\\//]VI{1,3}") ||
               userInput.matches("I{1,3}[\\+\\-\\*\\//]I?X") ||
               userInput.matches("I?V[\\+\\-\\*\\//]I{1,3}") ||
               userInput.matches("I?V[\\+\\-\\*\\//]I?V") ||
               userInput.matches("I?V[\\+\\-\\*\\//]VI{1,3}") ||
               userInput.matches("I?V[\\+\\-\\*\\//]I?X") ||
               userInput.matches("VI{1,3}[\\+\\-\\*\\//]I{1,3}") ||
               userInput.matches("VI{1,3}[\\+\\-\\*\\//]I?V") ||
               userInput.matches("VI{1,3}[\\+\\-\\*\\\\]VI{1,3}") ||
               userInput.matches("VI{1,3}[\\+\\-\\*\\//]I?X") ||
               userInput.matches("I?X[\\+\\-\\*\\//]I{1,3}") ||
               userInput.matches("I?X[\\+\\-\\*\\//]I?V") ||
               userInput.matches("I?X[\\+\\-\\*\\//]VI{1,3}") ||
               userInput.matches("I?X[\\+\\-\\*\\//]I?X");
    }

    private static String defineArithmeticalSign(String expression) {
        if (expression.contains("+")) {
            return "+";
        }
        if (expression.contains("-")) {
            return "-";
        }
        if (expression.contains("*")) {
            return "*";
        }
        if (expression.contains("/")) {
            return "/";
        }
        throw new IllegalArgumentException("expression doesn't contain arithmetical operation");
    }
}
