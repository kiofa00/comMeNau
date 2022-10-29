/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models.Payment;

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
public class PaymentDAO implements Serializable {

    public static List<PaymentDTO> getPayment() throws SQLException {
        Connection con = null;
        String sql = "SELECT [PId],[type]\n"
                + "  FROM [Payment] ";
        PreparedStatement stm=null;
        ResultSet rs =null;
        List<PaymentDTO> list=new ArrayList<>();
        try {
            con=DBConnect.getConnection();
            stm=con.prepareStatement(sql);
            rs=stm.executeQuery();
            while(rs.next()){
                list.add(new PaymentDTO(rs.getInt("PId"), rs.getString("type")));
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
        return list;
    }
    public static PaymentDTO getPaymentById(int PId) throws SQLException {
        Connection con = null;
        String sql = "SELECT [PId],[type]\n"
                + "  FROM [Payment]"
                + "where PId= ? ";
        PreparedStatement stm=null;
        ResultSet rs =null;
        PaymentDTO pay=null;
        try {
            con=DBConnect.getConnection();
            stm=con.prepareStatement(sql);
            stm.setInt(1, PId);
            rs=stm.executeQuery();
            if(rs.next()){
                pay= new PaymentDTO(PId, rs.getString("type"));
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
        return pay;
    }
}
