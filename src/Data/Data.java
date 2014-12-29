/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Data;

import java.util.ArrayList;

/**
 *
 * @author labic
 */
public class Data {
    
   private ArrayList<Photo> photo= new ArrayList<>();

    /**
     *
     */
    public Data() {
    }

    /**
     *
     * @return
     */
    public ArrayList<Photo> getPhoto() {
        return photo;
    }

    /**
     *
     * @param photo
     */
    public void setPhoto(ArrayList<Photo> photo) {
        this.photo = photo;
    }
   
   
   
}
