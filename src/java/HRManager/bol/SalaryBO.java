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
public class SalaryBO {

    PreparedStatement ps;
    DAO dao = new DAO();
    String sql;

    public double getSalaryOfEmployee(int salaryID) {
        try {
            sql = "SELECT wages FROM Salary WHERE salaryID = ?";
            ps = dao.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getDouble("wages");
            }
        } catch (SQLException e) {
            Logger.getLogger(SalaryBO.class.getName()).log(Level.SEVERE, null, e);
        }
        return 0;
    }
}
