/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gerencia;

import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author labic
 */
public class Instagram {

    String CLIENT_ID;
    String CLIENT_SECRET;
    String access_token;

    /**
     *
     * @param CLIENT_ID
     * @param CLIENT_SECRET
     * @param access_token
     */
    public Instagram(String CLIENT_ID, String CLIENT_SECRET, String access_token) {
        this.CLIENT_ID = CLIENT_ID;
        this.CLIENT_SECRET = CLIENT_SECRET;
        this.access_token = access_token;
    }

    /**
     *
     * @param search
     * @return
     */
    public JSONObject getMedia_Count(String search, String service) {
        Media_count media_count = new Media_count();

        String urlget = "https://api.instagram.com/v1/" + service + "/" + search + "?access_token=" + this.access_token;

        String receive = "";

        try {
            receive = new MetodosAdicionais().getPage(urlget);

        } catch (IOException ex) {
            Logger.getLogger(Instagram.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("NÃO CARREGOU A PAGINA. VERIFIQUE A CONEXÃO COM A INTERNET");
        }

        JSONObject urlgetjson = new JSONObject(receive);

        return urlgetjson;
    }

    /**
     *
     * @param search
     * @param min_id
     * @param max_id
     * @param service
     * @return
     */
    public String getTagsTagNameMediaRecent(String search, String min_id, String max_id, String service) {

        String url = "https://api.instagram.com/v1/" + service + "/" + search + "/media/recent?access_token=" + this.access_token + "&max_tag_id=" + max_id + "&min_tag_id=" + min_id;

        String receive = "";

        try {
            receive = new MetodosAdicionais().getPage(url);

        } catch (IOException ex) {
            Logger.getLogger(Instagram.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("NÃO CARREGOU A PAGINA. VERIFIQUE A CONEXÃO COM A INTERNET");
        }

        return receive;

    }

    public JSONObject getRecentTag(String search, String min_id, String max_id, String service) {

        String url = "https://api.instagram.com/v1/" + service + "/" + search + "/media/recent?access_token=" + this.access_token + "&max_tag_id=" + max_id + "&min_tag_id=" + min_id;

        String receive = "";

        try {
            receive = new MetodosAdicionais().getPage(url);

        } catch (IOException ex) {
            Logger.getLogger(Instagram.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("NÃO CARREGOU A PAGINA. VERIFIQUE A CONEXÃO COM A INTERNET");
            System.exit(0);
        }

        JSONObject jsonRecentTag = new JSONObject(receive);

        return jsonRecentTag;

    }

    /**
     *
     * @param client_id
     * @param client_secret
     * @return
     */
    public boolean autenticar(String client_id, String client_secret) {
        try {
            this.CLIENT_ID = client_id;
            this.CLIENT_SECRET = client_secret;

            return true;

        } catch (Exception e) {

            return false;
        }

    }

}
