package demo.orangehrmlive.com;

import com.cucumber.listener.Reporter;
import cucumber.api.Scenario;
import demo.orangehrmlive.com.propertyreader.PropertyReader;
import demo.orangehrmlive.com.utility.Utility;
import org.junit.After;
import org.junit.Before;

import java.io.IOException;

public class Hooks extends Utility {

    @Before
    public void setUP() {
        selectBrowser(PropertyReader.getInstance().getProperty("browser"));
        Reporter.assignAuthor("Ishita Malhotra");
    }

    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            String screenShotPath = Utility.getScreenshot(driver, scenario.getName().replace(" ", "_"));
            try {
                Reporter.addScreenCaptureFromPath(screenShotPath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        closeBrowser();
    }
}
