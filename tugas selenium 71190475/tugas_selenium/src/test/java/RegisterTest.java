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

public class RegisterTest {
    ChromeDriver driver;
    @Given("buka browser")
    public void buka_browser() {
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
    @Given("user in register page")
    public void user_in_register_page() {
        System.out.println("register page");
        driver.navigate().to("https://demo.guru99.com/insurance/v1/register.php");
    }

    @When("insert {string} {string} and {string}")
    public void insert_firstname_email_and_password(String firstname, String email, String password) {
        System.out.println("insert username dan password");
        driver.findElement(By.xpath("//*[@id=\"user_firstname\"]")).sendKeys(firstname);
        driver.findElement(By.xpath("//*[@id=\"user_user_detail_attributes_password\"]")).sendKeys(password);
        driver.findElement(By.xpath("//*[@id=\"user_user_detail_attributes_password_confirmation\"]")).sendKeys(password);
        driver.findElement(By.xpath("//*[@id=\"user_user_detail_attributes_email\"]")).sendKeys(email);
    }

    @When("ada tombol reset dan create")
    public void ada_tombol_reset_dan_create() {
        System.out.println("cek tombol reset dan create");
        WebElement resetBtn = driver.findElement(By.xpath("//*[@id=\"resetform\"]"));
        WebElement registerBtn = driver.findElement(By.xpath("//*[@id=\"new_user\"]/div[5]/input[2]"));
        Assertions.assertTrue(resetBtn.toString().length() > 0, "tombol reset ndak ada");
        Assertions.assertTrue(registerBtn.toString().length() > 0, "tombol register ndak ada");
    }

    @When("create account")
    public void create_account() {
        System.out.println("create account");
        driver.findElement(By.xpath("//*[@id=\"new_user\"]/div[5]/input[2]")).click();
        System.out.println("register selesai");
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
    @Then("{string}: cek email {string}, kata sandi {string}")
    public void cek_email_kata_sandi(String testid, String email, String pwd) {
        System.out.println("Validasi register");
        System.out.println("Test ID: " + testid);
        System.out.println("Email: " + email);
        System.out.println("Password: " + pwd);
        List<WebElement> btnLogin = driver.findElements(By.xpath("//*[@id=\"login-form\"]/div[3]/input"));
        String hasilValidasi = validasi(email, pwd);
        Boolean finalcek = null;
//        if(hasilValidasi.equals("passed") && hasilValidasi.equals(expectedReturn)){
//            finalcek = true;
//        }
//        else if(hasilValidasi != "passed" && hasilValidasi.equals(expectedReturn)){
//            finalcek = false;
//        }
        if(hasilValidasi.equals("passed")){
            finalcek = true;
        }
        else if(hasilValidasi != "passed"){
            finalcek = false;
        }
        driver.quit();
        Assertions.assertTrue(finalcek,hasilValidasi);

    }
}
