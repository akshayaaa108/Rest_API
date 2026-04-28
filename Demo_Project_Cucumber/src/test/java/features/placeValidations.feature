Feature: Validating Place API's

#  Scenario: Verify if Place is being successfully added using AddPlaceAPI
#    Given Add Place Payload
#    When user calls "AddPlaceAPI" with Post http request
#    Then the API call got success with status code 200
#    And "status" in response body is "OK"
#    And "scope" in response body is "APP"

  @smoke @SCRUM-3
  Scenario Outline: Verify if Place is being successfully added using AddPlaceAPI
    Given Add Place Payload "<name>" "<language>" "<address>"
    When user calls "AddPlaceAPI" with Post http request
    Then the API call got success with status code 200
    And "status" in response body is "OK"
    And "scope" in response body is "APP"
    Examples:
      |name   | language  | address           |
      |AAHouse| English   | World cross centre|
      |BBHouse|Spanish    | Sea cross centre  |



