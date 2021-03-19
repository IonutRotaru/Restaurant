package Tema4.T4;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import GUI.MainGUI;
import businessLayer.BaseProduct;
import businessLayer.CompositeProduct;
import businessLayer.MenuItem;
import businessLayer.Order;
import businessLayer.Restaurant;
import data_layer.RestaurantSerializator;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws ClassNotFoundException
    {	
    	Restaurant r = new Restaurant();
    	r= RestaurantSerializator.load(args[0]);
    	System.out.println("Start");
        MainGUI mainGUI = new MainGUI(r);
    }
}
