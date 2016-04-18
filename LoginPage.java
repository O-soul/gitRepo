package Projects.project1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

	public static String url = "https://www.facebook.com/";
	public static String title = "Добро пожаловать на Фейсбук - заходите, регистрируйтесь и находите друзей.";
	private WebElement putTo_email_textbox;
	private WebElement putTo_password_textbox;
	WebDriver driver;
 //	WebDriverWait wait;
	private WebElement submit;
	
	
	public LoginPage(WebDriver driver){
		this.driver = driver;
	//	wait = new WebDriverWait(driver, 10);
		putTo_email_textbox = driver.findElement(By.id("email")); 
		putTo_password_textbox = driver.findElement(By.id("pass")); // Важно! теперь при иниц-ии об LoginPage, К сразу найдет все необх-ые ЭУ на стр LoginPage
		submit = driver.findElement(By.xpath("//*[@id='loginbutton']/input"));
	}
	
	public String getTitle(){
		String title = driver.findElement(By.id("pageTitle")).getText();
		return title;
	}
	
	private void put_email(String email){
		putTo_email_textbox.clear();
		putTo_email_textbox.sendKeys(email);
		System.out.println("Enter text " + email);
	}
	
	private void put_password(String password){	
		putTo_password_textbox.clear();
		putTo_password_textbox.sendKeys(password);
		System.out.println("Enter text " + password);
	}
	
	private void submit(){			
		submit.click();
		System.out.println("Click! "); // отписал в консоль
		}

	public HomePage performLogin(String email, String password) { // этот м() инкапсулирует три соседних метода
		put_email(email);
		put_password(password);
		submit();  // переходим на стр HomePage	
		
	//	wait.until(ExpectedConditions.titleIs("Facebook"));
		return new HomePage(driver); // сейчас происходит инициализация	об HomePage(new HomePage(driver)) - я передаю в него driver и К(с пом driver) находит все необх-е ЭУ на стр HomePage. Важно! - если браузер сейчас не на стр HomePage, то поиск ЭУ на стр HomePage закончится исключением. Return вовсе не обязателен, проинить HomePage можно и в самом @тесте, но это плохая практика.
	                                 // собсно new "HomePage(driver);" осуществляет "поиск ЭУ на стр HomePage". Почему? Посмотри в его К!
	}

	public WarningPage performInvalidLogin(String wrongEmail, String wrongPassword) {
		put_email(wrongEmail);
		put_password(wrongPassword);
		submit();         // переходим на стр WarningPage	
				
		return new WarningPage(driver); // поиск ЭУ на стр WarningPage(подрбнее ситай в м performLogin() )
	}
		
	
	
}
