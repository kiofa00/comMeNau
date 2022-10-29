/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models.Chef;

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
public class ChefDAO implements Serializable {

    public static ChefDTO getChefById(int id) throws SQLException {
        Connection con = null;
        String sql = "SELECT   [ChId],[name],[phone] ,[address],[email],[salary]\n"
                + "  FROM [cmn].[dbo].[Chef]\n"
                + "  WHERE ChId=?";
        PreparedStatement stm = null;
        ResultSet rs = null;
        ChefDTO chef = null;
        try {
            con = DBConnect.getConnection();
            stm = con.prepareStatement(sql);
            stm.setInt(1, id);
            rs = stm.executeQuery();
            if (rs.next()) {
                chef = new ChefDTO(id, rs.getString("name"), rs.getString("phone"), rs.getInt("address"),
                        rs.getString("email"), rs.getDouble("salary"));
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
        return chef;
    }

    public static List<ChefDTO> getAllChef() throws SQLException {
        Connection con = null;
        String sql = "SELECT  [ChId]\n"
                + "FROM [Chef]"
                + "where [status]=1 ";
        Statement stm = null;
        ResultSet rs = null;
        List<ChefDTO> chefList = new ArrayList<>();
        try {
            con = DBConnect.getConnection();
            stm = con.createStatement();
            rs = stm.executeQuery(sql);
            while (rs.next()) {
                chefList.add(getChefById(rs.getInt("ChId")));
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
        return chefList;
    }

    public static boolean deleteChef(int chefId) throws SQLException {
        Connection con = null;
        String sql = "update Chef\n"
                + "	  set status=0 \n"
                + "	  where ChId=? ";
        PreparedStatement stm = null;
        try {
            con = DBConnect.getConnection();
            stm = con.prepareStatement(sql);
            stm.setInt(1, chefId);
            if (stm.executeUpdate() > 0) {
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

    public static boolean updateChef(int chefId, String name, String phone, int buildingId, String email, double salary) throws SQLException {
        Connection con = null;
        String sql = " UPDATE Chef\n"
                + "   set [name]=?, phone=?,[address]=?,[email]=?,[salary]=?  \n"
                + "	  where ChId=? ";
        PreparedStatement stm = null;
        try {
            con = DBConnect.getConnection();
            stm = con.prepareStatement(sql);
            stm.setString(1, name);
            stm.setString(2, phone);
            stm.setInt(3, buildingId);
            stm.setString(4, email);
            stm.setDouble(5, salary);
            stm.setInt(6, chefId);
            if (stm.executeUpdate() > 0) {
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

    public static boolean insertChef(String name, String phone, String email, double salary, int buildingId) throws SQLException {
        Connection con = null;
        String sql = " INSERT INTO Chef(name,phone,email,salary,[status],[address]) values(?,?,?,?,?,?)";
        PreparedStatement stm = null;
        try {
            con = DBConnect.getConnection();
            stm = con.prepareStatement(sql);
            stm.setString(1, name);
            stm.setString(2, phone);
            stm.setString(3, email);
            stm.setDouble(4, salary);
            stm.setInt(5, 1);
            stm.setInt(6, buildingId);
            if (stm.executeUpdate() > 0) {
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

    public static int getTotalQuantityByChef(int chefId, String code) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        String sql = "SELECT count([OId]) as total\n"
                + "  FROM [Order] join\n"
                + " MealGroup on [Order].mealId=MealGroup.MId \n"
                + " join Session on [Order].sessionId=[Session].SId\n"
                + "where MealGroup.chefId=? and Session.Code= ? and [Order].[status]=2";
        ResultSet rs = null;
        int result = 0;
        try {
            con = DBConnect.getConnection();
            stm = con.prepareStatement(sql);
            stm.setInt(1, chefId);
            stm.setString(2, code);
            rs = stm.executeQuery();
            if (rs.next()) {
                result = rs.getInt("total");
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
        return result;
    }

    public static List<ChefDTO> getChefInsession(int id) throws SQLException {
        Connection con = null;
        String sql = "  SELECT MealGroup.chefId\n"
                + "  FROM [MealGroupSession] join\n"
                + "  MealGroup on MealGroupSession.mealId=MealGroup.MId\n"
                + "  where MealGroupSession.sessionId=?\n"
                + "  group by MealGroup.chefId";
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<ChefDTO> list = new ArrayList<>();
        try {
            con = DBConnect.getConnection();
            stm = con.prepareStatement(sql);
            stm.setInt(1, id);
            rs = stm.executeQuery();
            while (rs.next()) {
                list.add(getChefById(rs.getInt("chefId")));
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
