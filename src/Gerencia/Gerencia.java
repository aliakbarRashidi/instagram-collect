/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gerencia;

import Metodos.Meta;
import Metodos.Pagination;
import java.awt.List;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Andrei
 */
public class Gerencia {

    private final JSONObject jSONtagRecent;
    private final JSONObject jSONMedia_count;

    Media_count media_count = new Media_count();
    
    Pagination pagination = new Pagination();

    public Gerencia(JSONObject jSONtagRecent, JSONObject jSONMedia_count) {
        this.jSONtagRecent = jSONtagRecent;
        this.jSONMedia_count = jSONMedia_count;
    }

    public Media_count getMedia_count() {
        return media_count;
    }

    public void setMedia_count(Media_count media_count) {
        this.media_count = media_count;
    }

    public Pagination getPagination() {
        return pagination;
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }

    
    
    
    
    
    public void media_Count() {

        JSONObject jsonData = jSONMedia_count.getJSONObject("data");
        JSONObject jsonMeta = jSONMedia_count.getJSONObject("meta");

        
       
        media_count.setName(jsonData.get("name").toString());
        media_count.setMedia_count(Integer.parseInt(jsonData.get("media_count").toString()));

        Meta meta = new Meta();

        meta.setCode(jsonMeta.getInt("code"));
        
        media_count.setMeta(meta);
        
        System.out.println(media_count.getName());
        System.out.println(media_count.getMedia_count());
        
        System.out.println(meta.getCode());

    }
    
     public void pagination() {

        JSONArray jsonData = jSONtagRecent.getJSONArray("data");
        JSONObject jsonMeta = jSONtagRecent.getJSONObject("meta");
        JSONObject jsonPagination = jSONtagRecent.getJSONObject("pagination");

       
        pagination.setNext_max_tag_id(jsonPagination.getString("next_max_tag_id"));
        pagination.setDeprecation_warning(jsonPagination.getString("deprecation_warning"));
        pagination.setNext_max_id(jsonPagination.getString("next_max_id"));
        pagination.setNext_min_id(jsonPagination.getString("next_min_id"));
        pagination.setMin_tag_id(jsonPagination.getString("min_tag_id"));
        pagination.setNext_url(jsonPagination.getString("next_url"));
       
        
                
       
        
        

    }
     
     
    
     
     
}
