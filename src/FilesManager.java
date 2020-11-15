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
// TODO: 11/14/2020 Handle the error cases
// TODO: 11/14/2020 Create "data" directory if it doesn't exist

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

public class FilesManager {
    private PrintWriter studentsFileWriter;
    private PrintWriter teachersFileWriter;
    private PrintWriter financesFileWriter;
    private Scanner studentsFileReader;
    private Scanner teachersFileReader;
    private Scanner financesFileReader;
    private File studentsFile;
    private File teachersFile;
    private File financesFile;
    private String teachersFileName;
    private String studentsFileName;
    private String financesFileName;

    public FilesManager(String studentsFileName, String teachersFileName, String financesFileName) {
        this.studentsFileName = studentsFileName;
        this.teachersFileName = teachersFileName;
        this.financesFileName = financesFileName;
        setStudentsFile();
        setTeachersFile();
        setFinancesFile();
    }

    private void setStudentsFile() {
        try {
            studentsFile = new File(studentsFileName);
            studentsFileWriter = new PrintWriter(new FileWriter(studentsFile, true));
            studentsFileReader = new Scanner(studentsFile);
        } catch (Exception ex) {
            System.out.println("Failed to create or open students file!");
            System.out.println(ex.getMessage());
            System.exit(1);
        }
    }

    private void setTeachersFile() {
        try {
            teachersFile = new File(teachersFileName);
            teachersFileWriter = new PrintWriter(new FileWriter(teachersFile, true));
            teachersFileReader = new Scanner(teachersFile);
        } catch (Exception ex) {
            System.out.println("Failed to create or open teachers file!");
            System.out.println(ex.getMessage());
            System.exit(1);
        }
    }

    private void setFinancesFile() {
        try {
            financesFile = new File(financesFileName);
            financesFileWriter = new PrintWriter(new FileWriter(financesFile, true));
            financesFileReader = new Scanner(financesFile);
        } catch (Exception ex) {
            System.out.println("Failed to create or open finances file!");
            System.out.println(ex.getMessage());
            System.exit(1);
        }
    }

    public void writeToStudentsFile(String lineToWrite) {
        studentsFileWriter.println(lineToWrite);

        studentsFileWriter.flush();
    }

    public void writeToTeachersFile(String lineToWrite) {
        teachersFileWriter.println(lineToWrite);

        teachersFileWriter.flush();
    }

    public void writeToFinancesFile(String lineToWrite) {
        financesFileWriter.println(lineToWrite);

        financesFileWriter.flush();
    }

    public String readNextLineFromStudentsFile() {
        if (studentsFileReader.hasNextLine()) {
            return studentsFileReader.nextLine().trim();
        } else {
            return null;
        }
    }

    public String readNextLineFromTeachersFile() {
        if (teachersFileReader.hasNextLine()) {
            return teachersFileReader.nextLine().trim();
        } else {
            return null;
        }
    }

    public String readNextLineFromFinancesFile() {
        if (financesFileReader.hasNextLine()) {
            return financesFileReader.nextLine().trim();
        } else {
            return null;
        }
    }

    public void clearTeachersDataFile() {
        try {
            teachersFileWriter = new PrintWriter(teachersFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void clearStudentsDataFile() {
        try {
            studentsFileWriter = new PrintWriter(studentsFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void clearFinancesDataFile() {
        try {
            financesFileWriter = new PrintWriter(financesFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


}