package oop_assignment;

import java.util.ArrayList;
import java.util.Scanner;

public class Staff implements StaffManager {

    private String staffName;
    private String password;
    ArrayList<Staff> staff = StaffManager.loadStaffFiles("staff.txt");
    
    
    public Staff() {

    }
    
    public Staff(String staffName, String password){
        this.staffName = staffName;
        this.password = password;
    }
    
    public String getStaffName() {
        return staffName;
    }
    
    public String getStaffPassword() {
        return password;
    }

    

}
