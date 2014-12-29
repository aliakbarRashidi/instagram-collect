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
public class Images {
    
    private Thumbnail thumbnail = new Thumbnail();
    private Low_resolution low_resolution= new  Low_resolution();
    private Standard_resolution standard_resolution=new Standard_resolution();

    /**
     *
     */
    public Images() {
    }

    /**
     *
     * @param thumbnail
     * @param low_resolution
     * @param standard_resolution
     */
    public Images(Thumbnail thumbnail, Low_resolution low_resolution, Standard_resolution standard_resolution) {
        this.thumbnail = thumbnail;
        this.low_resolution = low_resolution;
        this.standard_resolution = standard_resolution;
    }

    /**
     *
     * @return
     */
    public Thumbnail getThumbnail() {
        return thumbnail;
    }

    /**
     *
     * @param thumbnail
     */
    public void setThumbnail(Thumbnail thumbnail) {
        this.thumbnail = thumbnail;
    }

    /**
     *
     * @return
     */
    public Low_resolution getLow_resolution() {
        return low_resolution;
    }

    /**
     *
     * @param low_resolution
     */
    public void setLow_resolution(Low_resolution low_resolution) {
        this.low_resolution = low_resolution;
    }

    /**
     *
     * @return
     */
    public Standard_resolution getStandard_resolution() {
        return standard_resolution;
    }

    /**
     *
     * @param standard_resolution
     */
    public void setStandard_resolution(Standard_resolution standard_resolution) {
        this.standard_resolution = standard_resolution;
    }
    
    
    
}
