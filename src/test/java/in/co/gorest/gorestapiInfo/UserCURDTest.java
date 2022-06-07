package in.co.gorest.gorestapiInfo;


import in.co.gorest.testbase.TestBase;
import in.co.gorest.utils.TestUtils;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;
import java.util.List;

import static org.hamcrest.Matchers.hasValue;

@RunWith(SerenityRunner.class)
public class UserCURDTest extends TestBase {
    static String name = "Tenali Ramakrishna" + TestUtils.getRandomValue();
    static String gender = "female";
    static String email = "tenali.ramakrishna" + TestUtils.getRandomValue() + "@email.com";
    static String status = "active";
    static int userID;

    @Steps
    UserSteps usersSteps;


    @Test
    public void test001() {
        ValidatableResponse response = usersSteps.createUser(name, gender, email, status);
        response.log().all().statusCode(201);
        userID = response.log().all().extract().path("id");
        System.out.println(userID);
    }


    @Test
    public void test002() {
        ValidatableResponse response = usersSteps.getUserByID(userID);
        response.log().all().statusCode(200);
        HashMap<?, ?> userMap = response.log().all().extract().path("");
        Assert.assertThat(userMap, hasValue(name));
    }


    @Test
    public void test003() {
        name = name + "_updated";
        email = "tenali.ramakrishna" + TestUtils.getRandomValue() + "@email.com";
        ValidatableResponse response = usersSteps.updateUser(userID, name, gender, email, status);
        response.log().all().statusCode(200);
        HashMap<?, ?> userMap = response.log().all().extract().path("");
        Assert.assertThat(userMap, hasValue(name));
    }
    @Test
    public void test004() {
        ValidatableResponse response = usersSteps.deleteUser(userID);
        response.log().all().statusCode(204);
        ValidatableResponse response1 = usersSteps.getUserByID(userID);
        response1.log().all().statusCode(404);

    }
    @Test
    public void test005() {
        ValidatableResponse response = usersSteps.getAllUsers();
        List<?> totalRecord = response.log().all().extract().path("");
        Assert.assertEquals(20, totalRecord.size());
    }
    @Test
    public void test006() {
        ValidatableResponse response = usersSteps.getAllUsers();
        List<?> totalRecord = response.log().all().extract().path("findAll{it.id==3838}.name");
        Assert.assertEquals("Chaturbhuj Nehru DVM", totalRecord.get(0));
    }
    @Test
    public void test007() {
        ValidatableResponse response = usersSteps.getAllUsers();
        List<?> totalRecord = response.log().all().extract().path("findAll{it.id==3838}.email");
        Assert.assertEquals("dvm_chaturbhuj_nehru@pagac.name", totalRecord.get(0));
    }
    @Test
    public void test008() {
        ValidatableResponse response = usersSteps.getAllUsers();
        List<String> status = response.log().all().extract().path("status");
        for (String data : status) {
            if (data.equalsIgnoreCase("active")) {
                Assert.assertEquals("active", data);
            }
        }
    }

    @Test
    public void test009() {
        ValidatableResponse response = usersSteps.getAllUsers();
        List<String> femaleGender = response.log().all().extract().path("findAll{it.id==3829}.gender");
        Assert.assertEquals("female", femaleGender.get(0));
    }

    @Test
    public void test010() {
        ValidatableResponse response = usersSteps.getAllUsers();
        List<String> femaleGender = response.log().all().extract().path("findAll{it.id==3819}.gender");
        Assert.assertEquals("male", femaleGender.get(0));
    }

}
