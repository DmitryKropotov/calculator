package com.company;

import java.util.Scanner;

public class Main {

    private static UserInputParser userInputParser = new UserInputParser();
    private static ArabicRomanNumberConvertor numberConvertor = new ArabicRomanNumberConvertor();

    public static void main(String[] args) {
	// write your code here
        //1, 2 Scan user input and check correctness
       userInputParser.readUserInput();
       //3 define arithmetical operation
       String arithmeticalSign = userInputParser.defineArithmeticalSign();
       //4 define numbers
       String[] numbers = userInputParser.defineNumbers(arithmeticalSign);
       //5 convert numbers from string to int
       int number1, number2;
       boolean romans;
       //5.1
       if (numbers[0].matches("[0-9]*")) {
           number1 = Integer.parseInt(numbers[0]);
           number2 = Integer.parseInt(numbers[1]);
           romans = false;
       //5.2 convert roman to arabic
       } else {
           number1 = numberConvertor.convertRomanToArabic(numbers[0]);
           number2 = numberConvertor.convertRomanToArabic(numbers[1]);
           romans = true;
       }
       //6 calculate result
       int result = calculateResult(arithmeticalSign, number1, number2);
       System.out.println(romans ? numberConvertor.convertArabicToRoman(result): result);
    }

    private static int calculateResult(String arithmeticalSign, int... numbers) {
        int result = 0;
        switch (arithmeticalSign) {
            case "+":
                result = numbers[0];
                for (int i = 1; i < numbers.length; i++) {
                    result += numbers[i];
                }
                return result;
            case "-":
                result = numbers[0];
                for (int i = 1; i < numbers.length; i++) {
                    result -= numbers[i];
                }
                return result;
            case "*":
                result = numbers[0];
                for (int i = 1; i < numbers.length; i++) {
                    result *= numbers[i];
                }
                return result;
            case "/":
                result = numbers[0];
                for (int i = 1; i < numbers.length; i++) {
                    result /= numbers[i];
                }
                return result;
            default:
                throw new IllegalArgumentException("ArithmeticalSign should be '+', '-', '*' or '/'");
        }
    }
}
