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
public class Position {
    private String x;
    private String y;

    /**
     *
     * @param names
     * @param values
     */
    public void setPosition(String[] names, String[] values)
    {
      
        Field fld[] = Position.class.getDeclaredFields();  
        
            for (int i=0 ; i < fld.length&&i<values.length; i++)  
            {  
            
                if(fld[i].getName().equalsIgnoreCase(names[i]))
                {
                    
                    try {
                        fld[i].set(this, values[i]);
                 //       System.out.println(values[i]);
                       
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
     */
    public Position() {
    }

    /**
     *
     * @param x
     * @param y
     */
    public Position(String x, String y) {
        this.x = x;
        this.y = y;
    }

    /**
     *
     * @return
     */
    public String getX() {
        return x;
    }

    /**
     *
     * @param x
     */
    public void setX(String x) {
        this.x = x;
    }

    /**
     *
     * @return
     */
    public String getY() {
        return y;
    }

    /**
     *
     * @param y
     */
    public void setY(String y) {
        this.y = y;
    }
    
    
}
