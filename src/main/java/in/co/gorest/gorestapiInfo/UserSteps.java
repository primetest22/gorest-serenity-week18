package in.co.gorest.gorestapiInfo;

import in.co.gorest.constants.EndPoints;
import in.co.gorest.model.GoRestPojo;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

public class UserSteps {

    @Step
    public ValidatableResponse createUser(String name,String gender, String email,String status) {
        GoRestPojo goRestPojo = new GoRestPojo();
        goRestPojo.setName(name);
        goRestPojo.setGender(gender);
        goRestPojo.setEmail(email);
        goRestPojo.setStatus(status);

        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer 75c987adac5bd1820e58fb137137e2474501428068d1ec0dd68ae05bcff564c9")
                .body(goRestPojo)
                .when()
                .post(EndPoints.CREATE_USER)
                .then();
    }

    @Step
    public ValidatableResponse getUserByID(int userID) {

        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer 75c987adac5bd1820e58fb137137e2474501428068d1ec0dd68ae05bcff564c9")
                .pathParam("userID", userID)
                .when()
                .get(EndPoints.UPDATE_USER_BY_ID)
                .then();
    }

    @Step
    public ValidatableResponse getAllUsers() {

        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer 75c987adac5bd1820e58fb137137e2474501428068d1ec0dd68ae05bcff564c9")
                .when()
                .get(EndPoints.GET_ALL_USER)
                .then();
    }

    @Step
    public ValidatableResponse updateUser(int userID, String name, String gender, String email,String status) {
        GoRestPojo goRestPojo = new GoRestPojo();
        goRestPojo.setName(name);
        goRestPojo.setGender(gender);
        goRestPojo.setEmail(email);
        goRestPojo.setStatus(status);

        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer 75c987adac5bd1820e58fb137137e2474501428068d1ec0dd68ae05bcff564c9")
                .body(goRestPojo)
                .pathParam("userID",userID)
                .when()
                .patch(EndPoints.UPDATE_USER_BY_ID)
                .then();
    }

    @Step("Delete User with ID:{0}")
    public ValidatableResponse deleteUser(int userID) {
        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer 75c987adac5bd1820e58fb137137e2474501428068d1ec0dd68ae05bcff564c9")
                .pathParam("userID",userID)
                .when()
                .delete(EndPoints.DELETE_USER_BY_ID)
                .then();
    }
}
