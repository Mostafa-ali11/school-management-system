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

// TODO: 11/10/2020 Add the ability to read and write to files to save data permanently.
public class School {
    public static void main(String[] args) {

        String studentsFileName;
        String teachersFileName;
        String financesFileName;

        if (args.length != 3) {
            System.out.println("Usage: java School <studentsFileName> <teachersFileName> <financesFileName>");
            return;
        } else {
            studentsFileName = args[0];
            teachersFileName = args[1];
            financesFileName = args[2];
        }

        FilesManager filesManager = new FilesManager(studentsFileName, teachersFileName, financesFileName);
        Prompter prompter = new Prompter(filesManager);
        prompter.startProgram();
    }
}
