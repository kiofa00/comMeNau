/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models.Status;

/**
 *
 * @author PC
 */
public enum OrderStatus {
    _0("Đã hủy"),
    _1("Đang xử lí"),
    _2("Đã xác nhận");
    
    public final String label;
    private OrderStatus(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return label;
    }
    
}
