package org.kds.cucumbertest;

import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.kds.cucumbertest.dto.Account;
import org.kds.cucumbertest.util.ConnectionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNotNull;

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

    @Then("^response should contain following$")
    public void response_should_contain_following(DataTable dataTable) throws Throwable {
        Account account = responseEntity.getBody();
        Map<String, String> dataMap = dataTable.asMap(String.class, String.class);
        dataMap.entrySet().stream().forEach(entry -> {
            if(entry.getKey().equals("accountNumber")) {
                assertThat(entry.getValue(), is(account.getAccountNumber()));
            }

            if(entry.getKey().equals("accountBalance")) {
                assertThat(Double.valueOf(entry.getValue()), is(account.getAccountBalance()));
            }

            if(entry.getKey().equals("bsb")) {
                assertThat(entry.getValue(), is(account.getBsb()));
            }

            if(entry.getKey().equals("accountHolderName")) {
                assertThat(entry.getValue(), is(account.getAccountHolderName()));
            }

        });
    }

    @Then("^response should contain following headers$")
    public void response_should_contain_following_headers(DataTable dataTable) throws Throwable {
        HttpHeaders headers = responseEntity.getHeaders();
        Map<String, String> dataMap = dataTable.asMap(String.class, String.class);
        dataMap.entrySet().stream().forEach( entry -> {

            List<String> headerList = headers.get(entry.getKey());
            assertThat(headerList, hasSize(1));
            assertThat(entry.getValue(), is(headerList.get(0)));
        });
    }
}
