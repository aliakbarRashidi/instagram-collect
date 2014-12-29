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
public class Videos {
    
    private Low_bandwidth low_bandwidth = new Low_bandwidth();
    private Low_resolution low_resolution = new Low_resolution();
    private Standard_resolution standard_resolution = new Standard_resolution();

    /**
     *
     */
    public Videos() {
    }

    public Low_bandwidth getLow_bandwidth() {
        return low_bandwidth;
    }

    public Videos(Low_bandwidth low_bandwidth, Low_resolution low_resolution, Standard_resolution standard_resolution) {
        this.low_bandwidth = low_bandwidth;
        this.low_resolution = low_resolution;
        this.standard_resolution = standard_resolution;
    }

    public void setLow_bandwidth(Low_bandwidth low_bandwidth) {
        this.low_bandwidth = low_bandwidth;
    }

    public Low_resolution getLow_resolution() {
        return low_resolution;
    }

    public void setLow_resolution(Low_resolution low_resolution) {
        this.low_resolution = low_resolution;
    }

    public Standard_resolution getStandard_resolution() {
        return standard_resolution;
    }

    public void setStandard_resolution(Standard_resolution standard_resolution) {
        this.standard_resolution = standard_resolution;
    }

    
    
}
