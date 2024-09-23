package tests;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.Test;
import pageobjects.MainPage;
import pageobjects.RegistrationPage;

public class LoginTests extends AbstractTest {
    @Test
    @DisplayName("Логина пользователя, кнопка 'Войти в аккаунт'")
    public void logInAccountButtonTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.openMainPage()
                .clickAccountButton()
                .fillAuthFormLoginPage(createUserRequest.getEmail(), createUserRequest.getPassword())
                .clickLoginEnterButton();
        Assert.assertTrue(mainPage.isAuthorized());
    }

    //
    @Test
    @DisplayName("Логина пользователя, кнопка  'Личный кабинет'")
    public void logInPersonalProfileButton() {
        MainPage mainPage = new MainPage(driver);
        mainPage.openMainPage()
                .clickProfileButtonNotAuthorized()
                .fillAuthFormLoginPage(createUserRequest.getEmail(), createUserRequest.getPassword())
                .clickLoginEnterButton();
        Assert.assertTrue(mainPage.isAuthorized());
    }

    @Test
    @DisplayName("Логин, переход со страницы регистрации")
    public void loginFromRegistrationPage() {
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.openRegistrationPage()
                .clickEnterButtonOnRegistrationPage()
                .fillAuthFormLoginPage(createUserRequest.getEmail(), createUserRequest.getPassword())
                .clickLoginEnterButton();
        MainPage mainPage = new MainPage(driver);
        Assert.assertTrue(mainPage.isAuthorized());
    }

}
