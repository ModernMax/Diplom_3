package tests;
import tools.RandomUserGenerator;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pageobjects.RegistrationPage;

import java.time.Duration;

import static clients.Urls.ERROR_MESSAGE_OF_WRONG_PASSWORD;
import static pageobjects.RegistrationPage.registerWrongPasswordMessageInRegisterPage;

public class ErrorMessageTests extends AbstractTest {
    @Override
    @Before
    @Step("setUp")
    public void setUp() {
        createUserRequest = RandomUserGenerator.generateRandomUser();
        driver = getWebDriver(getWebDriverName());
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        driver.manage().window().maximize();

    }

    @Test
    @DisplayName("Нельзя зарегистрироваться с паролем меньше 6 символов")
    public void registrationWithShortPasswordTest() {
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.openRegistrationPage()
                .enterRegisterName(createUserRequest.getName())
                .enterRegisterEmail(createUserRequest.getEmail())
                .enterRegisterPassword("123")
                .clickRegistrationButton();
        Assert.assertEquals("Ошибка не появилась", ERROR_MESSAGE_OF_WRONG_PASSWORD,
                driver.findElement(registerWrongPasswordMessageInRegisterPage).getText());
    }

    @Override
    @After
    @Step("Закрыть браузер")
    public void tearDown() {
        driver.quit();
    }
}
