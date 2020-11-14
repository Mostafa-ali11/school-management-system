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

public class Validator {

    //////////////////////////////////////////////// Common Validators - Start /////////////////////////////////////////

    public static int validateOptionNumber(String input, ArrayList<String> menu) {
        int optionNumber;
        if (input.isEmpty()) {
            throw new IllegalArgumentException("Oops! Looks like you forgot to enter an option number.");
        } else {
            optionNumber = Integer.parseInt(input);
            if (optionNumber < 1 || optionNumber > menu.size()) {
                throw new IllegalArgumentException("Bummer! That doesn't look like a valid option number.");
            }
        }
        return optionNumber;
    }


    public static String validateName(String name) {
        if (name.isEmpty()) {
            throw new IllegalArgumentException("Oops! Looks like you forgot to enter the name.");
        } else {
            if (name.length() > 40) {
                throw new IllegalArgumentException("Bummer! The name can not exceed 40 characters.");
            }
        }
        return name;
    }


    public static String validatePersonSex(String sex) {
        if (sex.isEmpty()) {
            throw new IllegalArgumentException("Oops! Looks like you forgot to enter the sex.");
        } else {
            if (!sex.equalsIgnoreCase("male") && !sex.equalsIgnoreCase("female")) {
                throw new IllegalArgumentException("Bummer! That doesn't look like a valid sex.(Male/Female)");
            }
        }
        return sex;
    }

    public static double validateMoneyAmount(String amount) {
        if (amount.isEmpty()) {
            throw new IllegalArgumentException("Oops! Looks like you forgot to enter the amount.");
        } else {
            double moneyAmount = Double.parseDouble(amount);
            if (moneyAmount < 0) {
                throw new IllegalArgumentException("Bummer! That doesn't look like a valid amount." +
                        "(Amount can't be negative)");
            }

        }
        return Double.parseDouble(amount);
    }

    //////////////////////////////////////////////// General Validators - End //////////////////////////////////////////

    //////////////////////////////////////////////// Teacher Validators - Start ////////////////////////////////////////

    public static Teacher validateTeacherID(String id, ArrayList<Teacher> teachers) {
        Teacher teacher = null;
        if (id.isEmpty()) {
            throw new IllegalArgumentException("Oops! Looks like you forgot to enter the teacher's id.");
        } else {
            for (Teacher currentTeacher : teachers) {
                if (currentTeacher.getId().equals(id)) {
                    teacher = currentTeacher;
                    break;
                }
            }
            if (teacher == null) {
                throw new IllegalArgumentException("Bummer! We couldn't find a teacher with the ID: " + id + ".");
            }
        }
        return teacher;
    }

    //////////////////////////////////////////////// Teacher Validators - End //////////////////////////////////////////

    //////////////////////////////////////////////// Student Validators - Start ////////////////////////////////////////

    public static Student validateStudentID(String id, ArrayList<Student> students) {
        Student student = null;
        if (id.isEmpty()) {
            throw new IllegalArgumentException("Oops! Looks like you forgot to enter the student's id.");
        } else {
            for (Student currentStudent : students) {
                if (currentStudent.getId().equals(id)) {
                    student = currentStudent;
                    break;
                }
            }
            if (student == null) {
                throw new IllegalArgumentException("Bummer! We couldn't find a student with the ID: " + id + ".");
            }
        }
        return student;
    }

    public static boolean validateStudentIsFeesPaid(String isFeesPaid) {
        boolean mIsFeesPaid = false;
        if (isFeesPaid.isEmpty()) {
            throw new IllegalArgumentException("Oops! Looks like you forgot to enter whether or not the student paid their fees.");
        } else {
            if (Character.toLowerCase(isFeesPaid.charAt(0)) != 'y' && Character.toLowerCase(isFeesPaid.charAt(0)) != 'n') {
                throw new IllegalArgumentException("Bummer! That doesn't look like a valid choice.(y/n)");
            }
        }

        if (Character.toLowerCase(isFeesPaid.charAt(0)) == 'y') {
            mIsFeesPaid = true;
        }

        return mIsFeesPaid;
    }

    //////////////////////////////////////////////// Student Validators - End //////////////////////////////////////////

    //////////////////////////////////////////////// Finances Validators - Start ///////////////////////////////////////

    public static String validateNewExpenseName(String newExpenseName, ArrayList<Expense> expenses) {
        for (Expense currentExpense : expenses) {
            String currentExpenseName = currentExpense.getExpenseName();
            if (currentExpenseName.equalsIgnoreCase(newExpenseName)) {
                throw new IllegalArgumentException("Bummer! An expense with the same name already exists.");
            }
        }
        return newExpenseName;
    }

    public static Expense doesExpenseExist(String expenseName, ArrayList<Expense> expenses) {
        boolean expenseExist = false;
        Expense foundExpense = null;
        for (Expense currentExpense : expenses) {
            String currentExpenseName = currentExpense.getExpenseName();
            if (currentExpenseName.equalsIgnoreCase(expenseName)) {
                expenseExist = true;
                foundExpense = currentExpense;
                break;
            }
        }
        if (!expenseExist) {
            throw new IllegalArgumentException("Bummer! An expense with that name \"" + expenseName + "\" can not be found.");
        }
        return foundExpense;
    }

}
