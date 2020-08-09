/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

/**
 *
 * @author karim
 */
abstract class Component {
    public final static int  NO_COMPONENT= 0;
    public final static int CAN_ATTACK = 1;
    public final static int HAS_HEALTH_BAR = 1<<1;
    public final static int HAS_POSITION = 1<<2;
    public final static int RENDERABLE = 1<<3;
    public final static int IS_AN_NPC = 1<<4;
    public final static int IS_PLAYER_CHARACTER = 1<<5;
    public final static int HAS_INVENTORY = 1<<6;
    public final static int HAS_NAME_OR_ID = 1<<7;
    public final static int CAN_MOVE = 1<<8;
    
    
     
     
    
    
}
