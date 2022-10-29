/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models.Food;

import Models.Image.ImageDAO;
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
public class FoodDAO implements Serializable {

    public static List<String> getFoodImage(int id) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        String sql = "SELECT URL "
                + "FROM Image "
                + "WHERE foodId = ? ";
        List<String> list = new ArrayList<>();
        try {
            con = DBConnect.getConnection();
            stm = con.prepareStatement(sql);
            stm.setInt(1, id);
            rs = stm.executeQuery();
            while (rs.next()) {
                list.add(rs.getString(1));
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

    public static List<String> getFoodTimeline() throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        String sql = "SELECT [timeline] "
                + "FROM [Session]  ";
        List<String> list = new ArrayList<>();
        try {
            con = DBConnect.getConnection();
            stm = con.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                list.add(rs.getString(1));
            }
        } finally {

            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                rs.close();
            }
            if (con != null) {
                con.close();
            }

        }
        return list;
    }

    public static FoodDTO getFoodById(int id) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        String sql = "SELECT  [FId],[name],[nutrition] "
                + "  FROM [Food] "
                + "  where FId=? ";
        FoodDTO food = new FoodDTO();
        try {
            con = DBConnect.getConnection();
            stm = con.prepareStatement(sql);
            stm.setInt(1, id);
            rs = stm.executeQuery();
            if (rs.next()) {
                List<String> image = getFoodImage(id);
                food = new FoodDTO(rs.getInt(1), rs.getString(2), rs.getString(3), image);
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
        return food;
    }

    public static List<FoodDTO> getAllFood() throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        String sql = "SELECT fid FROM food"
                + "   where status = ? "
                + "  ORDER BY fid ";
        List<FoodDTO> list = new ArrayList<>();

        try {
            con = DBConnect.getConnection();
            stm = con.prepareStatement(sql);
            stm.setInt(1, 1);
            rs = stm.executeQuery();
            while (rs.next()) {
                list.add(getFoodById(rs.getInt(1)));
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

    public static List<FoodDTO> getFoodByMealId(int id) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        String sql = "SELECT  [foodId]  \n"
                + "    FROM [FoodItem] join\n"
                + "    food on FoodItem.foodId=Food.FId join\n"
                + "	Category on Food.categoryId=Category.CaId\n"
                + "    where [mealId]=? and status=?\n"
                + "	order by Category.type asc";
        List<FoodDTO> list = new ArrayList<>();

        try {
            con = DBConnect.getConnection();
            stm = con.prepareStatement(sql);
            stm.setInt(1, id);
            stm.setInt(2, 1);
            rs = stm.executeQuery();
            while (rs.next()) {
                list.add(getFoodById(rs.getInt(1)));
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

    public static List<FoodDTO> getFoodByCategory(int categoryId) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        String sql = " SELECT  FId  \n"
                + "   FROM Food \n"
                + "   where [categoryId]=? and status=? ";
        List<FoodDTO> list = new ArrayList<>();

        try {
            con = DBConnect.getConnection();
            stm = con.prepareStatement(sql);
            stm.setInt(1, categoryId);
            stm.setInt(2, 1);

            rs = stm.executeQuery();
            while (rs.next()) {
                list.add(getFoodById(rs.getInt(1)));
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

//    public static int CountFood() throws SQLException {
//        Connection con = DBConnect.getConnection();
//        String sql = "SELECT count(*) FROM Food ";
//        PreparedStatement stm = con.prepareStatement(sql);
//        ResultSet rs = stm.executeQuery();
//        if (rs.next()) {
//            return rs.getInt(1);
//        }
//        return 0;
//    }
//
//    public static int CountFoodSearch(String search) throws SQLException {
//        Connection con = DBConnect.getConnection();
//        String sql = "SELECT COUNT(*) FROM [Food] WHERE [name] LIKE ? ";
//        PreparedStatement stm = con.prepareStatement(sql);
//        stm.setString(1, "%" + search + "%");
//        ResultSet rs = stm.executeQuery();
//        if (rs.next()) {
//            return rs.getInt(1);
//        }
//        return 0;
//    }
//
//    public static int CountFoodByCategory(String category) throws SQLException {
//        Connection con = DBConnect.getConnection();
//        String sql = "SELECT COUNT(*)  "
//                + "FROM food "
//                + "JOIN Category ON food.categoryId=Category.CaId "
//                + "WHERE Category.[name]= ? ";
//        PreparedStatement stm = con.prepareStatement(sql);
//        stm.setString(1, category);
//        ResultSet rs = stm.executeQuery();
//        if (rs.next()) {
//            return rs.getInt(1);
//        }
//        return 0;
//    }
//    public static List<FoodDTO> getFoodByCategoryIndex(String category, String index) throws SQLException, ClassNotFoundException {
//        Connection con = null;
//        PreparedStatement stm = null;
//        ResultSet rs = null;
//        String sql = " WITH FoodList AS ( SELECT ROW_NUMBER() OVER (ORDER BY [FId] asc) AS stt, FId "
//                + "FROM Food "
//                + "JOIN Category ON food.categoryId=Category.CaId  "
//                + "WHERE Category.name= ?) "
//                + "select FId from FoodList where stt between ? *9-8 and ?*9 ";
//        List<FoodDTO> list = new ArrayList<>();
//
//        try {
//            con = DBConnect.getConnection();
//            stm = con.prepareStatement(sql);
//            stm.setString(1, category);
//            stm.setString(2, index);
//            stm.setString(3, index);
//            rs = stm.executeQuery();
//            while (rs.next()) {
//                list.add(getFoodById(rs.getInt(1)));
//            }
//        } finally {
//
//            if (rs != null) {
//                rs.close();
//            }
//            if (stm != null) {
//                stm.close();
//            }
//            if (con != null) {
//                con.close();
//            }
//
//        }
//        return list;
//    }
//    public static List<FoodDTO> getFoodByIndex(String index) throws SQLException, ClassNotFoundException {
//        Connection con = null;
//        PreparedStatement stm = null;
//        ResultSet rs = null;
//        String sql = "  WITH FoodList AS ( SELECT ROW_NUMBER() OVER (order by [FId] asc) AS stt, FId "
//                + "  FROM [Food] ) "
//                + "  SELECT FId FROM FoodList WHERE stt BETWEEN ? *9-8 and ?*9 ";
//        List<FoodDTO> list = new ArrayList<>();
//
//        try {
//            con = DBConnect.getConnection();
//            stm = con.prepareStatement(sql);
//            stm.setString(1, index);
//            stm.setString(2, index);
//
//            rs = stm.executeQuery();
//            while (rs.next()) {
//                list.add(getFoodById(rs.getInt(1)));
//            }
//        } finally {
//
//            if (rs != null) {
//                rs.close();
//            }
//            if (stm != null) {
//                stm.close();
//            }
//            if (con != null) {
//                con.close();
//            }
//
//        }
//        return list;
//    }
    public static List<FoodDTO> getFoodSearch(String search) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        String sql = "  SELECT  [FId],[name],[nutrition],[categoryId]\n"
                + " FROM [Food]\n"
                + " WHERE [name] like ? and status=? ";
        List<FoodDTO> list = new ArrayList<>();

        try {
            con = DBConnect.getConnection();
            stm = con.prepareStatement(sql);
            stm.setString(1, "%" + search + "%");
            stm.setInt(2, 1);
            rs = stm.executeQuery();
            while (rs.next()) {
                list.add(getFoodById(rs.getInt(1)));
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

    public static boolean updateFood(int Fid, String name, String nutrition, int CategoryId) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        String sql = "UPDATE [Food]\n"
                + "SET [name] = ?, [nutrition] = ?, [categoryId]=?\n"
                + "WHERE [FId]=? ";
        try {
            con = DBConnect.getConnection();
            stm = con.prepareStatement(sql);
            stm.setString(1, name);
            stm.setString(2, nutrition);
            stm.setInt(3, CategoryId);
            stm.setInt(4, Fid);
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

    public static boolean updateFoodCategory(int categoryId) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        String sql = "UPDATE [Food]\n"
                + "SET [categoryId]=?\n"
                + "WHERE [categoryId]=? ";
        try {
            con = DBConnect.getConnection();
            stm = con.prepareStatement(sql);
            stm.setString(1, "null");
            stm.setInt(2, categoryId);
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

    public static boolean insertFood(String name, String nutrition, int CategoryId, String imageName) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        String sql = "    INSERT INTO [Food] ([name], [nutrition], [categoryId],[status])\n"
                + "       VALUES (?, ?, ?, ?) ";
        try {
            con = DBConnect.getConnection();
            stm = con.prepareStatement(sql);
            stm.setString(1, name);
            stm.setString(2, nutrition);
            stm.setInt(3, CategoryId);
            stm.setInt(4, 1);
            if (stm.executeUpdate() > 0) {
                int lastFoodId = getLastIndexFood();
                if (ImageDAO.insertFoodImage(imageName, lastFoodId)) {
                    return true;
                }
            }
            con.close();
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

    public static int getLastIndexFood() throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        String sql = "SELECT TOP (1) [FId]\n"
                + "  FROM [Food]\n"
                + "  order by FId desc";
        ResultSet rs = null;
        int lastId = 0;
        try {
            con = DBConnect.getConnection();
            stm = con.prepareStatement(sql);
            rs = stm.executeQuery();
            if (rs.next()) {
                lastId = rs.getInt("FId");
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
        return lastId;
    }

    public static boolean deleteFood(int foodId) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        String sql = "update food \n"
                + "   set status=0\n"
                + "   where FId=? ";
        int effectRow;
        try {
            con = DBConnect.getConnection();
            stm = con.prepareStatement(sql);
            stm.setInt(1, foodId);
            effectRow = stm.executeUpdate();
            if (effectRow > 0) {
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

    public static boolean isFoodExist(String name) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        String sql = "select *\n"
                + "  from Food\n"
                + "  where Food.[name] = ? and [status]=1 ";
        ResultSet rs=null;
        try {
            con = DBConnect.getConnection();
            stm = con.prepareStatement(sql);
            stm.setString(1, name);
            rs=stm.executeQuery();
            if ( rs.next()) {
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
