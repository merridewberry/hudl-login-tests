@test

Feature: Logging in

  Background:
    Given I click 'Log in' button on the main page

  @positive @smoke
  Scenario: Logging in with valid credentials
    When I input valid email
    And I input valid password
    And I click 'Log in' button on the login page
    Then I'm logged in

  @negative @smoke
  Scenario: Logging in with incorrect password
    When I input valid email
    And I input the following password not-a-password
    And I click 'Log in' button on the login page
    Then I get an error message

  @positive
  Scenario: Clicking 'Need help' button
    When I click on 'Need help' button
    Then I see password reset form

  @positive
  Scenario: Requesting password reset
    When I input valid email
    And I click on 'Need help' button
    And I click 'Send Password Reset' button
    Then I see the message asking me to check my e-mail

  @positive
  Scenario Outline: Requesting password reset with valid email
    When I input the following email <email>
    And I click on 'Need help' button
    And I click 'Send Password Reset' button
    Then I get an error message saying there's no such user

    Examples:
      | email                                                                      |
      | with.dot@email.com                                                         |
      | with-dash@email.com                                                        |
      | xn--punycode@-86g3lk1b2d.xn--p1ai                                          |
      | localpartequalto64characters123456789012345678901234567890123456@email.com |
      | 1@z.com                                                                    |
      | with-non-english-domain@почта.рф                                           |

  @positive
  Scenario: Returning to login screen after pressing 'Back' button
    When I click on 'Need help' button
    And I click on 'Back' button
    Then I return to login screen

#    In case it's not clear - this test checks that nothing happens to the existing password after requesting reset
  @positive
  Scenario: Logging in after requesting password reset
    When I input valid email
    And I click on 'Need help' button
    And I click 'Send Password Reset' button
    And I click on 'Back' button
    And I input valid password
    And I click 'Log in' button on the login page
    Then I'm logged in

  @positive
  Scenario: Logging in with 'Remember me' checkbox ticked
    When I input valid email
    And I input valid password
    And I tick 'Remember me' checkbox
    And I click 'Log in' button on the login page
    And I'm logged in
    Then I receive cookie with expiry date

  @positive
  Scenario: Logging in without 'Remember me' checkbox ticked
    When I input valid email
    And I input valid password
    And I click 'Log in' button on the login page
    And I'm logged in
    Then I receive cookie without expiry date

  @positive
  Scenario: Logging in with email in upper case
    When I input valid email, but changed to upper case
    And I input valid password
    And I click 'Log in' button on the login page
    Then I'm logged in

  @negative
  Scenario: Logging in without password
    When I input valid email
    And I click 'Log in' button on the login page
    Then I get an error message

  @negative
  Scenario: Logging in without email
    And I input valid password
    And I click 'Log in' button on the login page
    Then I get an error message

  @negative
  Scenario: Logging in without credentials
    And I click 'Log in' button on the login page
    Then I get an error message

  @negative
  Scenario: Logging in with invalid credentials
    When I input the following email not-really@an.email
    And I input the following password not-a-password
    And I click 'Log in' button on the login page
    Then I get an error message

#    Note that the next two tests are supposed to check whether the password verification checks the whole password or just some limited amount of characters. Also I've used very long password, about 450 characters long.
  @negative
  Scenario: Logging in with password missing last symbols
    When I input valid email
    And I input valid password without last symbols
    And I click 'Log in' button on the login page
    Then I get an error message

  @negative
  Scenario: Logging in with password with extra symbols in the end
    When I input valid email
    And I input valid password with extra symbols
    And I click 'Log in' button on the login page
    Then I get an error message

#    Though I'd prefer to specify what result is expected when logging in with login with extra spaces
  @negative
  Scenario: Logging in with email that contains extra spaces in the beginning
    When I input valid email with extra spaces in the beginning
    And I input valid password
    And I click 'Log in' button on the login page
    Then I get an error message

  @negative
  Scenario: Logging in with email that contains extra spaces in the end
    When I input valid email with extra spaces in the end
    And I input valid password
    And I click 'Log in' button on the login page
    Then I get an error message

  @negative
  Scenario: Logging in with password that contains extra spaces in the beginning
    When I input valid email
    And I input valid password with extra spaces in the beginning
    And I click 'Log in' button on the login page
    Then I get an error message

  @negative
  Scenario: Logging in with password that contains extra spaces in the end
    When I input valid email
    And I input valid password with extra spaces in the end
    And I click 'Log in' button on the login page
    Then I get an error message

#    Given the password in not in upper|lower case already
  @negative
  Scenario: Logging in with password in upper case
    When I input valid email
    And I input valid password, but changed to upper case
    And I click 'Log in' button on the login page
    Then I get an error message

  @negative
  Scenario: Logging in with password in lower case
    When I input valid email
    And I input valid password, but changed to lower case
    And I click 'Log in' button on the login page
    Then I get an error message

  @negative
  Scenario: Requesting password reset with incorrect email
    When I input the following email not-really@an.email
    And I click on 'Need help' button
    And I click 'Send Password Reset' button
    Then I get an error message saying there's no such user

  @negative
  Scenario Outline: Requesting password reset with invalid email
    When I input the following email <email>
    And I click on 'Need help' button
    And I click 'Send Password Reset' button
    Then I get an error message saying that isn't a valid email

    Examples:
      | email                                                                                         |
      | with-no-at.email.com                                                                          |
      | with@two@ats                                                                                  |
      | local-part-to-long-1234567890123456789012345678901234567890123456789012345678901234@email.com |
      | with-underscore@in_domain_part.com                                                            |
      | with space@email.com                                                                          |
