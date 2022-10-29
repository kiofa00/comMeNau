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
import Models.Food.FoodDAO;
import Models.Food.FoodDTO;
import Models.Image.ImageDAO;
import Models.MealGroup.MealGroupDAO;
import Models.MealGroup.MealGroupDTO;
import Models.Order.OrderDAO;
import Models.Order.OrderDTO;
import Models.Payment.PaymentDAO;
import Models.Payment.PaymentDTO;
import Models.Session.SessionDAO;
import Models.Session.SessionDTO;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileItemFactory;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;

/**
 *
 * @author Acer
 */
@WebServlet(name = "FoodController", urlPatterns = {"/food"})
@MultipartConfig
public class FoodController extends HttpServlet {

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
        System.out.println(action);
        switch (action) {
            case "foodPage":
                showFoodPage(request, response);
                break;
            case "sessionPage":
                showSessionPage(request, response);
                break;
            case "sessionDetail":
                sessionDetail(request, response);
                break;
            case "categoryPage":
                showCategoryPage(request, response);
                break;
            case "foodPageAjax":
                showFoodCatogaryAjax(request, response);
                break;
            case "buildingPage":
                showBuildingPage(request, response);
                break;
            case "orderPage":
                showOrderPage(request, response);
                break;
            case "search":
                search(request, response);
                break;
            case "searchAjax":
                searchByAjax(request, response);
                break;
            case "mealDetail":
                mealDetail(request, response);
                break;
            case "categoryDetail":
                categoryDetail(request, response);
                break;
            case "createFood":
                createFood(request, response);
                break;
            case "createMeal":
                createMeal(request, response);
                break;
            case "createSession":
                createSession(request, response);
                break;
            case "createCategory":
                createCategory(request, response);
                break;
            case "newFood":
                newFood(request, response);
                break;
            case "newMeal":
                newMeal(request, response);
                break;
            case "newSession":
                newSession(request, response);
                break;
            case "newCategory":
                newCategory(request, response);
                break;
            case "deleteFood":
                deleteFood(request, response);
                break;
            case "mealPage":
                showMealPage(request, response);
                break;
            case "deleteMeal":
                deleteMeal(request, response);
                break;
            case "deleteCategory":
                deleteCategory(request, response);
                break;
            case "deleteSession":
                deleteSession(request, response);
                break;
            case "editFood":
                editFood(request, response);
                break;
            case "editMeal":
                editMeal(request, response);
                break;
            case "editSession":
                editSession(request, response);
                break;
            case "updateSession":
                updateSession(request, response);
                break;
            case "updateCategory":
                updateCategory(request, response);
                break;
            case "updateFood":
                updateFood(request, response);
                break;
            case "updateMeal":
                updateMeal(request, response);
                break;
            case "showSessionDetail":
                showSessionDetail(request, response);
                break;
            case "loadSessionPage":
                loadSessionPage(request, response);
                break;
            case "acceptOrder":
                acceptOrder(request, response);
                break;
            case "denyOrder":
                denyOrder(request, response);
                break;
            case "searchOrder":
                searchOrder(request, response);
                break;
            case "showOrderByStatus":
                showOrderByStatus(request, response);
                break;
            case "updateCategoryPage":
                updateCategoryPage(request, response);
                break;

        }

    }

    public void showFoodPage(HttpServletRequest request, HttpServletResponse response) {
        try {
            HttpSession session = request.getSession();
            String category = request.getParameter("categoryId");
            int categoryId;
            if (category == null) {
                categoryId = 1;
            } else {
                categoryId = Integer.parseInt(category);
            }
            List<CategoryDTO> categoryList = CategoryDAO.getFoodCategory();
            List<FoodDTO> foodList;
            request.setAttribute("categoryList", categoryList);

            foodList = FoodDAO.getFoodByCategory(categoryId);
            session.setAttribute("categoryId", categoryId);
            request.setAttribute("foodList", foodList);

            request.getRequestDispatcher("/WEB-INF/Layers/dashboard.jsp").forward(request, response);
        } catch (ClassNotFoundException | SQLException | ServletException | IOException e) {
            Logger.getLogger(FoodController.class.getName()).log(Level.SEVERE, null, e);
        }

    }

    public void updateCategoryPage(HttpServletRequest request, HttpServletResponse response) {
        try {
            int categoryId = Integer.parseInt(request.getParameter("categoryId"));
            CategoryDTO category = CategoryDAO.getCategoryById(categoryId);
            request.setAttribute("category", category);
            request.getRequestDispatcher("/WEB-INF/Layers/show.jsp").forward(request, response);
        } catch (SQLException | ClassNotFoundException | ServletException | IOException ex) {
            Logger.getLogger(FoodController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void searchOrder(HttpServletRequest request, HttpServletResponse response) {
        try {
            HttpSession sess = request.getSession();
            PrintWriter out = response.getWriter();
            String timeline = request.getParameter("fromTo");
            String day = request.getParameter("day");
            int status = (int) sess.getAttribute("status");

            List<OrderDTO> orders = OrderDAO.getOrderBySession(status, timeline, day);

            orders.forEach(o -> {
                try {
                    out.println("<tr style=\"background: #fff;\">\n"
                            + "                                    <td>\n"
                            + "                                        " + o.getId() + "\n"
                            + "                                    </td>\n"
                            + "                                    <td>\n"
                            + "                                        " + o.getCusName() + "\n"
                            + "                                    </td>\n"
                            + "                                    <td>\n"
                            + "                                        " + BuildingDAO.getBuildingById(o.getAddress()).getName() + "\n"
                            + "                                    </td>\n"
                            + "                                    <td>\n"
                            + "                                        " + SessionDAO.getSessionById(o.getSesssionId()).getFromto() + "\n"
                            + "                                    </td>\n"
                            + "                                    <td>\n"
                            + "                                        " + SessionDAO.getSessionById(o.getSesssionId()).getDay() + "\n"
                            + "                                    </td>\n"
                            + "                                    <td>\n"
                            + "                                        " + o.getPrice() + "\n"
                            + "                                    </td>\n"
                            + "                                    <td>\n"
                            + "                                        " + PaymentDAO.getPaymentById(o.getPayId()).getType() + "\n"
                            + "                                    </td>\n");
                    if (status == 1) {
                        out.println("                                    <td>\n"
                                + "                                        <button value=\"" + o.getId() + "\" class=\"text-decoration-none func-btn\" onclick=\"acceptOrder(this)\">Accept</button> | \n"
                                + "                                        <button value=\"" + o.getId() + "\" class=\"text-decoration-none func-btn\" onclick=\"denyOrder(this)\">Deny</button>\n"
                                + "                                    </td>\n");
                    } else {
                        out.println("                                    <td>\n"
                                + "                                    </td>\n");
                    };
                    out.println("                              </tr>");
                } catch (SQLException ex) {
                    Logger.getLogger(FoodController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });

        } catch (SQLException ex) {
            Logger.getLogger(FoodController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FoodController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void showMealPage(HttpServletRequest request, HttpServletResponse response) {

        try {

            List<CategoryDTO> categoryList = CategoryDAO.getFoodCategory();
            List<FoodDTO> foodList;
            request.setAttribute("categoryList", categoryList);

            List<MealGroupDTO> mealList = MealGroupDAO.getAllMealGroup();
            request.setAttribute("mealList", mealList);

            request.getRequestDispatcher("/WEB-INF/Layers/dashboard.jsp").forward(request, response);
        } catch (ClassNotFoundException | SQLException | ServletException | IOException e) {
            Logger.getLogger(FoodController.class.getName()).log(Level.SEVERE, null, e);
        }

    }

    public void showOrderPage(HttpServletRequest request, HttpServletResponse response) {

        try {
            HttpSession sess = request.getSession();
            List<OrderDTO> orderList = OrderDAO.getOrderByStatus(1);
            List<SessionDTO> sessions = SessionDAO.getSessionForOrder(1);
            sess.setAttribute("status", 1);
            System.out.println(orderList.size());
            Map<Integer, PaymentDTO> payment = new HashMap<>();
            Map<Integer, BuildingDTO> bulding = new HashMap<>();
            Map<Integer, SessionDTO> session = new HashMap<>();
            Set<String> timelines = new TreeSet<>();
            Set<String> days = new TreeSet<>();
            sessions.forEach(s -> {
                timelines.add(s.getFromto());
                days.add(s.getDay());
            });
            timelines.forEach(t -> {
                System.out.println(t);
            });
            days.forEach(d -> {
                System.out.println(d);
            });
            orderList.forEach(o -> {
                try {
                    payment.put(o.getId(), PaymentDAO.getPaymentById(o.getPayId()));
                    bulding.put(o.getId(), BuildingDAO.getBuildingById(o.getPayId()));
                    session.put(o.getId(), SessionDAO.getSessionById(o.getSesssionId()));
                } catch (SQLException ex) {
                    Logger.getLogger(FoodController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });

            request.setAttribute("orderList", orderList);
            request.setAttribute("payment", payment);
            request.setAttribute("bulding", bulding);
            request.setAttribute("session", session);
            request.setAttribute("timelines", timelines);
            request.setAttribute("days", days);

            request.getRequestDispatcher("/WEB-INF/Layers/dashboard.jsp").forward(request, response);
        } catch (SQLException | ServletException | IOException e) {
            Logger.getLogger(FoodController.class.getName()).log(Level.SEVERE, null, e);
        }

    }

    public void showCategoryPage(HttpServletRequest request, HttpServletResponse response) {

        try {
            List<CategoryDTO> categoryList = CategoryDAO.getFoodCategory();
            request.setAttribute("categoryList", categoryList);
            request.getRequestDispatcher("/WEB-INF/Layers/dashboard.jsp").forward(request, response);
        } catch (SQLException | ServletException | IOException e) {
            Logger.getLogger(FoodController.class.getName()).log(Level.SEVERE, null, e);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FoodController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void showSessionDetail(HttpServletRequest request, HttpServletResponse response) {

        try {
            PrintWriter out = response.getWriter();
            String code = request.getParameter("code");
            List<MealGroupDTO> mealList = MealGroupDAO.getAllMealbySessionId(code);
            out.println("<div class=\"modal-content\">\n"
                    + "                            <div class=\"modal-header justify-content-center\">\n"
                    + "                                <h2 class=\"modal-header-title\">" + code + "</h2>\n"
                    + "                                <span id=\"close\" class=\"close\">&times;</span>\n"
                    + "                            </div>"
                    + "<div class=\"row mt-50\" id=\"view\">");
            mealList.forEach(m -> {
                out.println("<div class=\"col l-4\">\n"
                        + "                                        <div class=\"card\">\n"
                        + "                                            <div class=\"card-top\">\n"
                        + "                                                <img src=\"/comMeNau/images/" + m.getImages().get(0) + "\" class=\"card-img-big\">\n"
                        + "                                                <h5 class=\"card-title\">" + m.getName() + "</h5>\n"
                        + "                                            </div>\n"
                        + "                                            <div class=\"card-body\">\n"
                        + "                                                <span class=\"card-price\">\n"
                        + "                                                    <span class=\"card-price-pre\">Từ</span>\n"
                        + "                                                     " + m.getPrice() + ""
                        + "                                                </span>       \n"
                        + "                                                <span class=\"card-chef\">\n"
                        + "                                                    <span class=\"card-chef-pre\">Đầu Bếp: </span>\n"
                        + "                                                    " + m.getChefId().getName() + "\n"
                        + "                                                </span>           \n"
                        + "                                            </div>\n"
                        + "                                        </div> \n"
                        + "                                 </div>                 ");
            });
            out.println("</div> ");
        } catch (ClassNotFoundException | SQLException | IOException e) {
            Logger.getLogger(FoodController.class.getName()).log(Level.SEVERE, null, e);
        }

    }

    public void showBuildingPage(HttpServletRequest request, HttpServletResponse response) {

        try {
            List<BuildingDTO> buildingList = BuildingDAO.getAllBuilding();
            request.setAttribute("buildingList", buildingList);
            request.getRequestDispatcher("/WEB-INF/Layers/dashboard.jsp").forward(request, response);
        } catch (ServletException | IOException e) {
            Logger.getLogger(FoodController.class.getName()).log(Level.SEVERE, null, e);
        } catch (SQLException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void showFoodCatogaryAjax(HttpServletRequest request, HttpServletResponse response) {
        int category;
        try {
            PrintWriter out = response.getWriter();
            HttpSession session = request.getSession();
            System.out.println(request.getParameter("categoryId"));

            category = Integer.parseInt(request.getParameter("categoryId"));
            session.setAttribute("categoryId", category);
            List<FoodDTO> foodList = FoodDAO.getFoodByCategory(category);
            System.out.println(foodList.size());
            foodList.forEach(food -> {
                out.println("<div class=\"row\">\n"
                        + "                                        <a class=\"col l-9 card-detail-item\"href=\"/comMeNau/food/editFood.do?foodId=" + (int) food.getId() + "\"\">\n"
                        + "                                            <div class=\"card-detail-box\">\n"
                        + "                                                <img src=\"/comMeNau/images/" + food.getImages().get(0) + "\" class=\"card-detail-img\">\n"
                        + "                                                <div class=\"card-detail-content\" >\n"
                        + "                                                    <h4 class=\"food-name\">" + food.getName() + "</h4>\n"
                        + "                                                    <p class=\"food-des\">\n"
                        + "                                                        " + food.getNutrition() + "\n"
                        + "                                                    </p>\n"
                        + "                                                </div>\n"
                        + "                                            </div>\n"
                        + "                                        </a> \n"
                        + "                                        <div class=\"col l-2\">        \n"
                        + "                                           <button class=\"trash-btn\" value=\"" + food.getId() + "\" onclick=\"deleteFood(this)\">\n"
                        + "                                                    <i class=\"fa-solid fa-trash-can\" style=\"font-size: 45px\"></i>\n"
                        + "                                            </button> "
                        + "                                        </div>    \n"
                        + "                                    </div>");
            });
        } catch (ClassNotFoundException | SQLException | IOException e) {
            Logger.getLogger(FoodController.class.getName()).log(Level.SEVERE, null, e);
        }

    }

    public static void categoryDetail(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        try {
            PrintWriter out = response.getWriter();
            int categoryId = Integer.parseInt(request.getParameter("categoryId"));
            if (categoryId > 0) {
                CategoryDTO category = CategoryDAO.getCategoryById(categoryId);
                out.println("<div class=\"modal-content\">\n"
                        + "                                <div class=\"modal-header justify-content-center\">\n"
                        + "                                    <h2 class=\"modal-header-title\">" + category.getName() + "</h2>\n"
                        + "                                    <span class=\"close\">&times;</span>\n"
                        + "                                </div>\n"
                        + "                                <form action=\"/comMeNau/food/editCategory.do?categoryId=" + category.getId() + "\" class=\"form\">\n"
                        + "                                 <input type=\"hidden\" name=\"categoryId\" value=\"" + category.getId() + "\" />\n"
                        + ""
                        + "<div class=\"box\">\n"
                        + "                                        <label class=\"label\" for=\"name\">Nhập Tên Category Mới:</label>\n"
                        + "                                        <input id=\"name\"\n"
                        + "                                               class=\"form-control\" type=\"text\" value=\"" + category.getName() + "\"  name=\"name\"/>\n"
                        + "                                        <br/><label class=\"label\" for=\"type\">Nhập Mức độ ưu tiên:</label>\n"
                        + "                                        <input id=\"type\"\n"
                        + "                                               class=\"form-control\" type=\"text\" value=\"" + category.getType() + "\"  name=\"type\"/>\n"
                        + "                                    </div>           \n"
                        + "<input type=\"submit-btn\" value=\"Lưu\" \n/>"
                        + "                                </form>\n"
                        + "                            </div>");
            }
        } catch (IOException | SQLException | ClassNotFoundException ex) {
            Logger.getLogger(FoodController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void mealDetail(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        try {

            String topTimeline = request.getParameter("topTimeline");
            String topDay = request.getParameter("topDay");
            System.out.println(topTimeline + topDay);
            if (topTimeline == null && topDay == null || topDay.isEmpty() || topTimeline.isEmpty()) {
                String timeline = (String) session.getAttribute("timeline");
                String day = (String) session.getAttribute("day");
                int mealId = Integer.parseInt(request.getParameter("mealId"));
                System.out.println(mealId);
                MealGroupDTO meal = MealGroupDAO.getMealGroupByIdAndTimelineAndDay(timeline, day, mealId);
                List<MealGroupDTO> mealList = MealGroupDAO.getMealGroupByTimelineAndDay(timeline, day);
                for (int i = 0; i < mealList.size(); i++) {
                    if (mealList.get(i).getId() == meal.getId()) {
                        mealList.remove(i);
                    }
                }
                request.setAttribute("meal", meal);
                request.setAttribute("mealList", mealList);
            } else {
                int mealId = Integer.parseInt(request.getParameter("mealId"));
                MealGroupDTO meal = MealGroupDAO.getMealGroupByIdAndTimelineAndDay(topTimeline, topDay, mealId);
                List<MealGroupDTO> mealList = MealGroupDAO.getMealGroupByTimelineAndDay(topTimeline, topDay);
                for (int i = 0; i < mealList.size(); i++) {
                    if (mealList.get(i).getId() == meal.getId()) {
                        mealList.remove(i);
                    }
                }
                request.setAttribute("meal", meal);
                request.setAttribute("mealList", mealList);
                request.setAttribute("topTimeline", topTimeline);
                request.setAttribute("topDay", topDay);
            }
            request.getRequestDispatcher("/WEB-INF/Layers/show.jsp").forward(request, response);
        } catch (ServletException | IOException | SQLException | ClassNotFoundException ex) {
            Logger.getLogger(FoodController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void search(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        try {
            List<CategoryDTO> categoryList = CategoryDAO.getFoodCategory();
            List<String> timeList = FoodDAO.getFoodTimeline();
            List<FoodDTO> foodList;
            request.setAttribute("timeList", timeList);
            request.setAttribute("categoryList", categoryList);
            String search = request.getParameter("Search");
            foodList = FoodDAO.getFoodSearch(search);
            request.setAttribute("foodList", foodList);
            session.setAttribute("Search", search);
            request.setAttribute("controller", "food");
            request.setAttribute("action", "foodPage");
            request.getRequestDispatcher("/WEB-INF/Layers/main.jsp").forward(request, response);
        } catch (ClassNotFoundException | SQLException | ServletException | IOException ex) {
            Logger.getLogger(FoodController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void searchByAjax(HttpServletRequest request, HttpServletResponse response) {
        try {
            List<FoodDTO> foodList;
            PrintWriter out = response.getWriter();
            String search = request.getParameter("Search");
            foodList = FoodDAO.getFoodSearch(search);
            foodList.forEach(food -> {
                out.println("<div class=\"row\">\n"
                        + "                                        <a class=\"col l-9 card-detail-item\"href=\"/comMeNau/home/detail.do?food=" + (int) food.getId() + "\"\">\n"
                        + "                                            <div class=\"card-detail-box\">\n"
                        + "                                                <img src=\"/comMeNau/images/" + food.getImages().get(0) + "\" class=\"card-detail-img\">\n"
                        + "                                                <div class=\"card-detail-content\" >\n"
                        + "                                                    <h4 class=\"food-name\">" + food.getName() + "</h4>\n"
                        + "                                                    <p class=\"food-des\">\n"
                        + "                                                        " + food.getNutrition() + "\n"
                        + "                                                    </p>\n"
                        + "                                                </div>\n"
                        + "                                            </div>\n"
                        + "                                        </a> \n"
                        + "                                        <div class=\"col l-2\">        \n"
                        + "                                           <button class=\"trash-btn\" value=\"" + food.getId() + "\" onclick=\"deleteFood(this)\">\n"
                        + "                                                    <i class=\"fa-solid fa-trash-can\" style=\"font-size: 45px\"></i>\n"
                        + "                                            </button> "
                        + "                                        </div>    \n"
                        + "                                    </div>");
            });
        } catch (ClassNotFoundException | SQLException | IOException ex) {
            Logger.getLogger(FoodController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void createFood(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        String name = null;
        String nutrition = null;
        int categoryId = 0;

        FileItemFactory itemfactory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(itemfactory);
        try {
            List<FileItem> items = upload.parseRequest(new ServletRequestContext(request));
            for (FileItem item : items) {

                if (item.isFormField()) {
                    String name1 = item.getFieldName();
                    String value = item.getString("UTF-8");
                    //String decodedToUTF8 = new String(value.getBytes("UTF-8"), "UTF-8");
                    System.out.println("name1: " + name1);
                    System.out.println("value: " + value);
                    if (name1.equals("foodName")) {
                        name = item.getString("UTF-8");
                    } else if (name1.equals("foodCategory")) {
                        categoryId = Integer.parseInt(item.getString());
                    } else if (name1.equals("foodNutrition")) {
                        nutrition = item.getString("UTF-8");
                    }
                } else {
                    System.out.println(name);
                    System.out.println(FoodDAO.isFoodExist(name));
                    if (!FoodDAO.isFoodExist(name)) {
                        File uploadDir = new File("C:\\Users\\luffy\\Downloads\\NetBeansProject\\SWP301\\COMMENAU\\CMN\\web\\images\\" + item.getName() + ".");

                        if (FoodDAO.insertFood(name, nutrition, categoryId, item.getName())) {
                            item.write(uploadDir);
                        }
                        request.setAttribute("message", "Tạo Thành Công!!");
                    } else {
                        request.setAttribute("foodName", name);
                        request.setAttribute("foodCategory", categoryId);
                        request.setAttribute("foodNutrition", nutrition);
                        request.setAttribute("message", "Tên Đã Tồn Tại!!");
                    }
                }
            }
            request.getRequestDispatcher("/food/newFood.do").forward(request, response);
        } catch (FileUploadException e) {
            e.printStackTrace();
            request.setAttribute("message", "File Bị Lỗi!!");
            request.setAttribute("foodName", name);
            request.setAttribute("foodNutrition", nutrition);
            request.setAttribute("foodCategory", categoryId);
            try {
                request.getRequestDispatcher("/food/newFood.do").forward(request, response);
            } catch (ServletException ex) {
                Logger.getLogger(FoodController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            request.setAttribute("message", "File Bị Lỗi!!");
            request.setAttribute("foodName", name);
            request.setAttribute("foodNutrition", nutrition);
            request.setAttribute("foodCategory", categoryId);
            try {
                request.getRequestDispatcher("/food/newFood.do").forward(request, response);
            } catch (ServletException e) {
                Logger.getLogger(FoodController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static void newFood(HttpServletRequest request, HttpServletResponse response) {
        try {
            List<CategoryDTO> categoryList = CategoryDAO.getFoodCategory();
            request.setAttribute("categoryList", categoryList);
            request.getRequestDispatcher("/WEB-INF/Layers/show.jsp").forward(request, response);
        } catch (Exception e) {
        }
    }

    public static void newSession(HttpServletRequest request, HttpServletResponse response) {
        try {
            SessionDTO lastSession = SessionDAO.getLastSession();
            List<MealGroupDTO> mealList = MealGroupDAO.getAllMealGroup();
            request.setAttribute("lastSession", lastSession);
            request.setAttribute("mealList", mealList);
            request.getRequestDispatcher("/WEB-INF/Layers/adminShow.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void createCategory(HttpServletRequest request, HttpServletResponse response) {
        try {
            String categoryName = request.getParameter("categoryName");
            System.out.println(request.getParameter("categoryName"));
            System.out.println(request.getParameter("categoryType"));
            int categoryType = Integer.parseInt(request.getParameter("categoryType"));
            System.out.println(CategoryDAO.isNameCategoryExist(categoryName));
            if (!CategoryDAO.isNameCategoryExist(categoryName)) {
                if (CategoryDAO.insertCategory(categoryName, categoryType)) {
                    request.setAttribute("message", "Thêm thành công!!");
                }
            } else {
                request.setAttribute("message", "Tên Đã tồn tại!!");
                request.setAttribute("categoryName", categoryName);
                request.setAttribute("categoryType", categoryType);
            }
            request.getRequestDispatcher("/food/newCategory.do").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void newCategory(HttpServletRequest request, HttpServletResponse response) {
        try {
            SessionDTO lastSession = SessionDAO.getLastSession();
            request.getRequestDispatcher("/WEB-INF/Layers/adminShow.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void deleteFood(HttpServletRequest request, HttpServletResponse response) {
        try {
            PrintWriter out = response.getWriter();
            HttpSession session = request.getSession();
            int foodId = Integer.parseInt(request.getParameter("foodId"));
            int categoryId = (int) session.getAttribute("categoryId");
            System.out.println(categoryId + " " + foodId);
            if (FoodDAO.deleteFood(foodId)) {
                System.out.println("done");
                List<FoodDTO> foodList = FoodDAO.getFoodByCategory(categoryId);
                foodList.forEach(food -> {
                    out.println("<div class=\"row\">\n"
                            + "                                        <a class=\"col l-9 card-detail-item\"href=\"/comMeNau/home/detail.do?food=" + (int) food.getId() + "\"\">\n"
                            + "                                            <div class=\"card-detail-box\">\n"
                            + "                                                <img src=\"/comMeNau/images/" + food.getImages().get(0) + "\" class=\"card-detail-img\">\n"
                            + "                                                <div class=\"card-detail-content\" >\n"
                            + "                                                    <h4 class=\"food-name\">" + food.getName() + "</h4>\n"
                            + "                                                    <p class=\"food-des\">\n"
                            + "                                                        " + food.getNutrition() + "\n"
                            + "                                                    </p>\n"
                            + "                                                </div>\n"
                            + "                                            </div>\n"
                            + "                                        </a> \n"
                            + "                                        <div class=\"col l-2\">        \n"
                            + "                                           <button class=\"trash-btn\" value=\"" + food.getId() + "\" onclick=\"deleteFood(this)\">\n"
                            + "                                                    <i class=\"fa-solid fa-trash-can\" style=\"font-size: 45px\"></i>\n"
                            + "                                            </button> "
                            + "                                        </div>    \n"
                            + "                                    </div>");
                });
            }
        } catch (IOException | ClassNotFoundException | NumberFormatException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static void newMeal(HttpServletRequest request, HttpServletResponse response) {
        try {
            List<FoodDTO> foodList = FoodDAO.getAllFood();
            List<ChefDTO> chefList = ChefDAO.getAllChef();
            request.setAttribute("foodList", foodList);
            request.setAttribute("chefList", chefList);
            request.getRequestDispatcher("/WEB-INF/Layers/adminShow.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void createMeal(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        String name = null;
        double price = 0;
        String Describe = null;
        int chefId = 0;
        List<Integer> foodId = new ArrayList<>();
        FileItemFactory itemfactory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(itemfactory);
        try {
            List<FileItem> items = upload.parseRequest(new ServletRequestContext(request));
            for (FileItem item : items) {

                if (item.isFormField()) {
                    String nameField = item.getFieldName();

                    if (nameField.equals("mealName")) {
                        name = item.getString("UTF-8");
                    } else if (nameField.equals("mealPrice")) {
                        price = Double.parseDouble(item.getString());
                    } else if (nameField.equals("mealDescribe")) {
                        Describe = item.getString("UTF-8");
                    } else if (nameField.equals("chef")) {
                        chefId = Integer.parseInt(item.getString("UTF-8"));
                    } else if (nameField.equals("mealFood")) {
                        foodId.add(Integer.parseInt(item.getString("UTF-8")));
                    }
                } else {
                    System.out.println(MealGroupDAO.isMealExist(name));
                    if (!MealGroupDAO.isMealExist(name)) {
                        File uploadDir = new File("C:\\Users\\luffy\\Downloads\\NetBeansProject\\SWP301\\COMMENAU\\CMN\\web\\images\\" + item.getName() + ".");
                        if (MealGroupDAO.insertMealGroup(price, chefId, name, Describe, 0, item.getName())) {
                            item.write(uploadDir);
                            int lastMealgroupID = MealGroupDAO.getLastIdMealgroup();
                            for (int id : foodId) {
                                MealGroupDAO.insertFoodIntoMeal(id, lastMealgroupID);
                            }
                            request.setAttribute("message", "Tạo thành công!!");
                        }
                    } else {
                        System.out.println(foodId.size());
                        request.setAttribute("mealName", name);
                        request.setAttribute("mealPrice", price);
                        request.setAttribute("mealDescribe", Describe);
                        request.setAttribute("foodId", foodId);
                        request.setAttribute("chefId", chefId);
                        request.setAttribute("message", "Tên đã tồn tại!!");

                    }
                }
            }
            request.getRequestDispatcher("/food/newMeal.do").forward(request, response);
        } catch (FileUploadException e) {

            out.println("Tải lên thất bại!!");
        } catch (Exception ex) {
            ex.printStackTrace();
            out.println("Không Thể Lưu");
        }
    }

    public static void createSession(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            //get timline
            int from = Integer.parseInt(request.getParameter("from"));
            int to = Integer.parseInt(request.getParameter("to"));
            String dayString = request.getParameter("day");
            String[] meal = request.getParameterValues("meal");
            //convert day string
            if (dayString != null) {
                String day = dayString.split("-")[2] + "/" + dayString.split("-")[1];
                //get now day to check
                LocalDate local = LocalDate.now();
                Calendar check = Calendar.getInstance();
                Calendar now = Calendar.getInstance();
                System.out.println(day);
                check.set(now.get(Calendar.YEAR), Integer.parseInt(day.split("/")[1]) - 1, Integer.parseInt(day.split("/")[0]));
                String timeline = from + "-" + to;
                if (!SessionDAO.isSessionExist(day, timeline)) {
                    if (!check.before(now)) {
                        if (from < to) {
                            try {
                                if (SessionDAO.insertSession(timeline, day)) {
                                    int lastSession = SessionDAO.getLastSessionInsert();
                                    for (String mealId : meal) {
                                        MealGroupDAO.insertMealInSession(Integer.parseInt(mealId), lastSession);
                                    }
                                }
                                request.setAttribute("message", "Tạo Thành Công!!");

                            } catch (SQLException ex) {
                                Logger.getLogger(FoodController.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (ClassNotFoundException ex) {
                                Logger.getLogger(FoodController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        } else {
                            request.setAttribute("message", "Thời gian bắt đầu không thể sau thời gian kết thúc!!");
                            request.setAttribute("from", from);
                            request.setAttribute("to", to);
                            request.setAttribute("day", dayString);
                            request.setAttribute("mealId", meal);
                        }
                    } else {
                        request.setAttribute("from", from);
                        request.setAttribute("to", to);
                        request.setAttribute("day", dayString);
                        request.setAttribute("mealId", meal);
                        request.setAttribute("message", "Ngày đã quá hạn!!");
                    }
                } else {
                    request.setAttribute("from", from);
                    request.setAttribute("to", to);
                    request.setAttribute("day", dayString);
                    request.setAttribute("mealId", meal);
                    request.setAttribute("message", "Khung giờ này đã có!!");
                }
            } else {
                request.setAttribute("from", from);
                request.setAttribute("to", to);
                request.setAttribute("day", dayString);
                request.setAttribute("mealId", meal);
                request.setAttribute("message", "Vui lòng nhập ngày tháng!!");
            }
            request.getRequestDispatcher("/food/newSession.do").forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(FoodController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ServletException ex) {
            Logger.getLogger(FoodController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void deleteSession(HttpServletRequest request, HttpServletResponse response) {
        try {
            PrintWriter out = response.getWriter();
            HttpSession session = request.getSession();
            String code = request.getParameter("code");
            System.out.println("code");
            int mealId = SessionDAO.getSessionIdByyCode(code);
            System.out.println(mealId);
            if (MealGroupDAO.deleteMealInSession(mealId)) {
                if (SessionDAO.deleteSession(code)) {
                    LocalDate localNow = LocalDate.now();
                    int nowDay = localNow.getDayOfMonth();
                    int nowMonth = localNow.getMonthValue();
                    List<SessionDTO> allSession = SessionDAO.getAllSession();
                    List<SessionDTO> sessionList = new ArrayList<>();
                    String time = (String) session.getAttribute("time");
                    if (time.equals("pass")) {
                        allSession.forEach(s -> {
                            int day = Integer.parseInt(s.getDay().split("/")[0]);
                            int month = Integer.parseInt(s.getDay().split("/")[1]);
                            if (month < nowMonth) {
                                sessionList.add(s);
                            } else if (month == nowMonth && day < nowDay) {
                                sessionList.add(s);
                            }
                            System.out.println(sessionList.size() + " " + allSession.size());
                        });
                    } else if (time.equals("now")) {
                        allSession.forEach(s -> {
                            int day = Integer.parseInt(s.getDay().split("/")[0]);
                            int month = Integer.parseInt(s.getDay().split("/")[1]);
                            if (month == nowMonth && day == nowDay) {
                                sessionList.add(s);
                            }
                        });
                    } else if (time.equals("future")) {
                        allSession.forEach(s -> {
                            int day = Integer.parseInt(s.getDay().split("/")[0]);
                            int month = Integer.parseInt(s.getDay().split("/")[1]);
                            if (month > nowMonth) {
                                sessionList.add(s);
                            } else if (month == nowMonth && day > nowDay) {
                                sessionList.add(s);
                            }
                        });
                    }
                    AtomicInteger autoCount = new AtomicInteger(0);
                    sessionList.forEach(s -> {
                        int count = autoCount.incrementAndGet();
                        out.println("<tr style=\"background: #fff;\">\n"
                                + "                                    <td>\n"
                                + "                                        " + count + "\n"
                                + "                                    </td>\n"
                                + "                                    <td>\n"
                                + "                                        " + s.getCode() + "\n"
                                + "                                    </td>\n"
                                + "                                    <td>\n"
                                + "                                        " + s.getFromto() + "\n"
                                + "                                    </td>\n"
                                + "                                    <td>\n"
                                + "                                        " + s.getDay() + "\n"
                                + "                                    </td>\n"
                                + "                                    <td>\n"
                                + "                                        <button id=\"myBtn\" value=\"" + s.getCode() + "\" class=\"view-btn\" onclick=\"showMealInSession(this)\">View</button> | <a class=\"text-decoration-none\" href=\"/comMeNau/food/editSession.do?code=" + s.getCode() + "\">Edit</a>\n"
                                + "                                    </td>\n"
                                + "                                    <td style=\"padding: 5px 0 0 0;\">\n"
                                + "                                        <button class=\"trash-btn\" onclick=\"deleteSession(this)\" value=\"" + s.getCode() + "\" >\n"
                                + "                                            <i class=\"fa-solid fa-trash-can\"></i>\n"
                                + "                                        </button>\n"
                                + "                                    </td>\n"
                                + "                                </tr>");

                    });

                }
            }
        } catch (IOException | ClassNotFoundException | NumberFormatException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteMeal(HttpServletRequest request, HttpServletResponse response) {
        try {
            PrintWriter out = response.getWriter();
            HttpSession session = request.getSession();
            int mealId = Integer.parseInt(request.getParameter("mealId"));
            if (MealGroupDAO.deleteMeal(mealId)) {
                List<MealGroupDTO> mealList = MealGroupDAO.getAllMealGroup();
                mealList.forEach(meal -> {
                    out.println("<div class=\"row\">\n"
                            + "                            <a class=\"col l-9 card-detail-item\" href=\"/comMeNau/food/editMeal.do?mealId=" + (int) meal.getId() + "\">\n"
                            + "                                <div class=\"card-detail-box\">\n"
                            + "                                    <img src=\"/comMeNau/images/" + meal.getImages().get(0) + "\" class=\"card-detail-img\">\n"
                            + "                                    <div class=\"card-detail-content\">\n"
                            + "                                        <h4 class=\"food-name\">" + meal.getName() + "</h4>\n"
                            + "                                        <div class=\"d-flex justify-content-around card-detail-info\">\n"
                            + "                                            <p class=\"card-price\">\n"
                            + "                                                <span class=\"card-price-pre\">Giá: </span>\n"
                            + "                                                    " + meal.getPrice() + "\n"
                            + "                                            </p>       \n"
                            + "                                            <p class=\"card-chef\">\n"
                            + "                                                <span class=\"card-chef-pre\">Đầu Bếp: </span>\n"
                            + "                                                " + meal.getChefId().getName() + "\n"
                            + "                                            </p>\n"
                            + "                                        </div>\n"
                            + "                                    </div>\n"
                            + "                                </div>\n"
                            + "                            </a> \n"
                            + "                            <div class=\"col l-2\">        \n"
                            + "                                <button class=\"trash-btn\" value=\"" + meal.getId() + "\" onclick=\"deleteMeal(this)\">\n"
                            + "                                    <i class=\"fa-solid fa-trash-can\" style=\"font-size: 45px\"></i>\n"
                            + "                                </button> \n"
                            + "                            </div");
                });
            }
        } catch (IOException | ClassNotFoundException | NumberFormatException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteCategory(HttpServletRequest request, HttpServletResponse response) {
        try {
            PrintWriter out = response.getWriter();
            HttpSession session = request.getSession();
            int categoryId = Integer.parseInt(request.getParameter("categoryId"));
            if (CategoryDAO.deleteCategory(categoryId)) {
                List<CategoryDTO> categoryList = CategoryDAO.getFoodCategory();
                AtomicInteger autoCount = new AtomicInteger(0);
                categoryList.forEach(c -> {
                    int count = autoCount.incrementAndGet();
                    out.println(
                            "<tr style=\"background: #fff;\">\n"
                            + "<td>\n"
                            + "                                        " + count + "\n"
                            + "                                    </td>\n"
                            + "                                    <td>\n"
                            + "                                        " + c.getName() + "\n"
                            + "                                    </td>\n"
                            + "                                    <td>\n"
                            + "                                        " + c.getType() + "\n"
                            + "                                    </td>\n"
                            + "                                    <td>\n"
                            + "                                        <button id=\"myBtn\" value=\"" + c.getId() + "\" class=\"view-btn\" onclick=\"showCategoryDetail(this)\" >Edit</button>\n"
                            + "                                    </td>\n"
                            + "                                    <td style=\"padding: 5px 0 0 0;\">\n"
                            + "                                        <button class=\"trash-btn\" value=\"" + c.getId() + "\" onclick=\"deleteCategory(this)\" >\n"
                            + "                                            <i class=\"fa-solid fa-trash-can\"></i>\n"
                            + "                                        </button>\n"
                            + "                                    </td>\n"
                            + "                                </tr>");
                });

            }

        } catch (IOException | ClassNotFoundException | NumberFormatException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static void editFood(HttpServletRequest request, HttpServletResponse response) {
        try {
            int foodId = Integer.parseInt(request.getParameter("foodId"));
            FoodDTO food = FoodDAO.getFoodById(foodId);
            int categoryIdOfFood = CategoryDAO.getCategoryIdOfFood(foodId);
            List<CategoryDTO> categoryList = CategoryDAO.getFoodCategory();
            request.setAttribute("food", food);
            request.setAttribute("categoryIdOfFood", categoryIdOfFood);
            request.setAttribute("categoryList", categoryList);
            request.getRequestDispatcher("/WEB-INF/Layers/adminShow.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void updateCategory(HttpServletRequest request, HttpServletResponse response) {
        try {
            int categoryId = Integer.parseInt(request.getParameter("id"));
            System.out.println(categoryId);
            String name = request.getParameter("name");
            int type = Integer.parseInt(request.getParameter("type"));

            if (categoryId > 0 && !name.isBlank() && type > 0) {
                CategoryDAO.updateCategory(categoryId, name, type);
                request.setAttribute("message", "Cập Nhật thành công!!");
            } else {
                request.setAttribute("message", "Vui Lòng nhập đúng yêu cầu!!");
            }
            request.setAttribute("categoryId", categoryId);
            request.setAttribute("name", name);
            request.setAttribute("type", type);
            request.getRequestDispatcher("/food/updateCategoryPage.do?categoryId=" + categoryId).forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void updateFood(HttpServletRequest request, HttpServletResponse response) {
        try {
            PrintWriter out = response.getWriter();
            String fName = null;
            String nutrition = null;
            int foodId = 0;
            int categoryId = 0;

            FileItemFactory itemfactory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(itemfactory);
            try {
                List<FileItem> items = upload.parseRequest(new ServletRequestContext(request));
                for (FileItem item : items) {

                    if (item.isFormField()) {
                        String name = item.getFieldName();
                        String value = item.getString("UTF-8");
                        System.out.println("name1: " + name);
                        System.out.println("value: " + value);
                        if (name.equals("foodId")) {
                            foodId = Integer.parseInt(item.getString("UTF-8"));
                        }
                        if (name.equals("foodName")) {
                            fName = item.getString("UTF-8");
                        } else if (name.equals("foodCategory")) {
                            categoryId = Integer.parseInt(item.getString());
                        } else if (name.equals("foodNutrition")) {
                            nutrition = item.getString("UTF-8");
                        }
                    } else {
                        System.out.println(item.getName());
                        if (FoodDAO.updateFood(foodId, fName, nutrition, categoryId)) {
                            FoodDTO food = FoodDAO.getFoodById(foodId);
                            if (!item.getName().isEmpty()) {
                                File removeDir = new File("C:\\Users\\PC\\Desktop\\test\\web\\images\\" + food.getImages().get(0));
                                System.out.println(removeDir.renameTo(new File("C:\\Users\\PC\\Desktop\\test\\web\\removeImages\\" + food.getImages().get(0))));
                                File uploadDir = new File("C:\\Users\\PC\\Desktop\\test\\web\\images\\" + item.getName());
                                item.write(uploadDir);
                                ImageDAO.updateFoodImage(foodId, food.getImages().get(0), item.getName());
                            }
                        }
                    }
                }
                response.sendRedirect("/comMeNau/food/foodPage.do");
            } catch (FileUploadException e) {
                request.setAttribute("message", "File Bị Lỗi!!");
                request.setAttribute("name", fName);
                request.setAttribute("nutrition", nutrition);
                request.setAttribute("categoryId", categoryId);
                try {
                    request.getRequestDispatcher("/food/editFood.do").forward(request, response);
                } catch (ServletException ex) {
                    Logger.getLogger(FoodController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (Exception ex) {
                request.setAttribute("message", "File Bị Lỗi!!");
                request.setAttribute("name", fName);
                request.setAttribute("nutrition", nutrition);
                request.setAttribute("categoryId", categoryId);
                try {
                    request.getRequestDispatcher("/food/editFood.do").forward(request, response);
                } catch (ServletException e) {
                    Logger.getLogger(FoodController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void editMeal(HttpServletRequest request, HttpServletResponse response) {
        try {
            int mealId = Integer.parseInt(request.getParameter("mealId"));
            MealGroupDTO meal = MealGroupDAO.getMealGroupById(mealId);
            List<ChefDTO> chefList = ChefDAO.getAllChef();
            List<FoodDTO> foodList = FoodDAO.getAllFood();
            request.setAttribute("meal", meal);
            request.setAttribute("quantity", MealGroupDAO.getSoldNumber(mealId));
            request.setAttribute("chefList", chefList);
            request.setAttribute("foodList", foodList);

            request.getRequestDispatcher("/WEB-INF/Layers/adminShow.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void editSession(HttpServletRequest request, HttpServletResponse response) {
        try {
            String code = request.getParameter("code");
            System.out.println(code);
            SessionDTO session = SessionDAO.getSessionByCode(code);

            List<MealGroupDTO> mealInSession = MealGroupDAO.getAllMealbySessionId(code);
            List<MealGroupDTO> mealList = MealGroupDAO.getAllMealGroup();

            LocalDate localNow = LocalDate.now();
            String sessionDay = session.getDay() + "/" + localNow.getYear();
            sessionDay = sessionDay.replace("/", "-");
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
            String date = sdf2.format(sdf.parse(sessionDay));
            session.setDay(date);

            request.setAttribute("from", session.getFromto().split("-")[0]);
            request.setAttribute("to", session.getFromto().split("-")[1]);
            request.setAttribute("session", session);
            request.setAttribute("mealList", mealList);
            request.setAttribute("mealInSession", mealInSession);

            request.getRequestDispatcher("/WEB-INF/Layers/adminShow.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void sessionDetail(HttpServletRequest request, HttpServletResponse response) {
        try {
            String sessionCode = request.getParameter("code");
            //meal list
            List<MealGroupDTO> mealList = MealGroupDAO.getAllMealbySessionId(sessionCode);
            Map<Integer, Integer> quantityOrderOfMeal = new HashMap<>();
            Map<Integer, Double> moneyOfMeal = new HashMap<>();

            mealList.forEach(m -> {
                try {
                    quantityOrderOfMeal.put(m.getId(), MealGroupDAO.getTotalMealInOrder(m.getId(), sessionCode));
                    moneyOfMeal.put(m.getId(), MealGroupDAO.getTotalMoneyByMeal(m.getId(), sessionCode));
                } catch (SQLException ex) {
                    Logger.getLogger(FoodController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            //customer list
            List<CustomerDTO> cusList = CustomerDAO.getCustomerInsession(sessionCode);
            Map<Integer, Integer> quantityOrderOfCustomer = new HashMap<>();
            Map<Integer, Double> moneyOfCustomer = new HashMap<>();
            Map<Integer, BuildingDTO> buildingListCus = new HashMap<>();
            cusList.forEach(cus -> {
                try {
                    quantityOrderOfCustomer.put(cus.getId(), CustomerDAO.getTotalOrderByUser(cus.getId(), sessionCode));
                    moneyOfCustomer.put(cus.getId(), CustomerDAO.getTotalMoneyByUser(cus.getId(), sessionCode));
                    buildingListCus.put(cus.getId(), BuildingDAO.getBuildingById(cus.getAddress()));
                    System.out.println(moneyOfCustomer.get(cus.getId()));
                } catch (SQLException ex) {
                    Logger.getLogger(FoodController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            //chef List
            int sessionId = SessionDAO.getSessionIdByyCode(sessionCode);
            List<ChefDTO> chefList = ChefDAO.getChefInsession(sessionId);
            System.out.println(sessionId + " " + chefList.size());
            Map<Integer, BuildingDTO> buildingListChef = new HashMap<>();
            Map<Integer, Integer> quantityOrderOfChef = new HashMap<>();

            chefList.forEach(chef -> {
                try {
                    quantityOrderOfChef.put(chef.getId(), ChefDAO.getTotalQuantityByChef(chef.getId(), sessionCode));
                    buildingListChef.put(chef.getId(), BuildingDAO.getBuildingById(chef.getAddress()));
                } catch (SQLException ex) {
                    Logger.getLogger(FoodController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            SessionDTO sess = SessionDAO.getSessionByCode(sessionCode);

            request.setAttribute("session", sess);
            request.setAttribute("code", sessionCode);

            request.setAttribute("mealList", mealList);
            request.setAttribute("quantityMeal", quantityOrderOfMeal);
            request.setAttribute("moneyMeal", moneyOfMeal);

            request.setAttribute("cusList", cusList);
            request.setAttribute("buildingListCus", buildingListCus);
            request.setAttribute("quantityCus", quantityOrderOfCustomer);
            request.setAttribute("moneyCus", moneyOfCustomer);

            request.setAttribute("chefList", chefList);
            request.setAttribute("buildingListChef", buildingListChef);
            request.setAttribute("quantityChef", quantityOrderOfChef);

            request.getRequestDispatcher("/WEB-INF/Layers/adminShow.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void showSessionPage(HttpServletRequest request, HttpServletResponse response) {
        try {
            List<SessionDTO> sessionList = new ArrayList<>();
            List<SessionDTO> allSession = SessionDAO.getAllSession();
            LocalDate localNow = LocalDate.now();
            int nowDay = localNow.getDayOfMonth();
            int nowMonth = localNow.getMonthValue();
            allSession.forEach(s -> {
                int day = Integer.parseInt(s.getDay().split("/")[0]);
                int month = Integer.parseInt(s.getDay().split("/")[1]);

                if (month == nowMonth && day == nowDay) {
                    sessionList.add(s);
                }
            });
            request.setAttribute("sessionList", sessionList);
            request.getRequestDispatcher("/WEB-INF/Layers/dashboard.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void loadSessionPage(HttpServletRequest request, HttpServletResponse response) {
        try {
            PrintWriter out = response.getWriter();
            HttpSession session = request.getSession();
            List<SessionDTO> allSession = SessionDAO.getAllSession();
            List<SessionDTO> sessionList = new ArrayList<>();
            String time = request.getParameter("time");
            session.setAttribute("time", time);
            LocalDate localNow = LocalDate.now();
            int nowDay = localNow.getDayOfMonth();
            int nowMonth = localNow.getMonthValue();
            System.out.println(time);

            if (time.equals("pass")) {
                allSession.forEach(s -> {
                    int day = Integer.parseInt(s.getDay().split("/")[0]);
                    int month = Integer.parseInt(s.getDay().split("/")[1]);
                    if (month < nowMonth) {
                        sessionList.add(s);
                    } else if (month == nowMonth && day < nowDay) {
                        sessionList.add(s);
                    }
                    System.out.println(sessionList.size() + " " + allSession.size());
                });
            } else if (time.equals("now")) {
                allSession.forEach(s -> {
                    int day = Integer.parseInt(s.getDay().split("/")[0]);
                    int month = Integer.parseInt(s.getDay().split("/")[1]);
                    if (month == nowMonth && day == nowDay) {
                        sessionList.add(s);
                    }
                });
            } else if (time.equals("future")) {
                allSession.forEach(s -> {
                    int day = Integer.parseInt(s.getDay().split("/")[0]);
                    int month = Integer.parseInt(s.getDay().split("/")[1]);
                    if (month > nowMonth) {
                        sessionList.add(s);
                    } else if (month == nowMonth && day > nowDay) {
                        sessionList.add(s);
                    }
                });
            }
            AtomicInteger autoCount = new AtomicInteger(0);
            sessionList.forEach(s -> {
                int count = autoCount.incrementAndGet();
                out.println("<tr style=\"background: #fff;\">\n"
                        + "                                    <td>\n"
                        + "                                        " + count + "\n"
                        + "                                    </td>\n"
                        + "                                    <td>\n"
                        + "                                        " + s.getCode() + "\n"
                        + "                                    </td>\n"
                        + "                                    <td>\n"
                        + "                                        " + s.getFromto() + "\n"
                        + "                                    </td>\n"
                        + "                                    <td>\n"
                        + "                                        " + s.getDay() + "\n"
                        + "                                    </td>\n"
                        + "                                    <td>\n");
                if (time.equals("pass")) {
                    out.println("                                        <button value=\"${s.code}\" class=\"view-btn\"><a class=\"text-decoration-none\" href=\"/comMeNau/food/sessionDetail.do?code=" + s.getCode() + "\">View</a></button>"
                            + "                                    </td>\n"
                            + "                                    <td style=\"padding: 5px 0 0 0;\">\n"
                            + "                                        <button class=\"trash-btn\" onclick=\"deleteSession(this)\" value=\"" + s.getCode() + "\" >\n"
                            + "                                            <i class=\"fa-solid fa-trash-can\"></i>\n"
                            + "                                        </button>\n"
                            + "                                    </td>\n"
                            + "                                </tr>");
                } else {
                    out.println("                                        <button value=\"${s.code}\" class=\"view-btn\"><a class=\"text-decoration-none\" href=\"/comMeNau/food/sessionDetail.do?code=" + s.getCode() + "\">View</a></button>"
                            + "                                        | <a class=\"text-decoration-none\" href=\"/comMeNau/food/editSession.do?code=" + s.getCode() + "\">Edit</a>\n"
                            + "                                    </td>\n"
                            + "                                    <td style=\"padding: 5px 0 0 0;\">\n"
                            + "                                        <button class=\"trash-btn\" onclick=\"deleteSession(this)\" value=\"" + s.getCode() + "\" >\n"
                            + "                                            <i class=\"fa-solid fa-trash-can\"></i>\n"
                            + "                                        </button>\n"
                            + "                                    </td>\n"
                            + "                                </tr>");
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void updateMeal(HttpServletRequest request, HttpServletResponse response) {
        try {
            PrintWriter out = response.getWriter();
            int mealId = 0;
            String name = null;
            double price = 0;
            String Describe = null;
            int chefId = 0;
            List<Integer> foodId = new ArrayList<>();
            FileItemFactory itemfactory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(itemfactory);
            try {
                List<FileItem> items = upload.parseRequest(new ServletRequestContext(request));
                for (FileItem item : items) {

                    if (item.isFormField()) {
                        String nameField = item.getFieldName();
                        if (nameField.equals("mealId")) {
                            mealId = Integer.parseInt(item.getString("UTF-8"));
                        } else if (nameField.equals("mealName")) {
                            name = item.getString("UTF-8");
                        } else if (nameField.equals("mealPrice")) {
                            price = Double.parseDouble(item.getString());
                        } else if (nameField.equals("mealDescribe")) {
                            Describe = item.getString("UTF-8");
                        } else if (nameField.equals("chef")) {
                            chefId = Integer.parseInt(item.getString("UTF-8"));
                        } else if (nameField.equals("mealFood")) {
                            foodId.add(Integer.parseInt(item.getString("UTF-8")));
                        }
                    } else {
                        System.out.println(item.getName());
                        if (MealGroupDAO.updateMealGroup(mealId, name, price, chefId, Describe)) {
                            System.out.println("helloo");
                            MealGroupDTO meal = MealGroupDAO.getMealGroupById(mealId);
                            if (!item.getName().isEmpty()) {
                                File removeDir = new File("C:\\Users\\luffy\\Downloads\\NetBeansProject\\SWP301\\COMMENAU\\CMN\\web\\images\\" + meal.getImages().get(0));
                                removeDir.renameTo(new File("C:\\Users\\luffy\\Downloads\\NetBeansProject\\SWP301\\COMMENAU\\CMN\\web\\removeImages\\" + meal.getImages().get(0)));
                                File uploadDir = new File("C:\\Users\\luffy\\Downloads\\NetBeansProject\\SWP301\\COMMENAU\\CMN\\web\\images\\" + item.getName());
                                item.write(uploadDir);
                                ImageDAO.updateMealImage(mealId, meal.getImages().get(0), item.getName());
                                if (MealGroupDAO.deleteMealItems(mealId)) {
                                    for (int id : foodId) {
                                        MealGroupDAO.insertFoodIntoMeal(id, mealId);
                                    }
                                }
                            } else {
                                if (MealGroupDAO.deleteMealItems(mealId)) {
                                    for (int id : foodId) {
                                        MealGroupDAO.insertFoodIntoMeal(id, mealId);
                                    }
                                }
                            }
                        }
                    }

                }
                response.sendRedirect("/comMeNau/food/mealPage.do");
            } catch (FileUploadException e) {
                e.printStackTrace();
                request.setAttribute("message", "File Bị Lỗi!!");
                request.setAttribute("mealName", name);
                request.setAttribute("mealId", mealId);
                request.setAttribute("mealDescribe", Describe);
                request.setAttribute("chef", chefId);
                try {
                    request.getRequestDispatcher("/food/newFood.do").forward(request, response);
                } catch (ServletException ex) {
                    Logger.getLogger(FoodController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                request.setAttribute("message", "File Bị Lỗi!!");
                request.setAttribute("mealName", name);
                request.setAttribute("mealId", mealId);
                request.setAttribute("mealDescribe", Describe);
                request.setAttribute("chef", chefId);
                try {
                    request.getRequestDispatcher("/food/newFood.do").forward(request, response);
                } catch (ServletException e) {
                    Logger.getLogger(FoodController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void updateSession(HttpServletRequest request, HttpServletResponse response) {
        //get timline
        String code = request.getParameter("codeSession");
        System.out.println(code);
        int from = Integer.parseInt(request.getParameter("from"));
        int to = Integer.parseInt(request.getParameter("to"));
        String dayString = request.getParameter("date");
        //convert day string
        String day = dayString.split("-")[2] + "/" + dayString.split("-")[1];
        //get now day to check
        LocalDate local = LocalDate.now();
        Calendar check = Calendar.getInstance();
        Calendar now = Calendar.getInstance();
        System.out.println(day);
        check.set(now.get(Calendar.YEAR), Integer.parseInt(day.split("/")[1]) - 1, Integer.parseInt(day.split("/")[0]));
        String[] meal = request.getParameterValues("meal");
        String timeline = from + "-" + to;
        try {
            if (!check.before(now)) {
                if (from < to) {
                    try {
                        if (SessionDAO.updateSession(timeline, day, code)) {
                            int sessionId = SessionDAO.getSessionIdByyCode(code);
                            if (MealGroupDAO.deleteMealInSession(sessionId)) {
                                for (String mealId : meal) {
                                    MealGroupDAO.insertMealInSession(Integer.parseInt(mealId), sessionId);
                                }
                            }
                        }
                        request.setAttribute("message", "Lưu thành Công!!");

                    } catch (SQLException ex) {
                        Logger.getLogger(FoodController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(FoodController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    request.setAttribute("message", "Thời gian bắt đầu không thể sau thời gian kết thúc!!");
                    request.setAttribute("from", from);
                    request.setAttribute("to", to);
                    request.setAttribute("day", dayString);
                    request.setAttribute("mealId", meal);
                }
            } else {
                request.setAttribute("from", from);
                request.setAttribute("to", to);
                request.setAttribute("day", dayString);
                request.setAttribute("mealId", meal);
                request.setAttribute("message", "Ngày đã quá hạn!!");
            }
            request.getRequestDispatcher("/food/editSession.do?code=" + code).forward(request, response);
        } catch (ServletException ex) {
            Logger.getLogger(FoodController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FoodController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void acceptOrder(HttpServletRequest request, HttpServletResponse response) {
        try {
            PrintWriter out = response.getWriter();
            int orderId = Integer.parseInt(request.getParameter("orderId"));
            if (orderId > 0) {
                OrderDAO.updateOrderStatus(2, orderId);
            }
            //set sold quantity mealgroup 
            OrderDTO order = OrderDAO.getOrderById(orderId);
            int mealId = order.getMealId();
            System.out.println(mealId);
            if (mealId > 0) {
                MealGroupDAO.setSoldNumber(mealId, MealGroupDAO.getSoldNumber(mealId) + 1);
            }
            //show
            List<OrderDTO> orderList = OrderDAO.getOrderByStatus(1);

            orderList.forEach(o -> {
                try {
                    out.println("<tr style=\"background: #fff;\">\n"
                            + "                                    <td>\n"
                            + "                                        " + o.getId() + "\n"
                            + "                                    </td>\n"
                            + "                                    <td>\n"
                            + "                                        " + o.getCusName() + "\n"
                            + "                                    </td>\n"
                            + "                                    <td>\n"
                            + "                                        " + BuildingDAO.getBuildingById(o.getAddress()).getName() + "\n"
                            + "                                    </td>\n"
                            + "                                    <td>\n"
                            + "                                        " + SessionDAO.getSessionById(o.getSesssionId()).getFromto() + "\n"
                            + "                                    </td>\n"
                            + "                                    <td>\n"
                            + "                                        " + SessionDAO.getSessionById(o.getSesssionId()).getDay() + "\n"
                            + "                                    </td>\n"
                            + "                                    <td>\n"
                            + "                                        " + o.getPrice() + "\n"
                            + "                                    </td>\n"
                            + "                                    <td>\n"
                            + "                                        " + PaymentDAO.getPaymentById(o.getPayId()).getType() + "\n"
                            + "                                    </td>\n"
                            + "                                    <td>\n"
                            + "                                        <button value=\"" + o.getId() + "\" class=\"text-decoration-none func-btn\" onclick=\"acceptOrder(this)\">Accept</button> | \n"
                            + "                                        <button value=\"" + o.getId() + "\" class=\"text-decoration-none func-btn\" onclick=\"denyOrder(this)\">Deny</button>\n"
                            + "                                    </td>"
                    );

                } catch (SQLException ex) {
                    Logger.getLogger(FoodController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void denyOrder(HttpServletRequest request, HttpServletResponse response) {
        try {
            PrintWriter out = response.getWriter();
            int orderId = Integer.parseInt(request.getParameter("orderId"));
            if (orderId > 0) {
                OrderDAO.updateOrderStatus(0, orderId);
            }
            List<OrderDTO> orderList = OrderDAO.getOrderByStatus(1);
            System.out.println(orderList.size());
            System.out.println(orderList.isEmpty());
            orderList.forEach(o -> {
                try {
                    out.println("<tr style=\"background: #fff;\">\n"
                            + "                                    <td>\n"
                            + "                                        " + o.getId() + "\n"
                            + "                                    </td>\n"
                            + "                                    <td>\n"
                            + "                                        " + o.getCusName() + "\n"
                            + "                                    </td>\n"
                            + "                                    <td>\n"
                            + "                                        " + BuildingDAO.getBuildingById(o.getAddress()).getName() + "\n"
                            + "                                    </td>\n"
                            + "                                    <td>\n"
                            + "                                        " + SessionDAO.getSessionById(o.getSesssionId()).getFromto() + "\n"
                            + "                                    </td>\n"
                            + "                                    <td>\n"
                            + "                                        " + SessionDAO.getSessionById(o.getSesssionId()).getDay() + "\n"
                            + "                                    </td>\n"
                            + "                                    <td>\n"
                            + "                                        " + o.getPrice() + "\n"
                            + "                                    </td>\n"
                            + "                                    <td>\n"
                            + "                                        " + PaymentDAO.getPaymentById(o.getPayId()).getType() + "\n"
                            + "                                    </td>\n"
                            + "                                    <td>\n"
                            + "                                        <button value=\"" + o.getId() + "\" class=\"text-decoration-none func-btn\" onclick=\"acceptOrder(this)\">Accept</button> | \n"
                            + "                                        <button value=\"" + o.getId() + "\" class=\"text-decoration-none func-btn\" onclick=\"denyOrder(this)\">Deny</button>\n"
                            + "                                    </td>"
                            + "                                </tr>\n"
                    );

                } catch (SQLException ex) {
                    Logger.getLogger(FoodController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void showOrderByStatus(HttpServletRequest request, HttpServletResponse response) {
        try {
            PrintWriter out = response.getWriter();
            int status = Integer.parseInt(request.getParameter("status"));
            HttpSession sess = request.getSession();
            sess.setAttribute("status", status);
            List<OrderDTO> orderList = OrderDAO.getOrderByStatus(status);
            List<SessionDTO> sessionList = SessionDAO.getSessionForOrder(status);
            Set<String> timelines = new TreeSet<>();
            Set<String> days = new TreeSet<>();
            sessionList.forEach(s -> {
                timelines.add(s.getFromto());
                days.add(s.getDay());
            });
            out.println("<div class=\"sort\">\n"
                    + "                        <span class=\"sort-bar-label\">Lựa Chọn Thời Gian: </span>"
                    + "<select class=\"sort-option\" name=\"fromTo\" id=\"fromTo\">");
            timelines.forEach(t -> {
                out.println("<option value=\"" + t + "\">" + t + "</option>");
            });
            out.println("</select>"
                    + "<select class=\"sort-option\" name=\"day\" id=\"day\">");
            days.forEach(d -> {
                out.println("<option value=\"" + d + "\">" + d + "</option>");
            });
            out.println("</select>\n"
                    + "                        <input type=\"submit\" value=\"Tìm Kiếm\" class=\"search-btn\" onclick=\"searchOrderbySession()\" />"
                    + "</div> "
                    + "<table class=\"table table-striped table-hover\">\n"
                    + "                        <thead class=\"table-light\">\n"
                    + "                            <tr>\n"
                    + "                                <th>ID</th>\n"
                    + "                                <th>Customer Name</th>\n"
                    + "                                <th>Customer Address</th>\n"
                    + "                                <th>From - To</th>\n"
                    + "                                <th>Date</th>\n"
                    + "                                <th>Price</th>\n"
                    + "                                <th>Payment Method</th>\n"
                    + "                                <th></th>\n"
                    + "                            </tr>\n"
                    + "                        </thead>\n"
                    + "                        <tbody id=\"showOrder\">");
            orderList.forEach(o -> {
                try {
                    out.println("<tr style=\"background: #fff;\">\n"
                            + "                                    <td>\n"
                            + "                                        " + o.getId() + "\n"
                            + "                                    </td>\n"
                            + "                                    <td>\n"
                            + "                                        " + o.getCusName() + "\n"
                            + "                                    </td>\n"
                            + "                                    <td>\n"
                            + "                                        " + BuildingDAO.getBuildingById(o.getAddress()).getName() + "\n"
                            + "                                    </td>\n"
                            + "                                    <td>\n"
                            + "                                        " + SessionDAO.getSessionById(o.getSesssionId()).getFromto() + "\n"
                            + "                                    </td>\n"
                            + "                                    <td>\n"
                            + "                                        " + SessionDAO.getSessionById(o.getSesssionId()).getDay() + "\n"
                            + "                                    </td>\n"
                            + "                                    <td>\n"
                            + "                                        " + o.getPrice() + "\n"
                            + "                                    </td>\n"
                            + "                                    <td>\n"
                            + "                                        " + PaymentDAO.getPaymentById(o.getPayId()).getType() + "\n"
                            + "                                    </td>\n");
                    if (status == 1) {
                        out.println("                                    <td>\n"
                                + "                                        <button value=\"" + o.getId() + "\" class=\"text-decoration-none func-btn\" onclick=\"acceptOrder(this)\">Accept</button> | \n"
                                + "                                        <button value=\"" + o.getId() + "\" class=\"text-decoration-none func-btn\" onclick=\"denyOrder(this)\">Deny</button>\n"
                                + "                                    </td>\n");
                    } else {
                        out.println("                                    <td>\n"
                                + "                                    </td>\n");
                    };
                    out.println("                              </tr>");
                } catch (SQLException ex) {
                    Logger.getLogger(FoodController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
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
