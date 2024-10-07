package com.nagarro.tests;

import io.restassured.RestAssured;
import io.restassured.config.EncoderConfig;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class JsonFileReader {

    public static void main(final String[] args) {

        final String ENVIRONMENT = "https://protech-qa-dev.aa-bts.com/titan/";
        final RequestSpecification reqSpec;

        reqSpec = RestAssured.given()
                .config(RestAssured.config().encoderConfig(EncoderConfig.encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false)))
                .baseUri(ENVIRONMENT).header("Host", "protech-qa-dev.aa-bts.com").header("User-Agent", "PostmanRuntime/7.28.4").header("Accept", "*/*")
                .header("Accept-Encoding", "gzip, deflate, br").header("Connection", "keep-alive").redirects().follow(false).relaxedHTTPSValidation().log()
                .all(); // Log request details for debugging

        final Response response = reqSpec.log().all().when().get("api/openings/389463/errorcheckrequest");
        System.out.println("Status code: " + response.getStatusCode());
        System.out.println("Response headers: " + response.getHeaders());
        System.out.println("Response body: " + response.getBody().asString());

    }
}
