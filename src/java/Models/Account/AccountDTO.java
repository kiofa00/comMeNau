/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models.Account;

/**
 *
 * @author PC
 */
public class AccountDTO {
    private int id;
    private String role;
    private boolean verify;
    public AccountDTO() {
    }

    public AccountDTO(int id, String role, boolean verify) {
        this.id = id;
        this.role = role;
        this.verify = verify;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean getVerify() {
        return verify;
    }

    public void setVerify(boolean verify) {
        this.verify = verify;
    }
    
    
    
}
