Feature: Account services

  Scenario Outline: We have an account for given user.
    Given I send a GET request to <uri>
    Then the response code should be <code>
    And the response content type should be <contentType>
    And body should not be empty
    And response should contain following
      | accountNumber       | 123455     |
      | accountBalance      | 56789.0    |
      | bsb                 | 6754       |
      | accountHolderName   | Job Villes |
    And response should contain following headers
      |ResponseVersion     | 1.0     |

    Examples:
      | uri                                                |code|contentType     |
      | http://localhost:8080/account?accountNumber=123455 |200 |application/json|
