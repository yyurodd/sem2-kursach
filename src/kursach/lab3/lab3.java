package kursach.lab3;

import java.util.Scanner;

public class lab3 {
    public static void main() {
        Scanner scanner = new Scanner(System.in);
        int num;
        String expression;
        while (true) {
            System.out.println("""
                             Меню                \s
                    -----------------------------------------------------------------------------------------------------------------
                    1 - Преобразование выражения.
                    2 - Вычисление простого выражения и выражения, записанного в прямой и обратной польских нотациях
                    -----------------------------------------------------------------------------------------------------------------
                    0 - Завершить выполнение программы.
                    """);

            num = scanner.nextInt();
            scanner.nextLine();
            if (num == 0) {
                break;
            }
            switch (num) {
                case 1:

                    System.out.println("""
                            Какое преобразование вы хотите совершить?
                            1 - Из простого выражения в обратную польскую нотацию.
                            2 - Из простого выражения в прямую польскую нотацию.
                            3 - Из обратной польской нотации в прямую запись.
                            4 - Из прямой польской нотации в прямую запись.
                            """);
                    num = scanner.nextInt();
                    scanner.nextLine();
                    switch (num) {
                        case 1:
                            System.out.println("Введите простое выражение");
                            expression = scanner.nextLine();
                            expression = converter.insertSpaces(expression);
                            if (converter.isValidInfixExpression(expression)) {
                                String rpn = converter.convertToPostfix(expression);
                                System.out.println("Выражение в обратной польской нотации: " + rpn);
                            } else {
                                System.out.println("Выражение введено некорректно");
                                return;
                            }
                            break;
                        case 2:
                            System.out.println("Введите простое выражение");
                            expression = scanner.nextLine();
                            expression = converter.insertSpaces(expression);
                            if (converter.isValidInfixExpression(expression)) {
                                String prefix = converter.convertToPrefix(expression);
                                System.out.println("Выражение в прямой польской нотации: " + prefix);
                            } else {
                                System.out.println("Выражение введено некорректно");
                                return;
                            }
                            break;
                        case 3:
                            System.out.println("Введите выражение в обратной польской нотации");
                            expression = scanner.nextLine();
                            expression = converter.insertSpaces(expression);
                            if (converter.isValidExpression(expression)) {
                                String prefix = converter.convertPostfixToInfix(expression);
                                System.out.println("Выражение в прямой записи: " + prefix);
                            } else {
                                System.out.println("Выражение введено некорректно");
                                return;
                            }
                            break;
                        case 4:
                            System.out.println("Введите простое выражение");
                            expression = scanner.nextLine();
                            expression = converter.insertSpaces(expression);
                            String prefix = converter.convertPrefixToInfix(expression);
                            System.out.println("Выражение в записи: " + prefix);
                            break;
                    }
                    break;
                case 2:
                    System.out.println("""
                                Вычисление выражения в какой записи вы хотите совершить?
                                1 - Из простого выражения.
                                2 - Из обратной польской нотации.
                                3 - Из прямой польской нотации.
                                """);
                    num = scanner.nextInt();
                    String result;
                    scanner.nextLine();
                    switch (num) {
                        case 1:
                            System.out.println("Введите выражение в прямой записи.");
                            expression = scanner.nextLine();
                            result = converter.evaluateInfix(expression);
                            System.out.println("Результат вычисления: " + result);
                            break;
                        case 2:
                            System.out.println("Введите выражение в обратной польской нотации.");
                            expression = scanner.nextLine();
                            result = converter.evaluatePostfix(expression);
                            System.out.println("Результат вычисления: " + result);
                            break;
                        case 3:
                            System.out.println("Введите выражение в обратной польской нотации.");
                            expression = scanner.nextLine();
                            result = converter.evaluatePrefix(expression);
                            System.out.println("Результат вычисления: " + result);
                            break;
                    }
                    break;
            }
        }
    }
}
