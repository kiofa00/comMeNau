/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models.Order;

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
public class OrderDAO implements Serializable {

    public static boolean insertOrder(OrderDTO order, String cusName, String phone, String email) throws SQLException {
        Connection con = null;
        String sql = "INSERT INTO [Order]([address],[orderDetail],[mealId],[customerId],[price],[status],[sessionId],[PId],[customerName],[phone],[email]) "
                + "              VALUES (?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement stm = null;
        int affectRow = 0;
        try {
            con = DBConnect.getConnection();
            stm = con.prepareStatement(sql);
            stm.setInt(1, order.getAddress());
            stm.setString(2, order.getOrderDetail());
            stm.setInt(3, order.getMealId());
            stm.setInt(4, order.getCustomerId());
            stm.setDouble(5, order.getPrice());
            stm.setInt(6, order.getStatus());
            stm.setInt(7, order.getSesssionId());
            stm.setInt(8, order.getPayId());
            stm.setString(9, cusName);
            stm.setString(10, phone);
            stm.setString(11, email);
            affectRow = stm.executeUpdate();
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return affectRow != 0;
    }

    public static boolean insertOrder2(int address, String note, int mealId, String cusName, String phone, double price, int status, int sessionId, int payId, String email) throws SQLException {
        Connection con = null;
        String sql = "INSERT INTO [Order]([address],[orderDetail],[mealId],[customerName],[price],[status],[sessionId],[PId],[phone],[email]) "
                + "              VALUES (?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement stm = null;
        int affectRow = 0;
        try {
            con = DBConnect.getConnection();
            stm = con.prepareStatement(sql);
            stm.setInt(1, address);
            stm.setString(2, note);
            stm.setInt(3, mealId);
            stm.setString(4, cusName);
            stm.setDouble(5, price);
            stm.setInt(6, status);
            stm.setInt(7, sessionId);
            stm.setInt(8, payId);
            stm.setString(9, phone);
            stm.setString(10, email);
            affectRow = stm.executeUpdate();
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return affectRow != 0;
    }

    public static OrderDTO getOrderById(int id) throws SQLException {
        Connection con = null;
        String sql = "SELECT  [OId],[address]\n"
                + "      ,[orderDetail]\n"
                + "      ,[mealId]\n"
                + "      ,[customerId]\n"
                + "      ,[price]\n"
                + "      ,[status]\n"
                + "      ,[sessionId]\n"
                + "      ,[PId]\n"
                + "      ,[customerName]\n"
                + "      ,[phone]\n"
                + "  FROM [Order]"
                + " where OId= ? ";
        PreparedStatement stm = null;
        OrderDTO order = null;
        ResultSet rs = null;
        try {
            con = DBConnect.getConnection();
            stm = con.prepareStatement(sql);
            stm.setInt(1, id);
            rs = stm.executeQuery();
            if (rs.next()) {
                order = new OrderDTO(rs.getInt("OId"), rs.getInt("address"), rs.getString("orderDetail"), rs.getInt("mealId"),
                        rs.getInt("customerId"), rs.getDouble("price"), rs.getInt("status"), rs.getInt("sessionId"), rs.getInt("PId"),
                        rs.getString("customerName"), rs.getString("phone"));
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return order;
    }

    public static List<OrderDTO> getOrderByStatus(int status) throws SQLException {
        Connection con = null;
        String sql = " SELECT  [OId]\n"
                + "  FROM [Order]\n"
                + "  WHERE status = ? ";
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<OrderDTO> orderList = new ArrayList<>();
        try {
            con = DBConnect.getConnection();
            stm = con.prepareStatement(sql);
            stm.setInt(1, status);
            rs = stm.executeQuery();
            while (rs.next()) {
                orderList.add(getOrderById(rs.getInt("OId")));
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return orderList;
    }

    public static List<OrderDTO> getOrderBySession(int status, String time, String day) throws SQLException {
        Connection con = null;
        String sql = " 		select *\n"
                + "		from [Order] join\n"
                + "		[Session] on [Order].sessionId=[Session].SId\n"
                + "		where [Order].status= ? and timeline=? and day=?";
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<OrderDTO> orderList = new ArrayList<>();
        try {
            con = DBConnect.getConnection();
            stm = con.prepareStatement(sql);
            stm.setInt(1, status);
            stm.setString(2, time);
            stm.setString(3, day);
            rs = stm.executeQuery();
            while (rs.next()) {
                orderList.add(getOrderById(rs.getInt("OId")));
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return orderList;
    }

    public static boolean updateOrderStatus(int status, int OId) throws SQLException {
        Connection con = null;
        String sql = " update [Order]\n"
                + "  set status=? \n"
                + "  where OId=?";
        PreparedStatement stm = null;
        List<OrderDTO> orderList = new ArrayList<>();
        try {
            con = DBConnect.getConnection();
            stm = con.prepareStatement(sql);
            stm.setInt(1, status);
            stm.setInt(2, OId);
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

    public static List<OrderDTO> getOrderByCustomer(int userId) throws SQLException {
        Connection con = null;
        String sql = " SELECT  [OId]\n"
                + "  FROM [Order] join\n"
                + "  Session on [Order].sessionId=Session.SId\n"
                + "  where [customerId]= ? "
                + " order by [OId] desc";
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<OrderDTO> orderList = new ArrayList<>();
        try {
            con = DBConnect.getConnection();
            stm = con.prepareStatement(sql);
            stm.setInt(1, userId);
            rs = stm.executeQuery();
            while (rs.next()) {
                orderList.add(getOrderById(rs.getInt("OId")));
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return orderList;
    }

    public static int lastOrder() throws SQLException {
        Connection con = null;
        String sql = " SELECT TOP (1) [OId]\n"
                + "  FROM [Order]\n"
                + "  order by OId desc";
        PreparedStatement stm = null;
        ResultSet rs = null;
        int result = 0;
        try {
            con = DBConnect.getConnection();
            stm = con.prepareStatement(sql);
            rs = stm.executeQuery();
            if (rs.next()) {
                result = rs.getInt("OId");
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

    public static int getTotalOrderByCus(int cusId, int sessionId) throws SQLException {
        Connection con = null;
        String sql = "select COUNT(OId) as total \n"
                + "  from [Order]\n"
                + "  where customerId=? and sessionId=?";
        PreparedStatement stm = null;
        ResultSet rs = null;
        int result = 0;
        try {
            con = DBConnect.getConnection();
            stm = con.prepareStatement(sql);
            stm.setInt(1, cusId);
            stm.setInt(2, sessionId);
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

    public static double getTotalMoneyByCus(int cusId, int sessionId) throws SQLException {
        Connection con = null;
        String sql = "select  sum(price) as [money]\n"
                + "  from [Order]\n"
                + "  where customerId=? and sessionId=?";
        PreparedStatement stm = null;
        ResultSet rs = null;
        double result = 0;
        try {
            con = DBConnect.getConnection();
            stm = con.prepareStatement(sql);
            stm.setInt(1, cusId);
            stm.setInt(2, sessionId);
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

    public static double getTotalMealByChef(int chefId, int sessionId) throws SQLException {
        Connection con = null;
        String sql = "SELECT count(OId)\n"
                + "  FROM [Order]\n"
                + "  join MealGroup on [Order].mealId=MealGroup.MId\n"
                + "  where [Order].sessionId=? and MealGroup.chefId=?";
        PreparedStatement stm = null;
        ResultSet rs = null;
        double result = 0;
        try {
            con = DBConnect.getConnection();
            stm = con.prepareStatement(sql);
            stm.setInt(1, chefId);
            stm.setInt(2, sessionId);
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

    public static int checkOrder(int orderId, String email) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        String sql = "SELECT [OId]\n"
                + "  FROM [Order]\n"
                + "  where email= ? and OId= ?";
        int OID = 0;
        try{
            con=DBConnect.getConnection();
            stm=con.prepareStatement(sql);
            stm.setString(1, email);
            stm.setInt(2, orderId);
            rs=stm.executeQuery();  
            if(rs.next()){
                OID=rs.getInt("OId");
            }
        }finally{
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
        return OID;
    }

   
}
