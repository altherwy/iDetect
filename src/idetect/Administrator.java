/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package idetect;

/**
 *
 * @author youssef 19
 */
public class Administrator   {
    private int adminId=-1;
    private String name;
    private String password;


    ////// Administrator Constructor /////
    public Administrator(String name,String password)
    {
        adminId++;
        this.setName(name);
        this.setPassword(password);
        
    }

    /**
     * @return the adminId
     */
    public int getAdminId() {
        return adminId;
    }

    /**
     * @param adminId the adminId to set
     */
    public void setAdminId(int adminId) {
        ////// Here DB checking //////
        this.adminId = adminId;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the eMail
     */
    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }
   
    
    



}
