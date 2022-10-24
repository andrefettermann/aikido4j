Feature: Save a rank

As a sensei
I want to save a rank
So that i can set students ranks

Scenario: Rank with no informatiom
	Given a new rank with no information
	When i request to save the new rank
	Then should be thrown a rank exception with this text "The rank is mandatory!"

Scenario: Rank without name
	Given i set the rank name as ""
	Given i set the rank required time as "10"
	Given i set the rank requires a test
	Given i set the rank next rank as "5th Kyu"
	When i request to save the new rank
	Then should be thrown a rank exception with this text "The rank name is mandatory!"

Scenario: Rank without required time
	Given i set the rank name as "6th Kyu"
	Given i set the rank required time as ""
	Given i set the rank requires a test
	Given i set the rank next rank as "5th Kyu"
	When i request to save the new rank
	Then should be thrown a rank exception with this text "The rank required time is mandatory!"

Scenario: Rank with next rank equals to rank name
	Given i set the rank name as "6th Kyu"
	Given i set the rank required time as "0"
	Given i set the rank requires a test
	Given i set the rank next rank as "6th Kyu"
	When i request to save the new rank
	Then should be thrown a rank exception with this text "The next rank cant be the same as current rank!"

Scenario: Rank saved successfully
	Given i set the rank name as "6th Kyu"
	Given i set the rank required time as "0"
	Given i set the rank requires a test
	Given i set the rank next rank as "5th Kyu"
	When i request to save the new rank
	Then should save the rank
	