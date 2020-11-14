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

public class ProgramManager {

    //////////////////////////////////////////////// Teachers - Start //////////////////////////////////////////////////
    public Teacher createTeacher(Scanner input) {
        String name = "";
        boolean isValidName = false;
        String sex = "";
        boolean isValidSex = false;
        String subject = "";
        boolean isValidSubject = false;
        double salary = 0;
        boolean isValidSalary = false;
        // Name
        do {
            try {
                System.out.print("Please enter the teacher's name: ");
                String line = input.nextLine().trim();
                name = normalizeText(Validator.validateName(line));
                isValidName = true;
            } catch (IllegalArgumentException iae) {
                System.out.printf("%n%s%n%n", iae.getMessage());
            }
        } while (!isValidName);
        // Sex
        do {
            try {
                System.out.print("Please enter the teacher's sex: ");
                String line = input.nextLine().trim();
                sex = normalizeText(Validator.validatePersonSex(line));
                isValidSex = true;
            } catch (IllegalArgumentException iae) {
                System.out.printf("%n%s%n%n", iae.getMessage());
            }
        } while (!isValidSex);
        // Subject
        do {
            try {
                System.out.print("Please enter the teacher's subject name: ");
                String line = input.nextLine().trim();
                subject = normalizeText(Validator.validateName(line));
                isValidSubject = true;
            } catch (IllegalArgumentException iae) {
                System.out.printf("%n%s%n%n", iae.getMessage());
            }
        } while (!isValidSubject);
        // Salary
        do {
            try {
                System.out.print("Please enter the teacher's salary: ");
                String line = input.nextLine().trim();
                salary = Validator.validateMoneyAmount(line);
                isValidSalary = true;
            } catch (NumberFormatException nfe) {
                System.out.println("\nOops! Only digits are allowed.\n");
            } catch (IllegalArgumentException iae) {
                System.out.printf("%n%s%n%n", iae.getMessage());
            }
        } while (!isValidSalary);

        Teacher teacher = new Teacher(name, sex, subject, salary);
        System.out.println("\nTeacher added successfully!\n");
        return teacher;
    }

    public void viewTeachers(ArrayList<Teacher> teachers) {
        final int TABLE_WIDTH = 123;
        System.out.println();
        // Table Header
        printTableRowSeparator(TABLE_WIDTH);
        System.out.printf("%n| %-2s | %-6s | %-40s | %-6s | %-40s | %-10s |%n",
                "#",
                "ID",
                "Name",
                "Sex",
                "Subject",
                "Salary");
        printTableRowSeparator(TABLE_WIDTH);
        // Table Content
        for (int index = 0; index < teachers.size(); index++) {
            System.out.printf("%n| %-2s | %-6s | %-40s | %-6s | %-40s | $%-9.2f |%n",
                    index + 1,
                    teachers.get(index).getId(),
                    teachers.get(index).getName(),
                    teachers.get(index).getSex(),
                    teachers.get(index).getSubject(),
                    teachers.get(index).getSalary());
            printTableRowSeparator(TABLE_WIDTH);
        }
        System.out.println();

    }

    public void removeTeacher(ArrayList<Teacher> teachers, Scanner input) {
        Teacher teacherToRemove;
        boolean isValidID = false;
        do {
            try {
                System.out.print("Please enter the teacher's id: ");
                String line = input.nextLine().trim();
                teacherToRemove = Validator.validateTeacherID(line, teachers);
                teachers.remove(teacherToRemove);
                System.out.println("Teacher removed successfully!");
                isValidID = true;
            } catch (IllegalArgumentException iae) {
                System.out.printf("%n%s%n%n", iae.getMessage());
            }
        } while (!isValidID);
    }

    //////////////////////////////////////////////// Teachers - End ////////////////////////////////////////////////////

    //////////////////////////////////////////////// Students - Start //////////////////////////////////////////////////

    public Student createStudent(Scanner input) {
        String name = "";
        boolean isValidName = false;
        String sex = "";
        boolean isValidSex = false;
        double fees = 0;
        boolean isValidFees = false;
        boolean isFeesPaid = false;
        boolean isValidFeesPaid = false;
        // Name
        do {
            try {
                System.out.print("Please enter the student's name: ");
                String line = input.nextLine().trim();
                name = normalizeText(Validator.validateName(line));
                isValidName = true;
            } catch (IllegalArgumentException iae) {
                System.out.printf("%n%s%n%n", iae.getMessage());
            }
        } while (!isValidName);
        // Sex
        do {
            try {
                System.out.print("Please enter the student's sex: ");
                String line = input.nextLine().trim();
                sex = normalizeText(Validator.validatePersonSex(line));
                isValidSex = true;
            } catch (IllegalArgumentException iae) {
                System.out.printf("%n%s%n%n", iae.getMessage());
            }
        } while (!isValidSex);
        // Fees
        do {
            try {
                System.out.print("Please enter the student's fees: ");
                String line = input.nextLine().trim();
                fees = Validator.validateMoneyAmount(line);
                isValidFees = true;
            } catch (NumberFormatException nfe) {
                System.out.println("\nOops! Only digits are allowed.\n");
            } catch (IllegalArgumentException iae) {
                System.out.printf("%n%s%n%n", iae.getMessage());
            }
        } while (!isValidFees);
        // Fees Paid?
        do {
            try {
                System.out.print("Did the student pay the fees? (y/n): ");
                String line = input.nextLine().trim();
                isFeesPaid = Validator.validateStudentIsFeesPaid(line);
                isValidFeesPaid = true;
            } catch (IllegalArgumentException iae) {
                System.out.printf("%n%s%n%n", iae.getMessage());
            }
        } while (!isValidFeesPaid);

        Student student = new Student(name, sex, fees, isFeesPaid);
        System.out.println("\nStudent added successfully!\n");

        return student;
    }

    public void viewStudents(ArrayList<Student> students) {
        final int TABLE_WIDTH = 92;
        System.out.println();
        // Table Header
        printTableRowSeparator(TABLE_WIDTH);
        System.out.printf("%n %-2s | %-6s | %-40s | %-6s | %-10s | %-9s |%n",
                "#",
                "ID",
                "Name",
                "Sex",
                "Fees",
                "Fees Paid");
        printTableRowSeparator(TABLE_WIDTH);
        // Table Content
        for (int index = 0; index < students.size(); index++) {
            System.out.printf("%n %-2s | %-6s | %-40s | %-6s | $%-9.2f | %9s |%n",
                    index + 1,
                    students.get(index).getId(),
                    students.get(index).getName(),
                    students.get(index).getSex(),
                    students.get(index).getFees(),
                    students.get(index).isFeesPaid());
            printTableRowSeparator(TABLE_WIDTH);
        }
        System.out.println();

    }

    public void removeStudent(ArrayList<Student> students, Scanner input) {
        Student studentToRemove;
        boolean isValidID = false;
        do {
            try {
                System.out.print("Please enter the student's id: ");
                String line = input.nextLine().trim();
                studentToRemove = Validator.validateStudentID(line, students);
                students.remove(studentToRemove);
                System.out.println("Student removed successfully!");
                isValidID = true;
            } catch (IllegalArgumentException iae) {
                System.out.printf("%n%s%n%n", iae.getMessage());
            }
        } while (!isValidID);
    }

    //////////////////////////////////////////////// Students - End ////////////////////////////////////////////////////

    //////////////////////////////////////////////// Finances - Start //////////////////////////////////////////////////

    public void createExpense(Scanner input, ArrayList<Expense> expenses) {

        String expenseName = "";
        boolean isValidExpenseName = false;
        double expenseAmount = 0.0;
        boolean isValidExpenseAmount = false;
        // Expense Name
        do {
            try {
                System.out.print("Please enter the expense name: ");
                String line = input.nextLine().trim();
                expenseName = Validator.validateNewExpenseName(normalizeText(Validator.validateName(line)), expenses);
                isValidExpenseName = true;
            } catch (IllegalArgumentException iae) {
                System.out.printf("%n%s%n%n", iae.getMessage());
            }
        } while (!isValidExpenseName);

        do {
            try {
                System.out.print("Please enter the expense amount: ");
                String line = input.nextLine().trim();
                expenseAmount = Validator.validateMoneyAmount(line);
                isValidExpenseAmount = true;
            } catch (NumberFormatException nfe) {
                System.out.println("\nOops! Only digits are allowed.\n");
            } catch (IllegalArgumentException iae) {
                System.out.printf("%n%s%n%n", iae.getMessage());
            }
        } while (!isValidExpenseAmount);

        Expense expenseItem = new Expense(expenseName, expenseAmount);
        expenses.add(expenseItem);

        System.out.println("Expense added successfully!");

    }

    public void showFinancialStatement(ArrayList<Student> students, ArrayList<Teacher> teachers, ArrayList<Expense> expenses) {
        final int TABLE_WIDTH = 54;

        double totalIncome = 0;
        double receivedIncome = 0;
        double pendingIncome = 0;
        double totalSalaries = 0;
        double totalExpenses = 0;
        double netProfit;

        // Calculate Income
        for (Student currentStudent : students) {
            totalIncome += currentStudent.getFees();
            if (currentStudent.isFeesPaid()) {
                receivedIncome += currentStudent.getFees();
            } else {
                pendingIncome += currentStudent.getFees();
            }
        }

        // Calculate

        for (Teacher currentTeacher : teachers) { // Salaries
            totalExpenses += currentTeacher.getSalary();
            totalSalaries += currentTeacher.getSalary();
        }

        for (Expense currentExpense : expenses) { // Secondary Expenses
            totalExpenses += currentExpense.getExpenseAmount();
        }

        netProfit = totalIncome - totalExpenses;

        System.out.println();
        printTableRowSeparator(TABLE_WIDTH);

        System.out.printf("%n %-50s%n",
                "Income");

        printTableRowSeparator(TABLE_WIDTH);

        System.out.printf("%n %-20s $%-25.2f%n",
                "Total Received Income:",
                receivedIncome);

        System.out.printf("%n %-20s $%-26.2f%n",
                "Total Pending Income:",
                pendingIncome);

        System.out.printf("%n %-13s $%-22.2f%n",
                "Total Income:",
                totalIncome);

        printTableRowSeparator(TABLE_WIDTH);

        System.out.printf("%n %-50s %n",
                "Expenses");

        printTableRowSeparator(TABLE_WIDTH);

        System.out.printf("%n %-17s $%-24.2f%n",
                "Teacher Salaries:",
                totalSalaries);

        for (Expense expense : expenses) {
            System.out.printf("%n %-2s: $%-24.2f%n",
                    expense.getExpenseName(),
                    expense.getExpenseAmount());
        }

        System.out.printf("%n %-10s $%-37.2f%n",
                "Total Expenses:",
                totalExpenses);

        printTableRowSeparator(TABLE_WIDTH);

        boolean isMakingProfit = netProfit > 0;
        String financialState;

        if (isMakingProfit) {
            financialState = "Net Profit:";
        } else {
            financialState = "Net Loss:";
        }

        System.out.printf("%n %-10s $%-37.2f%n",
                financialState,
                netProfit);

        printTableRowSeparator(TABLE_WIDTH);

        System.out.println();
    }

    public void removeExpense(Scanner input, ArrayList<Expense> expenses) {
        Expense expenseNameToRemove = null;
        boolean isValidExpenseName = false;
        do {
            try {
                System.out.print("Please enter the name of the expense to remove: ");
                String line = input.nextLine().trim();
                expenseNameToRemove = Validator.doesExpenseExist(line, expenses);
                isValidExpenseName = true;
            } catch (IllegalArgumentException iae) {
                System.out.printf("%n%s%n%n", iae.getMessage());
            }
        } while (!isValidExpenseName);

        if (expenseNameToRemove != null) {
            expenses.remove(expenseNameToRemove);
            System.out.println("Expense removed successfully!");
        }
    }

    //////////////////////////////////////////////// Finances - End ////////////////////////////////////////////////////


    //////////////////////////////////////////////// Normalizers - Start ///////////////////////////////////////////////

    private String normalizeText(String text) {
        // normalize text to UpperCamelCase
        char[] textLetters = text.toCharArray();
        textLetters[0] = Character.toUpperCase(textLetters[0]);
        for (int index = 1; index < textLetters.length; index++) {
            if (textLetters[index] == ' ') {
                textLetters[index + 1] = Character.toUpperCase(textLetters[index + 1]);
                ++index;
            } else {
                textLetters[index] = Character.toLowerCase(textLetters[index]);
            }
        }
        return String.valueOf(textLetters);
    }

    //////////////////////////////////////////////// Normalizers - End /////////////////////////////////////////////////

    //////////////////////////////////////////////// Methods - Start ///////////////////////////////////////////////////

    private void printTableRowSeparator(int width) {
        for (int i = 0; i < width; i++) {
            System.out.print("~");
        }
    }

}
