package Trip;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
// this Data driven approach is Not Valid for this program. 
// there are NO inputs for this Automation.
public class Trippackage {
 static WebDriver driver;
 public static void main(String[] args) throws IOException {
  Scanner sc=new Scanner(System.in);
  //Launch any browser as per the Choice ex:Chrome/Firefox
  System.out.println("Enter your choice for browser");
  System.out.println("1.Chrome");
  System.out.println("2.edge");
  int choice=sc.nextInt();
  switch(choice) {
  case 1:
   // to launch the Chrome Browser
   System.setProperty("webdriver.chrome.driver", "./driver/chrome/chromedriver.exe");
   driver = new ChromeDriver();
   break;
  case 2:
   // to launch the Firefox Browser
   System.setProperty("webdriver.gecko.driver", "./driver/gecko/geckodriver.exe");
   driver = new FirefoxDriver();
   break;
  default :
   System.out.println("wrong input");
  }
   //Open the given online Website (example: "https://www.yatra.com/").
  driver.get("https://www.yatra.com/");
  //To Maximize the Window
  driver.manage().window().maximize();
  //Navigate to Home page of the Application and click “Offers” link.
  driver.findElement(By.xpath("//*[@id=\"themeSnipe\"]/div/div/div[4]/div[2]/div/ul/li[3]/a")).click();
  try {
   Thread.sleep(2000);
  } catch (InterruptedException e) {
   //  Auto-generated catch block
   e.printStackTrace();
  }
  //Add a check point to validate whether the browser title is "Domestic Flights Offers | Deals on Domestic Flight Booking | Yatra.com".
  String title="Domestic Flights Offers | Deals on Domestic Flight Booking | Yatra.com";
  if(title.equals(driver.getTitle())) {
   System.out.println("Title is verified");
   String actuallogo ="Great Offers & Amazing Deals";
   String expectedlogo=driver.findElement(By.xpath("//h2[@class='wfull bxs']")).getText();
   //Add a check point to validate whether the banner logo is "Great Offers & Amazing Deals"
   if(actuallogo.equals(expectedlogo)) {
    System.out.println("logo verified");
   }
   else {
    System.out.println("logo not verifed");
   }
  }
  else {
   System.out.println("Not verified the page title");
  }
  takeScreenshot();
  System.out.println("Packages are:-");int i=0;
  ArrayList<String> elements=getPackages();
  for (String values  : elements) {
   System.out.println((i++)+"."+values);
  }
  closeBrowser();

 }
 //Capture the screen shot of the browser window.
 public static void takeScreenshot() throws IOException
 {
	 try
  {
   driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
  TakesScreenshot scrShot =((TakesScreenshot)driver);
  //Call getScreenshotAs method to create image file
  File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
  //Move the Image file to new destination
  Date date = new Date(); 
  SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
  String strDate= formatter.format(date);
  File DestFile=new File("C:\\Users\\NAGAPRIYADARSHINI\\Pictures\\snapshot"+strDate+".png");
  FileUtils.copyFile(SrcFile, DestFile);
  System.out.println("Screenshot succefully taken");
  }
  catch(Exception e) {
   System.out.println(e);
  }
 }
 //List the five holiday packages with its price.
 public static ArrayList<String> getPackages()
 {    
  driver.findElement(By.xpath("//*[@id=\"offer-box-shadow\"]/li[4]/a")).click();
  List<WebElement> elements=driver.findElements(By.xpath("//body/div[1]/div[1]/section[1]/section[1]/div[2]/div[1]/section[1]/div[1]/div[1]/ul[1]"));
  ArrayList<String> list= new ArrayList();
  int cou=0;
  for (WebElement webElement : elements) {
   cou++;
   if(cou==6)
   {
    break;
   }
   else
   {
    list.add(webElement.getText());}
  }
  return list;
  }
 //Close the browser.
 public static void closeBrowser() {
  driver.close();
 }
}