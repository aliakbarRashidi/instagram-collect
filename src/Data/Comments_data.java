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
public class Comments_data {
    
    private String id ;
    private String text ;
    private From from;
    private String created_time;

    /**
     *
     * @param names
     * @param values
     */
    public void setComments_data(String[] names, String[] values)
    {
      
        Field fld[] = Comments_data.class.getDeclaredFields();  
        
            for (int i=0 ; i < fld.length&&i<values.length; i++)  
            {  
            
                if(fld[i].getName().equalsIgnoreCase(names[i]))
                {
                    
                    try {
                        fld[i].set(this, values[i]);
                  //      System.out.println(values[i]);
                       
                    } catch (IllegalArgumentException ex) {
                        Logger.getLogger(Pagination.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IllegalAccessException ex) {
                        Logger.getLogger(Pagination.class.getName()).log(Level.SEVERE, null, ex);
                    }
                  
                }
                
               
            }  
    //       System.out.println(" ");
        
       
    }
    
    /**
     *
     */
    public Comments_data() {
    }

    /**
     *
     * @param id
     * @param text
     * @param from
     * @param created_time
     */
    public Comments_data(String id, String text, From from, String created_time) {
        this.id = id;
        this.text = text;
        this.from = from;
        this.created_time = created_time;
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
    public String getText() {
        return text;
    }

    /**
     *
     * @param text
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     *
     * @return
     */
    public From getFrom() {
        return from;
    }

    /**
     *
     * @param from
     */
    public void setFrom(From from) {
        this.from = from;
    }

    /**
     *
     * @return
     */
    public String getCreated_time() {
        return created_time;
    }

    /**
     *
     * @param created_time
     */
    public void setCreated_time(String created_time) {
        this.created_time = created_time;
    }
    
    
    
    
}
