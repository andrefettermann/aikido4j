Feature: Students promotion test

As a sensei
I want to check students requirements to take the promotion test
So that i can authorize a student to take the promotion test

Background:
	Given there are the following ranks
	| 6th Kyu | 0   | false | 5th Kyu  |
	| 5th Kyu | 60  | true  | 4th Kyu  |
	| 4th Kyu | 75  | true  | 3rd Kyu  |
	| 3rd Kyu | 90  | true  | 2nd Kyu  |
	| 2nd Kyu | 110 | true  | 1st Kyu  |
	| 1st Kyu | 160 | true  | Shodan   |
	| Shodan  | 200 | true  | Nidan    |
	| Nidan   | 300 | true  | Sandan   |
	| Sandan  | 400 | true  | Yondan   |
	| Yondan  | 500 | true  | Godan    |
	| Godan   | 600 | false |          |

Scenario: promotion test not informed
	 	Given i did not registered the promotion test data
	 	When i submit an application
	 	Then should be informed that the promotion test is mandatory

Scenario: student not informed
	 	Given i registered the promotion test data
	 	And i did not inform the student
	 	When i submit an application
	 	Then should be informed that the student is mandatory
	 
Scenario: ranks for promotion not informed
		Given i registered the promotion test data
		And i did not registered the ranks for the promotion test
		And i informed the student
	 	When i submit an application
	 	Then should be informed that the ranks are mandatory
	
Scenario: student with a rank not registered for the promotion test
		Given i registered the promotion test data
		And i registered the ranks for the promotion test
		And i informed the student
		And i did not registered the student rank for the promotion test
	 	When i submit an application
	 	Then should be informed that the student rank is not registered for the promotion test
	
Scenario: student without required practice test
		Given i registered the promotion test data
		And i registered the ranks for the promotion test
		And i informed the student
		And i registered the student rank for the promotion test
		And the student do not have the required practice time for the rank
	 	When i submit an application
	 	Then should be informed that the student do not have the required practice time

Scenario: student with exact required practice test
		Given i registered the promotion test data
		And i registered the ranks for the promotion test
		And i informed the student
		And i registered the student rank for the promotion test
		And the student have the exact required practice time for the rank
	 	When i submit an application
	 	Then the student application should be accepted

Scenario: student with more than required practice test
		Given i registered the promotion test data
		And i registered the ranks for the promotion test
		And i informed the student
		And i registered the student rank for the promotion test
		And the student have more than the required practice time for the rank
	 	When i submit an application
	 	Then the student application should be accepted