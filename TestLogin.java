package Projects.project1;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import Projects.project1.LoginPage;
import main_res.Drivers;

public class TestLogin {

	WebDriver driver;
	Drivers choseDriver;
	LoginPage loginPage;
	HomePage homePage;
	WarningPage warningPage;

	@BeforeClass
	public void setup(){

		choseDriver = new Drivers();
		driver = choseDriver.choseDriver(Drivers.enyDriver.FIREFOX);
        driver.get(LoginPage.url); // всегда выходим на начальн стр тут
	}

	@AfterClass
	public void down(){	 
		driver.quit();
	}

	@BeforeMethod
	public void setup_(){
		driver.get(LoginPage.url); // возврат на начальн стр п/д каждым новым тестом		
		loginPage = new LoginPage(driver);  // передаем driver в класс LoginPage и его К ищет все необ-е ЭУ страницы LoginPage
	}

	@AfterMethod
	public void down_(){	
	driver.manage().deleteAllCookies(); // после кажд @теста возвр-мся на начальн стр. Перед - не пойдет.
	}
	
	
	@Test
	public void loadPage(){
		Assert.assertEquals("https://www.facebook.com/", driver.getCurrentUrl()); // сверим по url
	}

	@Test
	public void successLogin_PASSED(){
		homePage = loginPage.performLogin("ignatev-o@mail.ua", "qazxcvbnm86"); // вводим почту и пароль и кликаем; потом происходит поиск ЭУ для стр HomePage в К HomePage				
		Assert.assertEquals(homePage.get_my_name(), "Olia"); // проверяем не по тайтлу, а более надежнее - по имени, н-р "привет, Оля!" - поприветствовал меня Facebook		
	}
	
	@Test
	public void empty_email_right_password_FAIL(){
		warningPage = loginPage.performInvalidLogin("", "qazxcvbnm86"); 
		Assert.assertEquals(warningPage.get_warning(), "The email or phone number you’ve entered doesn’t match any account. Sign up for an account."); // не проверяй по тайтлу...
	}
	
	@Test
	public void right_email_empty_password_FAIL(){
		warningPage = loginPage.performInvalidLogin("ignatev-o@mail.ua", "");
		Assert.assertEquals(warningPage.get_warning(), "The password you’ve entered is incorrect. Forgot Password?");
	}
	
	@Test
	public void wrong_email_and_password_FAIL(){
		warningPage = loginPage.performInvalidLogin("somename-o@mail.ua", "qwedvcm67553jlkmgng98");		
		Assert.assertEquals(warningPage.get_warning(), "The email you’ve entered doesn’t match any account. Sign up for an account.");
	}


}
