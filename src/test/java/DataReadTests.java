
import org.testng.annotations.Test;

import java.util.HashMap;

import org.testng.Assert;

import datahandlers.DataFileReader;
import datatypes.ItemType;

public class DataReadTests {
	@Test
  public void verifyResourcesLoad() {
		DataFileReader testReader = new DataFileReader();
		Assert.assertEquals(testReader.getAResource("Argon").getItemName(), "Argon");
  }
	@Test
	public void verifyResourcesCount() {
		DataFileReader testReader = new DataFileReader();
		HashMap<String, ItemType> testResources = testReader.getResources();
		
		Assert.assertEquals(testResources.size(), 107);
	}
}
