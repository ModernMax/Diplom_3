package pageobjects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static clients.Urls.LOGIN_PAGE_URL;
import static clients.Urls.LOGIN_USER_URL;
import static clients.Urls.REGISTER_PAGE_URL;

public class LoginPage extends AbstractPage {
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    private static final By loginEnterButton = By.xpath(".//*[text()='Войти']");
    private static final By loginEmail = By.xpath(".//label[text() = 'Email']/../input[contains(@name, 'name')]");
    private static final By loginPassword = By.xpath(".//label[text() = 'Пароль']/../input[contains(@type, 'password')]");


    @Step("Нажать на кнопку Вход на странице Логина")
    public MainPage clickLoginEnterButton() {
        click(loginEnterButton);
        return new MainPage(driver);
    }

    @Step("Открыть страницу входа")
    public LoginPage openLoginPage() {
        openUrl(LOGIN_PAGE_URL);
        return this;
    }

    @Step("Ввести логин (Email)")
    public void sendLogin(String login) {
        click(loginEmail);
        sendKeys(loginEmail, login);
    }

    @Step("Ввести пароль (Password)")
    public void sendPassword(String password) {
        click(loginPassword);
        sendKeys(loginPassword, password);
    }

    @Step("Заполнить формы для Входа (Login)")
    public LoginPage fillAuthFormLoginPage(String login, String password) {
        sendLogin(login);
        sendPassword(password);
        return this;
    }
}