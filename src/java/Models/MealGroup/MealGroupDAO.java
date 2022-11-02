/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models.MealGroup;

import Models.Chef.ChefDAO;
import Models.Food.FoodDAO;
import Models.Food.FoodDTO;
import Models.Image.ImageDAO;
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
public class MealGroupDAO implements Serializable {

    public static List<String> getMealGroupImage(int id) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        String sql = "SELECT URL "
                + "FROM Image "
                + "WHERE [mealId] = ? ";
        List<String> list = new ArrayList<>();
        try {
            con = DBConnect.getConnection();
            stm = con.prepareStatement(sql);
            stm.setInt(1, id);
            rs = stm.executeQuery();
            while (rs.next()) {
                list.add(rs.getString(1));
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
        return list;
    }

    public static boolean isMealExist(String name) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        String sql = " SELECT  [MId]\n"
                + "  FROM [MealGroup]\n"
                + "  where [name]=? and status=1 ";
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
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    public static MealGroupDTO getMealGroupById(int id) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        String sql = "SELECT  [MId],[price],[detail],[chefId],name "
                + "  FROM [MealGroup] "
                + "  where [MId]=? ";
        MealGroupDTO meal = new MealGroupDTO();

        try {
            con = DBConnect.getConnection();
            stm = con.prepareStatement(sql);
            stm.setInt(1, id);
            rs = stm.executeQuery();
            if (rs.next()) {
                List<String> image = getMealGroupImage(id);
                List<FoodDTO> foodList = FoodDAO.getFoodByMealId(id);
                meal = new MealGroupDTO(rs.getInt(1), rs.getDouble(2), rs.getString(3),
                        ChefDAO.getChefById(rs.getInt(4)), rs.getString(5), image, foodList);
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
        return meal;
    }

    public static List<MealGroupDTO> getAllMealGroup() throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        String sql = "SELECT  [MId],[price],[detail],[chefId],name "
                + "  FROM [MealGroup]"
                + " WHERE STATUS = 1 ";
        List<MealGroupDTO> meal = new ArrayList<>();

        try {
            con = DBConnect.getConnection();
            stm = con.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                List<String> image = getMealGroupImage(rs.getInt("Mid"));
                List<FoodDTO> foodList = FoodDAO.getFoodByMealId(rs.getInt("Mid"));
                meal.add(new MealGroupDTO(rs.getInt(1), rs.getDouble(2), rs.getString(3),
                        ChefDAO.getChefById(rs.getInt(4)), rs.getString(5), image, foodList));
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
        return meal;
    }

    public static List<MealGroupDTO> getMealGroupByTimelineAndDay(String timeline, String day) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        String sql = " SELECT mealId, m.price \n"
                + "    FROM MealGroupSession m \n"
                + "    JOIN Session s ON m.sessionId=s.SId \n"
                + "    JOIN MealGroup mg on mg.MId=m.mealId\n"
                + "    WHERE timeline=? AND day=? and mg.status=1 ";
        List<MealGroupDTO> meals = new ArrayList<>();

        try {
            con = DBConnect.getConnection();
            stm = con.prepareStatement(sql);
            stm.setString(1, timeline);
            stm.setString(2, day);
            rs = stm.executeQuery();
            MealGroupDTO meal;
            while (rs.next()) {
                meal = getMealGroupById(rs.getInt("mealId"));
                if (rs.getString("price") != null) {
                    meal.setPrice(Double.parseDouble(rs.getString("price")));
                }
                meals.add(meal);
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
        return meals;
    }

    public static MealGroupDTO getMealGroupByIdAndTimelineAndDay(String timeline, String day, int mealId) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        String sql = " SELECT mealId, price "
                + "  FROM MealGroupSession m "
                + "  JOIN Session s ON m.sessionId=s.SId "
                + "  WHERE timeline=? AND day=? AND m.mealId=?";
        MealGroupDTO meal = null;
        try {
            con = DBConnect.getConnection();
            stm = con.prepareStatement(sql);
            stm.setString(1, timeline);
            stm.setString(2, day);
            stm.setInt(3, mealId);
            rs = stm.executeQuery();

            if (rs.next()) {
                meal = getMealGroupById(rs.getInt("mealId"));
                if (rs.getString("price") != null) {
                    meal.setPrice(Double.parseDouble(rs.getString("price")));
                }
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
        return meal;
    }

    public static MealGroupDTO getMealGroupByIdInsession(int id) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        String sql = " SELECT mealId, price "
                + "  FROM MealGroupSession m "
                + "  JOIN Session s ON m.sessionId=s.SId "
                + "  WHERE mealId=? ";
        MealGroupDTO meal = null;

        try {
            con = DBConnect.getConnection();
            stm = con.prepareStatement(sql);
            stm.setInt(1, id);
            rs = stm.executeQuery();
            if (rs.next()) {
                meal = getMealGroupById(rs.getInt("mealId"));
                if (rs.getString("price") != null) {
                    meal.setPrice(Double.parseDouble(rs.getString("price")));
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
        return meal;
    }

    public static MealGroupDTO getMealTop1(String day,String timeline) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        String sql = " SELECT top (1) MId \n"
                + "    FROM [MealGroup] join \n"
                + "    MealGroupSession on MealGroup.MId=MealGroupSession.mealId \n"
                + "    join Session on MealGroupSession.sessionId=Session.SId\n"
                + "    WHERE MealGroup.status =1 and day=? and timeline=?\n"
                + "    ORDER BY soldNumber desc";
        MealGroupDTO meal = null;

        try {
            con = DBConnect.getConnection();
            stm = con.prepareStatement(sql);
            stm.setString(1, day);
            stm.setString(2, timeline);
            rs = stm.executeQuery();
            if (rs.next()) {
                meal = getMealGroupByIdInsession(rs.getInt("MId"));
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
        return meal;
    }

    public static List<MealGroupDTO> searchMealByDay(String search, String day, String timeline) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        String sql = "   SELECT mealSession.mealId, mealSession.price \n"
                + "  FROM Session\n"
                + "  JOIN (select MealGroupSession.mealId, MealGroupSession.sessionId,MealGroupSession.price\n"
                + "               FROM  MealGroup\n"
                + "               JOIN MealGroupSession ON MealGroup.MId = MealGroupSession.mealId\n"
                + "               WHERE MealGroup.name like ? ) mealSession ON mealSession.sessionId=Session.SId\n"
                + "  where Session.day=? and Session.timeline=?";
        List<MealGroupDTO> meals = new ArrayList<>();

        try {
            con = DBConnect.getConnection();
            stm = con.prepareStatement(sql);
            stm.setString(1, "%" + search + "%");
            stm.setString(2, day);
            stm.setString(3, timeline);
            rs = stm.executeQuery();
            MealGroupDTO meal;
            while (rs.next()) {
                meal = getMealGroupById(rs.getInt("mealId"));
                if (rs.getString("price") != null) {
                    meal.setPrice(Double.parseDouble(rs.getString("price")));
                }
                meals.add(meal);
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
        return meals;
    }

    public static int getLastIdMealgroup() throws SQLException {
        Connection con = null;
        String sql = "  SELECT TOP (1) MId \n"
                + "  FROM MealGroup\n"
                + "  ORDER BY MId desc";
        Statement stm = null;
        ResultSet rs = null;
        try {
            con = DBConnect.getConnection();
            stm = con.createStatement();
            rs = stm.executeQuery(sql);
            if (rs.next()) {
                return rs.getInt("Mid");
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
        return -1;
    }

    public static boolean insertMealGroup(double price, int chefId, String name, String detail, int soldnumber, String imageName) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        String sql = "    INSERT INTO MealGroup ([price],[chefId],[name],[detail],[soldNumber],[status]) "
                + "       values(?,?,?,?,?,?) ";
        try {
            con = DBConnect.getConnection();
            stm = con.prepareStatement(sql);
            stm.setDouble(1, price);
            stm.setInt(2, chefId);
            stm.setString(3, name);
            stm.setString(4, detail);
            stm.setInt(5, soldnumber);
            stm.setInt(6, 1);

            if (stm.executeUpdate() > 0) {
                int lastMealId = getLastIdMealgroup();
                if (ImageDAO.insertMealImage(imageName, lastMealId)) {
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

    public static boolean updateMealGroup(int MId, String name, double price, int chefId, String detail) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        String sql = "UPDATE [MealGroup]\n"
                + " SET [price] =? , [chefId] = ?, [name]=?,[detail]=?\n"
                + " WHERE [MId]=? ";
        try {
            con = DBConnect.getConnection();
            stm = con.prepareStatement(sql);
            stm.setDouble(1, price);
            stm.setInt(2, chefId);
            stm.setString(3, name);
            stm.setString(4, detail);
            stm.setInt(5, MId);
            if (stm.executeUpdate() > 0) {
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

    public static int getSoldNumber(int id) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        String sql = "SELECT [soldNumber]\n"
                + "FROM [MealGroup]\n"
                + "where MId=? ";
        try {
            con = DBConnect.getConnection();
            stm = con.prepareStatement(sql);
            stm.setInt(1, id);
            rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getInt("soldNumber");
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
        return -1;
    }

    public static boolean setSoldNumber(int id, int sold) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        String sql = "UPDATE [MealGroup]\n"
                + "SET [soldNumber]=? \n"
                + "WHERE [MId]=? ";
        try {
            con = DBConnect.getConnection();
            stm = con.prepareStatement(sql);
            stm.setInt(1, sold);
            stm.setInt(2, id);
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

    public static boolean deleteMeal(int mealId) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        String sql = "update MealGroup\n"
                + "  set status=?\n"
                + "  where MId=?";
        int effectRow;
        try {
            con = DBConnect.getConnection();
            stm = con.prepareStatement(sql);
            stm.setInt(1, 0);
            stm.setInt(2, mealId);

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

    public static boolean deleteMealItems(int mealId) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        String sql = "DELETE FROM  FoodItem\n"
                + "  WHERE mealId =?";
        int effectRow;
        try {
            con = DBConnect.getConnection();
            stm = con.prepareStatement(sql);
            stm.setInt(1, mealId);

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

    public static boolean insertFoodIntoMeal(int foodId, int mealId) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        String sql = "  INSERT INTO FoodItem(foodId,mealId) values(? ,?)";
        int effectRow;
        try {
            con = DBConnect.getConnection();
            stm = con.prepareStatement(sql);
            stm.setInt(1, foodId);
            stm.setInt(2, mealId);
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

    public static List<MealGroupDTO> getAllMealbySessionId(String Code) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        String sql = " SELECT MealGroupSession.mealId, MealGroupSession.price\n"
                + "  FROM [MealGroupSession]\n"
                + "  join Session on Session.SId=MealGroupSession.sessionId\n"
                + "  where Session.Code=? ";
        List<MealGroupDTO> meals = new ArrayList<>();

        try {
            con = DBConnect.getConnection();
            stm = con.prepareStatement(sql);
            stm.setString(1, Code);
            rs = stm.executeQuery();
            MealGroupDTO meal;
            while (rs.next()) {
                meal = getMealGroupById(rs.getInt("mealId"));
                if (rs.getString("price") != null) {
                    meal.setPrice(Double.parseDouble(rs.getString("price")));
                }
                meals.add(meal);
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
        return meals;
    }

    public static boolean deleteMealInSession(int SId) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        String sql = "DELETE from MealGroupSession \n"
                + "  WHERE sessionId=? ";
        try {
            con = DBConnect.getConnection();
            stm = con.prepareStatement(sql);
            stm.setInt(1, SId);
            if (stm.executeUpdate() > 0) {
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

    public static boolean insertMealInSession(int SId, int MId) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        String sql = "insert into MealGroupSession(mealId,sessionId) values(?,?)";
        try {
            con = DBConnect.getConnection();
            stm = con.prepareStatement(sql);
            stm.setInt(1, SId);
            stm.setInt(2, MId);

            if (stm.executeUpdate() > 0) {
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

    public static int getTotalMealInOrder(int orderId, String sessionCode) throws SQLException {
        Connection con = null;
        String sql = "SELECT count(OId) as total\n"
                + "                  FROM [Order]\n"
                + "                  join MealGroup on [Order].mealId=MealGroup.MId\n"
                + "				  join Session on [Order].sessionId=[Session].SId\n"
                + "                  where [Order].mealId=? and Session.Code= ? and [Order].[status]=2";
        PreparedStatement stm = null;
        ResultSet rs = null;
        int result = 0;
        try {
            con = DBConnect.getConnection();
            stm = con.prepareStatement(sql);
            stm.setInt(1, orderId);
            stm.setString(2, sessionCode);
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

    public static double getTotalMoneyByMeal(int orderId, String sessionCode) throws SQLException {
        Connection con = null;
        String sql = " SELECT sum([Order].price) as total\n"
                + "                  FROM [Order]\n"
                + "                  join MealGroup on [Order].mealId=MealGroup.MId\n"
                + "				  join Session on [Order].sessionId=[Session].SId\n"
                + "                  where [Order].mealId=? and Session.Code= ? and [Order].[status]=2";
        PreparedStatement stm = null;
        ResultSet rs = null;
        double result = 0;
        try {
            con = DBConnect.getConnection();
            stm = con.prepareStatement(sql);
            stm.setInt(1, orderId);
            stm.setString(2, sessionCode);
            rs = stm.executeQuery();
            if (rs.next()) {
                result = rs.getDouble("total");
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
    
    public static boolean isMealnameExistWhenUpdate(int MId, String name) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        String sql = "  select *\n"
                + "  from MealGroup\n"
                + "  where MealGroup.name= ? and MealGroup.status=? and MId != ? ";
        ResultSet rs= null;
        try {
            con = DBConnect.getConnection();
            stm = con.prepareStatement(sql);
            stm.setString(1, name);
            stm.setInt(2, 1);
            stm.setInt(3, MId);
            rs=stm.executeQuery();
            if (rs.next()) {
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
