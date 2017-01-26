package org.kds.cucumbertest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertNotNull;

import org.junit.Assert;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.kds.cucumbertest.dto.Account;
import org.kds.cucumbertest.util.ConnectionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * Created by KDS on 1/26/2017.
 */
public class AccountServicesStepDefs extends SpringIntegrationTest {

    @Autowired
    ConnectionManager connectionManager;

    private ResponseEntity<Account> responseEntity;

    @Given("^I send a GET request to (.*)$")
    public void i_send_a_GET_request_to_http_localhost_account_accountNumber(String url) throws Throwable {
        RestTemplate restTemplate = connectionManager.restTemplate();
        responseEntity = restTemplate.getForEntity(url, Account.class);
    }

    @Then("^the response code should be (\\d+)$")
    public void the_response_code_should_be(int responseCode) throws Throwable {
        assertThat(responseCode, is(responseEntity.getStatusCode().value()));
    }

    @Then("^the response content type should be (.*)$")
    public void the_response_content_type_should_be(String contentType) throws Throwable {
        assertThat(contentType, is(responseEntity.getHeaders().getContentType().toString()));
    }

    @Then("^body should not be empty$")
    public void body_should_not_be_empty() throws Throwable {
        assertNotNull(responseEntity.getBody());
    }

    @Then("^response should have (.*) and (.*)$")
    public void response_should_have_accountNumber_and(String key, String value) throws Throwable {
        Account body = responseEntity.getBody();
        assertThat(value, is(body.getAccountNumber()));
    }
}
