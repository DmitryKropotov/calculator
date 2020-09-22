package com.company;

import java.util.Scanner;

public class UserInputParser {

    private String userInput;

    public void readUserInput() {
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine().replaceAll("\\s", "");
        this.userInput = userInput;
        if (!correctInput()) {
            throw new IllegalArgumentException("format of user input is incorrect");
        }
    }

    private boolean correctInput() {
        return  userInput.matches("10[\\+\\-\\*\\//]10") ||
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

    public String defineArithmeticalSign() {
        if (userInput.contains("+")) {
            return "+";
        }
        if (userInput.contains("-")) {
            return "-";
        }
        if (userInput.contains("*")) {
            return "*";
        }
        if (userInput.contains("/")) {
            return "/";
        }
        throw new IllegalArgumentException("expression doesn't contain arithmetical operation");
    }

    public String[] defineNumbers(String arithmeticalSign) {
        switch (arithmeticalSign) {
            case "+":
                return userInput.split("\\+");
            case "*":
                return userInput.split("\\*");
        }
        return userInput.split(arithmeticalSign);
    }
}
