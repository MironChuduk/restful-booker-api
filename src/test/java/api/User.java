package api;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;

public class User {
    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User() {
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Token createToken(String username, String password) {
        User user = new User(username, password);
        return given()
                .contentType(ContentType.JSON)
                .body(user)
                .when()
                .post("https://restful-booker.herokuapp.com/auth")
                .then()
                .extract().as(Token.class);
    }
}
