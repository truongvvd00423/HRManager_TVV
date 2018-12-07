/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HRManager.entities;

/**
 *
 * @author badan
 */
public class SalaryManager {
    private int smID, employeeID, salaryID;
    private Salary salary;

    public Salary getSalary() {
        return salary;
    }

    public void setSalary(Salary salary) {
        this.salary = salary;
    }
    
    

    public SalaryManager(int smID, int employeeID, int salaryID) {
        this.smID = smID;
        this.employeeID = employeeID;
        this.salaryID = salaryID;
    }

    public SalaryManager() {
    }

    public int getSmID() {
        return smID;
    }

    public void setSmID(int smID) {
        this.smID = smID;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public int getSalaryID() {
        return salaryID;
    }

    public void setSalaryID(int salaryID) {
        this.salaryID = salaryID;
    }
    
    
}
