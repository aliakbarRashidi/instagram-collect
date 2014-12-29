/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Data;

import Metodos.Pagination;
import java.lang.reflect.Field;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author labic
 */
public class User {
    private String id;
    private String username;
    private String profile_picture;
    
    private String bio;
    private String website;
    private String full_name;
    /**
     *
     */
    public User() {
    }

    /**
     *
     * @param names
     * @param values
     */
    public void setUser(String[] names, String[] values)
    {
      
        Field fld[] = User.class.getDeclaredFields();  
        
            for (int i=0 ; i < fld.length&&i<values.length; i++)  
            {  
            
                if(fld[i].getName().equalsIgnoreCase(names[i]))
                {
                    
                    try {
                        fld[i].set(this, values[i]);
                      
                       
                    } catch (IllegalArgumentException ex) {
                        Logger.getLogger(Pagination.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IllegalAccessException ex) {
                        Logger.getLogger(Pagination.class.getName()).log(Level.SEVERE, null, ex);
                    }
                  
                }
                
               
            }  
     //      System.out.println(" ");
        
       
    }

    /**
     *
     * @param id
     * @param username
     * @param profile_picture
     * @param full_name
     * @param bio
     * @param website
     */
    public User(String id, String username, String profile_picture, String full_name, String bio, String website) {
        this.id = id;
        this.username = username;
        this.profile_picture = profile_picture;
        this.full_name = full_name;
        this.bio = bio;
        this.website = website;
    }

    /**
     *
     * @return
     */
    public String getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     *
     * @return
     */
    public String getUsername() {
        return username;
    }

    /**
     *
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     *
     * @return
     */
    public String getProfile_picture() {
        return profile_picture;
    }

    /**
     *
     * @param profile_picture
     */
    public void setProfile_picture(String profile_picture) {
        this.profile_picture = profile_picture;
    }

    /**
     *
     * @return
     */
    public String getFull_name() {
        return full_name;
    }

    /**
     *
     * @param full_name
     */
    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    /**
     *
     * @return
     */
    public String getBio() {
        return bio;
    }

    /**
     *
     * @param bio
     */
    public void setBio(String bio) {
        this.bio = bio;
    }

    /**
     *
     * @return
     */
    public String getWebsite() {
        return website;
    }

    /**
     *
     * @param website
     */
    public void setWebsite(String website) {
        this.website = website;
    }
  
    
    
    
}
