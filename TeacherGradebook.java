/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teachergradebook;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author Paarthurnax (jacob bitter)
 */
public class TeacherGradebook {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
        boolean running = true;
        Teacher teacher = new Teacher(); 
        ArrayList<Student> students = null;
        ArrayList<Assignment> assignments = new ArrayList<Assignment>();
        
        while(running){
            System.out.println("\n~~GRADEBOOK~~\n\nAre you a teacher or a Student?");
            String input = kb.nextLine();
            
            if(input.contains("teacher")){
                //teacher input
                
                //if nothing has been set up
                if(students == null){
                    System.out.println("Setup:");
                    students = setup(teacher);
                }
                boolean logedIn = true;
                while(logedIn){
                    //prompt user
                    System.out.println("What would you like to do?");
                    String prompt = kb.nextLine();
                    
                    //log out
                    if(prompt.contains("log out")){
                        System.out.println("Are you sure you want to log out?");
                        String confirm = kb.nextLine();
                        if(confirm.contains("yes") || confirm.contains("Yes")){
                            logedIn = false;
                        }
                    }
                    //new student
                    else if(prompt.contains("add student")){
                            Student temp = new Student();
                            temp.setName(teacher.studentName(students.size()+1));
                            students.add(temp);
                            System.out.print("\n\n");
                    }
                    //list students
                    else if(prompt.contains("list students")){
                        System.out.println("You have "+students.size()+" students. Their names are:");
                        for(int i = 0; i < students.size(); i++){
                            Student temp = students.get(i);
                            System.out.println("\t"+temp.getName());
                        }
                        System.out.print("\n\n");
                    }
                    //remove student
                    else if(prompt.contains("remove student")){
                        System.out.print("What student would you like to remove?");
                        String name = kb.nextLine();
                        for (int i = 0; i < students.size(); i++){
                            Student temp = students.get(i);
                            if(name.equals(temp.getName())){
                                students.remove(i);
                                System.out.print(name+" has been Removed");
                            }
                        }
                        System.out.print("\n\n");
                    }
                    //add assignment
                    if(prompt.contains("add assignment")){
                        Assignment temp = new Assignment();
                        temp = teacher.newAssignment();
                        assignments.add(temp);
                        for(int i = 0; i < students.size(); i++){
                            students.get(i).newAssignment(teacher.scoreAssignment(temp, students.get(i)));
                        }
                    }
                }
                //actuall stuff, not setu
            }
            else if(input.contains("student")){
                //student input
                if(students == null){
                    System.out.println("Sorry! the teacher hasn't set anything up yet!");
                }
                
            }
            else{
                //no proper input detected
                System.out.println("Please reply student or teacher");
            }
        }
    }
    //setup
    public static ArrayList<Student> setup(Teacher teacher){
        //temporary array list
        ArrayList<Student> res = new ArrayList<Student>();
        //student count
        int count = teacher.studentCount();
        
        //name students
        for(int i = 1; i <= count; i++){
           Student temp = new Student();
           temp.setName(teacher.studentName(i));
           res.add(temp);
        }
        
        //responds
        return res;
    }
    
}
