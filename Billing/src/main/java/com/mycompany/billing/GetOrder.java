/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

// IMPORTING REQUIRED LIBRARIES
package com.mycompany.billing;
import java.util.*;
import java.io.*;

/**
 *
 * @author ishanmalkan
 */

// SINGLETON CLASS TO CONVERT ORDER DATA FROM CSV TO HASHMAP (OBJECT / DICTIONARY)
public class GetOrder 
{    
    public HashMap<String, String> order = new HashMap<String, String>();
    public String card = new String();
    
    private String path = "/Users/ishanmalkan/Documents/SEM 2 Spring 2022/CMPE 202 Software Systems Engineering/Individual Project/Order.csv";

    private static GetOrder get_order;
    
    private GetOrder() throws FileNotFoundException
    {
        
        Scanner sc = new Scanner(new File(path));
        sc.useDelimiter(",");
        
        String title = sc.nextLine();
        
        String[] temp = sc.nextLine().split(",");
        order.put(temp[0].toLowerCase(), temp[1]);
        
        card = temp[2];
        
        String[] temp2 = new String[2];
          
        while (sc.hasNextLine())
        {
            temp2 = sc.nextLine().split(",");
            order.put(temp2[0].toLowerCase(), temp2[1]);
        }
        sc.close();
          
    }
    
    public static GetOrder get_instance() throws FileNotFoundException
    {
        if (get_order == null)
        {
            get_order = new GetOrder();
        }
        
        return get_order;
    }   
}
