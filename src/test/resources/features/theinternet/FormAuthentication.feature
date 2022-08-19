Feature: form authentication
  Scenario: login with correct username and password and then logout
    Given user is on Login Page
    When  user enters the "tomsmith" in username field
    And   user enters the "SuperSecretPassword!" in password field
    And   user clicks on Login button
    Then  user is entered into Secure Area
    And   user gets the message "You logged into a secure area!"