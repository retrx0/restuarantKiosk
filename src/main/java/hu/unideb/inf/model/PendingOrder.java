/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.unideb.inf.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ABDULRAHMAN ILLO
 */
public class PendingOrder {
    
    public enum OrderType{
     EATIN,TAKEAWAY;   
    }
    
    List<Object> orderItems = new ArrayList<>();
    OrderType orderType;
    
}
