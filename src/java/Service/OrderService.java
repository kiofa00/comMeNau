/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Models.Building.BuildingDAO;
import Models.Building.BuildingDTO;
import Models.MealGroup.MealGroupDAO;
import Models.MealGroup.MealGroupDTO;
import Models.Order.OrderDAO;
import Models.Order.OrderDTO;
import Models.Payment.PaymentDAO;
import Models.Payment.PaymentDTO;
import Models.Session.SessionDAO;
import Models.Session.SessionDTO;
import Models.Status.OrderStatus;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author PC
 */
public class OrderService {

    public static void CheckOrder(HttpServletRequest request, HttpServletResponse response) {
        try {
            int OrderId = Integer.parseInt(request.getParameter("id"));

            String Email = request.getParameter("email");
            int oId = OrderDAO.checkOrder(OrderId, Email);
            OrderDTO order = null;
            BuildingDTO build = null;
            MealGroupDTO meal = null;
            PaymentDTO payMethod = null;
            String status = null;

            SessionDTO session = new SessionDTO();
            if (oId > 0) {
                order = OrderDAO.getOrderById(oId);
                String key = "_" + order.getStatus();
                build = BuildingDAO.getBuildingById(order.getAddress());
                meal = MealGroupDAO.getMealGroupById(order.getMealId());
                status = OrderStatus.valueOf(key).toString();
                payMethod = PaymentDAO.getPaymentById(order.getPayId());
                session = SessionDAO.getSessionById(order.getSesssionId());
            }

            request.setAttribute("order", order);
            request.setAttribute("build", build);
            request.setAttribute("meal", meal);
            request.setAttribute("status", status);
            request.setAttribute("payMethod", payMethod);
            request.setAttribute("session", session);
            request.setAttribute("orderId", OrderId);
            request.setAttribute("email", Email);
            if (order == null) {
                request.setAttribute("message", "Không tìm Thấy Đơn hàng!!");
            }
            request.getRequestDispatcher("/home/checkOrderPage.do").forward(request, response);
        } catch (NumberFormatException e) {
            try {
                e.printStackTrace();
                request.setAttribute("message", "Không tìm Thấy Đơn hàng!!");
                request.getRequestDispatcher("/home/checkOrderPage.do").forward(request, response);
                
            } catch (ServletException | IOException ex) {
                Logger.getLogger(OrderService.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException | ServletException | IOException | ClassNotFoundException ex) {
            Logger.getLogger(OrderService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//end checkOrder

    public static void CheckOrderPage(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.getRequestDispatcher("/WEB-INF/Layers/show.jsp").forward(request, response);
        } catch (ServletException ex) {
            Logger.getLogger(OrderService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(OrderService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
