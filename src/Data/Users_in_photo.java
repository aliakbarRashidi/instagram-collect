/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Data;

/**
 *
 * @author labic
 */
public class Users_in_photo {
    
    private User user ;
    private Position position;

    /**
     *
     * @param user
     * @param position
     */
    public Users_in_photo(User user, Position position) {
        this.user = user;
        this.position = position;
    }

    /**
     *
     */
    public Users_in_photo() {
    }

    /**
     *
     * @return
     */
    public User getUser() {
        return user;
    }

    /**
     *
     * @param user
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     *
     * @return
     */
    public Position getPosition() {
        return position;
    }

    /**
     *
     * @param position
     */
    public void setPosition(Position position) {
        this.position = position;
    }
    
}
