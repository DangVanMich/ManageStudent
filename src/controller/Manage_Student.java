
package controller;
import common.Report;
import common.CourseOfStudent;
import common.Student;
import java.util.ArrayList;
import java.util.Collections;
import view.Menu;
import view.Validation;

/**
 *
 * @author admin
 */
public class Manage_Student extends Menu<String> {

    static String[] mc = {"Create", "Find and Sort", "Update and Delete", "Report", "Exit"};
    Validation va;
    ArrayList<Student> list_s;
    ArrayList<CourseOfStudent> list_cs;
    Student s;

    public Manage_Student() {
        super("WELCOME TO STUDENT MANAGEMENT", mc);
        va  = new Validation();
        list_s = new ArrayList<>();
        list_cs = new ArrayList<>();
        s = new Student();
    }

    @Override
    public void execute(int choice) {
        switch (choice) {
            case 1:
                CreateStudent();
                break;
            case 2:
                FindSort();
                break;
            case 3:
                UpdateDelete();
                break;
            case 4:
                Report();
                break;
            case 5:
                System.exit(0);
        }
    }
    
    public void CreateStudent(){
            
    }
    
    public void FindSort(){
        
    }
    
    public void UpdateDelete(){
        
    }
    
    public void Report(){
        
    }
   
    public void report() {
        if (list_s == null) {
            System.err.println("List empty");
            return;
        }
        ArrayList<Report> list_Rp = new ArrayList<>();
        for (CourseOfStudent cs : list_cs) {
            int total = 0;
            String id;
            id = cs.getId();
            String courseName = cs.getCourseName();
            if (checkReport(list_Rp, id, courseName, total)) {
                list_Rp.add(new Report(id, courseName, total+1));
            } else {
                for (Report r : list_Rp) {
                    if (cs.getId() == r.getId() && r.getCourseName().equalsIgnoreCase(courseName)) {
                        total++;
                        r.setId(id);
                        r.setCourseName(courseName);
                        r.setTotalCourse(total+1);
                    }

                }
            }
        }
        for (int i = 0; i < list_Rp.size(); i++) {
            System.out.println("Id:" + list_Rp.get(i).getId() + "  - Course: " + list_Rp.get(i).getCourseName() + " - Total: " + list_Rp.get(i).getTotalCourse());
        }
    }

     public boolean checkReport(ArrayList<Report> list_Rp, String id, String courseName, int total) {
        for (Report rp : list_Rp) {
            if (id.equalsIgnoreCase(rp.getId()) && courseName.equalsIgnoreCase(rp.getCourseName()) && total == rp.getTotalCourse()) {
                return false;
            }
        }
        return true;
    }
    
     public void createStudent() {
        String name;
        System.out.println("Enter ID");
        String id= Validation.checkInputString();
        if (!checkID(list_s, id)) {
            name = va.getInputString("Enter Student name: ");
            list_s.add(new Student(id, name));
        }
        int semester = va.getInt("Enter semester", 1, 10);
        String courseName = va.getInputString("Enter courseName: ");
        list_cs.add(new CourseOfStudent(id, semester, courseName));
    }

    public void displayStudent(ArrayList<Student> list_s) {
        for (Student s : list_s) {
            System.out.println("Id: " + s.getId() + " - Name: " + s.getStudentName());
            for (CourseOfStudent cs : list_cs) {
                if (s.getId() == cs.getId()) {
                    System.out.println("Semester: " + cs.getSemester() + " - courseName: " + cs.getCourseName());
                }
            }
        }
    }
    
    public void findSort() {
        if (list_s == null) {
            System.err.println("List empty");
            return;
        }
        ArrayList<Student> list_ByName = listByName(list_s);
        if (list_ByName.isEmpty()) {
            System.err.println("Not exist");
        } else {
            Collections.sort(list_ByName);
            displayStudent(list_ByName);
        }
    }

    public ArrayList<Student> listByName(ArrayList<Student> list_s) {
        ArrayList<Student> list_Found = new ArrayList<Student>();
        String name = va.getInputString("Enter name to search: ");
        for (Student s : list_s) {
            if (s.getStudentName().contains(name)) {
                list_Found.add(s);
            }
        }
        return list_Found;
    }

    public void update_Delete() {
        if (list_s == null) {
            System.err.println("List empty");
            return;
        }
        int id = va.getInt("Enter id to search", 1, 1000);
        ArrayList<Student> list_ById = listById(list_s, id);
        ArrayList<CourseOfStudent> list_ById_cs = listByIdCS(list_cs, id);
        if (list_ById.isEmpty() || list_ById_cs.isEmpty()) {
            System.err.println("Not exist");
        } else {
            System.out.println("Do you want to update or delete?");
            System.out.println("1. Update");
            System.out.println("2. Delete");
            Student s = list_ById.get(0);
            CourseOfStudent cs = list_ById_cs.get(0);
            int c = va.getInt("Enter choice: ", 1, 2);
            switch (c) {
                case 1:
                    //Exec update
                    s.setId();
                    s.setStudentName(va.getInputString("Enter name: "));
                    cs.setId(id);
                    cs.setSemester(va.getInt("Enter Semester: ", 1, 10));
                    cs.setCourseName(va.getInputString("Enter Course name: "));
                    System.out.println("Update Succcess");
                    break;
                case 2:
                    list_cs.remove(cs);
                    list_s.remove(s);
                    System.out.println("Delete success");
                    break;
                default:
                    return;
            }
        }
    }

     
   public ArrayList<CourseOfStudent> listByIdCS(ArrayList<CourseOfStudent> list_s, String id) {
        ArrayList<CourseOfStudent> list_Found = new ArrayList<CourseOfStudent>();

        for (CourseOfStudent s : list_s) {
            if (s.getId().equalsIgnoreCase(id)) {
                list_Found.add(s);
            }
        }
        return list_Found;
    }

    public ArrayList<Student> listById(ArrayList<Student> list_s, String id) {
        ArrayList<Student> list_Found = new ArrayList<Student>();

        for (Student s : list_s) {
            if (s.getId().equalsIgnoreCase(id)) {
                list_Found.add(s);
            }
        }
        return list_Found;
    }

    public Student GetById(String id) {
        for (Student st : list_s) {
            if (st.getId().equalsIgnoreCase(id)) {
                return st;
            }
        }
        return null;
    }

    public boolean checkID(ArrayList<Student> list ,String id){
        if(list.isEmpty()){
            return false;
        }else{
            for(Student s: list){
                if(s.getId().equalsIgnoreCase(id)){
                    return true;
                }
            }
        }
        return false;
    }

}
