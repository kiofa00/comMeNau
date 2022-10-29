/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models.Building;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import ultils.DBConnect;

/**
 *
 * @author PC
 */
public class BuildingDAO implements Serializable {

    public static BuildingDTO getBuildingById(int id) throws SQLException {
        Connection con = null;
        String sql = "SELECT Bid, name "
                + "FROM Building "
                + "WHERE BId =? ";
        PreparedStatement stm = null;
        ResultSet rs = null;
        BuildingDTO Building = null;
        try {
            con = DBConnect.getConnection();
            stm = con.prepareStatement(sql);
            stm.setInt(1, id);
            rs = stm.executeQuery();
            if (rs.next()) {
                Building = new BuildingDTO(rs.getInt("Bid"), rs.getString("name"));
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
        return Building;
    }
    public static boolean buildingIsExist(String name) throws SQLException {
        Connection con = null;
        String sql = "SELECT Bid "
                + "FROM Building "
                + "WHERE name =?  and status=1 ";
        PreparedStatement stm = null;
        ResultSet rs = null;
        BuildingDTO Building = null;
        try {
            con = DBConnect.getConnection();
            stm = con.prepareStatement(sql);
            stm.setString(1, name);
            rs = stm.executeQuery();
            if (rs.next()) {
                return true;
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
        return false;
    }
    public static List<BuildingDTO> getAllBuilding() throws SQLException {
        Connection con = null;
        String sql = "SELECT Bid, name "
                + "FROM Building "
                + "where [status]=1";
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<BuildingDTO> Building = new ArrayList<>();
        try {
            con = DBConnect.getConnection();
            stm = con.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                Building.add(new BuildingDTO(rs.getInt("Bid"), rs.getString("name")));
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
        return Building;
    }

    public static boolean deleteBuilding(int bId) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        String sql = "update Building\n"
                + "  set [status]=0\n"
                + "  where Bid=?";
        try {
            con = DBConnect.getConnection();
            stm = con.prepareStatement(sql);
            stm.setInt(1, bId);
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

    public static boolean updateBuilding(int bId, String name) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        String sql = "update Building\n"
                + "  set [name]=?\n"
                + "  where Bid=?";
        try {
            con = DBConnect.getConnection();
            stm = con.prepareStatement(sql);
            stm.setString(1, name);
            stm.setInt(2, bId);
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

    public static boolean checkNameisExist(String name) throws SQLException {
        Connection con = null;
        String sql = "SELECT [name]\n"
                + "  FROM [Building]\n"
                + "  where [name]=? and status=1";
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBConnect.getConnection();
            stm = con.prepareStatement(sql);
            stm.setString(1, name);
            rs = stm.executeQuery();
            if (rs.next()) {
                return true;
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
        return false;
    }

    public static boolean insertBuilding(String name) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        String sql = "INSERT INTO [Building]([name],[status]) values(?,?)";
        try {
            con = DBConnect.getConnection();
            stm = con.prepareStatement(sql);
            stm.setString(1, name);
            stm.setInt(2, 1);
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
}
