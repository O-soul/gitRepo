package Projects.project1;

import java.util.List;

import javax.swing.Action;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {

	WebDriver driver;
	private WebElement my_name;
	private WebElement textArea;
	private WebElement postFoto;
	WebElement findPostedFoto;
	private WebElement submit;
	private WebElement PostedText;
	public static String homePageUrl = "https://www.facebook.com/";
	WebDriverWait wait;
	Actions action;


	public HomePage(WebDriver driver){
		this.driver = driver;
		action = new Actions(driver);
		my_name = driver.findElement(By.xpath(".//a[@data-testid='blue_bar_profile_link']/span")); // ВСЕГДА: находим все неизменяемые ЭУ страницы в К класса этой стр
		textArea = driver.findElement(By.xpath(".//form//div[@class='_4bl9']/textarea[@class='uiTextareaAutogrow _3en1']"));  
		postFoto = driver.findElement(By.name("xhpc_message")); // находим инпут с местом для файла	
		////////////////////тут мы искали сабмит/////////////////////////
		wait = new WebDriverWait(driver, 15);

	}

	public String get_my_name(){		
		return my_name.getText(); 
	}


	public String set_PostText(String text){ // м() вводит текст в поле(текстбокс) и возвращает value текстбокса		
		textArea.sendKeys(text);		
		submit(); 
		// ждем, тк введенный текст не успевает попасть в тег и скрипт берет его старое значение.

		//wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//button[@type='submit' and text() = 'Опубликовать']"))); // ждем пока кн submit не "отомрет"
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PostedText = driver.findElement(By.xpath(".//div[@id='contentArea']//div/p")); 
		return PostedText.getText();
	}

	void submit(){
		System.out.println("Before submit!");
		//submit = driver.findElement(By.xpath(".//button[@type='submit' and text()='Post']"));/////////////////////////сабмит ВАЖНО искать им здесь, тк мы загрузили фотку и стр обновилась, обновилась - значит все ЭУ надо искать заново! А сабмит я искал в К. 
		submit = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//button[@type='submit' and text()='Post']")));
		submit.click();
		System.out.println("After submit!");
	}


	public boolean set_PostFoto(String photoPath, String text){ 
		textArea.sendKeys(text);

		List<WebElement> photosCount = driver.findElements(By.xpath(".//img[@alt=\"Olia Gorgia's photo.\"]"));
		System.out.println("we have "+photosCount.size()+"photos");

		WebElement uploadButton = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@name= 'composer_photo[]']")));
		// нашли логотип фотоаппарата				
		uploadButton.sendKeys("D:\\photo.jpg"); // "тронули" "фотик" - и произошла геннерация инпута, в котор. можно загрузить фото!

		submit();       
		wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.xpath(".//img[@alt=\"Olia Gorgia's photo.\"]"), photosCount.size()));
		/* try {
			Thread.sleep(5000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		List<WebElement> addPhoto = driver.findElements(By.xpath(".//img[@alt=\"Olia Gorgia's photo.\"]"));	
		System.out.println("we have "+addPhoto.size()+"photos");
		int photosCountDifference = addPhoto.size()- photosCount.size();
		if( photosCountDifference == 1 ){         */

		return true;
	}

	
	
	
	public boolean set_Share(String byText){
			
		WebElement share = driver.findElement(By.xpath("//p[text()='"+byText+"']//ancestor::div[@class='userContentWrapper _5pcr']//a[text()='Share']"));		
		//WebElement share = driver.findElement(By.xpath("//a[text()='Share']"));		
		
		/*Actions builder = new Actions(driver);
		  builder.moveToElement(WebElement).click(WebElement).build();  you can see Actions builder working here(it doesn't work with JS, so I can't use it here. The meter is - WebElement share is not a dropDownList, it's a JS-window, so you need use JavascriptExecutor executor to work with it here)*/ 
		
		JavascriptExecutor executor = (JavascriptExecutor)driver; // read comment above
		executor.executeScript("arguments[0].click();", share);
		
		WebElement shareToFriends = driver.findElement(By.xpath(".//span[text()='Share Now (Friends)']"));
		shareToFriends.click();
		
		try {
			Thread.sleep(7000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("before upload");
		
		driver.get(homePageUrl);
		
		System.out.println("after upload");
		
		try {
			Thread.sleep(7000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try{
		WebElement isShared = driver.findElement(By.xpath("//p[text()='"+byText+"']//ancestor::div[@class='userContentWrapper _5pcr']//a[text()='post']"));
		
		System.out.println("SharedLogo is found!");}
				
		catch(NoSuchElementException e){System.out.println(e.getMessage()); return false;}
				
		return true;		
	}

}
		



































	











