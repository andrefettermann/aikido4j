Feature: Read ranks

As a sensei
I want to read ranks
So that i can manage the ranks

Scenario: list all ranks
	Given there are the following ranks
	| 6th Kyu | 0 | false | 5th Kyu |
	| 5th Kyu | 60 | true | 4th Kyu |
	When i request to list all ranks
	Then all ranks should be listed
