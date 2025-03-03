
import org.testng.annotations.Test;

import java.util.Map;
import java.util.Set;

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
		Map<String, ItemType> testResources = testReader.getResources();
		
		Assert.assertEquals(testResources.size(), 107);
	}
	
	@Test
	public void verifyResourcesSort() {
		DataFileReader testReader = new DataFileReader();
		Map<String, ItemType> testResources = testReader.getResources();
		Set<String> resourceKeys = testResources.keySet();
		Assert.assertEquals(resourceKeys.toArray()[0], "Adaptive Frame");
		Assert.assertEquals(resourceKeys.toArray()[54], "Microsecond Regulator");
		Assert.assertEquals(resourceKeys.toArray()[106], "Zero-G Gimbal");
	}
	
	@Test
	  public void verifySpaceSuitsLoad() {
			DataFileReader testReader = new DataFileReader();
			Assert.assertEquals(testReader.getASpaceSuit("Bounty Hunter Spacesuit").getItemName(), "Bounty Hunter Spacesuit");
	}
	@Test
	public void verifySpaceSuitsCount() {
		DataFileReader testReader = new DataFileReader();
		Map<String, ItemType> testSpaceSuits = testReader.getSpaceSuits();
		Set<String> suitKeys = testSpaceSuits.keySet();
		for (String suitKey : suitKeys) {
			System.out.println(suitKey);
		}
		Assert.assertEquals(testSpaceSuits.size(), 65);
	}
	
	@Test
	public void verifySpaceSuitsSort() {
		DataFileReader testReader = new DataFileReader();
		Map<String, ItemType> testSpaceSuits = testReader.getSpaceSuits();
		Set<String> suitKeys = testSpaceSuits.keySet();
		Assert.assertEquals(suitKeys.toArray()[0], "Bounty Hunter Spacesuit");
		Assert.assertEquals(suitKeys.toArray()[35], "Starborn Spacesuit Solis");
		Assert.assertEquals(suitKeys.toArray()[64], "Zealot Spacesuit");
	}

	@Test
	  public void verifySpaceSuitsDLCFlagFalse() {
			DataFileReader testReader = new DataFileReader();
			Assert.assertEquals(testReader.getASpaceSuit("Bounty Hunter Spacesuit").getDLCFlag(), false);
	}
	
	@Test
	  public void verifySpaceSuitsDLCFlagTrue() {
			DataFileReader testReader = new DataFileReader();
			Assert.assertEquals(testReader.getASpaceSuit("Va'ruun Assault Spacesuit").getDLCFlag(), true);
	}
}
