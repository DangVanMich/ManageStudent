/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package common;

/**
 *
 * @author admin
 */
public class Student implements Comparable<Student>{
    private String id;
    private String studentName;

    public Student() {
    }

    public Student(String id, String studentName) {
        this.id = id;
        this.studentName = studentName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
    
    @Override
    public int compareTo(Student s){
        return s.studentName.compareTo(this.studentName);
    }
}
