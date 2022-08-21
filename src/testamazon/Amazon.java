package testamazon;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Amazon {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.setProperty("webdriver.chrome.driver", "chromedriver");
		WebDriver driver=new ChromeDriver();
		driver.get("https://www.amazon.in/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
		
		WebElement SearchPr = driver.findElement(By.xpath("//input[@type='text']"));
		SearchPr.sendKeys("Samsung");
		
		WebElement SearchBtn = driver.findElement(By.xpath("//input[@type='submit']"));
		SearchBtn.click();
		
		
		List<WebElement> ProductList = driver.findElements(By.xpath("//div[@data-component-type='s-search-result']//h2"));
		List<WebElement> PriceList = driver.findElements(By.xpath("//div[@data-component-type='s-search-result']//span[@class='a-price']"));
		
				
		System.out.println("The total number of products are " + ProductList.size());
		
		for(int i=0; i<ProductList.size();i++){
		
		System.out.println(ProductList.get(0).getText()+" "+PriceList.get(0).getText());
			
		}
			
		String ParentHd = driver.getWindowHandle();
		String ExpectedValue = ProductList.get(0).getText();
		
		ProductList.get(0).click();
		
		Set<String> AllWindowsHandler = driver.getWindowHandles();
		for(String win : AllWindowsHandler) {
			System.out.println(win);
			
			if(!win.equals(ParentHd)) {
				driver.switchTo().window(win);
			}
		}
		
		WebElement Title = driver.findElement(By.id("productTitle"));
		String str=Title.getText();
		
		if(str.equals(ExpectedValue)) {
			System.out.println("The Title is matching");
		}else {
			System.out.println("The Title is not matching");
		}
			
			driver.quit();
			
			
	}

}
