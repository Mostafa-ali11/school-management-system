/*
 * MIT License
 *
 * Copyright (c) 2020 Mostafa Ali
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

import java.util.ArrayList;
import java.util.Scanner;

public class Prompter {

    private final ArrayList<String> mainMenu = new ArrayList<>(4);
    private final ArrayList<String> teachersMenu = new ArrayList<>(4);
    private final ArrayList<String> studentsMenu = new ArrayList<>(4);
    private final ArrayList<String> financesMenu = new ArrayList<>(2);
    private final ArrayList<Teacher> teachers = new ArrayList<>();
    private final ArrayList<Student> students = new ArrayList<>();
    private final ArrayList<Expense> expenses = new ArrayList<>();
    Scanner menuInput = new Scanner(System.in);
    Scanner personInput = new Scanner(System.in);
    ProgramManager programManager;

    public Prompter(FilesManager filesManager) {
        programManager = new ProgramManager(filesManager);
    }

    public void startProgram() {
        System.out.println("Welcome to the School Management System!");
        setMainMenu();
        setTeachersMenu();
        setStudentsMenu();
        setFinancesMenu();
        mainMenu();
    }

    private void mainMenu() {
        printCurrentSection("Main");
        printMenu(mainMenu);
        int optionNumber = menuInput(mainMenu);

        switch (optionNumber) {
            case 1:
                teacherMenu();
                break;
            case 2:
                studentMenu();
                break;
            case 3:
                financesMenu();
                break;
            case 4:
                System.out.println("Goodbye!");
                System.exit(0);
                break;
        }
    }


    private void teacherMenu() {
        int optionNumber;
        boolean leaveSection = false;
        do {
            printCurrentSection("Teachers");
            printMenu(teachersMenu);
            optionNumber = menuInput(teachersMenu);

            switch (optionNumber) {
                case 1:
                    programManager.viewTeachers(teachers);
                    break;
                case 2:
                    programManager.createTeacher(personInput, teachers);
                    break;
                case 3:
                    programManager.removeTeacher(teachers, personInput);
                    break;
                case 4:
                    leaveSection = true;
                    break;
            }
        } while (!leaveSection);
        mainMenu();
    }

    private void studentMenu() {
        int optionNumber;
        boolean leaveSection = false;
        do {
            printCurrentSection("Students");
            printMenu(studentsMenu);
            optionNumber = menuInput(studentsMenu);

            switch (optionNumber) {
                case 1:
                    programManager.viewStudents(students);
                    break;
                case 2:
                    programManager.createStudent(personInput, students);
                    break;
                case 3:
                    programManager.removeStudent(students, personInput);
                case 4:
                    leaveSection = true;
                    break;
            }
        } while (!leaveSection);
        mainMenu();
    }

    private void financesMenu() {
        int optionNumber;
        boolean leaveSection = false;
        do {
            printCurrentSection("Finances");
            printMenu(financesMenu);
            optionNumber = menuInput(financesMenu);

            switch (optionNumber) {
                case 1:
                    programManager.showFinancialStatement(students, teachers, expenses);
                    break;
                case 2:
                    programManager.createExpense(personInput, expenses);
                    break;
                case 3:
                    programManager.removeExpense(personInput, expenses);
                    break;
                case 4:
                    leaveSection = true;
                    break;
            }
        } while (!leaveSection);
        mainMenu();
    }


    private void setMainMenu() {
        mainMenu.add("Teachers");
        mainMenu.add("Students");
        mainMenu.add("Finances");
        mainMenu.add("Exit");
    }

    private void setTeachersMenu() {
        teachersMenu.add("View Teachers");
        teachersMenu.add("Add Teacher");
        teachersMenu.add("Remove Teacher");
        teachersMenu.add("Main Menu");
    }

    private void setStudentsMenu() {
        studentsMenu.add("View Students");
        studentsMenu.add("Add student");
        studentsMenu.add("Remove Student");
        studentsMenu.add("Main Menu");
    }

    private void setFinancesMenu() {
        financesMenu.add("View Financial Statement");
        financesMenu.add("Add Expense");
        financesMenu.add("Remove Expense");
        financesMenu.add("Main Menu");
    }

    private void printMenu(ArrayList<String> menu) {
        for (int index = 0; index < menu.size(); index++) {
            System.out.printf("%d- %s%n",
                    index + 1,
                    menu.get(index));
        }
    }

    private void printCurrentSection(String section) {
        System.out.printf("%n%s Menu:%n%n", section);
    }

    private int menuInput(ArrayList<String> menu) {
        String inputLine;
        int optionNumber = 1;
        boolean isValidOptionNumber = false;
        do {
            try {
                System.out.print("Please enter an option number: ");
                inputLine = menuInput.nextLine().trim();
                optionNumber = Validator.validateOptionNumber(inputLine, menu);
                isValidOptionNumber = true;
            } catch (NumberFormatException nfe) {
                System.out.println("\nOops! Only digits are allowed.\n");
            } catch (IllegalArgumentException iae) {
                System.out.printf("%n%s%n%n", iae.getMessage());
            }
        } while (!isValidOptionNumber);
        return optionNumber;
    }

}
