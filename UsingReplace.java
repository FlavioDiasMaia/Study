/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication3;

import java.io.Console;
import java.util.Arrays;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 *
 * @author Flávio
 */
public class JavaApplication3 {

    /**
     * @param args the command line arguments
     */
    
    
    public static void main(String[] args) {
        // TODO code application logic here
       Scanner read = new Scanner (System.in); 
       System.out.print(" Number for replace : \n");
       String k = read.nextLine();
       k = k.replace(",",".");
       System.out.print(k);
                
               
}
                
      
    }   
    
}
