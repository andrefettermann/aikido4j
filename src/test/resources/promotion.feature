Feature: Students promotion test

As a sensei
I want to check students requirements to take the promotion test
So that i can authorize a student to take the promotion test

Background:
	Given there are the following ranks
	| 6th Kyu | 0 | false | 5th Kyu |
	| 5th Kyu | 60 | true | 4th Kyu |

Scenario: student without enough practice time to take the test
	Given a student with the rank "6th Kyu"
	Given a student with the following practice times
	| 2020-01-10 | 6th Kyu | 1 |
	| 2020-01-12 | 6th Kyu | 2 |
	| 2020-01-14 | 5th Kyu | 1 |
	When i request to apply the student for the promotion test
	Then the student should not be allowed to take the promotion test

