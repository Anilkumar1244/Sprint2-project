Feature: Create your account

  Scenario: User is creating the account
    Given user is on home page
    When user clicks login button
    And user click on create your account button
    And user enter the Phonenumber as "12534657600"
    And user click on continue button
    And user enter Otp
    And user enter the name as "Anil"
    And user enter the Email as "anilkumar@gmail.com"
    And user click on submit button
    Then User should be on Home page
