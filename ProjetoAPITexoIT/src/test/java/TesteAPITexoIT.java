import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class TesteAPITexoIT {
    @Before
    public void setup() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
        Relatorio.getInstance(); // Inicia o relatório
    }

    @Test
    public void ValidarRequisicaoTipoGET() {
        Relatorio.startTest("Validar Requisição Tipo GET");
        given()
                .queryParam("name", "alias odio sit")
                .when()
                .get("/comments")
                .then()
                .statusCode(200);
                Relatorio.logPass("Requisição GET bem-sucedida");
    }

    @Test
    public void ValidarRequisicaoTipoPOST() {
        Relatorio.startTest("Validar Requisição Tipo POST");
        String Body = "{\"name\":\"Renan\",\"username\":\"rsilva\",\"email\":\"teste@teste.com.br\"}";
        given()
                .body(Body)
                .header("Content-Type", "application/json")
                .when()
                .post("/users")
                .then()
                .statusCode(201)
                .body("id", notNullValue());
        Relatorio.logPass("Requisição POST bem-sucedida");
    }

    @Test
    public void ValidarRequisicaoTipoPUT() {
        Relatorio.startTest("Validar Requisição Tipo PUT");
        String Body = "{\"email\":\"teste@teste.com\",\"end\":{\"geo\":{\"lat\":\"40.7128\",\"lng\":\"-74.0060\"}}}";
        given()
                .body(Body)
                .header("Content-Type", "application/json")
                .when()
                .put("/users/5")
                .then()
                .statusCode(200)
                .body("email", equalTo("teste@teste.com"))
                .body("end.geo.lat", equalTo("40.7128"))
                .body("end.geo.lng", equalTo("-74.0060"));
        Relatorio.logPass("Requisição PUT bem-sucedida");
    }

    @After
    public void tearDown() {
        Relatorio.endTest(); // Finaliza o relatório
    }
}
