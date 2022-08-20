Feature: form authentication
  Scenario: successful login when correct username and password
    Given user is on Login Page
    When  user enters the "tomsmith" in username field
    And   user enters the "SuperSecretPassword!" in password field
    And   user clicks on Login button
    Then  user is entered into Secure Area
    And   user gets the success message "You logged into a secure area!"

  Scenario Outline: failed login when username and/or password is incorrect or empty
    Given user is on Login Page
    When  user enters the <username> in username field
    And   user enters the <password> in password field
    And   user clicks on Login button
    Then  user gets the fail message containing "invalid"
    And   user stays on the Login Page

    Examples:
      |username         |password                 |
      |"tomsmith"       |"wrongpassword"          |
      |"wrongusername"  |"SuperSecretPassword!"   |
      |""               |"SuperSecretPassword!"   |
      |"tomsmith"       |""                       |
      |""               |""                       |