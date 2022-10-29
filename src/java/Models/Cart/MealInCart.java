/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models.Cart;

import Models.MealGroup.MealGroupDTO;

/**
 *
 * @author PC
 */
public class MealInCart {
    private MealGroupDTO meal;
    private int quantity;

    public MealInCart() {
    }

    public MealInCart(MealGroupDTO meal, int quantity) {
        this.meal = meal;
        this.quantity = quantity;
    }

    public MealGroupDTO getMeal() {
        return meal;
    }

    public void setMeal(MealGroupDTO meal) {
        this.meal = meal;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    
}
