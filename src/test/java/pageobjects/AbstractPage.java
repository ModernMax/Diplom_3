package pageobjects;

import java.time.Duration;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AbstractPage {
    protected final WebDriver driver;

    private static final By goToConstructor = By.xpath(".//p[text()='Конструктор']");
    private static final By orderListButton = By.xpath(".//p[text()='Лента Заказов']");
    private static final By profileManeButton = By.xpath(".//p[text()='Личный Кабинет']");


    public AbstractPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Переход по адресу")
    public void openUrl(String url) {
        driver.get(url);
    }

    @Step("Нажать на элемент, согласно локатору")
    public void click(By elementLocator) {
        waitElementToBeVisible(elementLocator);
        waitElementToBeClickable(elementLocator);
        try {
            driver.findElement(elementLocator).click();
        } catch (Exception e) {
            System.out.println("Не удалось нажать посредством стандартного метода WebElement.click()" + e.getMessage());
            try {
                clickViaJS(elementLocator);
            } catch (Exception eJS) {
                System.out.println("Не удалось нажать посредством JS Executor" + eJS.getMessage());
            }
        }
    }

    @Step("Ввод данных в поле")
    public void sendKeys(By elementLocator, String inputText) {
        waitElementToBeVisible(elementLocator);
        waitElementToBeClickable(elementLocator);
        WebElement nameHtmlElement = driver.findElement(elementLocator);
        boolean isNameHtmlElementStale = ExpectedConditions.stalenessOf(nameHtmlElement).apply(driver);
        if (isNameHtmlElementStale) {
            nameHtmlElement = driver.findElement(elementLocator);
        }

        try {
            nameHtmlElement.sendKeys(inputText);
        } catch (Exception e) {
            System.out.println("Не удалось ввести данные посредством стандартного метода WebElement.sendKeys()" + e.getMessage());
            try {
                sendKeysViaJS(elementLocator, inputText);
            } catch (Exception eJS) {
                System.out.println("Не ввести данные посредством JS Executor" + eJS.getMessage());
            }
        }
    }

    @Step("Ожидание доступности элемента")
    public void waitElementToBeClickable(By element) {
        new WebDriverWait(driver, Duration.ofSeconds(30))
                .until(ExpectedConditions.elementToBeClickable(element));
    }

    @Step("Ожидание перехода по адресу")
    public void waitUrlToBe(String url) {
        new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(ExpectedConditions.urlToBe(url));
    }

    @Step("Ожидание отображение элемента")
    public void waitElementToBeVisible(By elementLocator) {
        new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(ExpectedConditions.visibilityOfElementLocated(elementLocator));
    }

    @Step("Ожидание пока элемент не пропадет")
    public void waitUntilNotVisibilityOfElementLocated(WebElement element) {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.invisibilityOf(element));
    }

    public void clickViaJS(By element) {
        WebElement ele = driver.findElement(element);
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", ele);
    }

    public void sendKeysViaJS(By elementLocator, String inputText) {
        WebElement ele = driver.findElement(elementLocator);
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript(String.format("arguments[0].setAttribute('value', '%s')", inputText), ele);
    }


    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
}
