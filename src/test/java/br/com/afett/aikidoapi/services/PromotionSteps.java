package br.com.afett.aikidoapi.services;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.com.afett.aikido4j.entities.PersonType;
import br.com.afett.aikido4j.entities.PracticeTime;
import br.com.afett.aikido4j.entities.PromotionTest;
import br.com.afett.aikido4j.entities.Student;
import br.com.afett.aikido4j.services.PromotionTestService;
import br.com.afett.aikido4j.services.RankService;
import br.com.afett.aikido4j.services.StudentService;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class PromotionSteps {

	RankService rankService;
	PromotionTestService promotionTestService;
	StudentService studentService;

	Student student;
	List<PersonType> studentPersonTypes = new ArrayList<>();

	PromotionTest promotionTest;

	String message;
	int applicationId;

	@Before
	public void setUp(Scenario scenario) {
		promotionTestService = new PromotionTestService();
		rankService = RankService.getInstance();
		studentService = new StudentService();
	}

	@After
	public void tearDown(Scenario scenario) {
	}

	// ----------------
	// GIVEN
	// ----------------
	@Given("the user did not inform a promotion test")
	public void givenTheSenseiDidNotInformAPromotionTest() {
		promotionTest = null;
	}

	@Given("the user informed a promotion test")
	public void givenTheUserInformedAPromotionTest() {
		promotionTest = promotionTestService.read("1st Exam");
	}

	@Given("the user informed a promotion test without ranks registered")
	public void givenTheUserInformedAPromotionTestWithoutRanks() {
		promotionTest = promotionTestService.read("1st Exam");
	}

	@Given("the user informed a promotion test with ranks registered")
	public void givenTheUserInformedAPromotionTestWithRanks() {
		promotionTest = promotionTestService.read("2nd Exam");
	}

	@Given("the user did not inform the student")
	public void givenTheUserDidNotInformStudent() {
		student = null;
	}
	
	@Given("the user informed a student")
	public void givenTheUserInformedAStudent() {
		student = studentService.read("Student");
	}

	@Given("the user informed a student without a rank registered for the promotion test")
	public void givenTheUserInformedAStudentWithouARankRegisteredForThePromotionTest() {
		student = studentService.read("Student");
	}

	@Given("the user informed a student with a rank registered for the promotion test")
	public void givenTheUserInformedAStudentWithARankRegisteredForThePromotionTest() {
		student = studentService.read("2nd Student");
	}
	
	@Given("i registered the student rank for the promotion test")
	public void givenIRegisteredTheStudentRankForThePromotionTest() {
		promotionTest.addRank(rankService.find("5th Kyu"));
	}
	
	@Given("the student do not have the required practice time for the rank")
	public void givenTheStudentDoNotHaveTheRequiredPracticeTime() {
		PracticeTime practiceTime = 
				new PracticeTime(LocalDate.of(2022, 1, 1), "6th Kyu", 50L);
		student.addPracticeTime(practiceTime);
		
		practiceTime = 
				new PracticeTime(LocalDate.of(2022, 1, 1), "6th Kyu", 9L);
		student.addPracticeTime(practiceTime);
		
		practiceTime = 
				new PracticeTime(LocalDate.of(2022, 1, 1), "5th Kyu", 10L);
		student.addPracticeTime(practiceTime);
	}

	@Given("the student have the exact required practice time for the rank")
	public void givenTheStudentHaveTheExactRequiredPracticeTime() {
		PracticeTime practiceTime = 
				new PracticeTime(LocalDate.of(2022, 1, 1), "6th Kyu", 50L);
		student.addPracticeTime(practiceTime);
		
		practiceTime = 
				new PracticeTime(LocalDate.of(2022, 1, 1), "6th Kyu", 10L);
		student.addPracticeTime(practiceTime);
		
		practiceTime = 
				new PracticeTime(LocalDate.of(2022, 1, 1), "5th Kyu", 10L);
		student.addPracticeTime(practiceTime);
	}

	@Given("the student have more than the required practice time for the rank")
	public void givenTheStudentHaveMoreThanTheRequiredPracticeTime() {
		PracticeTime practiceTime = 
				new PracticeTime(LocalDate.of(2022, 1, 1), "6th Kyu", 50L);
		student.addPracticeTime(practiceTime);
		
		practiceTime = 
				new PracticeTime(LocalDate.of(2022, 1, 1), "6th Kyu", 11L);
		student.addPracticeTime(practiceTime);
		
		practiceTime = 
				new PracticeTime(LocalDate.of(2022, 1, 1), "5th Kyu", 10L);
		student.addPracticeTime(practiceTime);
	}

	// ---------------
	// WHEN
	// ---------------

	@When("the user submit an application")
	public void whenTheUserSubmitAnApplication() {
		try {
		    applicationId =	promotionTestService.apply(promotionTest, student);
		} catch (Exception e) {
			message = e.getMessage();
		}
	}
	
	// ---------------
	// THEN
	// ---------------

	@Then("should be informed that the promotion test is mandatory")
	public void thenShouldBeInformedThatThePromotionTestIsMandatory() {
		assertEquals(message, PromotionTestService.PROMOTION_TEST_MANDATORY);
	}

	@Then("should be informed that the student is mandatory")
	public void thenShouldBeInformedThatTheStudentIsMandatory() {
		assertEquals(message, PromotionTestService.STUDENT_MANDATORY);
	}

	@Then("should be informed that the ranks are mandatory")
	public void thenShouldBeInformedThatTheRanksAreMandatory() {
		assertEquals(PromotionTestService.RANKS_MANDATORY, message);
	}

	@Then("should be informed that the student rank is not registered for the promotion test")
	public void thenShouldBeInformedThatTheStudentRankNotRegisteredForTest() {
		assertEquals(message, PromotionTestService.STUDENT_RANK_NOT_REGISTERED);
	}

	@Then("should be informed that the student do not have the required practice time")
	public void thenShouldBeInformedThatTheStudentDoNotHaveTHeRequiredPracticeTime() {
		assertEquals(message, PromotionTestService.STUDENT_NOT_ENOUGH_TIME);
	}

	@Then("the student application should be accepted")
	public void thenApplicationShouldBeAccepted() {
		assertNull(message);
		assertTrue(applicationId>0);
	}
}
