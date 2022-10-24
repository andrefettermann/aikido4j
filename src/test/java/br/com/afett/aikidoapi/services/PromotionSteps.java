package br.com.afett.aikidoapi.services;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.com.afett.aikido4j.entities.PersonType;
import br.com.afett.aikido4j.entities.PracticeTime;
import br.com.afett.aikido4j.entities.Rank;
import br.com.afett.aikido4j.entities.Student;
import br.com.afett.aikido4j.services.PromotionService;
import br.com.afett.aikido4j.services.RankService;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class PromotionSteps {

	Student student;
	PromotionService promotionService;
	RankService rankService;
	List<PersonType> personTypes = new ArrayList<>();
	boolean applicationRequired;
	
	@Before
	public void setUp(Scenario scenario) {
		promotionService = new PromotionService();
		rankService = RankService.getInstance();
	}

	@After
	public void tearDown(Scenario scenario) {
	}

	// ----------------
	// GIVEN
	// ----------------
	@Given("a student with the rank {string}")
	public void givenAStudentWithTheRank(String currentRank) {
		personTypes.add(PersonType.STUDENT);
		Rank rank = rankService.find(currentRank);
		student = new Student("Student 1", rank, personTypes);		
	}
	
	@Given("a student with the following practice times")
	public void givenAStudentWithPracticeTimeInTheCurrentRank(DataTable table) {
		 List<List<String>> rows = table.asLists(String.class);
		 for (List<String> columns:rows ) {
			 LocalDate classDate = LocalDate.parse(columns.get(0));
			 String rankId = columns.get(1);
			 Long classDuration = Long.parseLong(columns.get(2));
			 PracticeTime practiceTime = 
					 new PracticeTime(classDate, rankId, classDuration);
			 student.addPracticeTime(practiceTime);
		 }
	}
	
	// ---------------
	// WHEN
	// ---------------
	@When("i request to apply the student for the promotion test")
	public void whenIRequestToApplyTheStudentForThePromotionTest() {
		applicationRequired = true;
	}

	// ---------------
	// THEN
	// ---------------

	@Then("the student should not be allowed to take the promotion test")
	public void thenTheStudentShouldNotBeAllowedToTakeThePromotionTest() {
		try {
			assertFalse(promotionService.validateRequirements(student));
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

	@Then("the student should be allowed to take the promotion test")
	public void thenTheStudentShouldBeAllowedToTakeThePromotionTest() {
		try {
			assertTrue(promotionService.validateRequirements(student));
		} catch (Exception e) {
			fail();
		}
	}
}
