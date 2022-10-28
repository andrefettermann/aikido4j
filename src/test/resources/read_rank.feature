Feature: Read ranks

As a sensei
I want to read ranks
So that i can manage the ranks

Scenario: list all ranks
	Given there are ranks registered
	When i request to list all ranks
	Then all ranks should be listed
