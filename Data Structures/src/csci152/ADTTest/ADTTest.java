/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ADTTest;

import java.util.Map;
import java.util.TreeMap;



/**
 *
 * @author User
 */
public class ADTTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)  {
       MapTest();
    }
    public static void MapTest(){
        Map<Integer, String> mp = new TreeMap();
        System.out.println("Contents: "+mp+" size:"+mp.size());
        mp.put(10,"Izat");
        mp.put(9,"Assan");
        mp.put(8,"Timur");
        mp.put(7,"Nurbek");
        mp.put(0,"Adil");
        System.out.println("Contents: "+mp+" size:"+mp.size());
        mp.remove(10);
        System.out.println("Contents: "+mp+" size:"+mp.size());
        mp.remove(4);
        System.out.println("Contents: "+mp+" size:"+mp.size());
        mp.put(7,"Aliya");
        mp.put(0,"Kamila");
        System.out.println("Contents: "+mp+" size:"+mp.size());
        mp.put(20,"Timur");
        mp.put(15,"Nurbek");
        mp.put(-5,"Adil");
        System.out.println("Contents: "+mp+" size:"+mp.size());
       
        System.out.println("Contents: "+mp+" size:"+mp.size());
        mp.clear();
        System.out.println("Contents: "+mp+" size:"+mp.size());
        mp.put(15,"Nurbek");
        mp.put(-5,"Adil");
        System.out.println("Contents: "+mp+" size:"+mp.size());
    }
   
}
