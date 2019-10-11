/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teachergradebook;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

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
        boolean running;
        Teacher teacher = new Teacher(); 
        ArrayList<Student> students = null;
        ArrayList<Assignment> assignments = new ArrayList<Assignment>();
        
        running = true;
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
                    
                    //quit
                    if(prompt.contains("quit")){
                        System.out.print("Are you sure you would like to quit?");
                        String confirm = kb.nextLine();
                        if(confirm.contains("yes") || confirm.contains("Yes")){
                            save(students);
                            running = false;
                            logedIn = false;
                        }
                    }
                    //log out
                    if(prompt.contains("log out")){
                        System.out.println("Are you sure you want to log out?");
                        String confirm = kb.nextLine();
                        if(confirm.contains("yes") || confirm.contains("Yes")){
                            logedIn = false;
                            save(students);
                        }
                    }
                    //new student
                    else if(prompt.contains("add student")){
                            Student temp = new Student();
                            temp.setName(teacher.studentName(students.size()+1));
                            students.add(temp);
                            System.out.print("\n\n");
                    }
                    //list grades
                    else if(prompt.contains("list grades")){
                        System.out.println("You have "+students.size()+" students. Their names and grades are:");
                        for(int i = 0; i < students.size(); i++){
                            Student temp = students.get(i);
                            System.out.println("\t"+temp.getName()+", who has "+temp.grade()+"%");
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
                    //Individual grades
                    if(prompt.contains("see grades")){
                        System.out.print("What student?");
                        String name = kb.nextLine();
                        
                        for(int i = 0; i < students.size(); i++){
                            if(name.equals(students.get(i).getName())){
                                students.get(i).printGrades();
                                break;
                            }
                        }
                    }
                }
                
            }
            else if(input.contains("student")){
                //student input
                if(students == null){
                    System.out.println("Sorry! the teacher hasn't set anything up yet!");
                }
                else{
                    System.out.print("What is your name?");
                    String user = kb.nextLine();
                    for(int i = 0; i < students.size(); i++){
                        if(user.equals(students.get(i).getName())){
                            students.get(i).printGrades();
                        }
                    }
                    boolean on = true;
                    while(on){
                        if(kb.nextLine().equals("log out")){
                            System.out.print("Are you sure?");
                            String confirm = kb.nextLine();
                            if(confirm.contains("Yes") || confirm.contains("yes")){
                                break;
                            }
                        }
                    }
                }
                
            }
            else{
                //no proper input detected
                System.out.println("Please reply student or teacher");
            }
        }
        
        System.out.println("\nThank you for using Gradebook.");
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
    
    //Despite my efforts, I couldn't get this to work before the due-date
    public static void save(ArrayList<Student> s){
        for(int i = 0; i > s.size(); i++){
            //looks for file. If there isn't one, it should create it.
            File file = new File("H:/Files-to-read/Gradebook Students/"+s.get(i).getName()+".txt");
            
            try{
                if(file.createNewFile()){
                    FileWriter writer = new FileWriter(file);
                    writer.write(s.get(i).getName());
                    writer.write(System.lineSeparator());
                    for(int e = 0; e > s.get(i).getCount(); e++){
                        writer.write(s.get(i).getAssignment(e).getName());
                        writer.write(System.lineSeparator());
                        writer.write(s.get(i).getAssignment(e).getEarned()+" "+s.get(i).getAssignment(e).getTotal());
                        writer.write(System.lineSeparator());
                    }
                    writer.close();
                }
                else{
                    FileWriter writer = new FileWriter(file);
                    writer.write("a");
                    writer.close();
                }
            }
            catch(IOException error){
                System.out.print("There was an error saving the data. Sorry for the inconvinience");
            }
        }
    }
    
}
