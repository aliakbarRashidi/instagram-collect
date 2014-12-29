/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import Metodos.Pagination;
import java.lang.reflect.Field;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Equipe AVES
 */
public class Location {
    private String id;
    private String name;
    private String longitude;
    private String latitude;

    /**
     *
     * @param names
     * @param values
     */
    public void setLocation(String[] names, String[] values)
    {
      
        Field fld[] = Location.class.getDeclaredFields();  
        
            for (int i=0 ; i < fld.length&&i<values.length; i++)  
            {  
            
                if(fld[i].getName().equalsIgnoreCase(names[i]))
                {
                    
                    try {
                        fld[i].set(this, values[i]);
         //               System.out.println(values[i]);
                       
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
     * @param name
     * @param longitude
     * @param latitude
     */
    public Location(String id, String name, String longitude, String latitude) {
        this.id = id;
        this.name = name;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    /**
     *
     */
    public Location() {
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
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     */
    public String getLongitude() {
        return longitude;
    }

    /**
     *
     * @param longitude
     */
    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    /**
     *
     * @return
     */
    public String getLatitude() {
        return latitude;
    }

    /**
     *
     * @param latitude
     */
    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }
    
    
}
