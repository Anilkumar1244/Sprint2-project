Feature: Login feature

  Background: 
    Given user is on home page
    When user click login button

  Scenario: Login with valid credentials
    And user enters ValidPhonenumber as "12534657600"
    And user clicks on Login button
    Then user should be on OTP page
    And user enter the Otp
    And user enter the name as "Anil"
    And user enter the Email as "anilkumar@gmail.com"
    And user clicks on verify otp button
    Then User should be on Home page

  Scenario: Login with invalid credentials
    And user enters Invalidphonenumber "0000000000"
    And user clicks on Login button
    Then Dispaly the error message

  Scenario: Login with invalid email id
    And user clicks on continue with email button
    And user enters Invalidemailid as "anilkumar28p06@gmail"
    And user click on Login button
    Then Error message should be displayed
