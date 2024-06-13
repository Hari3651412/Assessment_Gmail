Feature: Compose Email based on Positive and Negative Scenarios

  Background:
    Given I am on the Gmail page
    And I enter the username
    And I enter the password

  @ComposeEmail_Positive
  Scenario: Compose Email with Positive Scenario
    When I click on the Compose button
    And I enter the email address
    And I enter the subject
    And I enter the body of the email
    And I click on the send button
    Then the email should be sent successfully

  @ComposeEmail_Negative
  Scenario: Compose Email with Negative Scenario
    When I click on the Compose button
    And I enter the body of the email
    And I click on the send button