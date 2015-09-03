/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gerencia;

import Data.Caption;
import Data.Comments;
import Data.Comments_data;
import Data.Data;
import Data.From;
import Data.Images;
import Data.Likes;
import Data.Location;
import Data.Low_bandwidth;
import Data.Low_resolution;
import Data.Photo;
import Data.Position;
import Data.Standard_resolution;
import Data.Thumbnail;
import Data.User;
import Data.Users_in_photo;
import Data.Videos;
import Metodos.Meta;
import Metodos.Pagination;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Andrei
 */
public class GerenciaGetTag {

    private Data data = new Data();

    /**
     *
     */
    public GerenciaGetTag() {
    }

    /**
     *
     * @return
     */
    public Data getData() {
        return data;
    }

    /**
     *
     * @param data
     */
    public void setData(Data data) {
        this.data = data;
    }

    /**
     *
     * @return
     */
    public String getSeparator_tag() {
        return separator_tag;
    }

    /**
     *
     * @param separator_tag
     */
    public void setSeparator_tag(String separator_tag) {
        this.separator_tag = separator_tag;
    }

    /**
     *
     */
    public String separator_tag = " ";

    /**
     *
     * @param string_names
     * @param jsonObjeto
     * @return
     */
    public String[] getValues(String[] string_names, JSONObject jsonObjeto) {
        String string_values[] = new String[string_names.length];
        int i = 0;

        while (i < string_names.length) {

//            if (jsonObjeto.toString().contains("\"" + string_names[i] + "\"")) {
            if (jsonObjeto.has(string_names[i])) {
                string_values[i] = jsonObjeto.get(string_names[i]).toString();
            } else {
                string_values[i] = null;
            }
            /*         
             System.out.print(string_names[i]+": ");
             System.out.println(string_values[i]);
             */
            i++;
        }

        return string_values;
    }

    /**
     *
     * @param string_names
     * @param s_jsonObjeto
     * @return
     */
    public String[] getValues(String[] string_names, String s_jsonObjeto) {
        String string_values[] = new String[string_names.length];
        int i = 0;
        JSONObject jsonObjeto = new JSONObject(s_jsonObjeto);

        while (i < string_names.length) {

            if (jsonObjeto.has(string_names[i])) {
                string_values[i] = jsonObjeto.get(string_names[i]).toString();
            } else {
                string_values[i] = null;
            }
            /*         
             System.out.print(string_names[i]+": ");
             System.out.println(string_values[i]);
             */
            i++;
        }

        return string_values;
    }

    /**
     *
     * @param jsonPage
     *
     * @return
     */
    public Pagination getPagination(JSONObject jsonPagination) {
        Pagination pagination = new Pagination();

        String next_max_tag_id = "next_max_tag_id";
        String deprecation_warning = "deprecation_warning";
        String next_max_id = "next_max_id";
        String next_min_id = "next_min_id";
        String min_tag_id = "min_tag_id";
        String next_url = "next_url";
        
        if(jsonPagination.has(next_max_tag_id)){
       
        pagination.setNext_max_tag_id(jsonPagination.getString(next_max_tag_id));
        }
        if(jsonPagination.has(deprecation_warning)){
        pagination.setDeprecation_warning(jsonPagination.getString(deprecation_warning));
        }
        if(jsonPagination.has(next_max_id)){
        pagination.setNext_max_id(jsonPagination.getString(next_max_id));
        }
        if(jsonPagination.has(next_min_id)){
        pagination.setNext_min_id(jsonPagination.getString(next_min_id));
        }
        if(jsonPagination.has(min_tag_id)){
        pagination.setMin_tag_id(jsonPagination.getString(min_tag_id));
        }
        if(jsonPagination.has(next_url)){
            
        pagination.setNext_url(jsonPagination.getString(next_url));
        }
      
        
        return pagination;
    }

    private Meta getMeta(JSONObject jsonMeta) {
        Meta meta = new Meta();

        meta.setCode(jsonMeta.getInt("code"));
        return meta;
    }

    private Data getData(JSONArray jsonData) {

        ArrayList<Photo> listaPhotos = new ArrayList<>();
        String data_names[] = {"tags", "location", "link", "user_has_liked", "caption", "type", "id", "likes", "images", "created_time", "users_in_photo", "user", "filter", "comments", "attribution", "videos"};

        String data_values[][] = new String[jsonData.length()][];

        int i = 0;

        while (i < jsonData.length()) {

            data_values[i] = getValues(data_names, jsonData.get(i).toString());

            i++;

        }
        i = 0;

        while (i < jsonData.length()) {
            JSONObject tempJsonPhoto = jsonData.getJSONObject(i);

            Photo photo = new Photo();

            String tags = "";
            Location location = new Location();
            String link = tempJsonPhoto.get("link").toString();
            String user_has_liked = tempJsonPhoto.get("user_has_liked").toString();
            Caption caption = new Caption();
            From from = new From();
            String type = tempJsonPhoto.get("type").toString();

            String id = tempJsonPhoto.get("id").toString();

            Likes likes = new Likes();

            ArrayList<User> user_liked = new ArrayList<User>();

            Videos videos = new Videos();
            Low_bandwidth low_bandwidth = new Low_bandwidth();
            Low_resolution low_resolution_video = new Low_resolution();
            Standard_resolution standard_resolution_video = new Standard_resolution();

            Images images = new Images();
            Thumbnail thumbnail = new Thumbnail();
            Low_resolution low_resolution = new Low_resolution();
            Standard_resolution standard_resolution = new Standard_resolution();

            String created_time = tempJsonPhoto.get("created_time").toString();

            ArrayList<Users_in_photo> users_in_photos = new ArrayList<Users_in_photo>();

            User user = new User();

            String filter = tempJsonPhoto.get("filter").toString();
            ArrayList<Comments_data> comments_datas_array = new ArrayList<Comments_data>();

            Comments comments = new Comments();

            String attribution = tempJsonPhoto.get("attribution").toString();

            int arg_caption = -1;
            int arg_likes = -1;
            int arg_images = -1;
            int arg_videos = -1;
            int arg_users_in_photo = -1;
            int arg_user = -1;
            int arg_comments = -1;
            int arg_location = -1;

            for (int j = 0; j < data_names.length; j++) {
                if (data_names[j].equalsIgnoreCase("location")) {
                    arg_location = j;
                }

                if (data_names[j].equalsIgnoreCase("caption")) {
                    arg_caption = j;
                }
                if (data_names[j].equalsIgnoreCase("likes")) {
                    arg_likes = j;
                }
                if (data_names[j].equalsIgnoreCase("images")) {
                    arg_images = j;
                }
                if (data_names[j].equalsIgnoreCase("videos")) {
                    arg_videos = j;
                }
                if (data_names[j].equalsIgnoreCase("users_in_photo")) {
                    arg_users_in_photo = j;
                }
                if (data_names[j].equalsIgnoreCase("user")) {
                    arg_user = j;
                }
                if (data_names[j].equalsIgnoreCase("comments")) {
                    arg_comments = j;
                }

            }

            //verificar quando arg_ ser diferente de "-1" com if
            JSONObject data_caption;

            if (arg_caption != -1 && !data_values[i][arg_caption].equalsIgnoreCase("null")) {
                data_caption = new JSONObject(data_values[i][arg_caption]);
            } else {
                data_caption = new JSONObject("{}");

            }

            JSONObject data_location = new JSONObject("{}");

            if (arg_location != -1 && !data_values[i][arg_location].equalsIgnoreCase("null")) {
                data_location = new JSONObject(data_values[i][arg_location]);
            } else {
                data_caption = new JSONObject("{}");

            }

            JSONObject data_likes = new JSONObject(data_values[i][arg_likes]);
            JSONObject data_images = new JSONObject(data_values[i][arg_images]);

            JSONArray data_users_in_photo = new JSONArray(data_values[i][arg_users_in_photo]);
            JSONObject data_user = new JSONObject(data_values[i][arg_user]);
            JSONObject data_comments = new JSONObject(data_values[i][arg_comments]);

            if (data_location.length() > 0) {
                String data_location_names[] = {"id", "name", "longitude", "latitude"};
                String data_location_values[] = getValues(data_location_names, data_location);

                location.setLocation(data_location_names, data_location_values);

            }

            if (data_caption.length() > 0) {
                String data_caption_names[] = {"id", "text", "from", "created_time"};
                String data_caption_values[] = getValues(data_caption_names, data_caption);

                String data_caption_names_no_from[] = {"id", "text", "", "created_time"};

                String data_caption_values_no_from[] = getValues(data_caption_names_no_from, data_caption);

                caption.setCaption(data_caption_names_no_from, data_caption_values_no_from);

                int arg_caption_from = -1;

                for (int j = 0; j < data_caption_names.length; j++) {
                    if (data_caption_names[j].equalsIgnoreCase("from")) {
                        arg_caption_from = j;
                    }
                }

                if (arg_caption_from != -1) {
                    JSONObject caption_from = new JSONObject(data_caption_values[arg_caption_from]);
                    String caption_from_names[] = {"id", "username", "profile_picture"};
                    String caption_from_values[] = getValues(caption_from_names, caption_from);
                    from.setFrom(caption_from_names, caption_from_values);
                    caption.setFrom(from);

                } else {
                    //talvez tenha que criar as variaveis antes do ifs.. depois olhar!
                }

            }

            String data_likes_names[] = {"count", "data"};
            String data_likes_values[] = getValues(data_likes_names, data_likes);

            likes.setCount(Integer.parseInt(data_likes_values[0]));

            int arg_like_data = -1;

            for (int j = 0; j < data_likes_names.length; j++) {
                if (data_likes_names[j].equalsIgnoreCase("data")) {
                    arg_like_data = j;
                }
            }

            if (arg_like_data != -1) {
                JSONArray like_data = new JSONArray(data_likes_values[arg_like_data]);
                user_liked = new ArrayList<User>();
                String like_data_names[] = {"id", "username", "profile_picture", "full_name"};
                String like_data_values[][] = new String[like_data.length()][];

                int k = 0;

                while (k < like_data.length()) {

                    like_data_values[k] = getValues(like_data_names, like_data.get(k).toString());
                    User user_liker = new User();
                    user_liker.setUser(like_data_names, like_data_values[k]);

                    user_liked.add(user_liker);

                    k++;

                }

            }
            likes.setUser_liked(user_liked);
            if (type.equalsIgnoreCase("video")) {

                JSONObject data_videos = new JSONObject(data_values[i][arg_videos]);

                String data_videos_names[] = {"low_bandwidth", "low_resolution", "standard_resolution"};
                String data_videos_values[] = getValues(data_videos_names, data_videos);
                int arg_low_bandwidth = -1;
                int arg_low_resolution_video = -1;
                int arg_standard_resolution_video = -1;
                for (int j = 0; j < data_videos_names.length; j++) {
                    if (data_videos_names[j].equalsIgnoreCase("low_bandwidth")) {
                        arg_low_bandwidth = j;
                    }
                    if (data_videos_names[j].equalsIgnoreCase("low_resolution")) {

                        arg_low_resolution_video = j;
                    }
                    if (data_videos_names[j].equalsIgnoreCase("standard_resolution")) {
                        arg_standard_resolution_video = j;
                    }

                }
                if (arg_low_bandwidth != -1&&data_videos_values[arg_low_bandwidth] !=null) {

                    
                    JSONObject images_thumbnail = new JSONObject(data_videos_values[arg_low_bandwidth]);
                    
                    String images_thumbnail_names[] = {"height", "width", "url"};
                    String images_thumbnail_values[] = getValues(images_thumbnail_names, images_thumbnail);

                    low_bandwidth.setLow_bandwidth(images_thumbnail_names, images_thumbnail_values);
                }

                if (arg_low_resolution_video != -1) {
                    if (data_videos_values[arg_low_resolution_video] != null){
                     JSONObject images_low_resolution = new JSONObject(data_videos_values[arg_low_resolution_video]);

                    String images_low_resolution_names[] = {"height", "width", "url"};
                    String images_low_resolution_values[] = getValues(images_low_resolution_names, images_low_resolution);
                    low_resolution_video.setLow_resolution(images_low_resolution_names, images_low_resolution_values);
                    }
                }

                if (arg_standard_resolution_video != -1) {

                    JSONObject images_standard_resolution = new JSONObject(data_videos_values[arg_standard_resolution_video]);

                    String images_standard_resolution_names[] = {"height", "width", "url"};
                    String images_standard_resolution_values[] = getValues(images_standard_resolution_names, images_standard_resolution);
                    standard_resolution_video.setStandard_resolution(images_standard_resolution_names, images_standard_resolution_values);

                }
                videos.setLow_resolution(low_resolution_video);
                videos.setStandard_resolution(standard_resolution_video);
                videos.setLow_bandwidth(low_bandwidth);

            }
            String data_images_names[] = {"thumbnail", "low_resolution", "standard_resolution"};
            String data_images_values[] = getValues(data_images_names, data_images);

            int arg_images_thumbnail = -1;
            int arg_low_resolution = -1;
            int arg_standard_resolution = -1;

            for (int j = 0; j < data_images_names.length; j++) {
                if (data_images_names[j].equalsIgnoreCase("thumbnail")) {
                    arg_images_thumbnail = j;
                }
                if (data_images_names[j].equalsIgnoreCase("low_resolution")) {

                    arg_low_resolution = j;
                }
                if (data_images_names[j].equalsIgnoreCase("standard_resolution")) {
                    arg_standard_resolution = j;
                }
            }

            if (arg_images_thumbnail != -1) {
                JSONObject images_thumbnail = new JSONObject(data_images_values[arg_images_thumbnail]);

                String images_thumbnail_names[] = {"height", "width", "url"};
                String images_thumbnail_values[] = getValues(images_thumbnail_names, images_thumbnail);

                thumbnail.setThumbnail(images_thumbnail_names, images_thumbnail_values);
            }

            if (arg_low_resolution != -1) {
                JSONObject images_low_resolution = new JSONObject(data_images_values[arg_low_resolution]);

                String images_low_resolution_names[] = {"height", "width", "url"};
                String images_low_resolution_values[] = getValues(images_low_resolution_names, images_low_resolution);
                low_resolution.setLow_resolution(images_low_resolution_names, images_low_resolution_values);

            }

            if (arg_standard_resolution != -1) {

                JSONObject images_standard_resolution = new JSONObject(data_images_values[arg_standard_resolution]);

                String images_standard_resolution_names[] = {"height", "width", "url"};
                String images_standard_resolution_values[] = getValues(images_standard_resolution_names, images_standard_resolution);
                standard_resolution.setStandard_resolution(images_standard_resolution_names, images_standard_resolution_values);

            }
            images.setLow_resolution(low_resolution);
            images.setStandard_resolution(standard_resolution);
            images.setThumbnail(thumbnail);

            String data_users_in_photo_names[] = {"position", "user"};
            String data_users_in_photo_values[][] = new String[data_users_in_photo.length()][];

            int k = 0;

            while (k < data_users_in_photo.length()) {

                Position position = null;
                User user1 = null;
                data_users_in_photo_values[k] = getValues(data_users_in_photo_names, data_users_in_photo.get(k).toString());
                int arg_users_in_photo_user = -1;
                int arg_users_in_photo_position = -1;

                for (int j = 0; j < data_users_in_photo_names.length; j++) {
                    if (data_users_in_photo_names[j].equalsIgnoreCase("user")) {
                        arg_users_in_photo_user = j;
                    }
                    if (data_users_in_photo_names[j].equalsIgnoreCase("position")) {
                        arg_users_in_photo_position = j;
                    }
                }

                if (arg_users_in_photo_position != -1) {
                    JSONObject data_users_in_photo_position = new JSONObject(data_users_in_photo_values[k][arg_users_in_photo_position]);

                    String data_users_in_photo_position_names[] = {"x", "y"};
                    String data_users_in_photo_position_values[] = getValues(data_users_in_photo_position_names, data_users_in_photo_position);
                    position = new Position();
                    position.setPosition(data_users_in_photo_position_names, data_users_in_photo_position_values);
                }

                if (arg_users_in_photo_user != -1) {

                    JSONObject data_users_in_photo_user = new JSONObject(data_users_in_photo_values[k][arg_users_in_photo_user]);
                    String data_users_in_photo_user_names[] = {"id", "username", "profile_picture", "full_name"};
                    String data_users_in_photo_user_values[] = getValues(data_users_in_photo_user_names, data_users_in_photo_user);
                    user1 = new User();
                    user1.setUser(data_users_in_photo_user_names, data_users_in_photo_user_values);

                }

                users_in_photos.add(new Users_in_photo(user1, position));

                k++;

            }

            String data_user_names[] = {"id", "username", "profile_picture", "bio", "website", "full_name"};
            String data_user_values[] = getValues(data_user_names, data_user);

            user.setUser(data_user_names, data_user_values);

            String data_comments_names[] = {"count", "data"};
            String data_comments_values[] = getValues(data_comments_names, data_comments);

            comments.setCount(Integer.parseInt(data_comments_values[0]));

            int arg_comments_data = -1;

            for (int j = 0; j < data_comments_names.length; j++) {
                if (data_comments_names[j].equalsIgnoreCase("data")) {
                    arg_comments_data = j;
                }
            }

            if (arg_comments_data != -1) {
                JSONArray comments_data = new JSONArray(data_comments_values[arg_comments_data]);

                String comments_data_names[] = {"id", "text", "from", "created_time"};
                String comments_data_values[][] = new String[comments_data.length()][];

                String comments_data_names_no_from[] = {"id", "text", "", "created_time"};
                String comments_data_values_no_from[][] = new String[comments_data.length()][];

                k = 0;

                while (k < comments_data.length()) {
                    Comments_data Comments_datas = new Comments_data();
                    From from1 = new From();
                    comments_data_values[k] = getValues(comments_data_names, comments_data.get(k).toString());
                    comments_data_values_no_from[k] = getValues(comments_data_names_no_from, comments_data.get(k).toString());

                    Comments_datas.setComments_data(comments_data_names_no_from, comments_data_values_no_from[k]);
         //      k++;

                    //   }
                    int arg_comments_from = -1;

                    for (int j = 0; j < comments_data_names.length; j++) {
                        if (comments_data_names[j].equalsIgnoreCase("from")) {
                            arg_comments_from = j;

                        }
                    }

                    if (arg_comments_from != -1) {

                        JSONObject comments_from = new JSONObject(comments_data_values[k][arg_comments_from]);
                        String comments_from_names[] = {"id", "username", "profile_picture", "full_name"};
                        String comments_from_values[] = getValues(comments_from_names, comments_from);
                        from1.setFrom(comments_from_names, comments_from_values);

                    } else {
                        //talvez tenha que criar as variaveis antes do ifs.. depois olhar!
                    }

                    Comments_datas.setFrom(from1);
                    //     System.out.println(Comments_datas.getText());
                    comments_datas_array.add(Comments_datas);

                    k++;
                }

                comments.setData(comments_datas_array);

            }

            JSONArray data_values_tags = new JSONArray(data_values[i][0].toString());

            for (int a = 0; a < data_values_tags.length(); a++) {
                tags += data_values_tags.getString(a) + separator_tag;

            }

            listaPhotos.add(new Photo(tags, location, link, user_has_liked, caption, type, id, likes, images, created_time, users_in_photos, user, filter, comments, attribution, videos));
            i++;
        }

        data.setPhoto(listaPhotos);

        return data;
    }

    public Recents getTagsRecentsNEW(JSONObject jsonPage) {
        
      
        Recents tagsRecents = new Recents();
//        System.out.println(jsonPage.toString());
          if (jsonPage.length()!=0){
           
        
        Pagination pagination = new Pagination();
        Data data = new Data();
        Meta meta = new Meta();

        JSONObject jsonPagination = jsonPage.getJSONObject("pagination");
        pagination = getPagination(jsonPagination);

        JSONObject jsonMeta = jsonPage.getJSONObject("meta");
        meta = getMeta(jsonMeta);

        JSONArray jsonData = jsonPage.getJSONArray("data");
        data = getData(jsonData);

        tagsRecents.setPagination(pagination);
        tagsRecents.setMeta(meta);
        tagsRecents.setData(data);
        }else{
              System.err.println("erro, json vazio");
          }
        return tagsRecents;
    }

    /**
     *
     * @param page
     * @return
     */
    public Recents separarObjetosJson(String page) {
        Recents returnSeparator = new Recents();
        JSONObject page_all = new JSONObject(page);
        Pagination pagination = new Pagination();
        String pagination_names[] = null;
        String pagination_values[] = null;

        JSONObject page_pagination = new JSONObject(page_all.get("pagination"));
        JSONObject page_meta = new JSONObject(page_all.get("meta"));
        pagination_names = new String[page_pagination.length()];
        pagination_values = new String[page_pagination.length()];
        //   JSONArray page_data = new JSONArray(page_all.get("data").toString()); 

        for (int i = 0; i < page_pagination.length(); i++) {
            pagination_names[i] = page_pagination.names().getString(i);
            pagination_values[i] = page_pagination.getString(page_pagination.names().getString(i));

        }

        pagination.setPagination(pagination_names, pagination_values);
        returnSeparator.setPagination(pagination);

        System.out.println(pagination_values[2]);

        System.out.println(pagination.getNext_url());

        return returnSeparator;
    }

}
