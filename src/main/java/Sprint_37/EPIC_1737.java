package Sprint_37;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Listeners;

import com.aventstack.extentreports.Status;
//import com.aventstack.extentreports.Status;
//import com.aventstack.extentreports.Status;
//import com.aventstack.extentreports.Status;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import io.restassured.RestAssured;
import io.restassured.response.Response;
@Listeners(TestListener.class)
public class EPIC_1737 extends UtilClass {

	public static String sendkeys = "";

	public EPIC_1737(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//iframe[@name='PegaGadget0Ifr']")
	public static WebElement frameName;

	@FindBy(id = "loginText2")
	public static WebElement ssoLogin;

	@FindBy(className = "table-row")
	public static WebElement code;

	@FindBy(id = "idTxtBx_SAOTCC_OTC")
	public static WebElement send;

	@FindBy(id = "idSubmit_SAOTCC_Continue")
	public static WebElement click;

	@FindBy(xpath = "//div[contains(@class,'launch-portals')]/descendant::a")
	public static WebElement LaunchPortal;

	@FindBy(xpath = "//span[contains(text(),'WareHouse UserPortal')]")
	public static WebElement warehouse;

	@FindBy(xpath = "//li[@title='Orders PAR']")
	public static WebElement OrdersPAR;

	@FindBy(xpath = "//h3[contains(text(),'POD Review')]")
	public static WebElement POD_Review;

	@FindBy(xpath = "//th[@aria-label='Order ID']//a[@class='filter highlight-ele']")
	public static WebElement OrderFilter;

	@FindBy(xpath = "//th[@aria-label='BOL']//a[@class='filter highlight-ele']")
	public static WebElement BOL_Filter_POD_Review;

	@FindBy(xpath = "//th[@aria-label='IB Load ID']//a[@class='filter highlight-ele']")
	public static WebElement IB_Load_ID_Filter_POD_Review;

	@FindBy(xpath = "//th[@aria-label='OB Load ID']//a[@class='filter highlight-ele']")
	public static WebElement OB_Load_ID_Filter_POD_Review;

	@FindBy(xpath = "//th[@aria-label='Status']//a[@class='filter highlight-ele']")
	public static WebElement Status_Filter_POD_Review;

	@FindBy(xpath = "//table[contains(@grid_ref_page, 'pyColumnFilterCriteria')]//tbody//tr[2]//td//div//input[2]")
	public static WebElement POD_Received_Status_Filter_POD_Review;

	@FindBy(xpath = "//table[contains(@grid_ref_page, 'pyColumnFilterCriteria')]//tbody//tr[3]//td//div//input[2]")
	public static WebElement Released_Status_Filter_POD_Review;

	@FindBy(xpath = "//th[@aria-label='Received']//a[@class='filter highlight-ele']")
	public static WebElement Received_Filter_POD_Review;

	@FindBy(xpath = "(//img[@class='inactvIcon'])[1]")
	public static WebElement From_Date_Received_Filter_POD_Review;

	@FindBy(xpath = "(//img[@class='inactvIcon'])[2]")
	public static WebElement To_Date_Received_Filter_POD_Review;

	@FindBy(xpath = "//th[@aria-label='Released']//a[@class='filter highlight-ele']")
	public static WebElement Released_Filter_POD_Review;

	@FindBy(xpath = "//th[@aria-label='Attached?']//a[@class='filter highlight-ele']")
	public static WebElement Attached_Filter_POD_Review;

	@FindBy(xpath = "//th[@aria-label='Req Status']//a[@class='filter highlight-ele']")
	public static WebElement Req_Status_Filter_POD_Review;

	// ****Order Search webElements****

//	@FindBy(xpath = "//input[@CLASS='leftJustifyStyle']")
//	public static WebElement OrderSearch_Filer;

	@FindBy(xpath = "//div[@class='content-inner ']/div/span/input[@type='text']")
	public static WebElement OrderSearch_Filter;

	@FindBy(xpath = "(//input[@type='checkbox' and contains(@name,'Inbound')])[21]")
	public static WebElement FilterCheckBox;

	@FindBy(xpath = "(//button[@class='pzhc pzbutton'])[1]")
	public static WebElement OrderFilterApply;

	@FindBy(xpath = "//a[@name='PODRequiredPARGrid_pyDisplayHarness_99']")
	public static WebElement POD_Review_refresh;

	public static void frameSwitch() {
		driver.switchTo().frame(frameName);

	}

	public void api() throws Exception {

		String prettyString = null;
		File file = new File(System.getProperty("user.dir") + "\\PAR.json");
		ObjectMapper objectMapper = new ObjectMapper();
		// Read JSON from file into a JsonNode
		JsonNode rootNode = objectMapper.readTree(file);
		// Ensure that the root node is an ObjectNode
		if (rootNode instanceof ObjectNode) {
			ObjectNode objectNode = (ObjectNode) rootNode;
			// Retrieve the "OrderRefs" object and add "InvoiceNumber" with a random value
			ObjectNode orderRefs = (ObjectNode) objectNode.get("OrderRefs");
			orderRefs.put("BOL", Math.ceil(Math.random() * 100000000));
			orderRefs.put("TrackingNumber", Math.ceil(Math.random() * 100000000));
			orderRefs.put("InvoiceNumber", Math.ceil(Math.random() * 100000000));
			// Convert the modified JsonNode back to a pretty-printed string
			prettyString = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(objectNode);

			RestAssured.baseURI = "https://epicuatlb.estes-express.com";
			Response response = RestAssured.given().auth().basic("EpicSevicesTest1", "Rules@1234")
					.contentType("application/json").body(prettyString)
					.post("/prweb/api/OrderServicePackage/V1/CreateOrUpdateOrder");
			String responseBody = response.getBody().asString();
			String[] split = responseBody.split("Reference is ");

			sendkeys = split[1];
			System.out.println(sendkeys);
			System.out.println("<------Result of PAR Json------>");
			System.out.println("Response Body: " + responseBody);
			int statusCode = response.getStatusCode();
			System.out.println("Status Code: " + statusCode);
//			extentTest.log(Status.PASS, "Order Created");
		}

	}

	public void PEGALogin() throws InterruptedException {
		ssoLogin.click();
		waits(code);
		code.click();

		// ****Scanner class to handle OTP****
		String scanner = scanner();
		send.sendKeys(scanner);
		waits(click);
		click.click();
//		extentTest.log(Status.PASS, "Logged into PEGA Succesfully");
	}

	public void LaunchWarehousePortal() throws InterruptedException {
		Await();
		waits(LaunchPortal);
		LaunchPortal.click();
		Await();
		waits(warehouse);
		warehouse.click();
		Await();
		Windows();
//		extentTest.log(Status.PASS, "Enetered into Warehouse Portal");
	}

	public void OrderPAR_POD_Review() throws InterruptedException {
		Await();
		OrdersPAR.click();
		Await();
		frameSwitch();
		waits(POD_Review);
		POD_Review.click();
//		extentTest.log(Status.PASS, "Enetering into POD Review");

	}

	public void OrderSearchandFilter() throws Exception {
		Await();
		OrderFilter.click();
		Await();
		// waits(OrderSearch_Filter);
		Await();
		OrderSearch_Filter.sendKeys(sendkeys);
		waits(OrderFilterApply);
		OrderFilterApply.click();
		Await();
//		extentTest.log(Status.PASS, "Filter the Order");

	}

	public void BOL_SearchandFilter() throws Exception {
		Await();
		BOL_Filter_POD_Review.click();
		Await();
		// waits(OrderSearch_Filter);
		Await();
		OrderSearch_Filter.sendKeys("1234");
		waits(OrderFilterApply);
		OrderFilterApply.click();
		Await();
//		extentTest.log(Status.PASS, "BOL Filtered");

	}

	public void IB_Load_ID_SearchandFilter() throws Exception {
		Await();
		IB_Load_ID_Filter_POD_Review.click();
		Await();
		// waits(OrderSearch_Filter);
		Await();
		OrderSearch_Filter.sendKeys("1234");
		waits(OrderFilterApply);
		OrderFilterApply.click();
		Await();

	}

	public void OB_Load_ID_SearchandFilter() throws Exception {
		Await();
		OB_Load_ID_Filter_POD_Review.click();
		Await();
		// waits(OrderSearch_Filter);
		Await();
		OrderSearch_Filter.sendKeys("1234");
		waits(OrderFilterApply);
		OrderFilterApply.click();
		Await();

	}

	public void Status_SearchandFilter() throws Exception {
		Await();
		Status_Filter_POD_Review.click();
		Await();
		// waits(OrderSearch_Filter);
		Await();
		OrderSearch_Filter.sendKeys("POD Received");

		// *******By clicking on POC Received status in Filter******

//		OrderSearch_Filter.click();
//		POD_Received_Status_Filter_POD_Review.click();

		// *******By clicking on Released status in Filter********

//		OrderSearch_Filter.click();
//		Released_Status_Filter_POD_Review.click();

		waits(OrderFilterApply);
		OrderFilterApply.click();
		Await();

	}

	public void Received_SearchandFilter() throws Exception {
		Await();
		Received_Filter_POD_Review.click();
		Await();
		// waits(OrderSearch_Filter);
		Await();
		// OrderSearch_Filter.sendKeys("5/14/24");

		From_Date_Received_Filter_POD_Review.click();
		Calendarss();

		To_Date_Received_Filter_POD_Review.click();
		Calendarss();

		waits(OrderFilterApply);
		OrderFilterApply.click();
		Await();

	}

	public void Released_SearchandFilter() throws Exception {
		Await();
		Released_Filter_POD_Review.click();
		Await();
		// waits(OrderSearch_Filter);
		Await();
		// OrderSearch_Filter.sendKeys("5/14/24");

		From_Date_Received_Filter_POD_Review.click();
		Calendarss();

		To_Date_Received_Filter_POD_Review.click();
		Calendarss();

		waits(OrderFilterApply);
		OrderFilterApply.click();
		Await();

	}

	public void Attached_SearchandFilter() throws Exception {
		Await();
		Attached_Filter_POD_Review.click();
		Await();
		// waits(OrderSearch_Filter);
		Await();
		OrderSearch_Filter.sendKeys("Y");
		waits(OrderFilterApply);
		OrderFilterApply.click();
		Await();

	}

	public void Req_Status_SearchandFilter() throws Exception {
		Await();
		Req_Status_Filter_POD_Review.click();
		Await();
		// waits(OrderSearch_Filter);
		Await();
		OrderSearch_Filter.sendKeys("N");
		waits(OrderFilterApply);
		OrderFilterApply.click();
		Await();

	}

}
