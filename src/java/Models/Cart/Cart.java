/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models.Cart;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author PC
 */
public class Cart {

    private List<MealInCart> list;
    private String timeline;

    public Cart() {
        this.list = new ArrayList<>();
    }

    public Cart(String timeline) {
        this.timeline = timeline;
        this.list = new ArrayList<>();

    }

    public String getTimeline() {
        return timeline;
    }

    public void setTimeline(String timeline) {
        this.timeline = timeline;
    }

    public void add(MealInCart product) {
        list.add(product);
    }

    public List<MealInCart> getList() {
        return list;
    }

    public void empty() {
        list.clear();
    }

    public int size() {
        return list.size();
    }

    public int search(int MealId) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getMeal().getId() == MealId) {
                return i;
            }
        }
        return -1;
    }

    public MealInCart get(int index) {
        return list.get(index);
    }

    public void delete(int index) {
        list.remove(index);
    }

    public float total() {
        float sum = 0;
        for (MealInCart m : list) {
            sum += m.getQuantity() * m.getMeal().getPrice();
        }
        return sum;
    }

}
