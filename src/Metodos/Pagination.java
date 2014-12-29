/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Metodos;

import java.lang.reflect.Field;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author labic
 */
public class Pagination {
  private String next_max_tag_id;
  private String deprecation_warning;
  private String next_max_id;  
  private String next_min_id;
  private String min_tag_id;
  private String next_url;
  Metodos.Meta meta ;
  
    /**
     *
     */
    public Pagination() {
   
        
    }
    
    /**
     *
     * @param names
     * @param values
     */
    public void setPagination(String[] names, String[] values)
    {
      
        Field fld[] = Pagination.class.getDeclaredFields();  
        
            for (int i=0 ; i < fld.length&&i<values.length; i++)  
            {  
               
                if(fld[i].getName().equalsIgnoreCase(names[i]))
                {
                   //  System.out.println(names[i]);
                    try {
                        fld[i].set(this, values[i]);
              //           System.out.println(values[i]);
                    } catch (IllegalArgumentException ex) {
                        Logger.getLogger(Pagination.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IllegalAccessException ex) {
                        Logger.getLogger(Pagination.class.getName()).log(Level.SEVERE, null, ex);
                    }
                  
                }
                
               
            }  
        
      
    }
  
    /**
     *
     * @param next_max_tag_id
     * @param deprecation_warning
     * @param next_max_id
     * @param next_min_id
     * @param min_tag_id
     * @param next_url
     */
    public void setPagination(String next_max_tag_id, String deprecation_warning, String next_max_id, String next_min_id, String min_tag_id, String next_url) {
        this.next_max_tag_id = next_max_tag_id;
        this.deprecation_warning = deprecation_warning;
        this.next_max_id = next_max_id;
        this.next_min_id = next_min_id;
        this.min_tag_id = min_tag_id;
        this.next_url = next_url;
    }
  
    /**
     *
     * @param next_max_tag_id
     * @param deprecation_warning
     * @param next_max_id
     * @param next_min_id
     * @param min_tag_id
     * @param next_url
     */
    public Pagination(String next_max_tag_id, String deprecation_warning, String next_max_id, String next_min_id, String min_tag_id, String next_url) {
        this.next_max_tag_id = next_max_tag_id;
        this.deprecation_warning = deprecation_warning;
        this.next_max_id = next_max_id;
        this.next_min_id = next_min_id;
        this.min_tag_id = min_tag_id;
        this.next_url = next_url;
    }

    /**
     *
     * @return
     */
    public String getNext_max_tag_id() {
        return next_max_tag_id;
    }

    /**
     *
     * @param next_max_tag_id
     */
    public void setNext_max_tag_id(String next_max_tag_id) {
        this.next_max_tag_id = next_max_tag_id;
    }

    /**
     *
     * @return
     */
    public String getDeprecation_warning() {
        return deprecation_warning;
    }

    /**
     *
     * @param deprecation_warning
     */
    public void setDeprecation_warning(String deprecation_warning) {
        this.deprecation_warning = deprecation_warning;
    }

    /**
     *
     * @return
     */
    public String getNext_max_id() {
        return next_max_id;
    }

    /**
     *
     * @param next_max_id
     */
    public void setNext_max_id(String next_max_id) {
        this.next_max_id = next_max_id;
    }

    /**
     *
     * @return
     */
    public String getNext_min_id() {
        return next_min_id;
    }

    /**
     *
     * @param next_min_id
     */
    public void setNext_min_id(String next_min_id) {
        this.next_min_id = next_min_id;
    }

    /**
     *
     * @return
     */
    public String getMin_tag_id() {
        return min_tag_id;
    }

    /**
     *
     * @param min_tag_id
     */
    public void setMin_tag_id(String min_tag_id) {
        this.min_tag_id = min_tag_id;
    }

    /**
     *
     * @return
     */
    public String getNext_url() {
        return next_url;
    }

    /**
     *
     * @param next_url
     */
    public void setNext_url(String next_url) {
        this.next_url = next_url;
    }

    public void setMeta(Meta meta) {
this.meta = meta;
    }
  
     
}
