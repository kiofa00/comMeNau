/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models.Building;

/**
 *
 * @author PC
 */
public class BuildingDTO {
    private int bId;
    private String name;

    public BuildingDTO() {
    }

    public BuildingDTO(int bId, String name) {
        this.bId = bId;
        this.name = name;
    }

    public int getbId() {
        return bId;
    }

    public void setbId(int bId) {
        this.bId = bId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    
    
}
