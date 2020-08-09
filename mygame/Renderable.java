/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

import java.util.ArrayList;

/**
 *
 * @author karim
 */
public class Renderable {
    String direction = "right";
    ArrayList<String> walkRight = new ArrayList<>();
    ArrayList<String>walkLeft = new ArrayList<>();
    ArrayList<String>attack = new ArrayList<>();
    ArrayList<String>otherSprites = new ArrayList<>();
    ArrayList<String>equipSprites = new ArrayList<>();
    int nextFrame  = 0;
    String state = "stationary";
    
    
    
    
    
}
