/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models.Category;

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
public class CategoryDAO implements Serializable {

    public static List<CategoryDTO> getFoodCategory() throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        String sql = "SELECT CaId, [name] ,[type]\n"
                + "    FROM [Category] "
                + "    where isDelete = 1 ";
        List<CategoryDTO> list = new ArrayList<>();
        try {
            con = DBConnect.getConnection();
            stm = con.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                list.add(new CategoryDTO(rs.getInt("CaId"), rs.getString("name"), rs.getInt("type")));
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

    public static CategoryDTO getCategoryById(int categoryId) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        CategoryDTO category = null;
        String sql = "SELECT  [CaId]\n"
                + "      ,[name]\n"
                + "      ,[type]\n"
                + "  FROM [Category] "
                + " where CaId= ? ";
        try {
            con = DBConnect.getConnection();
            stm = con.prepareStatement(sql);
            stm.setInt(1, categoryId);
            rs = stm.executeQuery();
            if (rs.next()) {
                category = new CategoryDTO(rs.getInt("CaId"), rs.getString("name"), rs.getInt("type"));
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
        return category;
    }

    public static int getCategoryIdOfFood(int foodId) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        String sql = "SELECT categoryId \n"
                + "	FROM Food \n"
                + "	WHERE FId=? ";
        int categoryId = 0;
        try {
            con = DBConnect.getConnection();
            stm = con.prepareStatement(sql);
            stm.setInt(1, foodId);
            rs = stm.executeQuery();
            if (rs.next()) {
                categoryId = rs.getInt("categoryId");
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
        return categoryId;
    }

    public static boolean insertCategory(String name, int type) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        String sql = "INSERT INTO Category(name,type,[isDelete]) values(?,?,?) ";
        try {
            con = DBConnect.getConnection();
            stm = con.prepareStatement(sql);
            stm.setString(1, name);
            stm.setInt(2, type);
            stm.setInt(3, 1);
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

    public static boolean deleteCategory(int categoryId) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        String sql = "UPDATE Category\n"
                + "  SET isDelete=0 "
                + " WHERE Caid= ? ";
        try {
            con = DBConnect.getConnection();
            stm = con.prepareStatement(sql);
            stm.setInt(1, categoryId);
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

    public static boolean updateCategory(int categoryId, String name, int type) throws SQLException, ClassNotFoundException {

        Connection con = null;
        PreparedStatement stm = null;
        String sql = "UPDATE Category\n"
                + "  SET name=?, type=? "
                + " WHERE Caid= ?";
        try {
            con = DBConnect.getConnection();
            stm = con.prepareStatement(sql);
            stm.setString(1, name);
            stm.setInt(2, type);
            stm.setInt(3, categoryId);
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

    public static boolean isNameCategoryExist(String name) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        String sql = "SELECT  [CaId]\n"
                + "  FROM [Category] "
                + " where [name]= ? and [isDelete]=1";
        try {
            con= DBConnect.getConnection();
            stm=con.prepareStatement(sql);
            stm.setString(1, name);
            rs=stm.executeQuery();
            if(rs.next()){
                return true;
            }
        } finally {
            if(rs!=null){
                rs.close();
            }
            if(stm!=null){
                stm.close();
            }
            if(con!=null){
                con.close();
            }
        }
        return false;
    }
    
    
}
