/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author frascog
 */
public class DataSets {
    
    public static List<String> dataSets;

    public DataSets() {
        dataSets =new ArrayList<String>();
        dataSets.add("presidential2008");
    }
    
    public static void add(String name){
        dataSets.add(name);
    }
    
    public static Object[] values(){
        if(dataSets == null){
            new DataSets();
        }
        return dataSets.toArray();
    }
    
}
