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

public class ParseDataFiles {
    private FilesManager filesManager;


    public ParseDataFiles(FilesManager filesManager) {
        this.filesManager = filesManager;
    }

    public Student readNextStudent() {
        String studentData = filesManager.readNextLineFromStudentsFile();
        if (studentData != null) {
            int nameEndIndex = studentData.indexOf('|');
            int sexEndIndex = studentData.indexOf('/');
            int feesEndIndex = studentData.indexOf('-');

            String studentName = studentData.substring(0, nameEndIndex).trim();
            String studentSex = studentData.substring(nameEndIndex + 1, sexEndIndex).trim();
            double studentFees = Double.parseDouble(studentData.substring(sexEndIndex + 1, feesEndIndex).trim());
            boolean isFeesPaid = Boolean.parseBoolean(studentData.substring(feesEndIndex + 1).trim());

            return new Student(studentName, studentSex, studentFees, isFeesPaid);
        } else {
            return null;
        }
    }

    public Teacher readNextTeacher() {
        String teacherData = filesManager.readNextLineFromTeachersFile();
        if (teacherData != null) {
            int nameEndIndex = teacherData.indexOf('|');
            int sexEndIndex = teacherData.indexOf('/');
            int subjectEndIndex = teacherData.indexOf('-');

            String teacherName = teacherData.substring(0, nameEndIndex).trim();
            String teacherSex = teacherData.substring(nameEndIndex + 1, sexEndIndex).trim();
            String teacherSubject = teacherData.substring(sexEndIndex + 1, subjectEndIndex).trim();
            double teacherSalary = Double.parseDouble(teacherData.substring(subjectEndIndex + 1).trim());

            return new Teacher(teacherName, teacherSex, teacherSubject, teacherSalary);
        } else {
            return null;
        }
    }

    public Expense readNextExpense() {
        String expenseData = filesManager.readNextLineFromFinancesFile();
        if (expenseData != null) {
            int expenseNameEndIndex = expenseData.indexOf('|');
            String expenseName = expenseData.substring(0, expenseNameEndIndex).trim();
            double expenseAmount = Double.parseDouble(expenseData.substring(expenseNameEndIndex + 1).trim());
            return new Expense(expenseName, expenseAmount);
        } else {
            return null;
        }
    }

}
