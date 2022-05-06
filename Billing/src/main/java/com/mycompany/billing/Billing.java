/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

// IMPORTING REQUIRED LIBRARIES
package com.mycompany.billing;
import java.util.*;
import java.io.*;
import com.mycompany.billing.*;

/**
 *
 * @author ishanmalkan
 */

// THE MAIN CLASS NAMED BILLING WITH THE ACTUAL FLOW OF THE PROGRAM
public class Billing 
{
    // AN ARRAYLIST TO STORE THE INVALID ORDERS (IF ANY) SO THOSE CAN BE WRITTEN IN THE OUTPUT FILE AS REQUIRED
    private static ArrayList<String> invalid_orders = new ArrayList<String>();
        
    // A FUNCTION TO CHECK IF THE ORDER IS VALID OR NOT
    private static boolean check_order_validity(GetItems items_obj, GetOrder order_obj)
    {
        boolean present_flag;
        boolean return_flag = true;
         
        // LOOPING THROUGH EACH ORDER IN THE ORDERS DICTIONARY (HASHMAP / OBJECT)
        // EACH ITEM MUST BE LESS THAN OR EQUAL TO THE AMOUNT PRESENT IN THE STORAGE
        // EACH ITEM ACCORDING TO ITS CATEGORY MUST NOT EXCEED A PARTICULAR AMOUNT
        // HERE THE INBUILT ITERATOR DESIGN PATTERN IS USED TO TRAVERSE ALONG THE KEYS OF THE HASHMAP
        for(String key: order_obj.order.keySet())
        {            
            present_flag = false;
            
            if(items_obj.items.keySet().contains(key))
            {  
                present_flag = true;
                    
                if (Integer.parseInt(items_obj.items.get(key).get("Quantity")) < Integer.parseInt(order_obj.order.get(key)))
                {
                    return_flag = false;
                    invalid_orders.add(key);
                }

                if(items_obj.items.get(key).get("Category").equals("Essentials") && Integer.parseInt(order_obj.order.get(key)) > 3)   
                { 
                    return_flag = false;
                    invalid_orders.add(key);                     
                }
                else if(items_obj.items.get(key).get("Category").equals("Luxury") && Integer.parseInt(order_obj.order.get(key)) > 4)   
                {
                    return_flag = false;
                    invalid_orders.add(key);
                }
                else if(items_obj.items.get(key).get("Category").equals("Misc") && Integer.parseInt(order_obj.order.get(key)) > 6)   
                {
                    return_flag = false;
                    invalid_orders.add(key);
                }  
            }
            else
            {
                present_flag = false;
            }
            
            if (present_flag == false)
            {
                return_flag = false;
                invalid_orders.add(key);           
            }
        }
        
        return return_flag;
    }
    
    // FUNCTION TO CALCULATE THE TOTAL AND PRINT IT IN A CSV OUTPUT FILE
    private static void calculate_total(GetItems items_obj, GetOrder order_obj) throws IOException
    {
        
        File output_file = new File("bill.csv");
        output_file.createNewFile();
        
        FileWriter bill_writer = new FileWriter("bill.csv");
        bill_writer.write("Item,Quantity,Price,TotalPrice");
        
        int summ = 0;
        int amount;
        
        for(String key: order_obj.order.keySet())
        {
            amount = Integer.parseInt(order_obj.order.get(key))*Integer.parseInt(items_obj.items.get(key).get("Price"));  
            bill_writer.write("\n" + key + "," + order_obj.order.get(key) + "," + Integer.toString(amount));
            summ += amount;
        }
        bill_writer.write("," + Integer.toString(summ));
        bill_writer.close();
        
    }
    
    // THE MAIN FUNCTION DEPICTING THE MAIN FLOW OF THE PROGRAM
    public static void main(String[] args) throws IOException
    {      
        // PRINTING THE DATA FROM CSV THAT HAS BEEN SAVED IN INTERNAL STATIC DATABASE
        GetItems items_obj = GetItems.get_instance();
        System.out.println(items_obj.items); 
        
        GetCards cards_obj = GetCards.get_instance();
        System.out.println(cards_obj.cards); 
        
        GetOrder order_obj = GetOrder.get_instance();
        System.out.println(order_obj.order); 
        System.out.println(order_obj.card); 
        
        // CALCULATING ORDER TOTAL AND ADDING CARD TO THE DATABASE IF NOT PRESENT AFTER CHECKING THE CART VALIDITY
        // ELSE WRITING THE ERROR FILE
        if(check_order_validity(items_obj, order_obj))
        {
            if(cards_obj.cards.contains(order_obj.card) )
            {
                calculate_total(items_obj, order_obj);
            }
            else
            {
                cards_obj.cards.add(order_obj.card);
                calculate_total(items_obj, order_obj);
            }
        }
        else
        {
            File error_file = new File("invalid_order.txt");
            error_file.createNewFile();
            FileWriter error_msg_writer = new FileWriter("invalid_order.txt");
            error_msg_writer.write("following invalid orders found: " + "\n");
            for(String item: invalid_orders)
            {
                error_msg_writer.write(item + "\n");
            }
            error_msg_writer.close();
        }
        
        
    }
}
