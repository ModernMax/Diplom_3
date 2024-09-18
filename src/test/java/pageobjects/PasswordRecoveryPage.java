package pageobjects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static clients.Urls.RECOVERY_PASSWORD_URL;

public class PasswordRecoveryPage extends AbstractPage {

    private static final By enterButtonOnRecoverPage = By.xpath(".//a[text()='Войти']");

    public PasswordRecoveryPage(WebDriver driver) {
        super(driver);
    }

    @Step("Переход на страницу восстановления пароля")
    public PasswordRecoveryPage openRecoveryPage() {
        openUrl(RECOVERY_PASSWORD_URL);
        return this;
    }

    @Step("Нажать на кнопку Войти на странице восстановления пароля")
    public LoginPage clickEnterButtonOnRecoveryPage() {
        click(enterButtonOnRecoverPage);
        return new LoginPage(driver);
    }
}
