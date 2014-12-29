/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Gerencia;

import Metodos.Meta;
import Metodos.Pagination;
import Data.Data;

/**
 *
 * @author labic
 */
public class TagsRecents {
    
  
  private Pagination pagination;
  private Meta meta;
  private Data data;

    /**
     *
     * @return
     */
    public Pagination getPagination() {
        return pagination;
    }

    /**
     *
     * @param pagination
     */
    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }

    /**
     *
     * @return
     */
    public Meta getMeta() {
        return meta;
    }

    /**
     *
     * @param meta
     */
    public void setMeta(Meta meta) {
        this.meta = meta;
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
     
}
