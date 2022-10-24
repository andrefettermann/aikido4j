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
	boolean insertRequested;
	boolean listAllRanksRequested;
	
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
		rank.setRankName(name);
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
		rank.setNextRank(nextRank);
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
			 rank.setNextRank(columns.get(3));
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
		insertRequested = true;
	}

	@When("i request to list all ranks")
	public void whenIRequestToListAllRanks() {
		listAllRanksRequested = true;
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
		try {
			if (insertRequested) rankService.insert(rank);
			assertNotNull(rankService.find(rank.getRankName()));
		} catch (Exception e) {
			fail();
		}
	}
	
	@Then("all ranks should be listed")
	public void thenAllRanksShouldBeListed() {
		assertEquals(2, rankService.list().size());
	}
}
