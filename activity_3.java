package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Department in an organisation
 */
public class Department {
    
    private String name;
    
    private ArrayList<String> ID1 = new ArrayList<String>();
    
    private ArrayList<String> temp1 = new ArrayList<String>();

    private ArrayList<int> employee = new ArrayList<int>();

    
    private int ID2;

    private String name;

    public String getID() {
        return ID2;
    }

    public void setID(int ID) {
        this.ID2 = ID;
    }

    
    public boolean checkEmployee(int empID) {
        // Overall code was made more concise and readable
        // Attributed to instantiation of employee exists boolean variable
        
        
        // Instantiation is based on the contains methods of employeeIDs rather than using for loop
        // eID was changed to employeeID to improve readability
        boolean employeeExists = employeeIDs.contains(empID);

        // employeeExists variable used for the if else rather than ambiguous value such as p
        if (employeeExists) {
            System.out.println("Employee exists");
        } else {
            System.out.println("Employee doesn't exist");
        }

        // Reduced redundent return statements to 1 by utilising employeeExists as return variable
        return employeeExists;
    }

    

    public void assignNew(string temp2, int ID) {
        this.temp1.add(temp2);
	  this.ID1.add(ID);
    }


}
