package tests;

import java.time.Duration;

import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pageobjects.MainPage;

public class IngredientsTests extends AbstractTest {

    private MainPage mainPage;

    @Override
    @Before
    @Step("setUp")
    public void setUp() {
        driver = getWebDriver(getWebDriverName());
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        driver.manage().window().maximize();
        mainPage = new MainPage(driver);
    }

    @Test
    @DisplayName("Выбрать соус")
    public void chooseSauceTest() {
        mainPage.openMainPage()
                .clickMenuSauce();
        Assert.assertEquals("неверный тип ингредиента", "Соусы", mainPage.getTextFromSelectedMenu());
    }

    @Test
    @DisplayName("выбрать булочки")
    public void chooseBunsTest() {
        mainPage.openMainPage()
                .clickMenuSauce()
                .clickMenuBun();
        Assert.assertEquals("неверный тип ингредиента", "Булки", mainPage.getTextFromSelectedMenu());
    }

    @Test
    @DisplayName("выбрать начинку")
    public void chooseTheFillingTest() {
        mainPage.openMainPage()
                .clickMenuFillings();
        Assert.assertEquals("неверный тип ингредиента", "Начинки", mainPage.getTextFromSelectedMenu());
    }

    @Override
    @After
    @Step("Закрыть браузер")
    public void tearDown() {
        driver.quit();
    }
}
