Feature:QA Challenge feature

  @Test
  Scenario: App Builder access and customer data update
    Given user visits the website and accept the cookies
    And user clicks on Try App Builder
    When user input the credentials
    And launch the app builder
    And saves all customizations
    Then user is accessing the customers list
    And modifies the first name of "Max Mustermann"
    Then first name has been successfully modified to "Maximilian Mustermann"
