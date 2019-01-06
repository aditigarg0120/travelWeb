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
Feature: Search for Cheapest Flight
  I want to search for the cheapest flight

Background: User navigates to website home page
    Given I am on the travelpage
    Then I should see "PHPTRAVELS | Travel Technology Partner"


Scenario Outline: Search for the cheapest flight
  When I click FLIGHTS
  And I select from source with "London City Arpt"
	And I select to destination with "Dubai Intl Arpt"
	And I select to Round Trip
	And I select departure date 2 weeks from today's date
	And I select return date 2 weeks from departure date
	And I select 2 Adult
	And I select 2 Child
	When I click SEARCH button
	And  I filter the first four Airlines
	And I click on BOOK NOW with the cheapest price
	Then I am taken to booking page
	
Examples:
     		|flightCarrier |
     		|Turkish Airlines|
				