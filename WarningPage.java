package Projects.project1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class WarningPage {

	WebDriver driver;
	WebElement warningMassage;

	public WarningPage(WebDriver driver){
		this.driver = driver;
		warningMassage = driver.findElement(By.className("_1tp8")); 
		}

	public String get_warning(){

		return warningMassage.getText(); 
	}
	
}
