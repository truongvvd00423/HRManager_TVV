/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package HRManager.bol;

import HRManager.ConvertData;
import HRManager.ValidData;
import HRManager.dal.DAO;
import HRManager.entities.Employee;
import HRManager.entities.SalaryManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EmployeeBO {

    PreparedStatement ps;
    DAO dao = new DAO();

    /**
     *
     * add new Employee to database
     */
    public int add(Employee e) {
        ConvertData cv = new ConvertData();
        String sql = "INSERT INTO Employee(lastName, firstName, birthDate, hireDate, address, city, country, "
                + "homePhone, mobile, email, note) VALUES(?,?,?,?,?,?,?,?,?,?,?)";
        try {

            ps = dao.getConnection().prepareStatement(sql);
            ps.setString(1, e.getLastName());
            ps.setString(2, e.getFirstName());
            ps.setString(3, cv.date2string(e.getBirthDate(), ValidData.EN_DATE));
            ps.setString(4, cv.date2string(e.getHireDate(), ValidData.EN_DATE));
            ps.setString(5, e.getAddress());
            ps.setString(6, e.getCity());
            ps.setString(7, e.getCountry());
            ps.setString(8, e.getHomePhone());
            ps.setString(9, e.getMobile());
            ps.setString(10, e.getEmail());
            ps.setString(11, e.getNote());
            System.out.println("add query" + sql);
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeBO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dao.executeUpdateQuery(ps);

    }

    /**
     *
     * delete Employee into database
     */
    public int delete(Employee e) {
        String sql = "DELETE FROM Employee WHERE employeeID=?";
        try {
            ps = dao.getConnection().prepareStatement(sql);
            ps.setInt(1, e.getEmployeeID());

        } catch (SQLException ex) {
            Logger.getLogger(EmployeeBO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dao.executeUpdateQuery(ps);
    }

    /**
     *
     * update old Employee by new Employee into database
     */
    public int edit(Employee e) {
        ConvertData cv = new ConvertData();
        String sql = "UPDATE Employee SET lastName=?, firstName=?, birthDate=?, hireDate=?, address=?, city=?, country=?,"
                + "homePhone=?, mobile=?, email=?, photoPath=?, note=? WHERE employeeID=?";
        try {

            ps = dao.getConnection().prepareStatement(sql);
            ps.setString(1, e.getLastName());
            ps.setString(2, e.getFirstName());
            ps.setString(3, cv.date2string(e.getBirthDate(), HRManager.ValidData.EN_DATE));
            ps.setString(4, cv.date2string(e.getHireDate(), HRManager.ValidData.EN_DATE));
            ps.setString(5, e.getAddress());
            ps.setString(6, e.getCity());
            ps.setString(7, e.getCountry());
            ps.setString(8, e.getHomePhone());
            ps.setString(9, e.getMobile());
            ps.setString(10, e.getEmail());
            ps.setString(11, e.getPhotoPath());
            ps.setString(12, e.getNote());
            ps.setInt(13, e.getEmployeeID());
            System.out.println("edit query" + sql);

        } catch (SQLException ex) {
            Logger.getLogger(EmployeeBO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dao.executeUpdateQuery(ps);
    }

    /**
     *
     * Lay ve tat ca cac Employee co trong CSDL.
     */
    public List<Employee> select() {
        String sql = "select * from Employee";

        List<Employee> list = new LinkedList();
        try {
            ResultSet rs = dao.executeQuery(sql);
            while (rs.next()) {
                Employee e = new Employee();
                e.setEmployeeID(rs.getInt("employeeID"));
                e.setLastName(rs.getString("lastName"));
                e.setFirstName(rs.getString("firstName"));
                e.setBirthDate(rs.getDate("birthDate"));
                e.setHireDate(rs.getDate("hireDate"));
                e.setAddress(rs.getString("address"));
                e.setCity(rs.getString("city"));
                e.setCountry(rs.getString("country"));
                e.setHomePhone(rs.getString("homePhone"));
                e.setMobile(rs.getString("mobile"));
                e.setEmail(rs.getString("email"));
                e.setPhotoPath(rs.getString("photoPath"));
                e.setNote(rs.getString("note"));
                list.add(e);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            dao.closeConnection();
            return null;
        }
        dao.closeConnection();
        return list;
    }

    public int covertSalary(int employeeID) {
         String sql = "select * from SalaryManager where employeeID=?";
        try {
            ps = dao.getConnection().prepareStatement(sql);
            ps.setInt(1, employeeID);
             ResultSet rs = dao.executeQuery(ps);
            if (rs.next()) {
                return rs.getInt("salaryID");
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeBO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    /**
     *
     * Tim kiem Employee co trong CSDL.
     */
    public List<Employee> find(int option, String value) {
        String sql = "";
        switch (option) {
            case 0:
                sql = "select * from Employee where firstName=? or lastName=?";

                try {
                    ps = dao.getConnection().prepareStatement(sql);
                    ps.setString(1, value);
                    ps.setString(2, value);
                } catch (SQLException ex) {
                    Logger.getLogger(EmployeeBO.class.getName()).log(Level.SEVERE, null, ex);
                }

                break;
            case 1:
                sql = "select * from Employee where city=?";
                try {
                    ps = dao.getConnection().prepareStatement(sql);
                    ps.setString(1, value);
                } catch (SQLException ex) {
                    Logger.getLogger(EmployeeBO.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
        }
//        DAO dao = new DAO();

        List<Employee> list = new LinkedList();
        try {
            ResultSet rs = dao.executeQuery(ps);
            while (rs.next()) {
                Employee e = new Employee();
                e.setEmployeeID(rs.getInt("employeeID"));
                e.setLastName(rs.getString("lastName"));
                e.setFirstName(rs.getString("firstName"));
                e.setBirthDate(rs.getDate("birthDate"));
                e.setHireDate(rs.getDate("hireDate"));
                e.setAddress(rs.getString("address"));
                e.setCity(rs.getString("city"));
                e.setCountry(rs.getString("country"));
                e.setHomePhone(rs.getString("homePhone"));
                e.setMobile(rs.getString("mobile"));
                e.setEmail(rs.getString("email"));
                e.setPhotoPath(rs.getString("photoPath"));
                e.setNote(rs.getString("note"));
                list.add(e);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            dao.closeConnection();
            return null;
        }
        dao.closeConnection();
        return list;
    }

    /**
     *
     * get Employee by EmployeeID
     */
    public Employee getByID(int id) {
        String sql = "select * from Employee where employeeID=?";
        try {
            ps = dao.getConnection().prepareStatement(sql);
            ps.setInt(1, id);
            System.out.println(sql);
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeBO.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            ResultSet rs = dao.executeQuery(ps);
            if (rs.next()) {
                Employee e = new Employee();
                e.setEmployeeID(rs.getInt("employeeID"));
                e.setLastName(rs.getString("lastName"));
                e.setFirstName(rs.getString("firstName"));
                e.setBirthDate(rs.getDate("birthDate"));
                e.setHireDate(rs.getDate("hireDate"));
                e.setAddress(rs.getString("address"));
                e.setCity(rs.getString("city"));
                e.setCountry(rs.getString("country"));
                e.setHomePhone(rs.getString("homePhone"));
                e.setMobile(rs.getString("mobile"));
                e.setEmail(rs.getString("email"));
                e.setPhotoPath(rs.getString("photoPath"));
                e.setNote(rs.getString("note"));
                return e;
            }
        } catch (SQLException ex) {
            dao.closeConnection();
            return null;
        }
        dao.closeConnection();
        return null;
    }

    /**
     *
     * Convert from Vector to Employee Array
     */
//    private Employee[] convertToArray(List v) {
//        int n = v.size();
//        if (n < 1) {
//            return null;
//        }
//        Employee[] arrEmployee = new Employee[n];
//        for (int i = 0; i < n; i++) {
//            arrEmployee[i] = (Employee) v.get(i);
//        }
//        return arrEmployee;
//    }
}
