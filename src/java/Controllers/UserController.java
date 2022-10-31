/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.Account.AccountDAO;
import Models.Account.AccountDTO;
import Models.Building.BuildingDAO;
import Models.Building.BuildingDTO;
import Models.Chef.ChefDAO;
import Models.Chef.ChefDTO;
import Models.Customer.CustomerDAO;
import Models.Customer.CustomerDTO;
import Models.MealGroup.MealGroupDAO;
import Models.MealGroup.MealGroupDTO;
import Models.Order.OrderDAO;
import Models.Order.OrderDTO;
import Models.Session.SessionDAO;
import Models.Session.SessionDTO;
import Models.Status.OrderStatus;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Acer
 */
@WebServlet(name = "userController", urlPatterns = {"/user"})
public class UserController extends HttpServlet {

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
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        String action = (String) request.getAttribute("action");
        switch (action) {
            case "login":
                request.getRequestDispatcher("/WEB-INF/Layers/show.jsp").forward(request, response);
                break;
            case "checkLogin":
                CheckLogin(request, response);
                break;
            case "logout":
                Logout(request, response);
                break;
            case "register":
                register(request, response);
                break;
            case "checkRegister":
                checkRegister(request, response);
                break;
            case "info":
                showUser(request, response);
                break;
            case "history":
                showHistory(request, response);
                break;
            case "verify":
                verify(request, response);
                break;
            case "editUser":
                editUser(request, response);
                break;
            case "cancelOrder":
                cancelOder(request, response);
                break;
            case "chef":
                showChef(request, response);
                break;
            case "newChef":
                newChef(request, response);
                break;
            case "editChef":
                editChef(request, response);
                break;
            case "customer":
                showCustomer(request, response);
                break;
            case "editCustomer":
                editCustomer(request, response);
                break;
        }
    }

    public void showUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        HttpSession session = request.getSession();
        CustomerDTO user = (CustomerDTO) session.getAttribute("user");
        if (user != null) {
            BuildingDTO building = BuildingDAO.getBuildingById(user.getAddress());
            request.setAttribute("location", building);
        }
        List<BuildingDTO> buildingList = BuildingDAO.getAllBuilding();
        request.setAttribute("buildingList", buildingList);
        request.getRequestDispatcher("/WEB-INF/Layers/about.jsp").forward(request, response);
    }

    public void cancelOder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        int orderId = Integer.parseInt(request.getParameter("orderId"));
        if (orderId > 0) {
            OrderDAO.updateOrderStatus(0, orderId);
        }
        response.sendRedirect("/comMeNau/user/history.do");
    }

    public void showChef(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        try {
            List<ChefDTO> chefList = ChefDAO.getAllChef();
            Map<Integer, BuildingDTO> bulding = new HashMap<>();
            chefList.forEach(c -> {
                try {
                    bulding.put(c.getId(), BuildingDAO.getBuildingById(c.getAddress()));
                } catch (SQLException ex) {
                    Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            request.setAttribute("bulding", bulding);
            request.setAttribute("chefList", chefList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.getRequestDispatcher("/WEB-INF/Layers/dashboard.jsp").forward(request, response);
    }

    public void newChef(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        List<BuildingDTO> buildingList = BuildingDAO.getAllBuilding();
        request.setAttribute("buildingList", buildingList);
        request.getRequestDispatcher("/WEB-INF/Layers/show.jsp").forward(request, response);
    }

    public void editChef(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        int chefId = Integer.parseInt(request.getParameter("chefId"));
        ChefDTO chef = ChefDAO.getChefById(chefId);
//        BuildingDTO building = BuildingDAO.getBuildingById(chef.getAddress());
        List<BuildingDTO> buildingList = BuildingDAO.getAllBuilding();
//        request.setAttribute("location", building);
        request.setAttribute("buildingList", buildingList);
        request.setAttribute("chef", chef);
        request.getRequestDispatcher("/WEB-INF/Layers/show.jsp").forward(request, response);
    }

    public void showCustomer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        try {
            List<CustomerDTO> customerList = CustomerDAO.getAllCustomer();
            Map<Integer, BuildingDTO> buildingList = new HashMap<>();

            customerList.forEach(c -> {
                try {
                    buildingList.put(c.getId(), BuildingDAO.getBuildingById(c.getAddress()));
                } catch (SQLException ex) {
                    Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            request.setAttribute("customerList", customerList);
            request.setAttribute("buildingList", buildingList);

        } catch (Exception e) {
            e.printStackTrace();
        }
        request.getRequestDispatcher("/WEB-INF/Layers/dashboard.jsp").forward(request, response);
    }

    public void editCustomer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        int customerId = Integer.parseInt(request.getParameter("customerId"));
        String message = request.getParameter("message");
        CustomerDTO customer = CustomerDAO.getCustomer(customerId);
        List<BuildingDTO> buildingList = BuildingDAO.getAllBuilding();
        request.setAttribute("buildingList", buildingList);
        request.setAttribute("customer", customer);
        request.getRequestDispatcher("/WEB-INF/Layers/show.jsp").forward(request, response);
    }

    public void showHistory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        HttpSession session = request.getSession();
        CustomerDTO user = (CustomerDTO) session.getAttribute("user");
        LocalDate local = LocalDate.now();
        if (user != null) {
            List<OrderDTO> orderList = OrderDAO.getOrderByCustomer(user.getId());
            if (orderList != null) {
                Map<Integer, String> status = new HashMap<>();
                Map<Integer, String> address = new HashMap<>();
                Map<Integer, String> timeline = new HashMap<>();
                Map<Integer, MealGroupDTO> meal = new HashMap<>();

                orderList.forEach(order -> {
                    String key = "_" + order.getStatus();
                    status.put(order.getId(), OrderStatus.valueOf(key).toString());
                    try {
                        address.put(order.getId(), BuildingDAO.getBuildingById(order.getAddress()).getName());
                        SessionDTO ses = SessionDAO.getSessionById(order.getSesssionId());
                        String time = ses.getFromto() + " " + ses.getDay() + "/" + local.getYear();
                        timeline.put(order.getId(), time);
                        meal.put(order.getId(), MealGroupDAO.getMealGroupById(order.getMealId()));
                    } catch (SQLException ex) {
                        Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
                request.setAttribute("status", status);
                request.setAttribute("address", address);
                request.setAttribute("timeline", timeline);
                request.setAttribute("meal", meal);
            }
            request.setAttribute("orderList", orderList);

        }
        request.getRequestDispatcher("/WEB-INF/Layers/about.jsp").forward(request, response);
    }

    public void verify(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        int accountId = Integer.parseInt(request.getParameter("id"));
        AccountDAO.VerifyAccount(accountId);
        request.setAttribute("information", "Verify Successfully!!");
        request.getRequestDispatcher("user/login.do").forward(request, response);
    }

    public static void CheckLogin(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        String account = request.getParameter("account");
        String password = request.getParameter("password");
        HttpSession session = request.getSession(true);
        AccountDTO acc;
        if (account.isEmpty() || password.isEmpty()) {
            try {
                request.setAttribute("account", account);
                request.setAttribute("password", password);
                request.setAttribute("message", "Incorrect Account or Password!");
                request.getRequestDispatcher("user/login.do").forward(request, response);
            } catch (ServletException | IOException ex) {
                Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            acc = AccountDAO.getAccount(account, password);
            if (acc == null) {
                try {
                    request.setAttribute("account", account);
                    request.setAttribute("password", password);
                    request.setAttribute("message", "Incorrect Account or Password!");
                    request.getRequestDispatcher("user/login.do").forward(request, response);
                } catch (ServletException | IOException ex) {
                    Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (acc.getVerify() == false) {
                try {
                    request.setAttribute("account", account);
                    request.setAttribute("password", password);
                    request.setAttribute("message", "Very Account in your Email first");
                    request.getRequestDispatcher("user/login.do").forward(request, response);
                } catch (ServletException | IOException ex) {
                    Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                try {
                    if (acc.getRole().equals("2")) {
                        CustomerDTO cus = CustomerDAO.getCustomerByAccount(acc.getId());
                        session.setAttribute("user", cus);
                        session.setAttribute("role", "2");
                        request.getRequestDispatcher("/home/homePage.do").forward(request, response);
                    } else if (acc.getRole().equals("1")) {
                        CustomerDTO admin = new CustomerDTO(0, "Admin", "", 0, "");
                        session.setAttribute("user", admin);
                        session.setAttribute("role", "1");
                        request.getRequestDispatcher("/food/foodPage.do").forward(request, response);
                    }
                } catch (ServletException | IOException ex) {
                    Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public static void Logout(HttpServletRequest request, HttpServletResponse response) {
        try {
            HttpSession session = request.getSession();
            session.invalidate();
            request.getRequestDispatcher("/home/homePage.do").forward(request, response);
        } catch (ServletException | IOException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void register(HttpServletRequest request, HttpServletResponse response) {
        try {
            List<BuildingDTO> buildingList = BuildingDAO.getAllBuilding();
            request.setAttribute("buildList", buildingList);
            request.getRequestDispatcher("/WEB-INF/Layers/show.jsp").forward(request, response);
        } catch (ServletException | IOException | SQLException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void editUser(HttpServletRequest request, HttpServletResponse response) {
        try {
            HttpSession session = request.getSession();
            request.setCharacterEncoding("UTF-8");
            int userId = Integer.parseInt(request.getParameter("userId"));
            String name = request.getParameter("name");
            String phone = request.getParameter("phone");
            int LocationId = Integer.parseInt(request.getParameter("address"));
            if (CustomerDAO.updateCustomer(userId, name, phone, LocationId)) {
                CustomerDTO user = (CustomerDTO) session.getAttribute("user");
                user.setName(name);
                user.setAddress(LocationId);
                user.setPhone(phone);
                session.setAttribute("user", user);
                response.sendRedirect("/comMeNau/user/info.do");
            }
        } catch (IOException | SQLException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void checkRegister(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        String account = request.getParameter("account");
        String name = request.getParameter("name");
        String passWord = request.getParameter("password");
        String checkPass = request.getParameter("checkPassword");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        int address = Integer.parseInt(request.getParameter("location"));

        if (account.isBlank() || passWord.isBlank() || checkPass.isBlank()) {
            try {
                request.setAttribute("message", "Incorrect Account or Password!");
                request.setAttribute("account", account);
                request.setAttribute("name", name);
                request.setAttribute("email", email);
                request.setAttribute("phone", phone);
                request.setAttribute("location", address);
                request.getRequestDispatcher("/user/register.do").forward(request, response);
            } catch (ServletException | IOException ex) {
                Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (AccountDAO.CheckExistAccount(account)) {
            request.setAttribute("message", "User is already existed !! ");
            request.setAttribute("account", account);
            request.setAttribute("name", name);
            request.setAttribute("email", email);
            request.setAttribute("phone", phone);
            request.setAttribute("location", address);
            try {
                request.getRequestDispatcher("/user/register.do").forward(request, response);
            } catch (ServletException | IOException ex) {
                Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            if (!checkPass.equals(passWord)) {
                try {
                    request.setAttribute("message", "Check Password again!");
                    request.setAttribute("account", account);
                    request.setAttribute("password", passWord);
                    request.setAttribute("checkPassword", checkPass);
                    request.setAttribute("name", name);
                    request.setAttribute("email", email);
                    request.setAttribute("phone", phone);
                    request.setAttribute("location", address);
                    request.getRequestDispatcher("user/register.do").forward(request, response);
                } catch (ServletException | IOException ex) {
                    Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                try {
                    System.out.println(name);
                    System.out.println(name.length());
                    if (name.length() < 6 || name.length() > 40) {
                        request.setAttribute("message", "name must be in range[6-40]");
                        request.setAttribute("account", account);
                        request.setAttribute("password", passWord);
                        request.setAttribute("name", name);
                        request.setAttribute("checkPassword", checkPass);
                        request.setAttribute("email", email);
                        request.setAttribute("phone", phone);
                        request.setAttribute("location", address);
                        request.getRequestDispatcher("/user/register.do").forward(request, response);
                    } else {
                        CustomerDAO.insertCustomer(name, phone, address, email, account, passWord);
                        String title = "Verify Account!!";
                        String message = "<a href=\"http://localhost:8080/comMeNau/user/verify.do?id="+AccountDAO.getCurrentId()+"\">VeriFy Account</a>";
                            AccountDAO.sendEmmail(email, "ntthuc321@gmail.com", "igaajhaauawyrusd", message, title);
                        request.setAttribute("message", "Verify your Account before login");
                        request.getRequestDispatcher("/user/register.do").forward(request, response);
                    }
                } catch (SQLException | ServletException | IOException ex) {
                    Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
    //note nho lam

}
