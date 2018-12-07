/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HRManager.bol;

import HRManager.dal.DAO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author badan
 */
public class SalaryManagerBO {

    PreparedStatement ps;
    DAO dao = new DAO();
    String sql;

    public int add(int employeeID) {
        try {
            sql = "INSERT INTO SalaryManager(employeeID, salaryID) VALUES(?, 1)";
            ps = dao.getConnection().prepareStatement(sql);
            ps.setInt(1, employeeID);
        } catch (SQLException e) {
            Logger.getLogger(SalaryManagerBO.class.getName()).log(Level.SEVERE, null, e);
        }
        return dao.executeUpdateQuery(ps);
    }

    public int update(int employeeID, int salaryID) {
        try {
            sql = "UPDATE SalaryManager SET salaryID = ? WHERE employeeID = ?";
            ps = dao.getConnection().prepareStatement(sql);
            ps.setInt(1, salaryID);
            ps.setInt(2, employeeID);
        } catch (SQLException e) {
            Logger.getLogger(SalaryManagerBO.class.getName()).log(Level.SEVERE, null, e);
        }
        return dao.executeUpdateQuery(ps);
    }

    public int delete(int employeeID) {
        try {
            String sql = "DELETE FROM SalaryManager WHERE employeeID=?";
            ps = dao.getConnection().prepareStatement(sql);
            ps.setInt(1, employeeID);

        } catch (SQLException ex) {
            Logger.getLogger(SalaryManagerBO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dao.executeUpdateQuery(ps);
    }

    public int getSalaryIDByEmployeeID(int employeeID) {
        try {
            sql = "SELECT salaryID FROM SalaryMannager WHERE employeeID = ?";
            ps = dao.getConnection().prepareStatement(sql);
            ps.setInt(1, employeeID);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) return rs.getInt("salaryID");
        } catch (SQLException e) {
            Logger.getLogger(SalaryManagerBO.class.getName()).log(Level.SEVERE, null, e);
        }
        return 0;
    }
}
