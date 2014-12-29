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
public class Likes {
    private int count;
    ArrayList<User> user_liked;

    /**
     *
     */
    public Likes() {
    }

    /**
     *
     * @param count
     * @param user_liked
     */
    public Likes(int count, ArrayList<User> user_liked) {
        this.count = count;
        this.user_liked = user_liked;
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
    public ArrayList<User> getUser_liked() {
        return user_liked;
    }

    /**
     *
     * @param user_liked
     */
    public void setUser_liked(ArrayList<User> user_liked) {
        this.user_liked = user_liked;
    }
    
}
