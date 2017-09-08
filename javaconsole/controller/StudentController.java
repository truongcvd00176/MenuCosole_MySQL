/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaconsole.controller;

import java.util.ArrayList;
import java.util.Scanner;
import javaconsole.entity.Student;
import javaconsole.model.StudentModel;

/**
 *
 * @author daolinh
 */
public class StudentController {
    // 1. getList.
    // 2. add.
    // 3. edit.
    // 4. delete.

    private StudentModel studentModel = new StudentModel();

    // Hàm này sẽ phải trả về danh sách sinh viên.
    public void getList() {
        System.out.println("======== Student list ========");
        studentModel.getlistStudent();
        ArrayList<Student> listStudent = studentModel.getlistStudent();
        for (Student student : listStudent) {
            if (student.getStatus() == 1) {
                System.out.println("» ID: " + student.getId()
                        + "\n • Name: " + student.getName()
                        + "\n • Email: " + student.getEmail()
                        + "\n • Roll Number: " + student.getRollNumber()
                        + "\n • Class Name: " + student.getClassName());
            }
        }

    }

    // Thêm mới sinh viên, thực hiện việc lấy dữ liệu từ người dùng,
    // validate dữ liệu đó, tiến hành lưu thông tin.
    public void addStudent() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter student information.");
        System.out.println("Please enter name: ");
        String name = scanner.nextLine();
        System.out.println("Please enter email: ");
        String email = scanner.nextLine();
        System.out.println("Please enter roll number");
        String rollNumber = scanner.nextLine();
        System.out.println("Please enter class name");
        String className = scanner.nextLine();

        // Validate dữ liệu ở đây.
        Student student = new Student();
        student.setName(name);
        student.setEmail(email);
        student.setRollNumber(rollNumber);
        student.setClassName(className);
        student.setStatus(1);
        // Lưu thông tin sinh viên vào db.
        studentModel.insert(student);
    }

    public void editStudent() {
        while (true) {
            System.out.println("What Records Do You Want To Edit?");
            System.out.print("ID = ");
            Scanner seach = new Scanner(System.in);

            String strSeach = seach.nextLine();
            // Kiểm tra dữ liệu người dùng nhập vào có là số hay không ?
            // Trong trường hợp không phải là số thì thông báo cho người dùng và bắt đầu lại vòng lặp.
            int x = 0;
            try {
                x = Integer.parseInt(strSeach);
            } catch (java.lang.NumberFormatException e) {
                System.err.println("Please enter a number.");
                continue;
            }

            Scanner scanner = new Scanner(System.in);
            System.out.println("Please enter student information.");
            System.out.println("Please enter name: ");
            String name = scanner.nextLine();
            System.out.println("Please enter email: ");
            String email = scanner.nextLine();
            System.out.println("Please enter roll number");
            String rollNumber = scanner.nextLine();
            System.out.println("Please enter class name");
            String className = scanner.nextLine();
            System.out.println("Please enter status(1 or 0)");
            Scanner status = new Scanner(System.in);
            String strStatus = scanner.nextLine();
            int y = 0;
            try {
                y = Integer.parseInt(strStatus);
                Student student = new Student();
                student.setId(x);
                student.setName(name);
                student.setEmail(email);
                student.setRollNumber(rollNumber);
                student.setClassName(className);
                student.setStatus(y);
                studentModel.update(student);
                break;
            } catch (java.lang.NumberFormatException e) {
                System.err.println("Please enter a number for status");
                continue;
            }

        }
    }

    public void deleteStudent() {
        while (true) {
            System.out.println("What Records Do You Want To Delete?");
            System.out.print("Id = ");
            Scanner seach = new Scanner(System.in);

            String strSeach = seach.nextLine();
            // Kiểm tra dữ liệu người dùng nhập vào có là số hay không ?
            // Trong trường hợp không phải là số thì thông báo cho người dùng và bắt đầu lại vòng lặp.
            int x = 0;
            try {
                x = Integer.parseInt(strSeach);
            } catch (java.lang.NumberFormatException e) {
                System.err.println("Please enter a number.");
                continue;
            }

            studentModel.delete(x);
            break;
        }
    }
}
