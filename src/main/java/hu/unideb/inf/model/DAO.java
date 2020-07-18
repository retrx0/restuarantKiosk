/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.unideb.inf.model;

import java.util.List;

/**
 *
 * @author ABDULRAHMAN ILLO
 */
public interface DAO {
    
    public List<SideMenuButton> listSideMenuButtons();
    public List<FoodButton> listFoodButtonsFor(String catName);
    public List<FoodButton> listFoodButtonsFor(SideMenuButton catName);
    public List<FoodMenuButton> listFoodMenuButtonFor(String catName);
    public List<FoodMenuButton> listFoodMenuButtonFor(SideMenuButton cat);
    
}
