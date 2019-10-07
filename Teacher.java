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
 * @author Paarthurnax
 */
public class Teacher {
    
    Scanner kb = new Scanner(System.in);
    
    //Teacher inputs student count
    public int studentCount(){
        System.out.println("How many students do you have?");
        int responce;
        responce = kb.nextInt();
        return responce;
    }
    
    //teacher gives student's names
    public String studentName(int i){
        boolean on = true;
        String name = null;
        while(on){
            System.out.println("What is the name of student number "+i+"?");
            name = kb.nextLine();
          
            System.out.print("Student "+i+" is named "+name+"?\n");
            String confirm = kb.nextLine();
            if(confirm.contains("Yes") || confirm.contains("yes")){
               break;
            }
        }
        
        return name;
    }
    
    //teacher adds a new assignment
    public Assignment newAssignment(){
        //setup
        Assignment responce = new Assignment();
        boolean on = true;
        
        while(on){
            //name and give assignment's worth
            System.out.println("What is the assigbment called?");
            String name = kb.nextLine();
            System.out.println("How many points is "+name+" worth?");
            int score = kb.nextInt();
            
            //confirm
            System.out.println("The assignment "+name+" is worth "+score+" points. Is this corect?");
            String confirm = kb.nextLine();
            confirm = kb.nextLine();
        
            if(confirm.equals("yes") || confirm.equals("Yes")){
                responce.setName(name);
                responce.setTotal(score);
                break;
            }
        }
        return responce;
    }
    
    public Assignment scoreAssignment(Assignment a, Student s){
        System.out.println("What did "+s.getName()+" score on "+a.getName()+"?");
        int score = kb.nextInt();
        
        a.setEarned(score);
        return a;
    }
    
}
