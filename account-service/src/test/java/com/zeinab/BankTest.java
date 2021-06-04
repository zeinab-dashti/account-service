package com.zeinab;

import com.zeinab.core.dto.TransactionDTO;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class BankTest {

    @LocalServerPort
    private int port;

    @BeforeEach
    public void setUp() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = port;
        RestAssured.defaultParser = Parser.JSON;
    }

    @Test
    void whenGetAccountsValidCustomerId_ThenReturnListWith200() {
        given().contentType(MediaType.APPLICATION_JSON_VALUE)
                .get("/customers/1/accounts")
                .then()
                .assertThat()
                .body("size()", equalTo(2))
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    void whenGetAccountsInValidCustomerId_ThenReturn404() {
        String invalidCustomerID = "1578935";
        given().contentType(MediaType.APPLICATION_JSON_VALUE)
                .get("/customers/" + invalidCustomerID + "/accounts")
                .then()
                .assertThat()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }

    @Test
    void whenGetTransactionsValidAccountId_ThenReturnListWith200() {
        TransactionDTO[] transactions =
                given().contentType(MediaType.APPLICATION_JSON_VALUE)
                        .get("/accounts/1/transactions")
                        .then()
                        .statusCode(HttpStatus.OK.value())
                        .extract()
                        .as(TransactionDTO[].class);

        Assertions.assertEquals(transactions.length, 5);
        Assertions.assertEquals(new BigDecimal("2000.00"), transactions[0].getBalanceAfter());
        Assertions.assertEquals(new BigDecimal("8000.00"), transactions[1].getBalanceAfter());
        Assertions.assertEquals(new BigDecimal("4000.00"), transactions[2].getBalanceAfter());
        Assertions.assertEquals(new BigDecimal("12000.00"), transactions[3].getBalanceAfter());
        Assertions.assertEquals(new BigDecimal("9000.00"), transactions[4].getBalanceAfter());
    }

    @Test
    void whenGetTransactionsInvalidAccountId_ThenReturn404() {
        String invalidAccountID = "1578935";
        given().contentType(MediaType.APPLICATION_JSON_VALUE)
                .get("/accounts/" + invalidAccountID + "/transactions")
                .then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }
}