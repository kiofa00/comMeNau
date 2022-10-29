/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models.MealGroup;

import Models.Chef.ChefDTO;
import Models.Food.FoodDTO;
import java.util.List;

/**
 *
 * @author PC
 */
public class MealGroupDTO {
    private int id;
    private double price;
    private String detail;
    private ChefDTO chefId;
    private String name;
    private List<String> images;
    private List<FoodDTO> foods;
    public MealGroupDTO() {
    }

    public MealGroupDTO(int id, double price, String detail, ChefDTO chefId, String name, List<String> images, List<FoodDTO> foods) {
        this.id = id;
        this.price = price;
        this.detail = detail;
        this.chefId = chefId;
        this.name = name;
        this.images = images;
        this.foods = foods;
    }

    public List<FoodDTO> getFoods() {
        return foods;
    }

    public void setFoods(List<FoodDTO> foods) {
        this.foods = foods;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public ChefDTO getChefId() {
        return chefId;
    }

    public void setChefId(ChefDTO chefId) {
        this.chefId = chefId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    
    
    
}
