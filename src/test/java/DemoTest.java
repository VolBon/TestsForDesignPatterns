import org.bon.pageComponents.FooterBar;
import org.bon.pageObjects.HomePage;
import org.bon.pageObjects.TripType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class DemoTest extends BaseTest {
    WebDriver driver;
    HomePage home;

    @Test (dataProvider = "getData")
    public void checkAvailabilityTest(HashMap<String, String> reservationDetails) {
        home.goToPage();
        //Strategy Design Pattern, don;t add if statements to HomePage, it will break SRP design
        TripType typeOfTrip = TripType.MULTI;
        home.setBookingStrategy(typeOfTrip); //new OneWayTrip(driver, section)
        home.checkAvailability(reservationDetails);
    }
    @Test
    public void flightFooterTest() {
        home.goToPage();
        FooterBar footer = home.getFooter();
        footer.getLinksCount();
        footer.selectFlight();
    }

    @Test
    public void flightNavigationTest() {
        home.goToPage();
        home.getNavigationBar().selectFlight();
        home.getNavigationBar().getLinksCount();
    }

    @Test
    public void hamburgerTest() {
        home.goToPage();
        //home.getNavigationBar().clickHamburger();
    }

    @BeforeTest
    public void init() {
        driver = initializeDriver();
        home = new HomePage(driver);
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }
    @DataProvider
    public Object[][] getData() throws IOException {
//        HashMap<String, String> reservationDetails = new HashMap<>();
//        reservationDetails.put("origin", "AIP");
//        reservationDetails.put("destination", "BHO");
//        reservationDetails.put("origin2", "GOI");
//        reservationDetails.put("destination2", "COK");
//        HashMap<String, String> reservationDetails1 = new HashMap<>();
//        reservationDetails1.put("origin", "AIP");
//        reservationDetails1.put("destination", "BHO");
//        reservationDetails1.put("origin2", "GOI");
//        reservationDetails1.put("destination2", "COK");
//        List<HashMap<String, String>> l = new ArrayList<>();
//        l.add(reservationDetails);
//        l.add(reservationDetails1);
        List<HashMap<String, String>> l = getJsonData(System.getProperty("user.dir") + "/reservationDetails.json");
        System.out.println("USER DIR: " + System.getProperty("user.dir"));
        return new Object[][]
                {
                        {l.get(0)} , {l.get(1)}
                };
    }
}
