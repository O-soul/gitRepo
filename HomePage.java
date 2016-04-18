package Projects.project1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {
	
	WebDriver driver;
	private WebElement my_name;
	
	public HomePage(WebDriver driver){
		this.driver = driver;	
	    my_name = driver.findElement(By.xpath("//span[text() ='Olia']")); // ВСЕГДА: находим все неизменяемые ЭУ страницы в К класса этой стр
	}
	
	public String get_my_name(){
		
		return my_name.getText(); 
	}

}
