/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models.Customer;

import Models.Account.AccountDAO;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import ultils.DBConnect;

/**
 *
 * @author PC
 */
public class CustomerDAO implements Serializable {

    public static CustomerDTO getCustomer(int id) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        String sql = "SELECT  [CuId],[name],[phone] ,[address],[email] "
                + "  FROM [Customer] "
                + "  WHERE [CuId]=? ";
        CustomerDTO cus = null;
        try {
            con = DBConnect.getConnection();
            stm = con.prepareStatement(sql);
            stm.setInt(1, id);
            rs = stm.executeQuery();
            if (rs.next()) {
                cus = new CustomerDTO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5));
            }
            con.close();
        } finally {

            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }

        }
        return cus;
    }

    public static CustomerDTO getCustomerByAccount(int accountId) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        String sql = "SELECT  [CuId],[name],[phone] ,[address],[email] "
                + "  FROM [Customer] "
                + "  WHERE [AccountId]=? ";
        CustomerDTO cus = null;
        try {
            con = DBConnect.getConnection();
            stm = con.prepareStatement(sql);
            stm.setInt(1, accountId);
            rs = stm.executeQuery();
            if (rs.next()) {
                cus = new CustomerDTO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5));
            }
            con.close();
        } finally {

            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }

        }
        return cus;
    }

    public static boolean insertCustomer(String name, String phone, int addressId, String email,
            String Account, String pass) throws SQLException {
        Connection con = null;
        String sqlInsertCustomer = "Insert Into [Customer] values(?,?,?,?,?)";
        PreparedStatement stm = null;
        int countCustomer = 0;
        int curAccount = 0;
        try {
            con = DBConnect.getConnection();
            if (AccountDAO.inserAccount(Account, pass, 2)) {
                curAccount = AccountDAO.getCurrentId();
                stm = con.prepareStatement(sqlInsertCustomer);
                stm.setString(1, name);
                stm.setString(2, phone);
                stm.setInt(3, addressId);
                stm.setString(4, email);
                stm.setInt(5, curAccount);
                if (curAccount != 0) {
                    countCustomer = stm.executeUpdate();
                }
                if (countCustomer != 0) {
                    return true;
                }
            }

        } finally {

            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    public static boolean updateCustomer(int id, String name, String phone, int address) throws SQLException {
        Connection con = null;
        String sqlInsertCustomer = "UPDATE [Customer]\n"
                + "  SET [name] = ? , [phone] = ? ,[address]= ?\n"
                + "   WHERE CuId= ?";
        PreparedStatement stm = null;
        int countCustomer;
        try {
            con = DBConnect.getConnection();
            stm = con.prepareStatement(sqlInsertCustomer);
            stm.setString(1, name);
            stm.setString(2, phone);
            stm.setInt(3, address);
            stm.setInt(4, id);
            countCustomer = stm.executeUpdate();
            if (countCustomer != 0) {
                return true;
            }
        } finally {

            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    public static boolean updateCustomer(int id, String name, String phone, int address, String email) throws SQLException {
        Connection con = null;
        String sqlInsertCustomer = "UPDATE [Customer]\n"
                + "  SET [name] = ? , [phone] = ? ,[address]= ?,email=?\n"
                + "   WHERE CuId= ?";
        PreparedStatement stm = null;
        int countCustomer;
        try {
            con = DBConnect.getConnection();
            stm = con.prepareStatement(sqlInsertCustomer);
            stm.setString(1, name);
            stm.setString(2, phone);
            stm.setInt(3, address);
            stm.setString(4, email);
            stm.setInt(5, id);
            countCustomer = stm.executeUpdate();
            if (countCustomer != 0) {
                return true;
            }
        } finally {

            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    public static int getTotalOrderByUser(int cId, String code) throws SQLException {
        Connection con = null;
        String sql;
        if (cId != 0) {
            sql = "  select count(OId) as total\n"
                    + "                 FROM [Order] join\n"
                    + "		  Session on [Order].sessionId=Session.SId\n"
                    + "                  where customerId=? and Session.Code=? and [Order].[status]=2";
        } else {
            sql = "select count(OId) as total\n"
                    + "                 FROM [Order] join\n"
                    + "		  Session on [Order].sessionId=Session.SId\n"
                    + "                  where customerId is null and Session.Code=? and [Order].[status]=2";
        }
        PreparedStatement stm = null;
        int result = 0;
        ResultSet rs = null;
        try {
            con = DBConnect.getConnection();
            stm = con.prepareStatement(sql);
            stm.setInt(1, cId);
            if (cId != 0) {
                stm.setInt(1, cId);
                stm.setString(2, code);
            } else {
                stm.setString(1, code);
            }
            rs = stm.executeQuery();

            if (rs.next()) {
                result = rs.getInt("total");
            }
        } finally {

            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return result;
    }

    public static double getTotalMoneyByUser(int cId, String code) throws SQLException {
        Connection con = null;
        String sql;
        if (cId != 0) {
            sql = "select sum(price) as [money]\n"
                    + "FROM [Order] join\n"
                    + "Session on [Order].sessionId=Session.SId\n"
                    + " where customerId=? and Session.Code= ? and [Order].[status]=2";
        } else {
            sql = "select sum(price) as [money]\n"
                    + "FROM [Order] join\n"
                    + "Session on [Order].sessionId=Session.SId\n"
                    + " where customerId is null and Session.Code= ? and [Order].[status]=2";
        }
        PreparedStatement stm = null;
        double result = 0;
        ResultSet rs = null;
        try {
            con = DBConnect.getConnection();
            stm = con.prepareStatement(sql);
            if (cId != 0) {
                stm.setInt(1, cId);
                stm.setString(2, code);
            } else {
                stm.setString(1, code);
            }
            rs = stm.executeQuery();

            if (rs.next()) {
                result = rs.getDouble("money");
            }
        } finally {

            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return result;
    }

    public static List<CustomerDTO> getAllCustomer() throws SQLException {
        Connection con = null;
        String sql = "SELECT [CuId]\n"
                + "FROM [Customer]";
        Statement stm = null;
        ResultSet rs = null;
        List<CustomerDTO> customerList = new ArrayList<>();
        try {
            con = DBConnect.getConnection();
            stm = con.createStatement();
            rs = stm.executeQuery(sql);
            while (rs.next()) {
                customerList.add(getCustomer(rs.getInt("CuId")));
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return customerList;
    }

    public static List<CustomerDTO> getCustomerInsession(String code) throws SQLException {
        Connection con = null;
        String sql = "   SELECT [customerId]\n"
                + " FROM [Order] join\n"
                + "Session on [Order].sessionId=Session.SId\n"
                + " where  Session.Code= ? and [Order].[status]=2 \n"
                + "group by customerId";
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<CustomerDTO> list = new ArrayList<>();
        try {
            con = DBConnect.getConnection();
            stm = con.prepareStatement(sql);
            stm.setString(1, code);
            rs = stm.executeQuery();
            while (rs.next()) {
                if (rs.getInt("customerId") != 0) {
                    list.add(getCustomer(rs.getInt("customerId")));
                } else {
                    list.add(new CustomerDTO(0, "Guest", "", 0, ""));
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return list;
    }
}
