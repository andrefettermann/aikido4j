Feature: Students promotion test

As a sensei
I want to check students requirements to take the promotion test
So that i can authorize a student to take the promotion test

@R1
Scenario: promotion test not informed
	 	Given the user did not inform a promotion test
	 	When the user submit an application
	 	Then should be informed that the promotion test is mandatory

@R2
Scenario: student not informed
	 	Given the user informed a promotion test
	 	And the user did not inform the student
	 	When the user submit an application
	 	Then should be informed that the student is mandatory

@R3	 
Scenario: ranks for promotion not informed
		Given the user informed a promotion test without ranks registered
		And the user informed a student
	 	When the user submit an application
	 	Then should be informed that the ranks are mandatory

@R4	
Scenario: student with a rank not registered for the promotion test
		Given the user informed a promotion test with ranks registered
		And the user informed a student
	 	When the user submit an application
	 	Then should be informed that the student rank is not registered for the promotion test
	
Scenario: student without required practice time
		Given the user informed a promotion test with ranks registered
		And the user informed a student with a rank registered for the promotion test
		And the student do not have the required practice time for the rank
	 	When the user submit an application
	 	Then should be informed that the student do not have the required practice time

Scenario: student with exact required practice test
		Given the user informed a promotion test with ranks registered
		And the user informed a student with a rank registered for the promotion test
		And the student have the exact required practice time for the rank
	 	When the user submit an application
	 	Then the student application should be accepted

Scenario: student with more than required practice test
		Given the user informed a promotion test with ranks registered
		And the user informed a student with a rank registered for the promotion test
		And the student have more than the required practice time for the rank
	 	When the user submit an application
	 	Then the student application should be accepted