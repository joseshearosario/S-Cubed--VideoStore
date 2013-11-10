/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package video.store;

import java.util.LinkedList;

/**
 * A class used to hold two linked list. One is for the movie inventory and the 
 * other is for the membership list. The membership list uses the LinkedList 
 * package in Java, while the movie inventory used my implementation of a 
 * linked list.
 * 
 * @author Jose Andres Rosario
 */
public class Global {
    public static videoList inventory = new videoList();
    public static LinkedList members = new LinkedList();
}
