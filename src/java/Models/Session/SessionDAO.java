/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models.Session;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import ultils.DBConnect;

/**
 *
 * @author PC
 */
public class SessionDAO implements Serializable {

    public static SessionDTO getTimelineByDay(String day) throws SQLException {
        Connection con = null;
        String sql = " SELECT timeline "
                + "  FROM Session "
                + "  WHERE day=? and status=1";
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<Timeline> timeline = new ArrayList<>();
        SessionDTO session;
        try {
            con = DBConnect.getConnection();
            stm = con.prepareStatement(sql);
            stm.setString(1, day);
            rs = stm.executeQuery();
            while (rs.next()) {
                String[] fromTo = rs.getString("timeline").split("-");
                timeline.add(new Timeline(Integer.parseInt(fromTo[0]), Integer.parseInt(fromTo[1])));
            }
            Collections.sort(timeline, (o1, o2) -> {
                return o1.getFrom() - o2.getFrom(); //To change body of generated lambdas, choose Tools | Templates.
            });
            session = new SessionDTO(day, timeline);
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
        return session;
    }

    public static int getSessionIdByyCode(String code) throws SQLException {
        Connection con = null;
        String sql = " SELECT SId "
                + "  FROM Session "
                + "  WHERE code=? ";
        PreparedStatement stm = null;
        ResultSet rs = null;
        SessionDTO session;
        try {
            con = DBConnect.getConnection();
            stm = con.prepareStatement(sql);
            stm.setString(1, code);
            rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getInt("SId");
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
        return 0;
    }

    public static int getSessionId(String day, String timeline) throws SQLException {
        Connection con = null;
        String sql = " SELECT SId "
                + "  FROM Session "
                + "  WHERE day=? and timeline=?";
        PreparedStatement stm = null;
        ResultSet rs = null;
        SessionDTO session;
        try {
            con = DBConnect.getConnection();
            stm = con.prepareStatement(sql);
            stm.setString(1, day);
            stm.setString(2, timeline);
            rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getInt("SId");
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
        return 0;
    }

    public static boolean isSessionExist(String day, String timeline) throws SQLException {
        Connection con = null;
        String sql = " SELECT SId "
                + "  FROM Session "
                + "  WHERE day=? and timeline=? and status=1";
        PreparedStatement stm = null;
        ResultSet rs = null;
        SessionDTO session;
        try {
            con = DBConnect.getConnection();
            stm = con.prepareStatement(sql);
            stm.setString(1, day);
            stm.setString(2, timeline);
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

    public static boolean insertSession(String timeline, String day) throws SQLException {
        Connection con = null;
        String sql = " INSERT INTO [Session]([timeline],[day],status) "
                + " Values(?,?,?)";
        PreparedStatement stm = null;
        try {
            con = DBConnect.getConnection();
            stm = con.prepareStatement(sql);
            stm.setString(1, timeline);
            stm.setString(2, day);
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

    public static SessionDTO getSessionById(int SId) throws SQLException {
        Connection con = null;
        String sql = " SELECT  [SId],[timeline],[day] ,[Code]\n"
                + "  FROM [Session] "
                + " WHERE SId= ?";
        PreparedStatement stm = null;
        ResultSet rs = null;
        SessionDTO session = null;
        try {
            con = DBConnect.getConnection();
            stm = con.prepareStatement(sql);
            stm.setInt(1, SId);
            rs = stm.executeQuery();
            if (rs.next()) {
                session = new SessionDTO(rs.getString("day"), rs.getString("Code"), rs.getString("timeline"));
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
        return session;
    }

    public static int getLastSessionInsert() throws SQLException {
        Connection con = null;
        String sql = " SELECT TOP (1) [SId]\n"
                + "  FROM [Session]\n"
                + "  order by [SId] desc";
        PreparedStatement stm = null;
        ResultSet rs = null;
        int index = 0;
        try {
            con = DBConnect.getConnection();
            stm = con.prepareStatement(sql);
            rs = stm.executeQuery();
            if (rs.next()) {
                index = rs.getInt("SId");
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
        return index;
    }

    public static SessionDTO getLastSession() throws SQLException {
        Connection con = null;
        String sql = " SELECT TOP (1) [SId]\n"
                + "  FROM [Session]\n"
                + "  order by day desc";
        PreparedStatement stm = null;
        ResultSet rs = null;
        SessionDTO session = null;
        try {
            con = DBConnect.getConnection();
            stm = con.prepareStatement(sql);
            rs = stm.executeQuery();
            if (rs.next()) {
                session = getSessionById(rs.getInt("SId"));
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
        return session;
    }

    public static List<SessionDTO> getAllSession() throws SQLException {
        Connection con = null;
        String sql = " SELECT  [SId]\n"
                + "  FROM [Session] "
                + "where status =1 ";
        PreparedStatement stm = null;
        List<SessionDTO> sessionList = new ArrayList<>();
        ResultSet rs = null;
        List<Timeline> timeline = new ArrayList<>();
        SessionDTO session;
        try {
            con = DBConnect.getConnection();
            stm = con.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                sessionList.add(getSessionById(Integer.parseInt(rs.getString("SId"))));
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
        return sessionList;
    }

    public static SessionDTO getSessionByCode(String code) throws SQLException {
        Connection con = null;
        String sql = " SELECT  [SId]\n"
                + "    FROM [Session]\n"
                + "    WHERE Code=? ";
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<Timeline> timeline = new ArrayList<>();
        SessionDTO session = null;
        try {
            con = DBConnect.getConnection();
            stm = con.prepareStatement(sql);
            stm.setString(1, code);
            rs = stm.executeQuery();
            if (rs.next()) {
                session = getSessionById(Integer.parseInt(rs.getString("SId")));
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
        return session;
    }

    public static boolean updateSession(String timeline, String day,String code) throws SQLException {
        Connection con = null;
        String sql = " update  [Session]\n"
                + "  set timeline=? ,day=?\n"
                + "  where Code=? ";
        PreparedStatement stm = null;
        try {
            con = DBConnect.getConnection();
            stm = con.prepareStatement(sql);
            stm.setString(1, timeline);
            stm.setString(2, day);
            stm.setString(3, code);
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

    public static boolean deleteSession(String code) throws SQLException {
        Connection con = null;
        String sql = " update Session\n"
                + "  set [status]=0\n"
                + "  where code=?";
        PreparedStatement stm = null;
        try {
            con = DBConnect.getConnection();
            stm = con.prepareStatement(sql);
            stm.setString(1, code);
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

    public static List<SessionDTO> getSessionForOrder(int status) throws SQLException {
        Connection con = null;
        String sql = "   select SId\n"
                + "  from Session join (\n"
                + "		select [sessionId]\n"
                + "		from [Order] \n"
                + "		where status= ? \n"
                + "		group by [sessionId]\n"
                + "  ) SessId on SessId.sessionId =Session.SId";
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<SessionDTO> sessions = new ArrayList<>();
        try {
            con = DBConnect.getConnection();
            stm = con.prepareStatement(sql);
            stm.setInt(1, status);
            rs = stm.executeQuery();
            while (rs.next()) {
                sessions.add(getSessionById(rs.getInt("SId")));
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
        return sessions;
    }

}
