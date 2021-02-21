package Steps;

import Pages.AddPage;
import Pages.EditPage;
import Pages.HomePage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class ComputerDatabaseSteps {
    WebDriver driver;
    private Scenario scenario;
    WebDriverWait wait;
    private static WebElement element = null;
    String compName = null;
    String intDate = "00000100";
    String disDate = null;
    String companyName = null;
    String newIntDate = null;
    String newDisDate = null;


    @Before(value = "@BeforeTest", order = 1)
    //  @Before
    public void before(Scenario scenario) {
        this.scenario = scenario;
        System.setProperty("webdriver.chrome.driver", "C:\\Chromedriver\\chromedriver.exe");
        //set the useAutomationExtension capability to false as causing error on machine 'Loading of unpacked extensions is disabled by the administrator.'
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("useAutomationExtension", false);
        driver = new ChromeDriver(options);
        //driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 10);

    }

    @After
    public void doSomethingAfter(Scenario scenario) {
        this.scenario = scenario;
        driver.close();
        driver.quit();
    }

    @Given("User is on the computer database")
    public void user_is_on_the_computer_database() {
        //Navigate to page
        driver.get("http://computer-database.herokuapp.com/computers");

        //Check page is correct
        String homeTitle = driver.getTitle();
        Assert.assertEquals(homeTitle, "Computers database");
    }


    @When("User completes the Add a new computer journey")
    public void user_completes_the_add_a_new_computer_journey(DataTable dataTable) {
        //Instantiate the Table
        List<List<String>> values = dataTable.asLists(String.class);

        //Navigate to Add screen
        HomePage.add_button(driver).click();
        wait.until(ExpectedConditions.elementToBeClickable(AddPage.compName_text(driver)));

        //get values
        compName = values.get(1).get(1);
        intDate = values.get(2).get(1);
        disDate = values.get(3).get(1);
        companyName = values.get(4).get(1);

        //enter values
        AddPage.compName_text(driver).sendKeys(compName);

        if (intDate.equals("Blank")) {
            intDate = "-";
        } else {
            AddPage.introduced_date(driver).sendKeys(intDate);
        }

        if (disDate.equals("Blank")) {
            disDate = "-";
        } else {
            AddPage.discontinued_date(driver).sendKeys(disDate);
        }

        if (companyName.equals("Default")) {
            companyName = "-";
        } else {
            Select company = new Select(AddPage.company_dropdown(driver));
            company.selectByVisibleText(companyName);
        }


        //submit values
        AddPage.save_button(driver).click();

    }

    @Then("the computer is added to the database")
    public void the_computer_is_added_to_the_database() {
        //Check on correct page
        wait.until(ExpectedConditions.elementToBeClickable(HomePage.filter_button(driver)));

        //Check message
        String message = HomePage.alert_message(driver).getText();
        Assert.assertEquals(message, "Done! Computer " + compName + " has been created");

        //Lookup new computer
        HomePage.filter_text(driver).sendKeys(compName);
        HomePage.filter_button(driver).click();

        //Convert date values for validation
        //Int date
        if (intDate.equals("-")) {
            newIntDate = "-";
        } else {
            String idYear = intDate.substring(0, 4);
            String idMonth = intDate.substring(5, 7);
            String idDay = intDate.substring(8, 10);

            String newIdMonth = null;

            if (idMonth.equals("01")) {
                newIdMonth = " Jan ";
            } else if (idMonth.equals("02")) {
                newIdMonth = " Feb ";
            } else if (idMonth.equals("03")) {
                newIdMonth = " Mar ";
            } else if (idMonth.equals("04")) {
                newIdMonth = " Apr ";
            } else if (idMonth.equals("05")) {
                newIdMonth = " May ";
            } else if (idMonth.equals("06")) {
                newIdMonth = " Jun ";
            } else if (idMonth.equals("07")) {
                newIdMonth = " Jul ";
            } else if (idMonth.equals("08")) {
                newIdMonth = " Aug ";
            } else if (idMonth.equals("09")) {
                newIdMonth = " Sep ";
            } else if (idMonth.equals("10")) {
                newIdMonth = " Oct ";
            } else if (idMonth.equals("11")) {
                newIdMonth = " Nov ";
            } else {
                newIdMonth = " Dec ";
            }
            ;

            //Get Int Date in dd MMM yyyy format
            newIntDate = idDay + newIdMonth + idYear;
        }

        //Dis date

        if (disDate.equals("-")) {
            newDisDate = "-";
        } else {
            String ddYear = disDate.substring(0, 4);
            String ddMonth = disDate.substring(5, 7);
            String ddDay = disDate.substring(8, 10);

            String newDdMonth = null;

            if (ddMonth.equals("01")) {
                newDdMonth = " Jan ";
            } else if (ddMonth.equals("02")) {
                newDdMonth = " Feb ";
            } else if (ddMonth.equals("03")) {
                newDdMonth = " Mar ";
            } else if (ddMonth.equals("04")) {
                newDdMonth = " Apr ";
            } else if (ddMonth.equals("05")) {
                newDdMonth = " May ";
            } else if (ddMonth.equals("06")) {
                newDdMonth = " Jun ";
            } else if (ddMonth.equals("07")) {
                newDdMonth = " Jul ";
            } else if (ddMonth.equals("08")) {
                newDdMonth = " Aug ";
            } else if (ddMonth.equals("09")) {
                newDdMonth = " Sep ";
            } else if (ddMonth.equals("10")) {
                newDdMonth = " Oct ";
            } else if (ddMonth.equals("11")) {
                newDdMonth = " Nov ";
            } else {
                newDdMonth = " Dec ";
            }
            ;

            //Get Int Date in dd MMM yyyy format
            newDisDate = ddDay + newDdMonth + ddYear;
        }

        //Check values
        String checkCompName = HomePage.compName_row1(driver).getText();
        Assert.assertEquals(checkCompName, compName);

        String checkCompanyName = HomePage.company_row1(driver).getText();
        Assert.assertEquals(checkCompanyName, companyName);

        String checkIntDate = HomePage.intDate_row1(driver).getText();
        Assert.assertEquals(checkIntDate, newIntDate);

        String checkDisDate = HomePage.disDate_row1(driver).getText();
        Assert.assertEquals(checkDisDate, newDisDate);

    }

    @When("User doesn't enter a Computer name")
    public void user_doesn_t_enter_a_computer_name() {
        HomePage.add_button(driver).click();
        wait.until(ExpectedConditions.elementToBeClickable(AddPage.compName_text(driver)));

        //submit values
        AddPage.save_button(driver).click();

    }

    @Then("validation on Computer name blocks the save")
    public void validation_on_computer_name_blocks_the_save() {
        //Check field changes colour due to validation
        wait.until(ExpectedConditions.elementToBeClickable(AddPage.compName_text(driver)));
        String backColour = AddPage.compName_container(driver).getCssValue("background-color");
        Assert.assertEquals(backColour, "rgba(250, 229, 227, 1)");
    }

    @When("User doesn't enter correct date format in Introduced date {string}")
    public void user_doesn_t_enter_correct_date_format_in_introduced_date(String INT_DATE) {
        HomePage.add_button(driver).click();
        wait.until(ExpectedConditions.elementToBeClickable(AddPage.compName_text(driver)));

        //enter values
        AddPage.compName_text(driver).sendKeys("TestComputer #3");
        AddPage.introduced_date(driver).sendKeys(INT_DATE);

        //submit values
        AddPage.save_button(driver).click();

    }

    @Then("validation on Introduced date blocks the save")
    public void validation_on_introduced_date_blocks_the_save() {
        //Check field changes colour due to validation
        wait.until(ExpectedConditions.elementToBeClickable(AddPage.compName_text(driver)));
        String backColour = AddPage.intDate_container(driver).getCssValue("background-color");
        Assert.assertEquals(backColour, "rgba(250, 229, 227, 1)");
    }

    @When("User doesn't enter correct date format in Discontinued date {string}")
    public void user_doesn_t_enter_correct_date_format_in_discontinued_date(String DIS_DATE) {
        HomePage.add_button(driver).click();
        wait.until(ExpectedConditions.elementToBeClickable(AddPage.compName_text(driver)));

        //enter values
        AddPage.compName_text(driver).sendKeys("TestComputer #4");
        AddPage.discontinued_date(driver).sendKeys(DIS_DATE);

        //submit values
        AddPage.save_button(driver).click();

    }

    @Then("validation on Discontinued date blocks the save")
    public void validation_on_discontinued_date_blocks_the_save() {
        //Check field changes colour due to validation
        wait.until(ExpectedConditions.elementToBeClickable(AddPage.compName_text(driver)));
        String backColour = AddPage.disDate_container(driver).getCssValue("background-color");
        Assert.assertEquals(backColour, "rgba(250, 229, 227, 1)");
    }

    @When("the computer is deleted")
    public void the_computer_is_deleted() {
        // navigate to edit screen to delete
        HomePage.compName_button(driver).click();

        wait.until(ExpectedConditions.elementToBeClickable(EditPage.compName_text(driver)));
        EditPage.delete_button(driver).click();

    }

    @Then("the computer is not in the database")
    public void the_computer_is_not_in_the_database() {
        //Check on correct page
        wait.until(ExpectedConditions.elementToBeClickable(HomePage.filter_button(driver)));

        //Check message
        String message = HomePage.alert_message(driver).getText();
        Assert.assertEquals(message, "Done! Computer has been deleted");

        //Lookup deleted computer
        HomePage.filter_text(driver).sendKeys(compName);
        HomePage.filter_button(driver).click();

        String results = HomePage.empty_row(driver).getText();
        Assert.assertEquals(results, "Nothing to display");

    }

    @When("User edits the values in the fields")
    public void user_edits_the_values_in_the_fields(DataTable dataTableb) {
        //Instantiate the Table
        List<List<String>> updateValues = dataTableb.asLists(String.class);

        //Navigate to Edit screen
        HomePage.compName_button(driver).click();
        wait.until(ExpectedConditions.elementToBeClickable(EditPage.compName_text(driver)));

        //get values
        compName = updateValues.get(1).get(1);
        intDate = updateValues.get(2).get(1);
        disDate = updateValues.get(3).get(1);
        companyName = updateValues.get(4).get(1);

        //enter values
        EditPage.compName_text(driver).clear();
        EditPage.compName_text(driver).sendKeys(compName);

        if (intDate.equals("Blank")) {
            EditPage.introduced_date(driver).clear();
            intDate = "-";
        } else {
            EditPage.introduced_date(driver).clear();
            EditPage.introduced_date(driver).sendKeys(intDate);
        }

        if (disDate.equals("Blank")) {
            EditPage.discontinued_date(driver).clear();
            disDate = "-";
        } else {
            EditPage.discontinued_date(driver).clear();
            EditPage.discontinued_date(driver).sendKeys(disDate);
        }

        if (companyName.equals("Default")) {
            Select company = new Select(EditPage.company_dropdown(driver));
            company.selectByVisibleText("-- Choose a company --");
            companyName = "-";
        } else {
            Select company = new Select(EditPage.company_dropdown(driver));
            company.selectByVisibleText(companyName);
        }


        //submit values
        EditPage.save_button(driver).click();
    }

    @Then("the computer is updated in the database")
    public void the_computer_is_updated_in_the_database() {
        //Check on correct page
        wait.until(ExpectedConditions.elementToBeClickable(HomePage.filter_button(driver)));

        //Check message
        String message = HomePage.alert_message(driver).getText();
        Assert.assertEquals(message, "Done! Computer " + compName + " has been updated");

        //Lookup new computer
        HomePage.filter_text(driver).sendKeys(compName);
        HomePage.filter_button(driver).click();

        //Convert date values for validation
        //Int date
        if (intDate.equals("-")) {
            newIntDate = "-";
        } else {
            String idYear = intDate.substring(0, 4);
            String idMonth = intDate.substring(5, 7);
            String idDay = intDate.substring(8, 10);

            String newIdMonth = null;

            if (idMonth.equals("01")) {
                newIdMonth = " Jan ";
            } else if (idMonth.equals("02")) {
                newIdMonth = " Feb ";
            } else if (idMonth.equals("03")) {
                newIdMonth = " Mar ";
            } else if (idMonth.equals("04")) {
                newIdMonth = " Apr ";
            } else if (idMonth.equals("05")) {
                newIdMonth = " May ";
            } else if (idMonth.equals("06")) {
                newIdMonth = " Jun ";
            } else if (idMonth.equals("07")) {
                newIdMonth = " Jul ";
            } else if (idMonth.equals("08")) {
                newIdMonth = " Aug ";
            } else if (idMonth.equals("09")) {
                newIdMonth = " Sep ";
            } else if (idMonth.equals("10")) {
                newIdMonth = " Oct ";
            } else if (idMonth.equals("11")) {
                newIdMonth = " Nov ";
            } else {
                newIdMonth = " Dec ";
            }
            ;

            //Get Int Date in dd MMM yyyy format
            newIntDate = idDay + newIdMonth + idYear;
        }

        //Dis date

        if (disDate.equals("-")) {
            newDisDate = "-";
        } else {
            String ddYear = disDate.substring(0, 4);
            String ddMonth = disDate.substring(5, 7);
            String ddDay = disDate.substring(8, 10);

            String newDdMonth = null;

            if (ddMonth.equals("01")) {
                newDdMonth = " Jan ";
            } else if (ddMonth.equals("02")) {
                newDdMonth = " Feb ";
            } else if (ddMonth.equals("03")) {
                newDdMonth = " Mar ";
            } else if (ddMonth.equals("04")) {
                newDdMonth = " Apr ";
            } else if (ddMonth.equals("05")) {
                newDdMonth = " May ";
            } else if (ddMonth.equals("06")) {
                newDdMonth = " Jun ";
            } else if (ddMonth.equals("07")) {
                newDdMonth = " Jul ";
            } else if (ddMonth.equals("08")) {
                newDdMonth = " Aug ";
            } else if (ddMonth.equals("09")) {
                newDdMonth = " Sep ";
            } else if (ddMonth.equals("10")) {
                newDdMonth = " Oct ";
            } else if (ddMonth.equals("11")) {
                newDdMonth = " Nov ";
            } else {
                newDdMonth = " Dec ";
            }
            ;

            //Get Int Date in dd MMM yyyy format
            newDisDate = ddDay + newDdMonth + ddYear;
        }

        //Check values
        String checkCompName = HomePage.compName_row1(driver).getText();
        Assert.assertEquals(checkCompName, compName);

        String checkCompanyName = HomePage.company_row1(driver).getText();
        Assert.assertEquals(checkCompanyName, companyName);

        String checkIntDate = HomePage.intDate_row1(driver).getText();
        Assert.assertEquals(checkIntDate, newIntDate);

        String checkDisDate = HomePage.disDate_row1(driver).getText();
        Assert.assertEquals(checkDisDate, newDisDate);
    }

    @When("User removes Computer name validation blocks the save")
    public void user_removes_computer_name_validation_blocks_the_save() {
        //Navigate to Edit screen
        HomePage.compName_button(driver).click();
        wait.until(ExpectedConditions.elementToBeClickable(EditPage.compName_text(driver)));

        //remove comp name
        EditPage.compName_text(driver).clear();

        //submit values
        EditPage.save_button(driver).click();

        //Check field changes colour due to validation
        wait.until(ExpectedConditions.elementToBeClickable(EditPage.compName_text(driver)));
        String backColour = EditPage.compName_container(driver).getCssValue("background-color");
        Assert.assertEquals(backColour, "rgba(250, 229, 227, 1)");


    }

    @Then("the computer retains the original values in the database")
    public void the_computer_retains_the_original_values_in_the_database() {
        //return to home page
        driver.navigate().back();
        driver.navigate().back();

        //Check on correct page
        wait.until(ExpectedConditions.elementToBeClickable(HomePage.filter_button(driver)));

        //Lookup new computer
        HomePage.filter_text(driver).clear();
        HomePage.filter_text(driver).sendKeys(compName);
        HomePage.filter_button(driver).click();

        //Convert date values for validation
        //Int date
        if (intDate.equals("-")) {
            newIntDate = "-";
        } else {
            String idYear = intDate.substring(0, 4);
            String idMonth = intDate.substring(5, 7);
            String idDay = intDate.substring(8, 10);

            String newIdMonth = null;

            if (idMonth.equals("01")) {
                newIdMonth = " Jan ";
            } else if (idMonth.equals("02")) {
                newIdMonth = " Feb ";
            } else if (idMonth.equals("03")) {
                newIdMonth = " Mar ";
            } else if (idMonth.equals("04")) {
                newIdMonth = " Apr ";
            } else if (idMonth.equals("05")) {
                newIdMonth = " May ";
            } else if (idMonth.equals("06")) {
                newIdMonth = " Jun ";
            } else if (idMonth.equals("07")) {
                newIdMonth = " Jul ";
            } else if (idMonth.equals("08")) {
                newIdMonth = " Aug ";
            } else if (idMonth.equals("09")) {
                newIdMonth = " Sep ";
            } else if (idMonth.equals("10")) {
                newIdMonth = " Oct ";
            } else if (idMonth.equals("11")) {
                newIdMonth = " Nov ";
            } else {
                newIdMonth = " Dec ";
            }
            ;

            //Get Int Date in dd MMM yyyy format
            newIntDate = idDay + newIdMonth + idYear;
        }

        //Dis date

        if (disDate.equals("-")) {
            newDisDate = "-";
        } else {
            String ddYear = disDate.substring(0, 4);
            String ddMonth = disDate.substring(5, 7);
            String ddDay = disDate.substring(8, 10);

            String newDdMonth = null;

            if (ddMonth.equals("01")) {
                newDdMonth = " Jan ";
            } else if (ddMonth.equals("02")) {
                newDdMonth = " Feb ";
            } else if (ddMonth.equals("03")) {
                newDdMonth = " Mar ";
            } else if (ddMonth.equals("04")) {
                newDdMonth = " Apr ";
            } else if (ddMonth.equals("05")) {
                newDdMonth = " May ";
            } else if (ddMonth.equals("06")) {
                newDdMonth = " Jun ";
            } else if (ddMonth.equals("07")) {
                newDdMonth = " Jul ";
            } else if (ddMonth.equals("08")) {
                newDdMonth = " Aug ";
            } else if (ddMonth.equals("09")) {
                newDdMonth = " Sep ";
            } else if (ddMonth.equals("10")) {
                newDdMonth = " Oct ";
            } else if (ddMonth.equals("11")) {
                newDdMonth = " Nov ";
            } else {
                newDdMonth = " Dec ";
            }
            ;

            //Get Int Date in dd MMM yyyy format
            newDisDate = ddDay + newDdMonth + ddYear;
        }

        //Check values
        String checkCompName = HomePage.compName_row1(driver).getText();
        Assert.assertEquals(checkCompName, compName);

        String checkCompanyName = HomePage.company_row1(driver).getText();
        Assert.assertEquals(checkCompanyName, companyName);

        String checkIntDate = HomePage.intDate_row1(driver).getText();
        Assert.assertEquals(checkIntDate, newIntDate);

        String checkDisDate = HomePage.disDate_row1(driver).getText();
        Assert.assertEquals(checkDisDate, newDisDate);

        //Adding to remove computer for repeat running
        HomePage.compName_button(driver).click();

        wait.until(ExpectedConditions.elementToBeClickable(EditPage.compName_text(driver)));
        EditPage.delete_button(driver).click();
    }

    @When("User updates to incorrect date format in Introduced date validation blocks the save {string}")
    public void user_updates_to_incorrect_date_format_in_introduced_date_validation_blocks_the_save(String INT_DATE) {
        HomePage.compName_button(driver).click();
        wait.until(ExpectedConditions.elementToBeClickable(EditPage.compName_text(driver)));

        //remove and enter values
        EditPage.introduced_date(driver).clear();
        EditPage.introduced_date(driver).sendKeys(INT_DATE);

        //submit values
        EditPage.save_button(driver).click();

        //Check field changes colour due to validation
        wait.until(ExpectedConditions.elementToBeClickable(AddPage.compName_text(driver)));
        String backColour = EditPage.intDate_container(driver).getCssValue("background-color");
        Assert.assertEquals(backColour, "rgba(250, 229, 227, 1)");
    }

    @When("User updates to incorrect date format in Discontinued date validation blocks the save {string}")
    public void user_updates_to_incorrect_date_format_in_discontinued_date_validation_blocks_the_save(String DIS_DATE) {
        HomePage.compName_button(driver).click();
        wait.until(ExpectedConditions.elementToBeClickable(EditPage.compName_text(driver)));

        //remove and enter values
        EditPage.discontinued_date(driver).clear();
        EditPage.discontinued_date(driver).sendKeys(DIS_DATE);

        //submit values
        EditPage.save_button(driver).click();

        //Check field changes colour due to validation
        wait.until(ExpectedConditions.elementToBeClickable(AddPage.compName_text(driver)));
        String backColour = EditPage.disDate_container(driver).getCssValue("background-color");
        Assert.assertEquals(backColour, "rgba(250, 229, 227, 1)");
    }

    @When("Remove computers before test")
    public void remove_computers_before_test() {
        wait.until(ExpectedConditions.elementToBeClickable(HomePage.filter_button(driver)));

        String results = null;

        int i = 1;

        do {
            HomePage.filter_text(driver).sendKeys("TestComputer #");
            HomePage.filter_button(driver).click();
            results = HomePage.empty_row(driver).getText();
            if (results.equals("Nothing to display")){
            System.out.println("Nothing to delete");
            i = 0;
        } else {
                HomePage.compName_button(driver).click();
                wait.until(ExpectedConditions.elementToBeClickable(EditPage.compName_text(driver)));

                wait.until(ExpectedConditions.elementToBeClickable(EditPage.compName_text(driver)));
                EditPage.delete_button(driver).click();
                System.out.println("Removed");
            i = 1;
        }
        }
        while (i > 0);
}

}
