/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models.Image;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import ultils.DBConnect;

/**
 *
 * @author PC
 */
public class ImageDAO {

    public static boolean insertFoodImage(String nameImage, int Fid) throws SQLException {
        Connection con = null;
        String sql = "INSERT INTO [Image]([url],[foodId]) values(?,?)";
        PreparedStatement stm = null;
        try {
            con = DBConnect.getConnection();
            stm = con.prepareStatement(sql);
            stm.setString(1, nameImage);
            stm.setInt(2, Fid);
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

    public static boolean insertMealImage(String nameImage, int Mid) throws SQLException {
        Connection con = null;
        String sql = "INSERT INTO [Image]([url],[mealId]) values(?,?)";
        PreparedStatement stm = null;
        try {
            con = DBConnect.getConnection();
            stm = con.prepareStatement(sql);
            stm.setString(1, nameImage);
            stm.setInt(2, Mid);
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

    public static boolean deleteFoodImag(int FId) throws SQLException {
        Connection con = null;
        String sql = "DELETE FROM [Image]\n"
                + "  WHERE foodId=?";
        PreparedStatement stm = null;
        try {
            con = DBConnect.getConnection();
            stm = con.prepareStatement(sql);
            stm.setInt(1, FId);
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

    public static boolean updateFoodImage(int Fid, String oldImage, String newImage) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        String sql = "UPDATE Image\n"
                + "  SET  url=? \n"
                + "  WHERE url=? AND foodId=?";
        try {
            con = DBConnect.getConnection();
            stm = con.prepareStatement(sql);
            stm.setString(1, newImage);
            stm.setString(2, oldImage);
            stm.setInt(3, Fid);
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
    public static boolean updateMealImage(int Mid, String oldImage, String newImage) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        String sql =" UPDATE Image \n"
                + "  SET  url=? \n"
                + "  WHERE url=? AND mealId=? ";
        try {
            con = DBConnect.getConnection();
            stm = con.prepareStatement(sql);
            stm.setString(1, newImage);
            stm.setString(2, oldImage);
            stm.setInt(3, Mid);
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
