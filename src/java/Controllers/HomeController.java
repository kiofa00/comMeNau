/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.Account.AccountDAO;
import Models.Building.BuildingDAO;
import Models.Building.BuildingDTO;
import Models.Cart.Cart;
import Models.Cart.MealInCart;
import Models.Customer.CustomerDTO;
import Models.MealGroup.MealGroupDAO;
import Models.MealGroup.MealGroupDTO;
import Models.Order.OrderDAO;
import Models.Order.OrderDTO;
import Models.Payment.PaymentDAO;
import Models.Payment.PaymentDTO;
import Models.Session.SessionDAO;
import Models.Session.SessionDTO;
import Models.Session.Timeline;
import Service.OrderService;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
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
 * @author PC
 */
@WebServlet(name = "HomeController", urlPatterns = {"/home"})
@MultipartConfig
public class HomeController extends HttpServlet {

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
            case "homePage":
                showHomePage(request, response);
                break;
            case "showMealByTimelineAjax":
                showMealByTimelineAjax(request, response);
                break;
            case "showMealbyDay":
                showMealbyDay(request, response);
                break;
            case "searchAjax":
                SearchMealAjax(request, response);
                break;
            case "addToCart":
                addToCart(request, response);
                break;
            case "showCart":
                showCart(request, response);
                break;
            case "checkout":
                checkout(request, response);
                break;
            case "thankyou":
                thankyou(request, response);
                break;
            case "buildingPage":
                showBuildingPage(request, response);
                break;
            case "BuildingDetail":
                showBuilding(request, response);
                break;
            case "updateBuilding":
                updateBuilding(request, response);
                break;
            case "newBuilding":
                newBuilding(request, response);
                break;
            case "checkOrderPage":
                OrderService.CheckOrderPage(request, response);
                break;
            case "CheckOrder":
                OrderService.CheckOrder(request, response);
                break;
        }
    }

    public void newBuilding(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        List<BuildingDTO> buildingList = BuildingDAO.getAllBuilding();
//        request.setAttribute("buildingList", buildingList);
        request.getRequestDispatcher("/WEB-INF/Layers/adminShow.jsp").forward(request, response);
    }

    public static void updateBuilding(HttpServletRequest request, HttpServletResponse response) {
        try {
            int buildingId = Integer.parseInt(request.getParameter("buildingId"));
            BuildingDTO building = BuildingDAO.getBuildingById(buildingId);
            request.setAttribute("building", building);
            request.getRequestDispatcher("/WEB-INF/Layers/show.jsp").forward(request, response);
        } catch (Exception e) {
        }
    }

    public void showHomePage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            HttpSession sess = request.getSession();
            HashMap<String, String> dayInVietNam = new HashMap<>();
            dayInVietNam.put("Monday", "Thu 2");
            dayInVietNam.put("Tuesday", "Thu 3");
            dayInVietNam.put("Wednesday", "Thu 4");
            dayInVietNam.put("Thursday", "Thu 5");
            dayInVietNam.put("Friday", "Thu 6");
            dayInVietNam.put("Saturday", "Thu 7");
            dayInVietNam.put("Sunday", "chu nhat");
            LocalDate local = LocalDate.now();
            HashMap<String, String> dayOfWeek = new HashMap<>();
            DayOfWeek d = local.getDayOfWeek();
            dayOfWeek.put(dayInVietNam.get(d.getDisplayName(TextStyle.FULL, Locale.getDefault())), local.getDayOfMonth() + "/" + local.getMonthValue());
            System.out.println(d.getDisplayName(TextStyle.FULL, Locale.getDefault()));
            for (int i = 0; i < 6; i++) {
                local = local.plusDays(1);
                d = local.getDayOfWeek();
                dayOfWeek.put(dayInVietNam.get(d.getDisplayName(TextStyle.FULL, Locale.getDefault())), local.getDayOfMonth() + "/" + local.getMonthValue());
            }
            sess.setAttribute("dayOfWeek", dayOfWeek);
            LocalDate localNow = LocalDate.now();
            String day = Integer.toString(localNow.getDayOfMonth()) + "/" + Integer.toString(localNow.getMonthValue());
            SessionDTO sessionForToday = SessionDAO.getTimelineByDay(day);
            Calendar cal = Calendar.getInstance();
            //get top meal in day
            int hour = cal.get(Calendar.HOUR_OF_DAY);
            int min = 24;
            Timeline topTimeline = new Timeline();
            for (Timeline t : sessionForToday.getTimeline()) {
                int to = t.getTo();
                int distance = t.getTo() - hour;
                System.out.println(distance);
                if (distance < min && distance > 0) {
                    min = t.getTo() - hour;
                    topTimeline = t;
                }
            }

            if (min == 24) {
                if (sessionForToday.getTimelineSize() > 0) {
                    topTimeline = sessionForToday.getTimeline().get(sessionForToday.getTimelineSize() - 1);
                }
            }
            String topFromTo = topTimeline.getFrom() + "-" + topTimeline.getTo();
            request.setAttribute("topFromTo", topFromTo);
            request.setAttribute("topDay", day);
            MealGroupDTO top1 = MealGroupDAO.getMealTop1(day, topFromTo);
            request.setAttribute("MealHot", top1);

            String dayInSess = (String) sess.getAttribute("day");
            String timelineInSess = (String) sess.getAttribute("timeline");
            System.out.println(dayInSess + timelineInSess);
            if (dayInSess != null && timelineInSess != null) {
                SessionDTO session1 = SessionDAO.getTimelineByDay(dayInSess);
                if (session1.getTimelineSize() == 0) {
                    request.setAttribute("message", "Hôm nay chưa có món mong khánh hàng chọn một ngày khác");
                } else {
                    request.setAttribute("session", session1);
                    List<MealGroupDTO> mealList = MealGroupDAO.getMealGroupByTimelineAndDay(timelineInSess, dayInSess);
                    request.setAttribute("mealList", mealList);
                }

            } else {
                SessionDTO session = SessionDAO.getTimelineByDay(day);
                System.out.println(day);
                request.setAttribute("session", session);
                if (!session.getTimeline().isEmpty()) {

                    List<MealGroupDTO> mealList = MealGroupDAO.getMealGroupByTimelineAndDay(
                            session.getTimeline().get(0).getFrom() + "-" + session.getTimeline().get(0).getTo(), day);
                    sess.setAttribute("day", day);
                    sess.setAttribute("timeline", session.getTimeline().get(0).getFrom() + "-" + session.getTimeline().get(0).getTo());
                    request.setAttribute("mealList", mealList);
                } else {
                    request.setAttribute("message", "Hôm nay chưa có món mong khánh hàng chọn một ngày khác");
                    sess.setAttribute("day", day);
                }
            }
            request.getRequestDispatcher("/WEB-INF/Layers/main.jsp").forward(request, response);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
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

    public void showBuilding(HttpServletRequest request, HttpServletResponse response) {

        try {
            HttpSession session = request.getSession();
            PrintWriter out = response.getWriter();
            int buildingId = Integer.parseInt(request.getParameter("buildingId"));
            request.setAttribute("buildingId", buildingId);
            BuildingDTO building = BuildingDAO.getBuildingById(buildingId);
            out.println("<div class=\"modal-content\">\n"
                    + "                            <div class=\"modal-header justify-content-center\">\n"
                    + "                                <h2 class=\"modal-header-title\" id=\"bName\">" + building.getName() + "</h2>\n"
                    + "                            </div>\n"
                    + "<form action=\"/comMeNau/admin/updateBuilding.do\" class=\"form\" style=\"padding-bottom: 25px;\">"
                    + "                                <div class=\"pt-35\">\n"
                    + "                                    <label class=\"label\" for=\"name\">Tên Building:</label>\n"
                    + "                                    <input id=\"bName\"\n"
                    + "                                           class=\"form-control\" type=\"text\" value=\"" + building.getName() + "\"  name=\"name\"/>\n"
                    + "                                </div>\n"
                    + "                                <button  onclick=\"updateBuilding()\" >Lưu</button>\n"
                    + "</form>"
                    + "                        </div>");
        } catch (IOException e) {
            Logger.getLogger(FoodController.class.getName()).log(Level.SEVERE, null, e);
        } catch (SQLException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void showMealbyDay(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            HttpSession sess = request.getSession();
            String day = request.getParameter("day1");
            System.out.println(day);
            SessionDTO session = SessionDAO.getTimelineByDay(day);
            PrintWriter out = response.getWriter();
            sess.setAttribute("day", day);

            if (!session.getTimeline().isEmpty()) {
                sess.setAttribute("timeline", session.getTimeline().get(0).getFrom() + "-" + session.getTimeline().get(0).getTo());
                List<MealGroupDTO> mealList = MealGroupDAO.getMealGroupByTimelineAndDay(
                        session.getTimeline().get(0).getFrom() + "-" + session.getTimeline().get(0).getTo(), day);
                out.println("<div class=\"sort\">\n"
                        + "                                    <span class=\"sort-bar-label\">Sắp Xếp Theo</span>");
                session.getTimeline().forEach(t -> {
                    out.println("<button value=\"" + t.getFrom() + "-" + t.getTo() + "\" class=\"sort-option\" onclick=\"getMealBySession(this)\">" + t.getFrom() + "-" + t.getTo() + "</button>");
                });
                out.println("</div>\n"
                        + "                                <div id=\"show1\" class=\"row\">");
                ////localhost:8080/comMeNau/food/mealDetail.do?mealId=" + m.getId() +
                mealList.stream().forEach(m -> {
                    out.println("<div class=\"col l-4 c-12 m-6\">\n"
                            + "                                            <a class=\"card\" href=\"//localhost:8080/comMeNau/food/mealDetail.do?mealId=" + m.getId() + "\"/>\n"
                            + "                                                <div class=\"card-top\">\n"
                            + "                                                    <img src=\"/comMeNau/images/" + m.getImages().get(0) + "\" class=\"card-img-big\">\n"
                            + "                                                    <h5 class=\"card-title\">" + m.getName() + "</h5>\n"
                            + "                                                </div>\n"
                            + "                                                <div class=\"card-body row\">\n"
                            + "                                                    <p class=\"card-price\">\n"
                            + "                                                        <span class=\"card-price-pre\">Từ</span>\n"
                            + "                                                            " + Math.round(m.getPrice()) + "VND \n"
                            + "                                                    </p>       \n"
                            + "                                                    <p class=\"card-chef\">\n"
                            + "                                                        <span class=\"card-chef-pre\">Đầu Bếp: </span>\n"
                            + "                                                        " + m.getChefId().getName() + "\n"
                            + "                                                    </p>           \n"
                            + "                                                </div>\n"
                            + "                                            </a> \n"
                            + "                                        </div>                 ");
                });
            } else {
                out.println("<div class=\"sort\">\n"
                        + "                                    <span class=\"sort-bar-label\">Sắp Xếp Theo</span>\n"
                        + "                                </div>"
                        + "<div id=\"show1\" class=\"row\">"
                        + "<h1>Hôm nay chưa có món mong khánh hàng chọn một ngày khác</h1>"
                        + "</div>");

            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void showMealByTimelineAjax(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {

            HttpSession sess = request.getSession();
            PrintWriter out = response.getWriter();
            String day = (String) sess.getAttribute("day");
            String timeline = request.getParameter("timeline");
            System.out.println(timeline);
            sess.setAttribute("timeline", timeline);
            List<MealGroupDTO> mealList = MealGroupDAO.getMealGroupByTimelineAndDay(timeline, day);
            mealList.stream().forEach(m -> {
                out.println("<div class=\"col l-4 c-12 m-6\">\n"
                        + "                                            <a class=\"card\" href=\"//localhost:8080/comMeNau/food/mealDetail.do?mealId=" + m.getId() + "\"/>\n"
                        + "                                                <div class=\"card-top\">\n"
                        + "                                                    <img src=\"/comMeNau/images/" + m.getImages().get(0) + "\" class=\"card-img-big\">\n"
                        + "                                                    <h5 class=\"card-title\">" + m.getName() + "</h5>\n"
                        + "                                                </div>\n"
                        + "                                                <div class=\"card-body row\">\n"
                        + "                                                    <p class=\"card-price\">\n"
                        + "                                                        <span class=\"card-price-pre\">Từ</span>\n"
                        + "                                                            " + Math.round(m.getPrice()) + "VND \n"
                        + "                                                    </p>       \n"
                        + "                                                    <p class=\"card-chef\">\n"
                        + "                                                        <span class=\"card-chef-pre\">Đầu Bếp: </span>\n"
                        + "                                                        " + m.getChefId().getName() + "\n"
                        + "                                                    </p>           \n"
                        + "                                                </div>\n"
                        + "                                            </a> \n"
                        + "                                        </div>                 ");

            });
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void SearchMealAjax(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {

            HttpSession sess = request.getSession();
            PrintWriter out = response.getWriter();
            String day = (String) sess.getAttribute("day");
            String timeline = (String) sess.getAttribute("timeline");
            String search = request.getParameter("Search");
            System.out.println(search);
            List<MealGroupDTO> mealList = MealGroupDAO.searchMealByDay(search, day, timeline);
            mealList.stream().forEach(m -> {
                out.println("<div class=\"col l-4 c-12 m-6\">\n"
                        + "                                            <a class=\"card\" href=\"//localhost:8080/comMeNau/food/mealDetail.do?mealId=" + m.getId() + "\"/>\n"
                        + "                                                <div class=\"card-top\">\n"
                        + "                                                    <img src=\"/comMeNau/images/" + m.getImages().get(0) + "\" class=\"card-img-big\">\n"
                        + "                                                    <h5 class=\"card-title\">" + m.getName() + "</h5>\n"
                        + "                                                </div>\n"
                        + "                                                <div class=\"card-body row\">\n"
                        + "                                                    <p class=\"card-price\">\n"
                        + "                                                        <span class=\"card-price-pre\">Từ</span>\n"
                        + "                                                            " + Math.round(m.getPrice()) + "VND \n"
                        + "                                                    </p>       \n"
                        + "                                                    <p class=\"card-chef\">\n"
                        + "                                                        <span class=\"card-chef-pre\">Đầu Bếp: </span>\n"
                        + "                                                        " + m.getChefId().getName() + "\n"
                        + "                                                    </p>           \n"
                        + "                                                </div>\n"
                        + "                                            </a> \n"
                        + "                                        </div>                 ");
            });
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void addToCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        PrintWriter out = response.getWriter();

        try {
            int mealId = Integer.parseInt(request.getParameter("mealId"));
            String timeline = (String) session.getAttribute("timeline");
            String day = (String) session.getAttribute("day");
            MealGroupDTO meal = MealGroupDAO.getMealGroupByIdAndTimelineAndDay(timeline, day, mealId);
            Cart cart = (Cart) session.getAttribute("cart");
            if (cart == null) {
                cart = new Cart(timeline);
            }
            int checkExist = cart.search(mealId);
            if (checkExist == -1) {
                cart.add(new MealInCart(meal, 1));
            } else {
                cart.get(checkExist).setQuantity(cart.get(checkExist).getQuantity() + 1);
            }
            System.out.println(cart.getList().size());

            session.setAttribute("cart", cart);
            int quantity = 0;
            for (int i = 0; i < cart.size(); i++) {
                quantity += cart.get(i).getQuantity();
            }
            session.setAttribute("quantity", quantity);
            out.println(quantity);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void showCart(HttpServletRequest request, HttpServletResponse response) {
        try {
            int mealId = Integer.parseInt(request.getParameter("mealId"));
            HttpSession session = request.getSession();
            String topTimeline = request.getParameter("topTimeline");
            String topDay = request.getParameter("topDay");
            String day = null;
            String timeline = null;
            System.out.println(topDay + " " + topTimeline);
            if (topTimeline.isEmpty() || topDay.isEmpty()) {
                day = (String) session.getAttribute("day");;
                timeline = (String) session.getAttribute("timeline");
            } else {
                day = topDay;
                timeline = topTimeline;
                request.setAttribute("topTimeline", topTimeline);
                request.setAttribute("topDay", topDay);
            }
            MealGroupDTO meal = MealGroupDAO.getMealGroupByIdAndTimelineAndDay(timeline, day, mealId);
            List<PaymentDTO> payList = PaymentDAO.getPayment();
            List<BuildingDTO> buildingList = BuildingDAO.getAllBuilding();

            String dayinMonth = day.split("/")[0];
            Calendar c = Calendar.getInstance();
            int hours = c.get(Calendar.HOUR_OF_DAY);
            int date = c.get(Calendar.DATE);
            String[] fromTo = timeline.split("-");
            if (Integer.parseInt(fromTo[1]) < hours && Integer.parseInt(dayinMonth) == date) {
                System.out.println(Integer.parseInt(fromTo[1]));
                request.setAttribute("message", "Đã quá thời gian của món vui lòng chọn khung giờ khác!");
                try {
                    request.getRequestDispatcher("/food/mealDetail.do").forward(request, response);
                    return;
                } catch (ServletException ex) {
                    Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            request.setAttribute("buildList", buildingList);
            request.setAttribute("payList", payList);
            request.setAttribute("meal", meal);
            request.getRequestDispatcher("/WEB-INF/Layers/show.jsp").forward(request, response);
        } catch (ServletException | IOException | SQLException | ClassNotFoundException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void checkout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        CustomerDTO cus = (CustomerDTO) session.getAttribute("user");
        String topTimeline = request.getParameter("topTimeline");
        String topDay = request.getParameter("topDay");
        String day = null;
        String timeline = null;
        System.out.println(topDay + " " + topTimeline);
        if (topTimeline.isBlank() || topDay.isBlank()) {
            day = (String) session.getAttribute("day");
            timeline = (String) session.getAttribute("timeline");
        } else {
            day = topDay;
            timeline = topTimeline;
        }
        System.out.println(day+" "+timeline);
        String email = request.getParameter("email");
        String dayinMonth = day.split("/")[0];
        String owner = request.getParameter("name");
        String phone = request.getParameter("phone");
        int address = Integer.parseInt(request.getParameter("location"));
        String note = request.getParameter("note");
        int mealId = Integer.parseInt(request.getParameter("mealId"));
        int payId = Integer.parseInt(request.getParameter("payment"));
        Calendar c = Calendar.getInstance();
        int hours = c.get(Calendar.HOUR_OF_DAY);
        int date = c.get(Calendar.DATE);
        String[] fromTo = timeline.split("-");
        System.out.println(email);
        try {
            int sessionId = SessionDAO.getSessionId(day, timeline);
            MealGroupDTO meal = MealGroupDAO.getMealGroupByIdAndTimelineAndDay(timeline, day, mealId);
            if (cus != null) {
                OrderDAO.insertOrder(new OrderDTO(address, note, mealId, cus.getId(), meal.getPrice(), 1, sessionId, payId), cus.getName(), cus.getPhone(), email);
            } else {
                OrderDAO.insertOrder2(address, note, mealId, owner, phone, meal.getPrice(), 1, sessionId, payId, email);
                int lastOrder = OrderDAO.lastOrder();
                String title = "Thông Tin Đơn hàng";
                String message = "<div class=\"row\">\n"
                        + "                        <div class=\"card-detail-item\">\n"
                        + "                            <div class=\"card-detail-box row m-0\">\n"
                        + "                                <img src=\"${pageContext.request.contextPath}/images/" + meal.getImages().get(0) + "\" class=\"card-detail-img col l-4 p-0\">\n"
                        + "                                <div class=\"card-detail-content col l-8 p-0\">\n"
                        + "                                   <h4 class=\"food-name\">Mã Đơn Hàng  " + lastOrder + "</h4>\n"
                        + "                                    <div class=\"row card-detail-info\">\n"
                        + "                                      <p class=\"card-meal-name col l-6\">Tên Mâm Cơm:  " + meal.getName() + "</p>\n"
                        + "                                        <p class=\"card-price col l-6\">\n"
                        + "                                            <span class=\"card-pre\">Giá: </span>\n"
                        + "                                           " + meal.getPrice() + "  \n"
                        + "                                        </p>       \n"
                        + "                                    </div>\n"
                        + "                                    <div class=\"row card-detail-info\">\n"
                        + "                             <p class=\"card-date col l-6\"> Khung thời gian:  \n"
                        + "                                            " + timeline + " " + day + "\n"
                        + "                                        </p>\n"
                        + "                                        <p class=\"card-address col l-6\">\n"
                        + "                                            <span class=\"card-pre\">Địa Chỉ:</span> " + BuildingDAO.getBuildingById(address).getName() + "\n"
                        + "                                        </p> \n"
                        + "                                    </div>\n"
                        + "                                </div>\n"
                        + "                            </div>\n"
                        + "                        </div> \n"
                        + "                    </div>"
                        + ""
                        + "<a class=\"logo-link\" href=\"http://localhost:8080/comMeNau/home/checkOrderPage.do\">Kiểm Tra Thông tin Đơn Hàng</a>";
                AccountDAO.sendEmmail(email, "ntthuc321@gmail.com", "igaajhaauawyrusd", message, title);

            }

            response.sendRedirect("//localhost:8080/comMeNau/home/thankyou.do");
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void thankyou(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/Layers/show.jsp").forward(request, response);
    }

    public static void a(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        if (!ServletFileUpload.isMultipartContent(request)) {
            out.println("Nothing to upload");
            return;
        }
        FileItemFactory itemfactory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(itemfactory);
        try {
            List<FileItem> items = upload.parseRequest(new ServletRequestContext(request));
            for (FileItem item : items) {
                String contentType = item.getContentType();
                System.out.println(contentType);
                if (!contentType.equals("image/jpeg")) {
                    out.println("only png format image files supported");
                    continue;
                }
                File uploadDir = new File("C:\\Users\\PC\\Desktop\\test\\web\\images\\" + item.getName() + ".");

                //File file = File.createTempFile("img", ".jpeg", uploadDir);
                item.write(uploadDir);
                System.out.println(item.getName());
                out.println("File Saved Successfully");
            }
        } catch (FileUploadException e) {

            out.println("upload fail");
        } catch (Exception ex) {
            out.println("can't save");
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
