package br.com.afett.aikidoapi.services;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.afett.aikido4j.entities.PersonType;
import br.com.afett.aikido4j.entities.PracticeTime;
import br.com.afett.aikido4j.entities.PromotionTest;
import br.com.afett.aikido4j.entities.Rank;
import br.com.afett.aikido4j.entities.Student;
import br.com.afett.aikido4j.services.PromotionTestService;
import br.com.afett.aikido4j.services.RankService;

class PromotionTestServiceStudentApplicationTest {
	
	RankService rankService;
	PromotionTestService promotionService;
	Student student;
	List<PersonType> studentPersonTypes = new ArrayList<>();
	PromotionTest promotionTest;
	
	@BeforeEach
	public void setUp() {	
		promotionService = new PromotionTestService();
		rankService = RankService.getInstance();
		
		try {
			promotionTest = new PromotionTest("Test");
			promotionService.insert(promotionTest);
			
			Rank rank = new Rank("6th Kyu", 0L, false);
			rank.setNextRankId("5th Kyu");
			rank.setId(rank.getName());
			rankService.insert(rank);

			rank = new Rank("5th Kyu", 60L, true);
			rank.setNextRankId("4th Kyu");
			rank.setId(rank.getName());
			rankService.insert(rank);
			
			rank = new Rank("4th Kyu", 75L, true);
			rank.setNextRankId("3rd Kyu");
			rank.setId(rank.getName());
			rankService.insert(rank);

			rank = new Rank("3rd Kyu", 75L, true);
			rank.setNextRankId("2nd Kyu");
			rank.setId(rank.getName());
			rankService.insert(rank);

			rank = new Rank("1st Kyu", 75L, true);
			rank.setNextRankId("Shodan");
			rank.setId(rank.getName());
			rankService.insert(rank);

			rank = new Rank("Godan", 450L, false);
			rank.setNextRankId("Rokudan");
			rank.setId(rank.getName());
			rankService.insert(rank);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		student = new Student("Student 1", null, null);		
	}
	
	/**
	 * <b>Given</b> i did not register the promotion test data<br/>
	 * <b>When</b> i submit an application<br/>
	 * <b>Then</b> should be told that a promotion test is mandatory<br/>
	 */
	@Test
	public void testPromotionTestDataMandatory() {
		try {
			promotionService.apply(null, null);
			fail();
		} catch (Exception e) {
			assertEquals(
				PromotionTestService.PROMOTION_TEST_MANDATORY, e.getMessage());
		}
	}

	/**
	 * <b>Given</b> i registered the promotion test data<br/>
	 * <b>And</b> i did not inform a student<br/>
	 * <b>When</b> i submit an application<br/>
	 * <b>Then</b> should be told that the student is mandatory<br/>
	 */
	@Test
	public void testStudentDataMandatory() {
		try {
			promotionService.apply(promotionService.read(""), null);
			fail();
		} catch (Exception e) {
			assertEquals(
				PromotionTestService.STUDENT_MANDATORY, e.getMessage());
		}
	}
	
	/**
	 * <b>Given</b> i registered the promotion test data<br/>
	 * <b>And</b> i did not registered the ranks for the promotion test<br/>
	 * <b>And</b> i inform a student<br/>
	 * <b>When</b> i submit an application<br/>
	 * <b>Then</b> should be told that the ranks are mandatory<br/>
	 */
	@Test
	public void testNoRanksForTestInformed() {
		try {
			promotionService.apply(promotionService.read(""), student);
			fail();
		} catch (Exception e) {
			assertEquals(
				PromotionTestService.RANKS_MANDATORY, e.getMessage());
		}
	}
	
	/**
	 * <b>Given</b> i registered the promotion test data<br/>
	 * <b>And</b> i registered the ranks for the promotion test<br/>
	 * <b>And</b> i inform a student with a rank not registered for the 
	 * promotion test<br/>
	 * <b>When</b> i submit an application<br/>
	 * <b>Then</b> should be told that the student rank is not registered for 
	 * the promotion test<br/>
	 */
	@Test
	public void testStudentRankNotRegisteredForTest() {
		student.setRank(rankService.find("6th Kyu"));
		promotionTest.addRank(rankService.find("Godan"));
		try {
			promotionService.apply(promotionService.read(""), student);
			fail();
		} catch (Exception e) {
			assertEquals(
				PromotionTestService.STUDENT_RANK_NOT_REGISTERED
				, e.getMessage());
		}
	}

	/**
	 * <b>Given</b> i registered the promotion test data<br/>
	 * <b>And</b> i registered the ranks for the promotion test<br/>
	 * <b>And</b> i inform a student with a rank registered for the 
	 * promotion test<br/>
	 * <b>And</b> the student do not have the practice time required</b><br/>
	 * <b>When</b> i submit an application<br/>
	 * <b>Then</b> should be told that the student do not have the practice 
	 * time required<br/>
	 */
	@Test
	public void testStudentDontHavePracticeTimeRequested() {
		PracticeTime practiceTime = 
				new PracticeTime(LocalDate.of(2022, 1, 1), "6th Kyu", 50L);
		student.addPracticeTime(practiceTime);
		
		practiceTime = 
				new PracticeTime(LocalDate.of(2022, 1, 1), "6th Kyu", 9L);
		student.addPracticeTime(practiceTime);
		
		practiceTime = 
				new PracticeTime(LocalDate.of(2022, 1, 1), "5th Kyu", 10L);
		student.addPracticeTime(practiceTime);

		student.setRank(rankService.find("6th Kyu"));
		promotionTest.addRank(rankService.find("5th Kyu"));
		try {
			promotionService.apply(promotionService.read(""), student);
			fail();
		} catch (Exception e) {
			assertEquals(
				PromotionTestService.STUDENT_NOT_ENOUGH_TIME
				, e.getMessage());
		}
	}

	/**
	 * <b>Given</b> i registered the promotion test data<br/>
	 * <b>And</b> i registered the ranks for the promotion test<br/>
	 * <b>And</b> i inform a student with a rank registered for the 
	 * promotion test<br/>
	 * <b>And</b> the student have the exact practice time required</b><br/>
	 * <b>When</b> i submit an application<br/>
	 * <b>Then</b> the application should be accepted<br/>
	 */
	@Test
	public void testStudentHavePracticeTimeRequested() {
		PracticeTime practiceTime = 
				new PracticeTime(LocalDate.of(2022, 1, 1), "6th Kyu", 50L);
		student.addPracticeTime(practiceTime);
		
		practiceTime = 
				new PracticeTime(LocalDate.of(2022, 1, 1), "6th Kyu", 10L);
		student.addPracticeTime(practiceTime);
		
		practiceTime = 
				new PracticeTime(LocalDate.of(2022, 1, 1), "5th Kyu", 10L);
		student.addPracticeTime(practiceTime);
		
		student.setRank(rankService.find("6th Kyu"));
		promotionTest.addRank(rankService.find("5th Kyu"));
		try {
			int id = promotionService.apply(promotionService.read(""), student);
			assertTrue(id>0);
		} catch (Exception e) {
			fail();
		}
	}

	/**
	 * <b>Given</b> i registered the promotion test data<br/>
	 * <b>And</b> i registered the ranks for the promotion test<br/>
	 * <b>And</b> i inform a student with a rank registered for the 
	 * promotion test<br/>
	 * <b>And</b> the student have more practice time required</b><br/>
	 * <b>When</b> i submit an application<br/>
	 * <b>Then</b> the application should be accepted<br/>
	 */
	@Test
	public void testStudentHaveMorePracticeTimeRequested() {
		PracticeTime practiceTime = 
				new PracticeTime(LocalDate.of(2022, 1, 1), "6th Kyu", 50L);
		student.addPracticeTime(practiceTime);
		
		practiceTime = 
				new PracticeTime(LocalDate.of(2022, 1, 1), "6th Kyu", 11L);
		student.addPracticeTime(practiceTime);
		
		practiceTime = 
				new PracticeTime(LocalDate.of(2022, 1, 1), "5th Kyu", 10L);
		student.addPracticeTime(practiceTime);
		
		student.setRank(rankService.find("6th Kyu"));
		promotionTest.addRank(rankService.find("5th Kyu"));
		try {
			int id = promotionService.apply(promotionService.read(""), student);
			assertTrue(id>0);
		} catch (Exception e) {
			fail();
		}
	}
}
