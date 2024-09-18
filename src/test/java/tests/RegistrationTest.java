package tests;
import clients.ApiClient;
import steps.UserSteps;
import tools.RandomUserGenerator;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pageobjects.MainPage;
import pageobjects.RegistrationPage;

import java.time.Duration;

public class RegistrationTest extends AbstractTest {
    @Override
    @Before
    @Step("setUp")
    public void setUp() {
        userSteps = new UserSteps(new ApiClient());
        createUserRequest = RandomUserGenerator.generateRandomUser();
        driver = getWebDriver(getWebDriverName());
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        driver.manage().window().maximize();

    }

    @Test
    @DisplayName("Регистрация пользователя")
    public void successfulUserRegistrationTest() {
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.openRegistrationPage()
                .fillRegistrationFields(createUserRequest.getName(), createUserRequest.getEmail(), createUserRequest.getPassword())
                .clickRegistrationButton()
                .fillAuthFormLoginPage(createUserRequest.getEmail(), createUserRequest.getPassword())
                .clickLoginEnterButton();
        MainPage mainPage = new MainPage(driver);
        Assert.assertTrue(mainPage.isAuthorized());
    }

    @Override
    @After
    @Step("Удалить пользователя и закрыть браузер")
    public void tearDown() {
        String accessToken = userSteps.getUserToken(createUserRequest.getEmail(), createUserRequest.getPassword());
        if (accessToken != null) {
            userSteps.deleteUser(accessToken);
        }
        driver.quit();
    }
}
