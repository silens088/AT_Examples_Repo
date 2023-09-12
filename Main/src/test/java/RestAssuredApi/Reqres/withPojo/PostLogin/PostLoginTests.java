package RestAssuredApi.Reqres.withPojo.PostLogin;

import RestAssuredApi.Reqres.withPojo.CustomListener;
import RestAssuredApi.Reqres.withPojo.Specification;
import io.restassured.RestAssured;
import jdk.jfr.Description;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

@Listeners(CustomListener.class)
public class PostLoginTests {

    private final String URL_MAIN = "https://reqres.in/";
    private final String LOGIN_API = "/api/login";

    @Test
    @Description("Тест проверяет создание нового пользователя")
    public void createNewUserTest() {
        Specification.InstallSpecification(Specification.requestSpec(URL_MAIN), Specification.responseSpecOK200());
        PostLoginRequest request = new PostLoginRequest();
        request.setEmail("eve.holt@reqres.in");
        request.setPassword("cityslicka");

//        String actualTokenData = "QpwL5tke4Pnpja7X4";
        String actualTokenData = "QpwL5sdfnpja7X4";

        PostLoginResponse response = given()
                .body(request)
                .when()
                .post(LOGIN_API)
                .then().log().all()
                .extract().as(PostLoginResponse.class);

        Assert.assertEquals(actualTokenData, response.getToken());
    }
}