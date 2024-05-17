package kursach.lab3;

import java.util.HashMap;
import java.util.Map;

public class converter {
    public static String convertToPostfix(String expression) {
        Stack operatorStack = new Stack();
        String rpnExpression = "";

        Map<Character, Integer> priorities = new HashMap<>();
        priorities.put('(', 0);
        priorities.put(')', 0);
        priorities.put('+', 1);
        priorities.put('-', 1);
        priorities.put('*', 2);
        priorities.put('/', 2);

        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            if (Character.isDigit(c)) {
                String num = "";
                while (Character.isDigit(c)) {
                    num += c;
                    i++;
                    if (i < expression.length()) {
                        c = expression.charAt(i);
                    } else {
                        break;
                    }
                }
                i--;
                rpnExpression += num + " ";
            } else if (Character.isLetter(c)) {
                rpnExpression += c + " ";
            }
            else if (c == '(') {
                operatorStack.push(Character.toString(c));
            } else if (c == ')') {
                while (!operatorStack.isEmpty() && !operatorStack.peek().equals("(")) {
                    rpnExpression += operatorStack.pop() + " ";
                }
                operatorStack.pop();
            } else if (priorities.containsKey(c)) {
                while (!operatorStack.isEmpty() && priorities.get(operatorStack.peek().charAt(0)) >= priorities.get(c)) {
                    rpnExpression += operatorStack.pop() + " ";
                }
                operatorStack.push(Character.toString(c));
            }
        }

        while (!operatorStack.isEmpty()) {
            rpnExpression += operatorStack.pop() + " ";
        }

        return rpnExpression.trim();
    }

    public static String convertToPrefix(String expression) {
        Stack stack = new Stack();
        String prefixExpression = "";
        String number = "";

        Map<Character, Integer> priorities = new HashMap<>();
        priorities.put('(', 0);
        priorities.put(')', 0);
        priorities.put('+', 1);
        priorities.put('-', 1);
        priorities.put('*', 2);
        priorities.put('/', 2);

        for (char c : expression.toCharArray()) {
            if (Character.isDigit(c) || Character.isLetter(c)) {
                number += c;
            } else {
                if (!number.isEmpty()) {
                    prefixExpression = number + " " + prefixExpression;
                    number = "";
                }
            }

            if (c == '(') {
                stack.push(Character.toString(c));
            } else if (c == ')') {
                while (!stack.isEmpty() && !stack.peek().equals("(")) {
                    prefixExpression = stack.pop() + " " + prefixExpression;
                }
                stack.pop();
            } else if (priorities.containsKey(c)) {
                while (!stack.isEmpty() && priorities.get(stack.peek().charAt(0)) >= priorities.get(c)) {
                    prefixExpression = stack.pop() + " " + prefixExpression;
                }
                stack.push(Character.toString(c));
            }
        }
        if (!number.isEmpty()) {
            prefixExpression = number + " " + prefixExpression;
        }
        while (!stack.isEmpty()) {
            prefixExpression = stack.pop() + " " + prefixExpression;
        }
        return prefixExpression.trim();
    }

    public static String insertSpaces(String expression) {
        StringBuilder fixedExpression = new StringBuilder();

        int pointer = 0;
        while (pointer < expression.length()) {
            char curChar = expression.charAt(pointer);
            if (curChar == '+' || curChar == '-' || curChar == '*' || curChar == '/') {
                fixedExpression.append(curChar).append(" ");
                pointer++;
            } else if (Character.isDigit(curChar) || Character.isLetter(curChar)) {
                StringBuilder numberOrVariable = new StringBuilder();
                while (pointer < expression.length() && (Character.isDigit(expression.charAt(pointer)) || Character.isLetter(expression.charAt(pointer)))) {
                    numberOrVariable.append(expression.charAt(pointer));
                    pointer++;
                }
                fixedExpression.append(numberOrVariable).append(" ");
            } else {
                pointer++;
            }
        }

        String result = fixedExpression.toString().replaceAll("(?<=\\p{L})(?=\\p{L})|\\s+", " ").trim();
        return result;
    }
    public static boolean isValidInfixExpression(String expression) {
        int bracketCount = 0;
        for(char c : expression.toCharArray()) {
            if (c == '(') {
                bracketCount++;
            } else if (c == ')') {
                bracketCount--;
            }
        }
        if (bracketCount != 0) {
            return false;
        }


        boolean hasOperator = false;
        boolean hasOperands = false;
        for(char c : expression.toCharArray()) {
            if (c == '+' || c == '-' || c == '*' || c == '/') {
                hasOperator = true;
            } else if (Character.isDigit(c)) {
                hasOperands = true;
            }
        }
        return hasOperator && hasOperands;
    }

    public static boolean isValidExpression(String expression) {
        String operators = "+-*/";
        String[] tokens = expression.split("\\s+");
        int operandCount = 0;

        for (String token : tokens) {
            if (operators.contains(token)) {
                operandCount -= 1;
            } else {
                operandCount += 1;
            }
        }

        return operandCount == 1;
    }

    public static String convertPrefixToInfix(String prefix) {
        Stack stack = new Stack();

        if (!isValidExpression(prefix)) {
            return "Неверный ввод";
        }

        for (int i = prefix.length() - 1; i >= 0; i-=2) {
            char ch = prefix.charAt(i);

            if (Character.isLetterOrDigit(ch)) {
                stack.push(String.valueOf(ch));
            } else {
                String operand1 = stack.pop();
                String operand2 = stack.pop();
                String expression = "(" + operand1 + ch + operand2 + ")";
                stack.push(expression);
            }
        }

        return stack.pop();

    }

    public static String convertPostfixToInfix(String postfixExpression) {
        Stack stack = new Stack();

        if (!isValidExpression(postfixExpression)) {
            return "Неверный ввод";
        }

        for (int i = 0; i < postfixExpression.length(); i+=2) {
            char ch = postfixExpression.charAt(i);
            if (Stack.isOperator(ch)) {
                String operand2 = stack.pop();
                String operand1 = stack.pop();
                stack.push("(" + operand1 + ch + operand2 + ")");
            } else {
                stack.push(String.valueOf(ch));
            }
        }
        return stack.pop();
    }

    public static String performOperation(int operand1, int operand2, char operator) {
        return switch (operator) {
            case '+' -> String.valueOf(operand1 + operand2);
            case '-' -> String.valueOf(operand1 - operand2);
            case '*' -> String.valueOf(operand1 * operand2);
            case '/' -> String.valueOf(operand1 / operand2);
            default -> throw new IllegalArgumentException("Недопустимый оператор: " + operator);
        };
    }
    public static String evaluatePostfix(String postfix){
        Stack stack = new Stack();
        postfix = insertSpaces(postfix);
        if (!isValidExpression(postfix)) {
            return "Неверный ввод";
        }
        for (int i = 0; i < postfix.length(); i+=2) {
            char ch = postfix.charAt(i);
            if (Stack.isOperator(ch)) {
                String operand2 = stack.pop();
                String operand1 = stack.pop();
                stack.push(performOperation(Integer.parseInt(operand1),Integer.parseInt(operand2),ch));
            } else {
                stack.push(String.valueOf(ch));
            }
        }
        return stack.pop();
    }

    public static String evaluatePrefix(String prefix){
        Stack stack = new Stack();
        prefix = insertSpaces(prefix);
        if (!isValidExpression(prefix)) {
            return "Неверный ввод";
        }
        for (int i = prefix.length() - 1; i >= 0; i-=2) {
            char ch = prefix.charAt(i);

            if (Character.isLetterOrDigit(ch)) {
                stack.push(String.valueOf(ch));
            } else {
                String operand1 = stack.pop();
                String operand2 = stack.pop();
                stack.push(performOperation(Integer.parseInt(operand1),Integer.parseInt(operand2),ch));
            }
        }
        return stack.pop();
    }

    public static String evaluateInfix(String infix){
        if (!isValidInfixExpression(infix)) {
            return "Неверный ввод";
        }
        String postfix = convertToPostfix(infix);
        return evaluatePostfix(postfix);
    }

}
