package tests;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.Test;
import pageobjects.MainPage;

import static clients.Urls.*;

public class TransitionTests extends AbstractTest {

    @Test
    @DisplayName("Перехода в личный кабинет не авторизированного пользователя")
    public void accessToAccountOfUnauthorizedUserTest() {
        MainPage mainPage = new MainPage(driver);
        String actualUrl = mainPage.openMainPage()
                .clickProfileButtonNotAuthorized()
                .getCurrentUrl();
        Assert.assertEquals("Открыта неверная страница", LOGIN_PAGE_URL, actualUrl);
    }

    @Test
    @DisplayName("Перехода в личный кабинет авторизированным пользователем")
    public void accessToAccountByAuthorizedUserTest() {
        MainPage mainPage = new MainPage(driver);
        String actualUrl = mainPage.openMainPage()
                .clickAccountButton()
                .fillAuthFormLoginPage(createUserRequest.getEmail(), createUserRequest.getPassword())
                .clickLoginEnterButton()
                .clickProfileButtonAuthorized()
                .getCurrentUrl();
        Assert.assertTrue("Открыта неверная страница",
                PROFILE_URL.equalsIgnoreCase(actualUrl) || ACCOUNT_URL.equalsIgnoreCase(actualUrl));
    }

    @Test
    @DisplayName("Переход на главную страницу")
    @Description("Проверка, что осуществляется переход на Главную страницу")
    public void goToMainPageTest() {
        MainPage mainPage = new MainPage(driver);
        String actualUrl = mainPage.openMainPage()
                .clickAccountButton()
                .fillAuthFormLoginPage(createUserRequest.getEmail(), createUserRequest.getPassword())
                .clickLoginEnterButton()
                .clickProfileButtonAuthorized()
                .clickOnLogo()
                .getCurrentUrl();
        Assert.assertEquals("Открыта неверная страница", BASE_URL, actualUrl);
    }

}
