/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.Building.BuildingDAO;
import Models.Building.BuildingDTO;
import Models.Category.CategoryDAO;
import Models.Category.CategoryDTO;
import Models.Chef.ChefDAO;
import Models.Chef.ChefDTO;
import Models.Customer.CustomerDAO;
import Models.Customer.CustomerDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author PC
 */
@WebServlet(name = "AdminController", urlPatterns = {"/Admin"})
public class AdminController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String action = (String) request.getAttribute("action");
        switch (action) {
            case "updateChef":
                updateChef(request, response);
                break;
            case "deleteChef":
                deleteChef(request, response);
                break;
            case "newChef":
                newChef(request, response);
                break;
            case "updateCustomer":
                updateCustomer(request, response);
                break;
            case "updateBuilding":
                updateBuilding(request, response);
                break;
            case "newBuilding":
                newBuilding(request, response);
                break;
            case "deleteBuilding":
                deleteBuilding(request, response);
                break;

        }
    }

    public void updateChef(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println(request.getParameter("chefId"));
        int chefId = Integer.parseInt(request.getParameter("chefId"));
        String chefName = request.getParameter("chefName");
        String chefPhone = request.getParameter("chefPhone");
        int address = Integer.parseInt(request.getParameter("address"));
        String email = request.getParameter("chefEmail");
        double salary = Double.parseDouble(request.getParameter("chefSalary"));
        try {
            if (ChefDAO.updateChef(chefId, chefName, chefPhone, address, email, salary)) {
                request.setAttribute("message", "Cập Nhật Thành Công!!");
                request.getRequestDispatcher("/user/editChef.do?chefId=" + chefId).forward(request, response);
            } else {
                request.setAttribute("message", "Cập Nhật Thất Bại!!");
                request.getRequestDispatcher("/user/editChef.do?chefId=" + chefId).forward(request, response);
            }

        } catch (SQLException ex) {
            Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void newChef(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String chefName = request.getParameter("chefName");
        String chefPhone = request.getParameter("chefPhone");
        int address = Integer.parseInt(request.getParameter("address"));
        String email = request.getParameter("chefEmail");
        double salary = Double.parseDouble(request.getParameter("chefSalary"));
        try {
            if (ChefDAO.insertChef(chefName, chefPhone, email, salary, address)) {
                
                request.setAttribute("message", "Thêm Thành Công!!");
                request.getRequestDispatcher("/user/newChef.do").forward(request, response);
            } else {
                request.setAttribute("message", "Thêm Thất Bại!!");
                request.getRequestDispatcher("/user/newChef.do").forward(request, response);
            }

        } catch (SQLException ex) {
            Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void newBuilding(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String Name = request.getParameter("buildingName");
        try {
            if (!BuildingDAO.buildingIsExist(Name)) {
                BuildingDAO.insertBuilding(Name);
                request.setAttribute("message", "Tạo Thành công!!");
            } else {
                request.setAttribute("message", "Tên đã tồn tại!!");
                request.setAttribute("buildingName", Name);
            }
            request.getRequestDispatcher("/home/newBuilding.do").forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void deleteChef(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int chefId = Integer.parseInt(request.getParameter("chefId"));
        PrintWriter out = response.getWriter();
        try {
            if (ChefDAO.deleteChef(chefId)) {
                AtomicInteger autoCount = new AtomicInteger(0);
                List<ChefDTO> chefList = ChefDAO.getAllChef();
                System.out.println("hello");
                chefList.forEach(c -> {
                    int count = autoCount.getAndIncrement();
                    try {
                        out.println("<tr style=\"background: #fff;\">\n"
                                + "                                    <td>\n"
                                + "                                        " + count + "\n"
                                + "                                    </td>\n"
                                + "                                    <td>\n"
                                + "                                        " + c.getName() + "\n"
                                + "                                    </td>\n"
                                + "                                    <td>\n"
                                + "                                        " + c.getPhone() + "\n"
                                + "                                    </td>\n"
                                + "                                    <td>\n"
                                + "                                        " + BuildingDAO.getBuildingById(c.getAddress()).getName() + "\n"
                                + "                                    </td>\n"
                                + "                                    <td>\n"
                                + "                                        " + c.getEmail() + "\n"
                                + "                                    </td>\n"
                                + "                                    <td>\n"
                                + "                                            " + c.getSalary() + "\n"
                                + "                                    </td>\n"
                                + "                                    <td>\n"
                                + "                                        <a href=\"/comMeNau/user/editChef.do?chefId=" + c.getId() + "\" class=\"text-decoration-none\">Edit</a>\n"
                                + "                                    </td>\n"
                                + "                                    <td style=\"padding: 5px 0 0 0;\">\n"
                                + "                                        <button class=\"trash-btn\" value=\"${c.id}\" onclick=\"deleteChef(this)\" >\n"
                                + "                                            <i class=\"fa-solid fa-trash-can\"></i>\n"
                                + "                                        </button>\n"
                                + "                                    </td>\n"
                                + "                                </tr>");
                    } catch (SQLException ex) {
                        Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
            }

        } catch (SQLException ex) {
            Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void deleteBuilding(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int buildingId = Integer.parseInt(request.getParameter("buildingId"));
        PrintWriter out = response.getWriter();
        try {
            if (BuildingDAO.deleteBuilding(buildingId)) {
                AtomicInteger autoCount = new AtomicInteger(0);
                List<BuildingDTO> buildingList = BuildingDAO.getAllBuilding();
                buildingList.forEach(b -> {
                    int count = autoCount.getAndIncrement();
                    out.println("<tr style=\"background: #fff;\">\n"
                            + "                                    <td>\n"
                            + "                                        " + count + "\n"
                            + "                                    </td>\n"
                            + "                                    <td>\n"
                            + "                                        " + b.getName() + "\n"
                            + "                                    </td>\n"
                            + "                                    <td>\n"
                            + "                                        <button id=\"myBtn\" value=\"" + b.getbId() + "\" class=\"view-btn\" onclick=\"showCategoryDetail(this)\" >Edit</button>\n"
                            + "                                    </td>\n"
                            + "                                    <td style=\"padding: 5px 0 0 0;\">\n"
                            + "                                        <button class=\"trash-btn\" value=\"" + b.getbId() + "\" onclick=\"deleteBuilding(this)\" >\n"
                            + "                                            <i class=\"fa-solid fa-trash-can\"></i>\n"
                            + "                                        </button>\n"
                            + "                                    </td>\n"
                            + "                                </tr>");
                });
            }

        } catch (SQLException ex) {
            Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void updateBuilding(HttpServletRequest request, HttpServletResponse response) {
        try {
            HttpSession session = request.getSession();
            PrintWriter out = response.getWriter();
            request.setCharacterEncoding("UTF-8");
            int buildingId = Integer.parseInt(request.getParameter("bId"));
            String buildingName = request.getParameter("name");
            System.out.println(buildingName);
            System.out.println(BuildingDAO.checkNameisExist(buildingName));
            BuildingDTO building = new BuildingDTO(buildingId, buildingName);
            if (BuildingDAO.checkNameisExist(buildingName)) {
                building.setName(BuildingDAO.getBuildingById(buildingId).getName());
                request.setAttribute("name", buildingName);
                request.setAttribute("building", building);
                request.setAttribute("message", "Tên đã tồn tại!!");
            } else {
                request.setAttribute("name", buildingName);
                request.setAttribute("building", building);
                request.setAttribute("message", "Cập nhật thành công!!");
                BuildingDAO.updateBuilding(buildingId, buildingName);

            }
            request.setAttribute("controller", "home");
            request.setAttribute("action", "updateBuilding");
            request.getRequestDispatcher("/WEB-INF/Layers/show.jsp").forward(request, response);
        } catch (IOException | SQLException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ServletException ex) {
            Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateCustomer(HttpServletRequest request, HttpServletResponse response) {
        try {
            HttpSession session = request.getSession();
            request.setCharacterEncoding("UTF-8");
            int userId = Integer.parseInt(request.getParameter("userId"));
            String name = request.getParameter("customerName");
            String phone = request.getParameter("customerPhone");
            String email = request.getParameter("customerEmail");
            int LocationId = Integer.parseInt(request.getParameter("address"));
            if (CustomerDAO.updateCustomer(userId, name, phone, LocationId, email)) {
                CustomerDTO cus = new CustomerDTO(userId, name, phone, LocationId, email);
                List<BuildingDTO> buildingList = BuildingDAO.getAllBuilding();
                request.setAttribute("buildingList", buildingList);
                request.setAttribute("message", "Lưu thành công!!");
                request.setAttribute("customer", cus);
                request.setAttribute("action", "editCustomer");
                request.setAttribute("controller", "user");
                request.getRequestDispatcher("/WEB-INF/Layers/show.jsp").forward(request, response);
            } else {
                CustomerDTO cus = new CustomerDTO(userId, name, phone, LocationId, email);
                List<BuildingDTO> buildingList = BuildingDAO.getAllBuilding();
                request.setAttribute("buildingList", buildingList);
                request.setAttribute("customer", cus);
                request.setAttribute("action", "editCustomer");
                request.setAttribute("controller", "user");
                request.setAttribute("message", "Có lỗi xảy ra!!");
                request.getRequestDispatcher("/WEB-INF/Layers/show.jsp").forward(request, response);
            }
        } catch (IOException | SQLException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ServletException ex) {
            Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
