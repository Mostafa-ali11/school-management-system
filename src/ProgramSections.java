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

public class ProgramSections {

    private final ArrayList<String> mainMenu = new ArrayList<>(4);
    private final ArrayList<String> teachersMenu = new ArrayList<>(4);
    private final ArrayList<String> studentsMenu = new ArrayList<>(4);
    private final ArrayList<String> financesMenu = new ArrayList<>(2);
    private final ArrayList<Teacher> teachers = new ArrayList<>();
    private final ArrayList<Student> students = new ArrayList<>();
    private final ArrayList<Expense> expenses = new ArrayList<>();
    private final ParseDataFiles parseDataFiles;
    private final Scanner menuInput = new Scanner(System.in);
    private final Scanner personInput = new Scanner(System.in);
    private final ProgramManager programManager;
    private final FilesManager filesManager;


    public ProgramSections(FilesManager filesManager) {
        this.filesManager = filesManager;
        programManager = new ProgramManager();
        parseDataFiles = new ParseDataFiles(filesManager);
    }

    public void startProgram() {
        System.out.println("Welcome to the School Management System!");
        setMainMenu();
        setTeachersMenu();
        setStudentsMenu();
        setFinancesMenu();
        populateStudentsArrayList(students);
        populateTeachersArrayList(teachers);
        populateExpensesArrayList(expenses);
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
                saveDataFiles(expenses, students, teachers);
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
                    saveDataFiles(expenses, students, teachers);
                    break;
                case 3:
                    programManager.removeTeacher(teachers, personInput);
                    saveDataFiles(expenses, students, teachers);
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
                    saveDataFiles(expenses, students, teachers);
                    break;
                case 3:
                    programManager.removeStudent(students, personInput);
                    saveDataFiles(expenses, students, teachers);
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
                    saveDataFiles(expenses, students, teachers);
                    break;
                case 3:
                    programManager.removeExpense(personInput, expenses);
                    saveDataFiles(expenses, students, teachers);
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
        mainMenu.add("Save & Exit");
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


    private void populateStudentsArrayList(ArrayList<Student> students) {
        Student nextStudent = parseDataFiles.readNextStudent();
        while (nextStudent != null) {
            students.add(nextStudent);
            nextStudent = parseDataFiles.readNextStudent();
        }
    }

    private void populateTeachersArrayList(ArrayList<Teacher> teachers) {
        Teacher nextTeacher = parseDataFiles.readNextTeacher();
        while (nextTeacher != null) {
            teachers.add(nextTeacher);
            nextTeacher = parseDataFiles.readNextTeacher();
        }
    }

    private void populateExpensesArrayList(ArrayList<Expense> expenses) {
        Expense nextExpense = parseDataFiles.readNextExpense();
        while (nextExpense != null) {
            expenses.add(nextExpense);
            nextExpense = parseDataFiles.readNextExpense();
        }
    }

    private void saveDataFiles(ArrayList<Expense> expenses, ArrayList<Student> students, ArrayList<Teacher> teachers) {
        saveFinancesDataFile(expenses);
        saveStudentsDataFile(students);
        saveTeachersDataFile(teachers);
    }

    private void saveTeachersDataFile(ArrayList<Teacher> teachers) {
        filesManager.clearTeachersDataFile();
        for (Teacher currentTeacher : teachers) {
            String teacherData = "";
            teacherData += currentTeacher.getName() + " |";
            teacherData += " " + currentTeacher.getSex() + " /";
            teacherData += " " + currentTeacher.getSubject() + " -";
            teacherData += " " + currentTeacher.getSalary();
            filesManager.writeToTeachersFile(teacherData);
        }
    }

    private void saveStudentsDataFile(ArrayList<Student> students) {
        filesManager.clearStudentsDataFile();
        for (Student currentStudent : students) {
            String studentData = "";
            studentData += currentStudent.getName() + " |";
            studentData += " " + currentStudent.getSex() + " /";
            studentData += " " + currentStudent.getFees() + " -";
            studentData += " " + currentStudent.isFeesPaid();
            filesManager.writeToStudentsFile(studentData);
        }
    }

    private void saveFinancesDataFile(ArrayList<Expense> expenses) {
        filesManager.clearFinancesDataFile();
        for (Expense currentStudent : expenses) {
            String expenseData = "";
            expenseData += currentStudent.getExpenseName() + " |";
            expenseData += " " + currentStudent.getExpenseAmount();
            filesManager.writeToFinancesFile(expenseData);
        }
    }

}
