import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class LoginTest {
    ChromeDriver driver;
    @Given("browser opened")
    public void browser_opened() {
        System.out.println("Buka browser");
        System.setProperty("webdriver.chrome.driver",
                Objects.requireNonNull(getClass().getClassLoader()
                        .getResource("webdriver/chromedriver.exe")).getFile());
        ChromeOptions options = new ChromeOptions();
        options.addArguments("headless");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(40));
    }
    @Given("user in register page for login")
    public void user_in_register_page_for_login() {
        System.out.println("register page");
        driver.navigate().to("https://demo.guru99.com/insurance/v1/register.php");
    }
    @Given("user click login")
    public void user_click_login() {
        System.out.println("click login");
        driver.findElement(By.xpath("/html/body/div[3]/a")).click();
    }
    @Given("user in login page")
    public void user_in_login_page() {
        System.out.println("cek login page");
        Boolean loginBtnFound;
        try{
            WebElement loginBtn = driver.findElement(By.xpath("//*[@id=\"login-form\"]/div[3]/input"));
            loginBtnFound = true;
        }
        catch(Exception e){
            loginBtnFound = false;
        }
        Assertions.assertTrue(loginBtnFound, "belum di login page");

    }
    @When("user insert {string} and {string}")
    public void user_insert_email_and_password(String email, String password) {
        System.out.println("insert email and password");
        driver.findElement(By.xpath("//*[@id=\"email\"]")).sendKeys(email);
        driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys(password);
    }
    @When("login button clicked")
    public void login_button_clicked() {
        System.out.println("klik login button");
        driver.findElement(By.xpath("//*[@id=\"login-form\"]/div[3]/input")).click();
    }
    private boolean cekAlfanumerik(String input){
        char[] chars = input.toLowerCase().toCharArray();

        for (char ch: chars) {
            if(!Character.isLetterOrDigit(ch)){
                return false;
            }
        }
        return true;
    }
    private String validasi(String email, String pwd){
        ArrayList<String> message = new ArrayList<String>();
        if(email.equals("")){
            message.add("email harus diisi");
        }
        if(pwd.equals("")){
            message.add("password harus diisi");
        }
        if(!(pwd.length() >= 8 && pwd.length() <=13)){
            message.add("jumlah karakter password harus 8-13");
        }
        if(!cekAlfanumerik(pwd)){
            message.add("password harus alfanumerik");
        }
        String finalMessage = String.join(", ", message);
        if(finalMessage.equals("")){
            return "passed";
        }
        else{
            return finalMessage;
        }
    }
    @Then("{string}: email {string}, kata sandi {string}")
    public void cek_email_kata_sandi(String testid, String email, String pwd) {
        System.out.println("Validasi Login");
        System.out.println("Test ID: " + testid);
        System.out.println("Email: " + email);
        System.out.println("Password: " + pwd);
//        Boolean logoutBtnFound;
        String hasilValidasi = validasi(email, pwd);
        Boolean finalcek = null;
        if(hasilValidasi.equals("passed")){
            finalcek = true;
        }
        else if(hasilValidasi != "passed"){
            finalcek = false;
        }
//        try{
//            WebElement logoutBtn = driver.findElement(By.xpath("/html/body/div[3]/form/input"));
//             logoutBtnFound = true;
//        }
//        catch (Exception e){
//            logoutBtnFound = false;
//        }
//        Assertions.assertTrue(logoutBtnFound, "login gagal");
        List<WebElement> logoutBtn = driver.findElements(By.xpath("/html/body/div[3]/form/input"));
        System.out.println("test login selesai");
        driver.quit();
        Assertions.assertTrue((logoutBtn.size()>0) && finalcek,hasilValidasi);
    }
}
