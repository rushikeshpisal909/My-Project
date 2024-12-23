

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
public class PRoject{

	public static void main(String[] args) throws InterruptedException, IOException {
		WebDriver driver = new ChromeDriver();// use 'fix project' when at 1st the
		// imports r not loading 
		
		driver.get("https://www.flipkart.com/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		driver.manage().window().maximize();
		// click on login
		driver.findElement(By.xpath("//header/div[2]/div[2]/div[1]/div[1]/div[1]")).click();
		// hower to Electronics and hold , so action class
		// this below is electronic element
		WebElement ele = driver.findElement(By.xpath("//body/div[@id='container']/div[1]/div[2]/div[1]/div[1]/span[1]"));
		Actions actions = new Actions(driver);
		Thread.sleep(3000);
		actions.clickAndHold(ele).build().perform();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//body/div[@id='container']/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/a[1]")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//body/div[@id='container']/div[1]/div[1]/div[1]/div[2]/div[2]/form[1]/div[1]/div[1]/input[1]")).sendKeys("Mobiles");
		
		driver.findElement(By.xpath("//body/div[@id='container']/div[1]/div[1]/div[1]/div[2]/div[2]/form[1]/div[1]/button[1]")).click();
		Thread.sleep(3000);
		
		List<WebElement> products = driver.findElements(By.xpath("//body/div[@id='container']/div[1]/div[3]/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]"));
		Thread.sleep(3000);
		
		XSSFWorkbook workbook  = new XSSFWorkbook();
		XSSFSheet sheet =workbook.createSheet("phones");
		Thread.sleep(3000);
		int rowNum=0;
		
		
		List<WebElement> name = List.of();
		List<WebElement> price = List.of();
	
		Row row =null;
		
		for(WebElement product:products) {
			name = product.findElements(By.xpath("//div[@class=\"KzDlHZ\"]")); // for all name of phns we took same class of the text of name of phns
			price = product.findElements(By.xpath("//div[@class=\"Nx9bqj _4b5DiR\"]")); // same in case of class of text of price			
		}
		for(int i=0;i<name.size();i++) {
			String item =name.get(i).getText();
			String pp = price.get(i).getText();
			
			row =sheet.createRow(rowNum++);
			row.createCell(0).setCellValue(item);
			row.createCell(1).setCellValue(pp);
			
			
			System.out.println(item + "-"+ pp );
		}
		// to get data on excell
		FileOutputStream os = new FileOutputStream("Phones.xls");
		
		workbook.write(os);
		
		Thread.sleep(3);
	}

}
