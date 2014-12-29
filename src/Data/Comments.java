/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import java.util.ArrayList;

/**
 *
 * @author Equipe AVES
 */
public class Comments {
  private int count;
  private ArrayList<Comments_data> data;

    /**
     *
     * @param count
     * @param data
     */
    public Comments(int count, ArrayList<Comments_data> data) {
        this.count = count;
        this.data = data;
    }

    /**
     *
     */
    public Comments() {
          }

    /**
     *
     * @return
     */
    public int getCount() {
        return count;
    }

    /**
     *
     * @param count
     */
    public void setCount(int count) {
        this.count = count;
    }

    /**
     *
     * @return
     */
    public ArrayList<Comments_data> getData() {
        return data;
    }

    /**
     *
     * @param data
     */
    public void setData(ArrayList<Comments_data> data) {
        this.data = data;
    }
  
  
  
}
