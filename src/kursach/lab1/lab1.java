package kursach.lab1;

import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

import static kursach.lab1.ApplicantArray.MAX_APPLICANTS;
import static kursach.lab1.StudentArray.MAX_STUDENTS;

class Applicant {
    String name;
    String gender;
    int age;
    String city;
    float averageExamGrade;
    boolean original;
    float averageCertGrade;
    int exNum;
    int certNum;
    public Applicant(String name, String gender, int age, String city, float averageExamGrade, boolean original, float averageCertGrade ,int exNum,int certNum){
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.city = city;
        this.averageExamGrade = averageExamGrade;
        this.original = original;
        this.averageCertGrade = averageCertGrade;
        this.exNum = exNum;
        this.certNum = certNum;
    }
}

class Student {
    String name;
    String gender;
    int groupNumber;
    int listNumber;
    int[] grades;
    float averageGrade;

    public Student(String name, String gender, int groupNumber, int listNumber, int[] grades) {
        this.name = name;
        this.gender = gender;
        this.groupNumber = groupNumber;
        this.listNumber = listNumber;
        this.grades = grades;
    }
}

class ApplicantArray{
    public static final int MAX_APPLICANTS = 100;
    public static final Applicant[] applicants = new Applicant[MAX_APPLICANTS];
    public static int applicantCount = 0;

    public static Applicant createNewApplicantFromInput() {
        Scanner scanner = new Scanner(System.in);


        System.out.println("Введите имя абитуриента: ");
        String name = scanner.nextLine();

        String gender;
        boolean validGender = false;
        do {
            System.out.println("Введите пол абитуриента(М или Ж): ");
            gender = scanner.nextLine();
            if (gender.equalsIgnoreCase("М") || gender.equalsIgnoreCase("Ж")) {
                validGender = true;
            } else {
                System.out.println("Неверно введён пол абитуриента. Повторите ввод.");
            }
        } while (!validGender);

        System.out.println("Введите возраст абитуриента: ");
        int age = Integer.parseInt(scanner.nextLine());

        System.out.println("Введите город абитуриента: ");
        String city = scanner.nextLine();



        System.out.println("Введите количество сданных предметов ЕГЭ: ");
        int exNum = Integer.parseInt(scanner.nextLine());
        int[] examScores = new int[exNum];
        System.out.println("Введите баллы ЕГЭ через пробел: ");
        String scoresInput = scanner.nextLine();
        String[] scoresStrings = scoresInput.split(" ");
        for (int i = 0; i < exNum; i++) {
            examScores[i] = Integer.parseInt(scoresStrings[i]);
        }
        float sumex = 0;
        for(int exScore : examScores){
            sumex += exScore;
        }
        float averageExamScore = sumex / exNum;

        System.out.println("Наличие оригинала аттестата?(1-да/0-нет): ");
        boolean original = (Integer.parseInt(scanner.nextLine()) == 1);


        System.out.println("Введите количество предметов в аттестате: ");
        int certNum = Integer.parseInt(scanner.nextLine());
        int[] certGrades = new int[certNum];
        System.out.println("Введите оценки в аттестате через пробел: ");
        String gradesInput = scanner.nextLine();
        String[] gradesStrings = gradesInput.split(" ");

        for (int i = 0; i < certNum; i++) {
            certGrades[i] = Integer.parseInt(gradesStrings[i]);
        }

        float sum = 0;

        for (int grade : certGrades) {
            sum += grade;
        }
        float averageCertGrade = sum / certNum;

        return new Applicant(name, gender, age, city, averageExamScore, original, averageCertGrade,exNum,certNum);
    }

    public static void averageExamGrade(){
        System.out.println("Абитуриенты со средним баллом ЕГЭ выше 85: ");
        for (int i = 0; i < MAX_APPLICANTS; i++){
            if (applicants[i] != null && applicants[i].averageExamGrade > 85) {
                System.out.println("Абитуриент " + (i+1) + ": " + applicants[i].name + ", Пол: " + applicants[i].gender + ", Возраст: " + applicants[i].age + ", Город: " + applicants[i].city
                        + ", Средний балл ЕГЭ: " + applicants[i].averageExamGrade + " , Наличие оригинала аттестата: " + applicants[i].original + " , Средний балл аттестата: " + applicants[i].averageCertGrade);
            }
        }
    }

    public static void lessThan18(){
        for (int i = 0; i < MAX_APPLICANTS; i++){
            if (applicants[i] != null &&  applicants[i].age < 18){
                System.out.println("Абитуриент " + (i+1) + ": " + applicants[i].name + ", Пол: " + applicants[i].gender + ", Возраст: " + applicants[i].age + ", Город: " + applicants[i].city
                        + ", Средний балл ЕГЭ: " + applicants[i].averageExamGrade + " , Наличие оригинала аттестата: " + applicants[i].original + " , Средний балл аттестата: " + applicants[i].averageCertGrade);
            }
        }
    }

    public static void notSPB(){
        for (int i = 0; i < MAX_APPLICANTS; i++){
            if (applicants[i] != null && !applicants[i].city.equalsIgnoreCase("Санкт-Петербург")){
                System.out.println("Абитуриент " + (i+1) + ": " + applicants[i].name + ", Пол: " + applicants[i].gender + ", Возраст: " + applicants[i].age + ", Город: " + applicants[i].city
                        + ", Средний балл ЕГЭ: " + applicants[i].averageExamGrade + " , Наличие оригинала аттестата: " + applicants[i].original + " , Средний балл аттестата: " + applicants[i].averageCertGrade);
            }
        }
    }

    public static void goldMedal(){
        for (int i = 0; i < MAX_APPLICANTS; i++){
            if (applicants[i] != null && applicants[i].averageCertGrade == 5){
                System.out.println("Абитуриент " + (i+1) + ": " + applicants[i].name + ", Пол: " + applicants[i].gender + ", Возраст: " + applicants[i].age + ", Город: " + applicants[i].city
                        + ", Средний балл ЕГЭ: " + applicants[i].averageExamGrade + " , Наличие оригинала аттестата: " + applicants[i].original + " , Средний балл аттестата: " + applicants[i].averageCertGrade);
            }
        }
    }
}


class StudentArray {

    public static final int MAX_STUDENTS = 100;
    public static final Student[] students = new Student[MAX_STUDENTS];
    public static int studentCount = 0;


    public static void showAllStudents() {
        for (int i = 0; i < studentCount; i++) {
            if (students[i] != null) {
                System.out.println("Имя: " + students[i].name);
                System.out.println("Пол: " + students[i].gender);
                System.out.println("Номер группы: " + students[i].groupNumber);
                System.out.println("Номер в списке: " + students[i].listNumber);

                System.out.print("Оценки: ");
                for (int grade : students[i].grades) {
                    System.out.print(grade + " ");
                }
                System.out.println();

                System.out.println("-------------------------------");
            }
        }
    }

    public static void showStudentsByGroupNumber(int groupNumber) {
        for (int i = 0; i < MAX_STUDENTS; i++) {
            if (students[i] == null) {
                continue;
            }
            if (students[i].groupNumber == groupNumber) {
                System.out.println("Студент " + (i+1) + ": " + students[i].name + ", Пол: " + students[i].gender + ", Номер в списке: " + students[i].listNumber
                        + ", Оценки за сессию: " + Arrays.toString(students[i].grades));
            }
        }
    }

    public static void showStudentsByGroupNumberFromInput() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите номер группы : ");
        int groupNumber = scanner.nextInt();
        scanner.nextLine();

        showStudentsByGroupNumber(groupNumber);
    }



    public static Student createNewStudentFromInput() {
        Scanner scanner = new Scanner(System.in);


        System.out.println("Введите имя студента: ");
        String name = scanner.nextLine();

        String gender;
        boolean validGender = false;
        do {
            System.out.println("Введите пол студента(М или Ж): ");
            gender = scanner.nextLine();
            if (gender.equalsIgnoreCase("М") || gender.equalsIgnoreCase("Ж")) {
                validGender = true;
            } else {
                System.out.println("Неверно введён пол студента. Повторите ввод.");
            }
        } while (!validGender);

        System.out.println("Введите номер группы студента: ");
        int groupNumber = Integer.parseInt(scanner.nextLine());

        System.out.println("Введите номер студента в списке: ");
        int listNumber = Integer.parseInt(scanner.nextLine());



        int[] grades = new int[8];
        System.out.println("""
                Введите оценки за прошедшую сессию через пробел
                1.АиГ, 2.МатАн, 3.Программирование, 4.Физика, 5.Философия, 6.Ин.Яз., 7.Информатика, 8.ОРГ""");
        String gradesInput = scanner.nextLine();
        String[] gradesStrings = gradesInput.split(" ");
        if (gradesStrings.length < 8) {
            System.out.println("Вы ввели меньше 8 оценок, введите данные студента заново.");
            return createNewStudentFromInput();
        }

        for (int i = 0; i < 8; i++) {
            grades[i] = Integer.parseInt(gradesStrings[i]);
        }


        return new Student(name, gender, groupNumber, listNumber, grades);
    }


    public static void updateStudentInformation(Student student, String ID) {
        Scanner scanner = new Scanner(System.in);

        String studentID = String.valueOf(student.groupNumber) + student.listNumber;
        if(!ID.equals(studentID)){
            System.out.println("Студент с указанным ID не найден.");
            return;
        }

        int[] grades;
        System.out.println("""
        Какую информацию вы хотите изменить?"
        "1. Имя студента"
        "2. Номер группы"
        "3. Номер студента в списке"
        "4. Оценки за сессию""");

        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                System.out.println("Введите новое имя студента: ");
                scanner.nextLine();
                student.name = scanner.nextLine();
                break;
            case 2:
                System.out.println("Введите новый номер группы студента: ");
                student.groupNumber = scanner.nextInt();
                break;
            case 3:
                System.out.println("Введите новый номер студента в списке: ");
                student.listNumber = scanner.nextInt();

                break;
            case 4:
                System.out.println("Введите новые оценки за прошедшую сессию через пробел:");
                scanner.nextLine();
                grades = new int[8];
                String gradesInput = scanner.nextLine();
                String[] gradesStrings = gradesInput.split(" ");
                if (gradesStrings.length < 8) {
                    System.out.println("Вы ввели меньше 8 оценок.");
                    updateStudentInformation(student,studentID);
                }

                for (int i = 0; i < 8; i++) {
                    grades[i] = Integer.parseInt(gradesStrings[i]);
                }
                student.grades = grades;
                break;
            default:
                System.out.println("Некорректный выбор. Изменение отменено.");
                break;
        }

    }

    public static void showTopRatedStudents() {
        for (int i = 0; i < MAX_STUDENTS; i++) {
            float sum = 0;
            if (students[i] == null) {
                continue;
            }
            for (int grade : students[i].grades) {
                sum += grade;
            }
            students[i].averageGrade = sum / 8;
        }

        for (int i = 0; i < MAX_STUDENTS - 1; i++){
            if (students[i] == null) {
                continue;
            }
            for (int j = i + 1; j < MAX_STUDENTS; j++){
                if (students[j] == null) {
                    continue;
                }
                if (students[j].averageGrade > students[i].averageGrade){
                    Student tmp = students[i];
                    students[i] = students[j];
                    students[j] = tmp;
                }
            }
        }

        System.out.println("Топ студентов по рейтингу:");
        for (int i = 0; i < MAX_STUDENTS; i++) {
            if (students[i] == null) {
                continue;
            }

            System.out.println((i + 1) + ". " + students[i].name + " - средний балл: " + students[i].averageGrade);

        }
    }

    public static void showMaleFemaleCnt(){
        int cntMale = 0;
        int cntFemale = 0;
        for (int i = 0; i < MAX_STUDENTS; i++){
            if (students[i] == null) {
                continue;
            }
            if (Objects.equals(students[i].gender, "М") || !Objects.equals(students[i].gender, "м")){
                cntMale+=1;
            }
            else if (Objects.equals(students[i].gender, "Ж") || Objects.equals(students[i].gender, "ж")){
                cntFemale+=1;
            }

        }
        System.out.println("Количество студентов мужского пола: " + cntMale +"\nКоличество студентов женского пола: " + cntFemale);
    }

    public static void showKNumberedStudents(){
        System.out.println("Введите номер студента в списке: ");
        Scanner scan = new Scanner(System.in);
        int k = scan.nextInt();
        int falses = 0;
        int success = 0;
        for (int i = 0; i < MAX_STUDENTS; i++){
            if (students[i] == null) {
                continue;
            }
            if (students[i].listNumber == k){
                System.out.println("Студент " + (i+1) + ": " + students[i].name + ", Пол: " + students[i].gender + ", Номер группы: " + students[i].groupNumber + ", Номер в списке: " + students[i].listNumber
                        + ", Оценки за сессию: " + Arrays.toString(students[i].grades));
                success+=1;
            }
            else{
                falses+=1;
            }
        }

        if (falses > 0 && success == 0){
            System.out.println("Нет студентов с таким номером.");
        }
    }

    public static boolean checkForDiffInListNums(Student[] students, Student newStudent, int studentCount) {
        for (int l = 0; l < studentCount; l++) {
            if (students[l] == null) {
                continue;
            }

            if ((students[l].groupNumber == newStudent.groupNumber) && (students[l].listNumber == newStudent.listNumber)) {
                return true;
            }
        }
        return false;
    }




    public static void showExcellentStudents(){
        System.out.println("\nСтуденты, учащиеся только на 5:");
        for (int i = 0; i < MAX_STUDENTS; i++){
            if (students[i] == null) {
                continue;
            }
            int sum = 0;
            for (int grade : students[i].grades){
                sum+=grade;
            }
            if (sum/8 == 5){
                System.out.println("Студент " + (i+1) + ": " + students[i].name + ", Пол: " + students[i].gender + ", Номер группы: " + students[i].groupNumber + ", Номер в списке: " + students[i].listNumber
                        + ", Оценки за сессию: " + Arrays.toString(students[i].grades));
            }
        }
    }

    public static void showC_Students(){
        System.out.println("\nСтуденты, не получающие стипендию:");
        for (int i = 0; i < MAX_STUDENTS; i++){
            if (students[i] == null) {
                continue;
            }
            for (int grade : students[i].grades){
                if (grade == 3 || grade == 2){
                    System.out.println("Студент " + (i+1) + ": " + students[i].name + ", Пол: " + students[i].gender + ", Номер группы: " + students[i].groupNumber + ", Номер в списке: " + students[i].listNumber
                            + ", Оценки за сессию: " + Arrays.toString(students[i].grades));
                    break;
                }
            }

        }
    }

    public static void showHonorStudents(){
        System.out.println("\nСтуденты, учащиеся на 4 и 5:");
        for (int i = 0; i < MAX_STUDENTS; i++){
            int sum = 0;
            int cnt = 0;
            if (students[i] == null) {
                continue;
            }
            for (int grade : students[i].grades){
                if (grade == 4 | grade == 5){
                    sum+=grade;
                    cnt+=1;
                }
            }
            if (sum/8 != 5 && cnt == 8){
                System.out.println("Студент " + (i+1) + ": " + students[i].name + ", Пол: " + students[i].gender + ", Номер группы: " + students[i].groupNumber + ", Номер в списке: " + students[i].listNumber
                        + ", Оценки за сессию: " + Arrays.toString(students[i].grades));
            }

        }
    }


}
public class lab1 {
    public static void main() {
        Scanner scanner = new Scanner(System.in);
        int number;
        while (true) {
            System.out.println("""
                              Меню
                    1 - Создать новую запись о студенте
                    2 - Внести изменения в уже имеющуюся запись
                    3 - Вывести все данные о студентах
                    4 - Вывести информацию обо всех студентах определенной группы
                    5 - Вывести топ самых успешных студентов с наивысшим по рейтингу средним баллом за прошедшую сессию
                    6 - Вывести количество студентов мужского и женского пола
                    7 - Вывести данные о студентах, которые не получают стипендию; учатся только на «хорошо» и «отлично»; учатся только на «отлично»
                    8 - Вывести данные о студентах, имеющих определенный номер в списке
                    ---------------------------------------------------------------------------------------------------------------------------------
                              ИДЗ
                    ---------------------------------------------------------------------------------------------------------------------------------
                    9 - Создать новую запись об абитуриенте
                    10 - Вывести информацию об абитуриентах младше 18 лет
                    11 - Вывести информацию об абитуриентах, чей средний балл ЕГЭ больше 85
                    12 - Вывести информацию об иногородних абитуриентах
                    13 - Вывести информацию об абитуриентах с аттестатом с отличием
                    0 - Выйти из программы
                    """);
            number = scanner.nextInt();
            if (number == 0) {
                break;
            }
            switch (number) {
                case 1:
                    if (StudentArray.studentCount >= MAX_STUDENTS) {
                        System.out.println("Достигнуто максимальное количество студентов.");
                        break;
                    }

                    System.out.print("Введите количество студентов: ");
                    int size = scanner.nextInt();

                    // Добавьте проверку, чтобы убедиться, что не превышено максимальное количество студентов
                    if (StudentArray.studentCount + size > MAX_STUDENTS) {
                        System.out.println("Нельзя добавить столько студентов, так как превышено максимальное количество.");
                        break;
                    }


                    for (int i = 0; i < size; i++) {
                        Student newStudent;
                        boolean hasSameNumbers;

                        do {
                            newStudent = StudentArray.createNewStudentFromInput();

                            // Проверяем на совпадение номеров с уже существующими студентами
                            hasSameNumbers = StudentArray.checkForDiffInListNums(StudentArray.students, newStudent, StudentArray.studentCount);

                            if (hasSameNumbers) {
                                System.out.println("Найдено совпадение номеров. Введите данные заново.");
                            }
                        } while (hasSameNumbers);

                        StudentArray.students[StudentArray.studentCount] = newStudent;
                        StudentArray.studentCount++;
                    }


                    System.out.println("Новые студенты успешно добавлены.");
                    break;

                case 2:
                    System.out.print("Введите ID студента, информацию которого нужно изменить(ID - номер группы + номер в списке без пробела, например 337222: ");
                    scanner.nextLine();
                    String studentId = scanner.nextLine();

                    boolean found = false;
                    for (Student student : StudentArray.students) {
                        if (student == null) {
                            continue;
                        }
                        String ID = String.valueOf(student.groupNumber) + student.listNumber;
                        if (studentId.equals(ID)) {
                            StudentArray.updateStudentInformation(student, ID);
                            found = true;
                            break;
                        }
                    }

                    if (!found) {
                        System.out.println("Студент с указанным ID не найден.");
                    }

                    break;
                case 3:
                    StudentArray.showAllStudents();
                    System.out.println("\n");
                    break;
                case 4:
                    StudentArray.showStudentsByGroupNumberFromInput();
                    System.out.println("\n");
                    break;
                case 5:
                    StudentArray.showTopRatedStudents();
                    System.out.println("\n");
                    break;
                case 6:
                    StudentArray.showMaleFemaleCnt();
                    System.out.println("\n");
                    break;
                case 7:
                    StudentArray.showExcellentStudents();
                    StudentArray.showHonorStudents();
                    StudentArray.showC_Students();
                    System.out.println("\n");
                    break;
                case 8:
                    StudentArray.showKNumberedStudents();
                    System.out.println("\n");
                    break;
                case 9:

                    if (ApplicantArray.applicantCount >= MAX_APPLICANTS) {
                        System.out.println("Достигнуто максимальное количество абитуриентов.");
                        break;
                    }

                    System.out.print("Введите количество абитуриентов: ");
                    int applicantSize = scanner.nextInt();

                    // Добавьте проверку, чтобы убедиться, что не превышено максимальное количество студентов
                    if (StudentArray.studentCount + applicantSize > MAX_APPLICANTS) {
                        System.out.println("Нельзя добавить столько абитуриентов, так как превышено максимальное количество.");
                        break;
                    }
                    for (int i = 0; i < applicantSize; i++) {
                        Applicant newApplicant = ApplicantArray.createNewApplicantFromInput();


                        ApplicantArray.applicants[ApplicantArray.applicantCount] = newApplicant;
                        ApplicantArray.applicantCount++;
                    }


                    System.out.println("Новые абитуриенты успешно добавлены.");
                    System.out.println("\n");
                    break;
                case 10:
                    ApplicantArray.lessThan18();
                    System.out.println("\n");
                    break;
                case 11:
                    ApplicantArray.averageExamGrade();
                    System.out.println("\n");
                    break;
                case 12:
                    ApplicantArray.notSPB();
                    System.out.println("\n");
                    break;
                case 13:
                    ApplicantArray.goldMedal();
                    System.out.println("\n");
                    break;
            }

        }
    }
}
