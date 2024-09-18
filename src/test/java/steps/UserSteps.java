package steps;

import clients.ApiClient;
import dto.CreateUserRequest;
import dto.LoginUserRequest;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

public class UserSteps {
    private final ApiClient apiClient;

    public UserSteps(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    @Step("Создание пользователя")
    public ValidatableResponse createUser(String email, String password, String name) {
        CreateUserRequest createUserRequest = CreateUserRequest.builder()
                .email(email)
                .password(password)
                .name(name)
                .build();
        return apiClient.createUser(createUserRequest)
                .then();
    }


    @Step("Авторизация пользователя")
    public ValidatableResponse loginUser(String email, String password) {
        LoginUserRequest loginUserRequest = LoginUserRequest.builder()
                .email(email)
                .password(password)
                .build();
        return apiClient.loginUser(loginUserRequest)
                .then();
    }

    @Step("Получение токена")
    public String getUserToken(String email, String password) {
        return loginUser(email, password)
                .extract()
                .path("accessToken");
    }

    @Step("Удаление пользователя")
    public ValidatableResponse deleteUser(String accessToken) {
        return apiClient.deleteUser(accessToken)
                .then();
    }
}