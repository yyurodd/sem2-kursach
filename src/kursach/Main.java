package kursach;

import kursach.lab1.lab1;
import kursach.lab2.lab2;
import kursach.lab3.lab3;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;
        System.out.println("""
                Выберите программу:
                1 - Работа со структурами данных на примере студентов и абитуриентов.
                2 - Работа с двусвязными списками и динамическими массивами.
                3 - Работа со стеком на примере использования прямой и обратной польских нотаций.
                0 - Окончание работы программы.
                """);
        while (true) {
            choice = scanner.nextInt();
            scanner.nextLine();
            if (choice == 0){
                break;
            }
            switch (choice) {
                case 1:
                    lab1.main();
                    break;
                case 2:
                    lab2.main();
                    break;
                case 3:
                    lab3.main();
                    break;
            }
        }
    }
}
