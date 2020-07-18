/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.unideb.inf.model;

import hu.unideb.inf.view.FXMLSceneController;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author ABDULRAHMAN ILLO
 */
public class JPA implements DAO{

    @Override
    public List<SideMenuButton> listSideMenuButtons() {
        List<SideMenuButton> l  = new ArrayList<>();
        try {
            File excelFile = new File(FXMLSceneController.class.getResource("/files/McDonalds.xlsx").toURI());
            FileInputStream fis = new FileInputStream(excelFile);
            Workbook mcdworkbook = new XSSFWorkbook(fis);

            for (int h = 0; h < mcdworkbook.getNumberOfSheets(); h++) {
                Sheet mcdsheet = mcdworkbook.getSheetAt(h);
                int rowCount = mcdsheet.getLastRowNum() - mcdsheet.getFirstRowNum();

                if (mcdsheet.getSheetName().equals("Categories")) {
                    for (int m = 0; m < rowCount + 1; m++) {
                        Row row = mcdsheet.getRow(m);
                            SideMenuButton s = new SideMenuButton(row.getCell(1).getStringCellValue(), row.getCell(0).getStringCellValue(), true);
                            l.add(s);
                    }
                }

                for (int i = 0; i < rowCount + 1; i++) {
                    Row row = mcdsheet.getRow(i);
                    for (int j = 0; j < row.getLastCellNum(); j++) {
                        System.out.print(row.getCell(j).getStringCellValue() + "|| ");
                    }
                    System.out.println();
                }
            }

        }catch (IOException | URISyntaxException ex) {
            Logger.getLogger(FXMLSceneController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return l;
    }

    @Override
    public List<FoodButton> listFoodButtonsFor(String catName) {
        List<FoodButton> l  = new ArrayList<>();
        
        try {
            File excelFile = new File(FXMLSceneController.class.getResource("/files/McDonalds.xlsx").toURI());
            FileInputStream fis = new FileInputStream(excelFile);
            Workbook mcdworkbook = new XSSFWorkbook(fis);

            for (int h = 0; h < mcdworkbook.getNumberOfSheets(); h++) {
                Sheet mcdsheet = mcdworkbook.getSheetAt(h);
                int rowCount = mcdsheet.getLastRowNum() - mcdsheet.getFirstRowNum();

                if (mcdsheet.getSheetName().equals(catName)) {
                    for (int m = 0; m < rowCount + 1; m++) {
                        Row row = mcdsheet.getRow(m);
                            FoodButton f= new FoodButton(catName,"https://"+row.getCell(1).getStringCellValue() , row.getCell(0).getStringCellValue(), (float) 10.00);
                            l.add(f);
                    }
                }
            }

        }catch (IOException | URISyntaxException ex) {
            Logger.getLogger(FXMLSceneController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return l;
    }

    @Override
    public List<FoodButton> listFoodButtonsFor(SideMenuButton cat) {
        List<FoodButton> l  = new ArrayList<>();
        
        try {
            File excelFile = new File(FXMLSceneController.class.getResource("/files/McDonalds.xlsx").toURI());
            FileInputStream fis = new FileInputStream(excelFile);
            Workbook mcdworkbook = new XSSFWorkbook(fis);

            for (int h = 0; h < mcdworkbook.getNumberOfSheets(); h++) {
                Sheet mcdsheet = mcdworkbook.getSheetAt(h);
                int rowCount = mcdsheet.getLastRowNum() - mcdsheet.getFirstRowNum();

                if (mcdsheet.getSheetName().equals(cat.getButtonName())) {
                    for (int m = 0; m < rowCount + 1; m++) {
                        Row row = mcdsheet.getRow(m);
                            FoodButton f= new FoodButton(cat.getButtonName(),"https://"+row.getCell(1).getStringCellValue().substring(0, row.getCell(1).getStringCellValue().indexOf("$")) , row.getCell(0).getStringCellValue(), (float) 10.00);
                            l.add(f);
                    }
                }
            }

        }catch (IOException | URISyntaxException ex) {
            Logger.getLogger(FXMLSceneController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return l;
    }

    @Override
    public List<FoodMenuButton> listFoodMenuButtonFor(String catName) {
        List<FoodMenuButton> l  = new ArrayList<>();
        
        for (int i = 0; i < 10; i++) {
            
        }
        
        return l;
    }

    @Override
    public List<FoodMenuButton> listFoodMenuButtonFor(SideMenuButton cat) {
        List<FoodMenuButton> l  = new ArrayList<>();
        
        for (int i = 0; i < 10; i++) {
            
        }
        
        return l;
    }
    
}
