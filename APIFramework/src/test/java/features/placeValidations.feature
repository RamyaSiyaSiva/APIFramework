#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template

Feature: Validating Place APIs
@Addplace @Regression 
  Scenario Outline: Verify if Place is being successfully added using AddplaceAPI
    Given Add Place Payload with "<name>" "<language>" "<address>"
    When User calls "AddPlaceAPI" with "POST" http request
    Then the API call is success with status code 200
    And "status" in response body is "OK"
    And "scope" in response body is "APP"
    And verify placeid created maps to "<name>" using "GetPlaceAPI"
    
  Examples: 
  	|name    |language |address           |
  	|Siya Krishna S|German   |World Trade Centre| 
 	  |BBHouse |Spanish  |Mumbai            |
  	|AAHouse |German   |World Trade Centre| 
    |Siya Krishna S |Spanish  |MumbaiD      |
 @Deleteplace @Regression 
    Scenario: Verify if DeletePlace functionality is working
    
    Given DeletePlace Payload
    When User calls "DeletePlaceAPI" with "POST" http request
    Then the API call is success with status code 200
    And "status" in response body is "OK"
    
   # Scenario: Verify if GetPlace functionality is working
   #When User calls "GetPlaceAPI" with "GET" http request
   #Then the API call is success with status code 404
    
