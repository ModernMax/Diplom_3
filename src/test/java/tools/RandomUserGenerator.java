package tools;
import dto.CreateUserRequest;
import org.apache.commons.lang3.RandomStringUtils;

public class RandomUserGenerator {
    public static CreateUserRequest generateRandomUser() {
        return CreateUserRequest.builder()
                .email(RandomStringUtils.randomAlphanumeric(6) + "@ya.ru")
                .password(RandomStringUtils.randomAlphanumeric(7))
                .name(RandomStringUtils.randomAlphanumeric(10))
                .build();
    }
}
