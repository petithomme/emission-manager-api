package org.right.hibernate.orm.panache

import io.restassured.RestAssured.given
import org.junit.jupiter.api.Test
import io.quarkus.test.junit.QuarkusTest

import org.right.hibernate.orm.panache.Company


@QuarkusTest
class CompaniesEndpointTest {

    @Test
    fun `Check basic paths`() {

        given()
            .`when`()
            .get("/companies")
            .then()
            .statusCode(200)

        val c = Company("test2", "A2.12.12", 2.5f, 5.4f, 1.4f, 2.3f)
        val er = Company("test2", "Q2.WER", 2.5f, 5.4f, 1.4f, 2.3f)

        given()
                .body(c)
                .header("content-type", "application/json")
                .header("Accept", "application/json")
                .`when`()
                .post("/companies")
                .then()
                .statusCode(201)

        given()
                .body(er)
                .header("content-type", "application/json")
                .header("Accept", "application/json")
                .`when`()
                .post("/companies")
                .then()
                .statusCode(404)

        given()
                .`when`().delete("/companies/1")
                .then()
                .statusCode(200)

        given()
                .`when`().delete("/companies/1")
                .then()
                .statusCode(404)

    }
}