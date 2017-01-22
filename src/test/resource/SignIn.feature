Feature: test

  Background:
    Given a new user account is created
    #This step would normally be broken down into several steps and have its own feature file

  @test @signIn
  Scenario: Login and verify that the correct user is logged in
    Given The user navigates to the BBC homepage
    And clicks on the My Account link
    And enters a valid username
    And enters a valid password
    And clicks the submit button
    And clicks on the My Account link
    And navigates to the user settings page
    Then verify the correct user is logged in



