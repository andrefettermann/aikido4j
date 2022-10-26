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
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class PromotionSteps {

	RankService rankService;
	PromotionTestService promotionTestService;

	Student student;
	List<PersonType> studentPersonTypes = new ArrayList<>();

	PromotionTest promotionTest;

	String message;
	int applicationId;

	@Before
	public void setUp(Scenario scenario) {
		promotionTestService = new PromotionTestService();
		rankService = RankService.getInstance();
	}

	@After
	public void tearDown(Scenario scenario) {
	}

	// ----------------
	// GIVEN
	// ----------------
	@Given("i did not registered the promotion test data")
	public void givenIDidNotRegisteredThePromotionTestData() {
		promotionTest = null;
	}

	@Given("i registered the promotion test data")
	public void givenIRegisteredThePromotionData() {
		try {
			promotionTest = new PromotionTest("Test");
			promotionTestService.insert(promotionTest);
		} catch (Exception e) {
			fail();
		}
	}

	@Given("i did not inform the student")
	public void givenIDidNotInformStudent() {
		student = null;
	}
	
	@Given("i informed the student")
	public void givenIInformedTheStudent() {
		student = new Student("Student name"
				, rankService.find("6th Kyu"), studentPersonTypes);
	}

	@Given("i did not registered the ranks for the promotion test")
	public void givenIDidNotRegisteredTHeRanksForTheTest() {
		promotionTest = promotionTestService.read(0);
		promotionTest.getRanks().clear();
	}

	@Given("i registered the ranks for the promotion test")
	public void givenIRegisteredTheRanksForTheTest() {
		promotionTest = promotionTestService.read(0);
		promotionTest.addRank(rankService.find("1st Kyu"));
		promotionTest.addRank(rankService.find("Godan"));
	}

	@Given("i did not registered the student rank for the promotion test")
	public void givenIDidNotRegisteredTheStudentRankForThePromotionTest() {
		//promotionTest.addRank(rankService.find("5th Kyu"));
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

	@When("i submit an application")
	public void whenISubmitAnApplication() {
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
		assertEquals(message, PromotionTestService.RANKS_MANDATORY);
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
