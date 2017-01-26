Feature: Account services

  Scenario Outline: We have an account for given user.
    Given I send a GET request to <uri>
    Then the response code should be <code>
    And the response content type should be <contentType>
    And body should not be empty
    And response should have <key> and <value>

    Examples:
      | uri                                                |code|contentType     | key           | value |
      | http://localhost:8080/account?accountNumber=123455 |200 |application/json| accountNumber | 123455|

