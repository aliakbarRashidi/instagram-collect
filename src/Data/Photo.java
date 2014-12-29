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
public class Photo {
    
    
    private String tags;
    private Location location;
    private String link;
    private String user_has_liked;
    private Caption caption;
    private String type;
    private String id;
    private Likes likes;
    private Images images;
    private String created_time;
    private ArrayList<Users_in_photo> users_in_photo;
    private User user;
    private String filter;
    private Comments comments;
    private String attribution;
    private Videos videos = new Videos();

    /**
     *
     */
    public Photo() {
    }

    
    
    /**
     *
     * @param tags
     * @param location
     * @param link
     * @param user_has_liked
     * @param caption
     * @param type
     * @param id
     * @param likes
     * @param images
     * @param created_time
     * @param users_in_photo
     * @param user
     * @param filter
     * @param comments
     * @param attribution
     * @param videos
     */
    public Photo(String tags, Location location, String link, String user_has_liked, Caption caption, String type, String id, Likes likes, Images images, String created_time, ArrayList<Users_in_photo> users_in_photo, User user, String filter, Comments comments, String attribution,Videos videos) {
        this.tags = tags;
        this.location = location;
        this.link = link;
        this.user_has_liked = user_has_liked;
        this.caption = caption;
        this.type = type;
        this.id = id;
        this.likes = likes;
        this.images = images;
        this.created_time = created_time;
        this.users_in_photo = users_in_photo;
        this.user = user;
        this.filter = filter;
        this.comments = comments;
        this.attribution = attribution;
        this.videos = videos;
    }

    public Videos getVideos() {
        return videos;
    }

    public void setVideos(Videos videos) {
        this.videos = videos;
    }
    
    

    /**
     *
     * @return
     */
    public String getTags() {
        return tags;
    }

    
    
    /**
     *
     * @param tags
     */
    public void setTags(String tags) {
        this.tags = tags;
    }

    /**
     *
     * @return
     */
    public Location getLocation() {
        return location;
    }

    /**
     *
     * @param location
     */
    public void setLocation(Location location) {
        this.location = location;
    }

    /**
     *
     * @return
     */
    public String getLink() {
        return link;
    }

    /**
     *
     * @param link
     */
    public void setLink(String link) {
        this.link = link;
    }

    /**
     *
     * @return
     */
    public String getUser_has_liked() {
        return user_has_liked;
    }

    /**
     *
     * @param user_has_liked
     */
    public void setUser_has_liked(String user_has_liked) {
        this.user_has_liked = user_has_liked;
    }

    /**
     *
     * @return
     */
    public Caption getCaption() {
        return caption;
    }

    /**
     *
     * @param caption
     */
    public void setCaption(Caption caption) {
        this.caption = caption;
    }

    /**
     *
     * @return
     */
    public String getType() {
        return type;
    }

    /**
     *
     * @param type
     */
    public void setType(String type) {
        this.type = type;
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
    public Likes getLikes() {
        return likes;
    }

    /**
     *
     * @param likes
     */
    public void setLikes(Likes likes) {
        this.likes = likes;
    }

    /**
     *
     * @return
     */
    public Images getImages() {
        return images;
    }

    /**
     *
     * @param images
     */
    public void setImages(Images images) {
        this.images = images;
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

    /**
     *
     * @return
     */
    public ArrayList<Users_in_photo> getUsers_in_photo() {
        return users_in_photo;
    }

    /**
     *
     * @param users_in_photo
     */
    public void setUsers_in_photo(ArrayList<Users_in_photo> users_in_photo) {
        this.users_in_photo = users_in_photo;
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
    public String getFilter() {
        return filter;
    }

    /**
     *
     * @param filter
     */
    public void setFilter(String filter) {
        this.filter = filter;
    }

    /**
     *
     * @return
     */
    public Comments getComments() {
        return comments;
    }

    /**
     *
     * @param comments
     */
    public void setComments(Comments comments) {
        this.comments = comments;
    }

    /**
     *
     * @return
     */
    public String getAttribution() {
        return attribution;
    }

    /**
     *
     * @param attribution
     */
    public void setAttribution(String attribution) {
        this.attribution = attribution;
    }
    
  
}
