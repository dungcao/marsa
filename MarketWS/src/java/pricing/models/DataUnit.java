/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pricing.models;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlElement;

/**
 *
 * @author ttu01
 */
public class DataUnit extends CostbaseModel{
    private ArrayList<Stream> streams;

    @XmlElement(required = true)
    public ArrayList<Stream> getStreams() {
        return streams;
    }

    public void setStreams(ArrayList<Stream> streams) {
        this.streams = streams;
    }
}
