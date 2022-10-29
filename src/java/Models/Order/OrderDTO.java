/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models.Order;

/**
 *
 * @author PC
 */
public class OrderDTO {

    private int id;
    private int address;
    private String orderDetail;
    private int mealId;
    private int customerId;
    private double price;
    private int status;
    private int sesssionId;
    private int payId;
    private String cusName;
    private String phone;
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public OrderDTO() {
    }

    public OrderDTO(int id, int address, String orderDetail, int mealId, int customerId, double price, int status, int sesssionId, int payId, String cusName, String phone) {
        this.id = id;
        this.address = address;
        this.orderDetail = orderDetail;
        this.mealId = mealId;
        this.customerId = customerId;
        this.price = price;
        this.status = status;
        this.sesssionId = sesssionId;
        this.payId = payId;
        this.cusName = cusName;
        this.phone = phone;
    }

   
    
    public OrderDTO(int id, int address, String orderDetail, int mealId, int customerId, double price, int status, int sesssionId, int payId) {
        this.id = id;
        this.address = address;
        this.orderDetail = orderDetail;
        this.mealId = mealId;
        this.customerId = customerId;
        this.price = price;
        this.status = status;
        this.sesssionId = sesssionId;
        this.payId = payId;
    }
    public OrderDTO( int address, String orderDetail, int mealId, int customerId, double price, int status, int sesssionId, int payId) {
        this.address = address;
        this.orderDetail = orderDetail;
        this.mealId = mealId;
        this.customerId = customerId;
        this.price = price;
        this.status = status;
        this.sesssionId = sesssionId;
        this.payId = payId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAddress() {
        return address;
    }

    public void setAddress(int address) {
        this.address = address;
    }

    public String getOrderDetail() {
        return orderDetail;
    }

    public void setOrderDetail(String orderDetail) {
        this.orderDetail = orderDetail;
    }

    public int getMealId() {
        return mealId;
    }

    public void setMealId(int mealId) {
        this.mealId = mealId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getSesssionId() {
        return sesssionId;
    }

    public void setSesssionId(int sesssionId) {
        this.sesssionId = sesssionId;
    }

    public int getPayId() {
        return payId;
    }

    public void setPayId(int payId) {
        this.payId = payId;
    }

    public String getCusName() {
        return cusName;
    }

    public void setCusName(String cusName) {
        this.cusName = cusName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    
    
}
