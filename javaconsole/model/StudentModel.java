/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaconsole.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javaconsole.entity.Student;

/**
 *
 * @author daolinh
 */
public class StudentModel {

    private static ArrayList<Student> listStudent;

    public ArrayList<Student> getList() {
        return listStudent;
    }

    public void insert(Student student) {
        try {
            Connection cnn = DAO.getConnection();
            Statement stt = cnn.createStatement();
            String sqlQuerry = "INSERT INTO "
                    + "student "
                    + "(name, email, roll_number, class_name, status) "
                    + "VALUE "
                    + "('"
                    + student.getName() + "' , '"
                    + student.getEmail() + "' , '"
                    + student.getRollNumber() + "' , '"
                    + student.getClassName() + "' , "
                    + student.getStatus()
                    + ")";
            System.out.println("Thuc thi lenh SQL:" + sqlQuerry);
            stt.execute(sqlQuerry);
            System.out.println("Thanh cong");
        } catch (SQLException e) {
            System.err.println("ERROR" + e.getMessage());
        }
    }

    public ArrayList<Student> getlistStudent() {
        ArrayList<Student> listStudent = new ArrayList<>();
        try {
            Statement stt = DAO.getConnection().createStatement();
            ResultSet rs = stt.executeQuery("SELECT * FROM student");
            while (rs.next()) {
                Student student = new Student();
                student.setId(rs.getInt("id"));
                student.setName(rs.getString("name"));
                student.setEmail(rs.getString("email"));
                student.setRollNumber(rs.getString("roll_number"));
                student.setClassName(rs.getString("class_name"));
                student.setStatus(rs.getInt("status"));
                listStudent.add(student);

            }
        } catch (SQLException ex) {
            System.err.println("Loi" + ex.getMessage());
        }
        return listStudent;
    }
//

    public static void main(String[] args) {
        StudentModel studentModel = new StudentModel();
      
       
    }

//        System.out.println("======Danh sách sinh viên======");
//        for (Student student1 : listStudent) {
//            System.out.println(student1.getName());
//        }
//        System.out.println("==============================");
    public void update(Student student) {

        try {

            String sqlQuery = "update student set name=?, email=?, roll_number=?, class_name=?, status=? where id=?";
            PreparedStatement stm = DAO.getConnection().prepareStatement(sqlQuery);
            stm.setString(1, student.getName());
            stm.setString(2, student.getEmail());
            stm.setString(3, student.getRollNumber());
            stm.setString(4, student.getClassName());
            stm.setInt(5, student.getStatus());
            stm.setInt(6, student.getId());
        
            int rowsUpdated = stm.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("Loi" + ex.getMessage());
        }

    }

    public void delete(int x) {
        try {
            String sqlQuery = "update student set status=? where id=?";
            PreparedStatement stm = DAO.getConnection().prepareStatement(sqlQuery);
            stm.setInt(1, 0);
            stm.setInt(2, x);

            int rowsUpdated = stm.executeUpdate();
            System.out.println("Thuc thi lenh SQL:" + sqlQuery);
        } catch (SQLException ex) {
            System.err.println("Loi" + ex.getMessage());
        }

    }

}
