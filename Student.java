/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teachergradebook;

import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author Paarthrnax
 */
public class Student {
    String name;
    ArrayList<Assignment> assignments = new ArrayList<Assignment>();
    
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    
    public void newAssignment(Assignment a){
        assignments.add(a);
    }
    
    public double grade(){
        double responce;
        double total = 0;
        double earned = 0;
        
        for(int i = 0; i < assignments.size(); i++){
            total += assignments.get(i).getTotal();
            earned += assignments.get(i).getEarned();
        }
        
        responce = (earned / total) * 100;
        return responce;
    }
    
    public void printGrades(){
        System.out.println(name+":");
        for(int i = 0; i < assignments.size(); i++){
            System.out.println(assignments.get(i).getName()+":\t"+assignments.get(i).getEarned()+"/"+assignments.get(i).getTotal());
        }
        System.out.println("Total:\t"+grade());
    }
    
    public Assignment getAssignment(int i){
        return assignments.get(i);
    }
    
    public int getCount(){
        return assignments.size();
    }
    
}
