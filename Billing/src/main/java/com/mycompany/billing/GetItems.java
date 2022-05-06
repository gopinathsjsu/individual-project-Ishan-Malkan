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

// SINGLETON CLASS TO CONVERT ITEMS DATA FROM CSV TO HASHMAP (OBJECT / DICTIONARY)
public class GetItems 
{
    
    public HashMap<String, HashMap<String, String>> items = new HashMap<String, HashMap<String, String>>();
    
    private String path = "/Users/ishanmalkan/Documents/SEM 2 Spring 2022/CMPE 202 Software Systems Engineering/Individual Project/Items.csv";
    
    private static GetItems get_items; 
    
    private GetItems() throws FileNotFoundException
    {
       
        Scanner sc = new Scanner(new File(path));
        sc.useDelimiter(",");
                
        String[] columns = sc.nextLine().split(",");
        String[] values = new String[columns.length];
        
        while(sc.hasNextLine())
        {
            values = sc.nextLine().split(",");
            items.put(values[0].toLowerCase(), new HashMap<String, String>());
            for(int i = 1; i < columns.length; i++)
            {
                items.get(values[0].toLowerCase()).put(columns[i],values[i]);
            }     
        }       
        sc.close();  
    } 
    
    public static GetItems get_instance() throws FileNotFoundException
    {
        if (get_items == null)
        {
            get_items = new GetItems();
        }
        
        return get_items;
    }
    
    
}

   
