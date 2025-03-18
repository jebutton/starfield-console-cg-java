/**
 * A class for handling reading the data of the Excel File.
 */
package com.jebutton.starfield.console.cg.datahandlers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import com.jebutton.starfield.console.cg.datatypes.*;

/**
 * Handles the reading of the data sheet with all of the information on it.
 */
public class DataFileReader {
    private String filePath;
    private Map<String, ItemType> resources;
    private Map<String, ItemType> spaceSuits;
    private Map<String, ItemType> helmets;
    private Map<String, ItemType> packs;
    private Map<String, SpaceSuitSet> spaceSuitSets;

    public DataFileReader() {
        this.filePath = "Starfield_Datatable.xls";
        this.initDataStructures();
        loadData();
    }

    public DataFileReader(String filePath) {
        this.filePath = filePath;
        this.initDataStructures();
        loadData();
    }

    /**
     * Initializes all of the data structures;
     */
    private void initDataStructures() {
        this.resources = new LinkedHashMap<String, ItemType>();
        this.spaceSuits = new LinkedHashMap<String, ItemType>();
        this.helmets = new LinkedHashMap<String, ItemType>();
        this.packs = new LinkedHashMap<String, ItemType>();
        this.spaceSuitSets = new LinkedHashMap<String, SpaceSuitSet>();
    }

    /**
     * Loads all of the data structures with their data
     * from the spreadsheets.
     */
    private void loadData() {
        this.loadResources();
        this.loadSpaceSuits();
        this.loadHelmets();
        this.loadPacks();
        this.loadSpaceSuitSets();
    }

    /**
     * Prints the cell contents. For Debugging.
     * @param tgtCell The cell you want printed.
     */
    private void printCell(Cell tgtCell) {
        switch (tgtCell.getCellType()) {
        case STRING:
            System.out.print(tgtCell.getStringCellValue());
            break;
        case BOOLEAN:
            System.out.print(tgtCell.getBooleanCellValue());
            break;
        case NUMERIC:
            System.out.print(tgtCell.getNumericCellValue());
            break;
        default:
            break;
        }
    }

    /**
     * Interprets the contents of the cells meant to be
     * boolean as if they were boolean and accounts for 
     * some things that aren't normalized in the data.
     * @param tgtCell a Cell to read.
     * @return A boolean result of what is in the cell.
     */
    private boolean handleSheetBooleans(Cell tgtCell) {
        boolean result = false;
        try {
            result = tgtCell.getBooleanCellValue();
        } catch (IllegalStateException e) {
        switch(tgtCell.getStringCellValue().toLowerCase()) {
            case "true":
                result = true;
            break;
                case "false":
                break;
            default:
                // If there is some other type of value, ignore it.
                break;
        }
    }
        return result;
    }

    /**
     * Stub to be called when a file is missing.
     * @param filePath a String of the file path.
     */
    private void handleMissingFiles(String filePath) {
        System.out.println("The File  at " + filePath + " is missing or otherwise unreadable.");
    }

    /**
     * Loads all of the resources.
     */
    private void loadResources() {
        try (
            InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(this.filePath);
            HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
        ) {
            HSSFSheet sheet = workbook.getSheet("Resources");
            
            Iterator<Row> iterator = sheet.iterator();
            
            // Skip First row
            iterator.next();
            // Iterate through all rows and add each resource to the Resources hashmap.
            while (iterator.hasNext()) {
            Row nextRow = iterator.next();
            String itemName = nextRow.getCell(0).getStringCellValue();
            String itemId = nextRow.getCell(1).getStringCellValue();
            
            ItemType newResource = new ItemType(itemId, itemName);
            this.resources.put(itemName.toLowerCase(), newResource);
        }
        } catch (FileNotFoundException e) {
            this.handleMissingFiles(this.filePath);
            e.printStackTrace();
        } catch (IOException e) {
            this.handleMissingFiles(this.filePath);
            e.printStackTrace();
        }
    }
    
    /**
     * Loads all of the Space Suits.
     */
    private void loadSpaceSuits(){
        try (
            InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(this.filePath);
            HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
        ) {
            
            HSSFSheet sheet = workbook.getSheet("Spacesuits");          
            Iterator<Row> iterator = sheet.iterator();
              
            // Skip First row
            iterator.next();
            
            // Iterate through all rows and add each resource to the Resources HashMap.
            while (iterator.hasNext()) {
                Row nextRow = iterator.next();
                String itemName = nextRow.getCell(0).getStringCellValue();
                        
                String itemId = nextRow.getCell(1).getStringCellValue();
                        
                ItemType newSpaceSuit = new ItemType(itemId, itemName);
                newSpaceSuit.setDLCFlag(this.handleSheetBooleans(nextRow.getCell(2)));
                        
                this.spaceSuits.put(itemName.toLowerCase(), newSpaceSuit);
            }
            
            } catch (FileNotFoundException e) {
                this.handleMissingFiles(this.filePath);
                e.printStackTrace();
            } catch (IOException e) {
                this.handleMissingFiles(this.filePath);
                e.printStackTrace();
            }
    }

    /**
     * Loads all of the Helmets. 
     */
    private void loadHelmets(){
        try (
            InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(this.filePath);
            HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
        ) {

            HSSFSheet sheet = workbook.getSheet("Helmets");
              
            Iterator<Row> iterator = sheet.iterator();
      
            // Skip First row
            iterator.next();
            
            // Iterate through all rows and add each resource to the Resources HashMap.
            while (iterator.hasNext()) {
                Row nextRow = iterator.next();
                String itemName = nextRow.getCell(0).getStringCellValue();
                
                String itemId = nextRow.getCell(1).getStringCellValue();
                
                ItemType newHelmet = new ItemType(itemId, itemName);
                newHelmet.setDLCFlag(this.handleSheetBooleans(nextRow.getCell(2)));
                
                this.helmets.put(itemName.toLowerCase(), newHelmet);
            }
      
        } catch (FileNotFoundException e) {
            this.handleMissingFiles(this.filePath);
            e.printStackTrace();
        } catch (IOException e) {
            this.handleMissingFiles(this.filePath);
            e.printStackTrace();
        }
    }
    
    /**
     * Loads all of the Packs. 
     */
    private void loadPacks(){
        try (
            InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(this.filePath);
            HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
        ) {

            HSSFSheet sheet = workbook.getSheet("Packs");
              
            Iterator<Row> iterator = sheet.iterator();
      
            // Skip First row
            iterator.next();
            
            // Iterate through all rows and add each resource to the Resources HashMap.
            while (iterator.hasNext()) {
                Row nextRow = iterator.next();
                String itemName = nextRow.getCell(0).getStringCellValue();
                
                String itemId = nextRow.getCell(1).getStringCellValue();
                
                ItemType newPack = new ItemType(itemId, itemName);
                newPack.setDLCFlag(this.handleSheetBooleans(nextRow.getCell(2)));
                
                this.packs.put(itemName.toLowerCase(), newPack);
            }
      
        } catch (FileNotFoundException e) {
            this.handleMissingFiles(this.filePath);
            e.printStackTrace();
        } catch (IOException e) {
            this.handleMissingFiles(this.filePath);
            e.printStackTrace();
        }
    }
    
    /**
     * Loads all of the Space Suit Sets.
     */
    private void loadSpaceSuitSets(){
        try (
            InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(this.filePath);
            HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
        ) {
            
            HSSFSheet sheet = workbook.getSheet("Spacesuit_Sets");
              
            Iterator<Row> iterator = sheet.iterator();
      
            // Skip First row
            iterator.next();
            
            // Iterate through all rows and add each resource to the Resources HashMap.
            while (iterator.hasNext()) {
                Row nextRow = iterator.next();
                
                String itemName = nextRow.getCell(0).getStringCellValue();
                String suitName = nextRow.getCell(1).getStringCellValue();
                String helmetName = nextRow.getCell(2).getStringCellValue();
                String packName = nextRow.getCell(3).getStringCellValue();
                
                SpaceSuitSet newSet = new SpaceSuitSet();
                newSet.setItemName(itemName);
                newSet.setDLCFlag(this.handleSheetBooleans(nextRow.getCell(5)));                
                newSet.setSpaceSuitItem(this.getASpaceSuit(suitName));
                newSet.setHelmetItem(this.getAHelmet(helmetName));
                newSet.setPackItem(this.getAPack(packName));

                this.spaceSuitSets.put(itemName.toLowerCase(), newSet);
            }
      
        } catch (FileNotFoundException e) {
            this.handleMissingFiles(this.filePath);
            e.printStackTrace();
        } catch (IOException e) {
            this.handleMissingFiles(this.filePath);
            e.printStackTrace();
        } catch (NullPointerException e) {
            System.out.println("One or more of the component data structures are null");
            System.out.println(e.getMessage());
           e.printStackTrace();
        }
    }
    
    /**
     * Gets the LinkedHashMap for the Helmets.
     * @return a LinkedHashMap<String, ItemType> of the Helmets.
     */
    public Map<String, ItemType> getHelmets(){
        return this.helmets;
    }
    
    /**
     * Gets a specific Helmet by name.
     * @return an ItemType of the Helmet.
     */
    public ItemType getAHelmet(String itemName) {
        return this.helmets.get(itemName);
    }
    
    /**
     * Gets the LinkedHashMap for the Packs.
     * @return a LinkedHashMap<String, ItemType> of the Packs.
     */
    public Map<String, ItemType> getPacks(){
        return this.packs;
    }
    
    /**
     * Gets a specific Pack by name.
     * @return an ItemType of the Pack.
     */
    public ItemType getAPack(String itemName) {
        return this.packs.get(itemName);
    }
    
    /**
     * Gets the LinkedHashMap for the resources.
     * @return a LinkedHashMap<String, ItemType> of the resources.
     */
    public Map<String, ItemType> getResources(){
        return this.resources;
    }
    
    /**
     * Gets a specific resource by name.
     * @return an ItemType of the resource.
     */
    public ItemType getAResource(String itemName) {
        return this.resources.get(itemName);
    }
    
    /**
     * Gets the LinkedHashMap for the Space Suits.
     * @return a LinkedHashMap<String, ItemType> of the Space Suits.
     */
    public Map<String, ItemType> getSpaceSuits(){
        return this.spaceSuits;
    }
    
    /**
     * Gets a specific Space Suit by name.
     * @return an ItemType of the Space Suit.
     */
    public ItemType getASpaceSuit(String itemName) {
        return this.spaceSuits.get(itemName);
    }
    
    /**
     * Gets the LinkedHashMap for the Space Suit Sets.
     * @return a LinkedHashMap<String, ItemType> of the Space Suit Sets.
     */
    public Map<String, SpaceSuitSet> getSpaceSuitSets(){
        return this.spaceSuitSets;
    }
    
    /**
     * Gets a specific Space Suit Set by name.
     * @return an SpaceSuitSet of the Space Suit Set.
     */
    public SpaceSuitSet getASpaceSuitSet(String itemName) {
        return this.spaceSuitSets.get(itemName);
    }
    
}
