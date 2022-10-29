/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models.Food;

import java.util.List;

/**
 *
 * @author PC
 */
public class FoodDTO {
    private int id;
    private String name;
    private String nutrition;
    private List<String> images;
    public FoodDTO() {
    }

    public FoodDTO(int id, String name, String nutrition, List<String> images) {
        this.id = id;
        this.name = name;
        this.nutrition = nutrition;
        this.images = images;
    }

    public FoodDTO( String name, String nutrition, List<String> images) {
        this.name = name;
        this.nutrition = nutrition;
        this.images = images;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    

    public String getNutrition() {
        return nutrition;
    }

    public void setNutrition(String nutrition) {
        this.nutrition = nutrition;
    }


    
    
}
