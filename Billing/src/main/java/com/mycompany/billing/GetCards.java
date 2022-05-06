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

// SINGLETON CLASS TO CONVERT CARDS DATA FROM CSV TO HASHSET
public class GetCards 
{
    
    public HashSet<String> cards = new HashSet<String>();
    
    private String path = "/Users/ishanmalkan/Documents/SEM 2 Spring 2022/CMPE 202 Software Systems Engineering/Individual Project/Cards.csv";
    
    private static GetCards get_cards;
    
    private GetCards() throws FileNotFoundException
    {
        Scanner sc = new Scanner(new File(path));
        sc.useDelimiter(",");
        
        String title = sc.nextLine();
        
        while (sc.hasNextLine())
        {
            cards.add(sc.nextLine());
        }
        sc.close();              
    }
    
    public static GetCards get_instance() throws FileNotFoundException
    {
        if (get_cards == null)
        {
            get_cards = new GetCards();
        }
        
        return get_cards;
    }

    
}
