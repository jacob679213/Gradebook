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
    
}
