package br.com.afett.aikidoapi.services;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import br.com.afett.aikido4j.entities.Rank;
import br.com.afett.aikido4j.services.RankService;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class RankSteps {

	Rank rank;
	RankService rankService;
	
	List<Rank> ranks;
	String message;
	
	@Before
	public void setUp(Scenario scenario) {
		rankService = RankService.getInstance();
		rank = new Rank(null, null, false);
	}

	@After
	public void tearDown(Scenario scenario) {
	}

	// ----------------
	// GIVEN
	// ----------------
	@Given("a new rank with no information")
	public void givenANewRankWithNoInformation() {
		rank = null;
	}
	
	@Given("i set the rank name as {string}")
	public void givenISetTheRankNameAs(String name) {
		rank.setName(name);
	}

	@Given("i set the rank required time as {string}")
	public void givenISetTheRankRequiredTimeAs(String requiredTime) {
		if (!requiredTime.isEmpty()) {
			rank.setRequiredTime(Long.parseLong(requiredTime));
		}
	}

	@Given("i set the rank requires a test")
	public void givenISetTheRankRequiresATest() {
		rank.setTestRequired(true);
	}

	@Given("i set the rank not requires a test")
	public void givenISetTheRankNotRequiresATest() {
		rank.setTestRequired(false);
	}

	@Given("i set the rank next rank as {string}")
	public void givenISetTheRankNextRankAs(String nextRank) {
		rank.setNextRankId(nextRank);
	}

	@Given("there are ranks registered")
	public void givenThereAreRanksRegistered() {
		
	}
	
	@Given("there are the following ranks")
	public void givenIHaveTheFollowingRanks(DataTable table) {
		 List<List<String>> rows = table.asLists(String.class);
		 for (List<String> columns:rows ) {
			 String rankName = columns.get(0);
			 Long rankRequiredTime = Long.parseLong(columns.get(1));
			 boolean rankkIsTestRequired = 
					 columns.get(2).equals("true")?true:false;
			 Rank rank = new Rank(
					 rankName, rankRequiredTime, rankkIsTestRequired);
			 rank.setNextRankId(columns.get(3));
			 rank.setId(rankName);
			 try {
				rankService.insert(rank);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }
	}
	
	// ---------------
	// WHEN
	// ---------------
	@When("i request to save the new rank")
	public void whenIRequestToSaveTheNewRank() {
		try {
			rankService.insert(rank);
		} catch (Exception e) {
			message = e.getMessage();
		}
	}

	@When("i request to list all ranks")
	public void whenIRequestToListAllRanks() {
		ranks = rankService.list();
	}
	
	// ---------------
	// THEN
	// ---------------

	@Then("should be thrown a rank exception with this text {string}")
	public void thenShouldBeThrownARankExceptionWithThisText(String message) {
		Exception exception =  assertThrows(Exception.class, () -> {
			rankService.insert(rank);
		});
		assertEquals(message, exception.getMessage());
	}
	
	@Then("should save the rank")
	public void thenShouldSaveTheRank() {
		assertNotNull(rankService.find(rank.getId()));
	}
	
	@Then("all ranks should be listed")
	public void thenAllRanksShouldBeListed() {
		assertEquals(12, ranks.size());
	}
}
