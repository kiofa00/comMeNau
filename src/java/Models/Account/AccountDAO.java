/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models.Account;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import ultils.DBConnect;
import javax.mail.internet.*;
import javax.mail.*;

/**
 *
 * @author PC
 */
public class AccountDAO implements Serializable {

    public static AccountDTO getAccount(String account, String pass) throws SQLException {
        Connection con = null;
        String sqlGetAccount = "SELECT [AId],[role],[Verify] "
                + "  FROM [Account] "
                + "  where account=? and password=?";
        PreparedStatement stm = null;
        ResultSet rs = null;
        AccountDTO acc = null;
        try {
            con = DBConnect.getConnection();
            stm = con.prepareStatement(sqlGetAccount);
            stm.setString(1, account);
            stm.setString(2, pass);
            rs = stm.executeQuery();
            if (rs.next()) {
                acc = new AccountDTO(rs.getInt(1), rs.getString(2), rs.getBoolean(3));
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
        return acc;
    }

    public static boolean CheckExistAccount(String acc) throws SQLException {
        Connection con = null;
        String sqlGetAccount = "SELECT [AId] "
                + "  FROM [Account] "
                + "  where account=? ";
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBConnect.getConnection();
            stm = con.prepareStatement(sqlGetAccount);
            stm.setString(1, acc);
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

    public static boolean inserAccount(String account, String pass, int role) throws SQLException {
        Connection con = null;
        String sqlInsertAccount = "Insert Into Account values(?,?,?,?)";
        PreparedStatement stm = null;
        int count = 0;
        try {
            con = DBConnect.getConnection();
            stm = con.prepareStatement(sqlInsertAccount);
            stm.setString(1, account);
            stm.setString(2, pass);
            stm.setInt(3, role);
            stm.setBoolean(4, false);
            count = stm.executeUpdate();
            if (count != 0) {
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

    public static int getCurrentId() throws SQLException {
        Connection con = null;
        String sqlInsertAccount = "SELECT IDENT_CURRENT( '[Account]' )";
        PreparedStatement stm = null;
        int cur = 0;
        ResultSet rs = null;
        try {
            con = DBConnect.getConnection();
            stm = con.prepareStatement(sqlInsertAccount);
            rs = stm.executeQuery();
            if (rs.next()) {
                cur = rs.getInt(1);
            }

        } finally {

            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return cur;
    }

    public static void sendEmmail(String to, final String user, final String pass,String message, String title) {
        Properties pros = new Properties();
        pros.put("mail.smtp.host", "smtp.gmail.com");
        pros.put("mail.smtp.port", "587");
        pros.put("mail.smtp.auth", "true");
        pros.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getDefaultInstance(pros, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, pass);
            }
        });
        try {
            MimeMessage mess = new MimeMessage(session);
            mess.setFrom(new InternetAddress(user));
            mess.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            mess.setSubject(title,"UTF-8");
            //String message = "<a href=\"http://localhost:8080/comMeNau/user/verify.do?id=" + accountId + "\">VeriFy Account</a>";
            mess.setContent(message, "text/html ; charset=UTF-8");

            Transport.send(mess);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public static void VerifyAccount(int id) throws SQLException {
        Connection con = null;
        String sqlInsertAccount = "  UPDATE [Account] "
                + "SET [Verify] = 'true' "
                + "WHERE Aid=? ;";
        PreparedStatement stm = null;

        try {
            con = DBConnect.getConnection();
            stm = con.prepareStatement(sqlInsertAccount);
            stm.setInt(1, id);
            stm.executeUpdate();
        } finally {

            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }
}
