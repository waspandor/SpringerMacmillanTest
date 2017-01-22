Feature: test

  Background:
    Given a new user account is created
    #This step would normally be broken down into several steps and have its own feature file


  @test @forgottenPassword
  Scenario: Forgotten password flow
    Given The user navigates to the BBC homepage
    And clicks on the My Account link
    And clicks the need help signing in link
    And clicks on the forgotten password link
    Then enters a valid email address
    And clicks the submit button
    And follows the link from within the received email
    # Then enters the new password

