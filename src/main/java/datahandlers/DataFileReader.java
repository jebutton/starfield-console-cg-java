/**
 * A class for handling reading the data of the Excel File.
 */
package datahandlers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import datatypes.ItemType;

/**
 * Handles the reading of the data sheet with all of the information on it.
 */
public class DataFileReader {
	private String filePath;
	private HashMap<String, ItemType> Resources;
	
	public DataFileReader() {
		this.filePath = ".\\data\\Starfield_Datatable.xls";
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
		this.Resources = new HashMap<String, ItemType>();
	}
	
	/**
	 * Loads all of the data structures with their data
	 * from the spreadsheets.
	 */
	private void loadData() {
		this.loadResources();
		
    }
	
	/**
	 * Prints the cell contents.
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
	 * Loads all of the resources.
	 */
	private void loadResources() {
		File dataFile = new File(this.filePath);
		try {
			FileInputStream inputStream = new FileInputStream(dataFile);
			HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
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
	            this.Resources.put(itemName, newResource);
	        }
	  
	        // Close workbook and streams.
			workbook.close();
			inputStream.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public HashMap<String, ItemType> getResources(){
		return this.Resources;
	}
	
	public ItemType getAResource(String itemName) {
		return this.Resources.get(itemName);
	}
}
