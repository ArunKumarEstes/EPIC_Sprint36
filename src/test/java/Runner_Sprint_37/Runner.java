package Runner_Sprint_37;

import org.testng.annotations.Test;

import Sprint_37.EPIC_1737;
import Sprint_37.UtilClass;

public class Runner extends UtilClass {
	@Test
	public void EPICPLTFRM_1737() throws Exception {

		EPIC_1737 login = new EPIC_1737(driver);
		login.api();
		Await();
		login.PEGALogin();
		login.LaunchWarehousePortal();
		login.OrderPAR_POD_Review();
		Await();
		EPIC_1737.driver.switchTo().defaultContent();
		Thread.sleep(9000);
		EPIC_1737.frameSwitch();
		Await();
		login.OrderSearchandFilter();
		System.out.println("Successfully filtered the Order ID field");
		Await();
		EPIC_1737.POD_Review_refresh.click();
		Await();
		EPIC_1737.driver.switchTo().defaultContent();
		EPIC_1737.frameSwitch();
		Await();
		login.BOL_SearchandFilter();
		System.out.println("Successfully filtered the BOL field");
		EPIC_1737.POD_Review_refresh.click();
		Await();
		EPIC_1737.driver.switchTo().defaultContent();
		EPIC_1737.frameSwitch();
		Await();
		login.IB_Load_ID_SearchandFilter();
		System.out.println("Successfully filtered the IB Load ID field");
		EPIC_1737.POD_Review_refresh.click();
		Await();
		EPIC_1737.driver.switchTo().defaultContent();
		EPIC_1737.frameSwitch();
		Await();
		login.Status_SearchandFilter();
		System.out.println("Successfully filtered the Status field");
		EPIC_1737.POD_Review_refresh.click();
		Await();
		EPIC_1737.driver.switchTo().defaultContent();
		EPIC_1737.frameSwitch();
		Await();
		login.Received_SearchandFilter();
		System.out.println("Successfully filtered the Received field");
		EPIC_1737.POD_Review_refresh.click();
		Await();
		EPIC_1737.driver.switchTo().defaultContent();
		EPIC_1737.frameSwitch();
		Await();
		login.Released_SearchandFilter();
		System.out.println("Successfully filtered the Released field");
		EPIC_1737.POD_Review_refresh.click();
		Await();
		EPIC_1737.driver.switchTo().defaultContent();
		EPIC_1737.frameSwitch();
		Await();
		login.Attached_SearchandFilter();
		System.out.println("Successfully filtered the Attached? field");
		EPIC_1737.POD_Review_refresh.click();
		Await();
		EPIC_1737.driver.switchTo().defaultContent();
		EPIC_1737.frameSwitch();
		Await();
		login.Req_Status_SearchandFilter();
		System.out.println("Successfully filtered the Req Status field");
	}
	
	

}
