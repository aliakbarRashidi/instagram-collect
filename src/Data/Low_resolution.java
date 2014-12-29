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
public class Low_resolution {
   private String height;
    private String width;
    private String url;

    /**
     *
     * @param names
     * @param values
     */
    public void setLow_resolution(String[] names, String[] values)
    {
      
        Field fld[] = Low_resolution.class.getDeclaredFields();  
        
            for (int i=0 ; i < fld.length&&i<values.length; i++)  
            {  
                
                
                if(fld[i].getName().equalsIgnoreCase(names[i]))
                {
                    
                    try {
                        
                        fld[i].set(this, values[i]);
                 //      System.out.println(values[i]);
                       // System.out.println(created_time);
                       
                    } catch (IllegalArgumentException ex) {
                        Logger.getLogger(Pagination.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IllegalAccessException ex) {
                        Logger.getLogger(Pagination.class.getName()).log(Level.SEVERE, null, ex);
                    }
                  
                }
                
               
            }  
      //     System.out.println(" ");
        
         
    }

    /**
     *
     */
    public Low_resolution() {
    }

    /**
     *
     * @param height
     * @param width
     * @param url
     */
    public Low_resolution(String height, String width, String url) {
        this.height = height;
        this.width = width;
        this.url = url;
    }

    /**
     *
     * @return
     */
    public String getHeight() {
        return height;
    }

    /**
     *
     * @param height
     */
    public void setHeight(String height) {
        this.height = height;
    }

    /**
     *
     * @return
     */
    public String getWidth() {
        return width;
    }

    /**
     *
     * @param width
     */
    public void setWidth(String width) {
        this.width = width;
    }

    /**
     *
     * @return
     */
    public String getUrl() {
        return url;
    }

    /**
     *
     * @param url
     */
    public void setUrl(String url) {
        this.url = url;
    }

  
    
    
    
    
}
