import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

public class BaseTest {

    public WebDriver initializeDriver() {
        System.setProperty("webdriver.chrome.driver", "/var/lib/jenkins/chromedriver");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--window-size=1000,700");
        options.addArguments("--headless");
        WebDriver driver = new ChromeDriver(options);
        return driver;
    }

    public List<HashMap<String, String>> getJsonData(String pathToJson) throws IOException {
        //read each array of json and save to some var

        String jsonContent = FileUtils.readFileToString(new File(pathToJson), StandardCharsets.UTF_8);
        ObjectMapper om = new ObjectMapper();
        List <HashMap <String, String>> data = om.readValue(jsonContent, new TypeReference<List < HashMap < String, String >>>(){});
        return data;
    }
}
