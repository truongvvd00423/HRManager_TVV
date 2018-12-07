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
public class Salary {
    private int salaryID, salaryLevel;
    private double wages;

    public Salary(int salaryID, int salaryLevel, double wages) {
        this.salaryID = salaryID;
        this.salaryLevel = salaryLevel;
        this.wages = wages;
    }

    public Salary() {
    }

    public int getSalaryID() {
        return salaryID;
    }

    public void setSalaryID(int salaryID) {
        this.salaryID = salaryID;
    }

    public int getSalaryLevel() {
        return salaryLevel;
    }

    public void setSalaryLevel(int salaryLevel) {
        this.salaryLevel = salaryLevel;
    }

    public double getWages() {
        return wages;
    }

    public void setWages(double wages) {
        this.wages = wages;
    }
    
    
}
