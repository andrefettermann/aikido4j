package br.com.afett.aikidoapi.services;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.afett.aikido4j.entities.PersonType;
import br.com.afett.aikido4j.entities.PracticeTime;
import br.com.afett.aikido4j.entities.Rank;
import br.com.afett.aikido4j.entities.Student;
import br.com.afett.aikido4j.services.PromotionService;
import br.com.afett.aikido4j.services.RankService;

class PromotionServiceTest {
	
	RankService rankService;
	PromotionService promotionService;
	
	@BeforeEach
	public void setUp() {
		promotionService = new PromotionService();
		rankService = RankService.getInstance();

		try {
			Rank rank = new Rank("6th Kyu", 0L, false);
			rank.setNextRankName("5th Kyu");
			rankService.insert(rank);

			rank = new Rank("5th Kyu", 60L, true);
			rank.setNextRankName("4th Kyu");
			rankService.insert(rank);

			rank = new Rank("Godan", 600L, false);
			rank.setNextRankName("Rokudan");
			rankService.insert(rank);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Test
	void testWhenStudentWithoutEnoughTimeShouldNotApplyForTest() {
		List<PersonType> studentPersonTypes = new ArrayList<>();
		studentPersonTypes.add(PersonType.STUDENT);
		
		Rank testRank = new Rank("5th Kyu", 60L, true);
		testRank.setId(testRank.getName());
		
		Rank studentRank = new Rank("6th Kyu", 0L, false);
		studentRank.setNextRankName("5th Kyu");
		Student student = new Student(
				"Student 1", studentRank, studentPersonTypes);
		studentRank.setId(studentRank.getName());
		
		LocalDate practiceDate = LocalDate.of(2022, 1, 10);
		PracticeTime practiceTime = 
				new PracticeTime(practiceDate, "6th Kyu", 1L);
		student.addPracticeTime(practiceTime);
		
		practiceDate = LocalDate.of(2022, 1, 12);
		practiceTime = new PracticeTime(practiceDate, "6th Kyu", 58L);
		student.addPracticeTime(practiceTime);
		
		practiceDate = LocalDate.of(2022, 1, 14);
		practiceTime = new PracticeTime(practiceDate, "5th Kyu", 1L);
		student.addPracticeTime(practiceTime);
		
		try {
			promotionService.applyForTest(testRank, student);
			fail();
		} catch (Exception e) {
			assertEquals(
					PromotionService.STUDENT_NOT_ENOUGH_TIME, e.getMessage());
		}
	}

	@Test
	void testWhenStudentWithEnoughTimeShouldApplyForTest() {
		List<PersonType> studentPersonTypes = new ArrayList<>();
		studentPersonTypes.add(PersonType.STUDENT);
		
		Rank testRank = new Rank("5th Kyu", 60L, true);
		testRank.setId(testRank.getName());
		
		Rank studentRank = new Rank("6th Kyu", 0L, false);
		studentRank.setNextRankName("5th Kyu");
		studentRank.setId(studentRank.getName());
		
		Student student = new Student(
				"Student 1", studentRank, studentPersonTypes);
		
		LocalDate practiceDate = LocalDate.of(2022, 1, 10);
		PracticeTime practiceTime = 
								new PracticeTime(practiceDate, "6th Kyu", 45L);
		student.addPracticeTime(practiceTime);
		
		practiceDate = LocalDate.of(2022, 1, 12);
		practiceTime = new PracticeTime(practiceDate, "6th Kyu", 15L);
		student.addPracticeTime(practiceTime);
		
		practiceDate = LocalDate.of(2022, 1, 14);
		practiceTime = new PracticeTime(practiceDate, "5th Kyu", 1L);
		student.addPracticeTime(practiceTime);
		
		try {
			promotionService.applyForTest(testRank, student);
			assertTrue(true);
		} catch (Exception e) {
			fail();
		}
	}

	@Test
	void testWhenStudentNotInPreviousRankShouldNotApplyForTest() {
		List<PersonType> studentPersonTypes = new ArrayList<>();
		studentPersonTypes.add(PersonType.STUDENT);
		
		Rank testRank = new Rank("Godan", 600L, true);
		testRank.setId(testRank.getName());
		
		Rank studentRank = new Rank("6th Kyu", 0L, false);
		studentRank.setNextRankName("5th Kyu");
		studentRank.setId(studentRank.getName());
		
		Student student = new Student(
				"Student 1", studentRank, studentPersonTypes);
		
		LocalDate practiceDate = LocalDate.of(2022, 1, 10);
		PracticeTime practiceTime = 
								new PracticeTime(practiceDate, "6th Kyu", 45L);
		student.addPracticeTime(practiceTime);
		
		practiceDate = LocalDate.of(2022, 1, 12);
		practiceTime = new PracticeTime(practiceDate, "6th Kyu", 15L);
		student.addPracticeTime(practiceTime);
		
		practiceDate = LocalDate.of(2022, 1, 14);
		practiceTime = new PracticeTime(practiceDate, "5th Kyu", 1L);
		student.addPracticeTime(practiceTime);
		
		try {
			promotionService.applyForTest(testRank, student);
			fail();
		} catch (Exception e) {
			assertEquals(
					PromotionService.STUDENT_NOT_ALLOWED_FOR_RANK, e.getMessage());
		}
	}

	@Test
	void testWhenRankNotRequireTestShouldNotApplyForTestR4() {
		List<PersonType> studentPersonTypes = new ArrayList<>();
		studentPersonTypes.add(PersonType.STUDENT);
		
		Rank testRank = new Rank("4th Kyu", 75L, false);
		testRank.setId(testRank.getName());
		
		Rank studentRank = new Rank("6th Kyu", 0L, false);
		studentRank.setNextRankName("5th Kyu");
		studentRank.setId(studentRank.getName());
		
		Student student = new Student(
				"Student 1", studentRank, studentPersonTypes);
		
		LocalDate practiceDate = LocalDate.of(2022, 1, 10);
		PracticeTime practiceTime = 
								new PracticeTime(practiceDate, "6th Kyu", 45L);
		student.addPracticeTime(practiceTime);
		
		practiceDate = LocalDate.of(2022, 1, 12);
		practiceTime = new PracticeTime(practiceDate, "6th Kyu", 5L);
		student.addPracticeTime(practiceTime);
		
		practiceDate = LocalDate.of(2022, 1, 14);
		practiceTime = new PracticeTime(practiceDate, "5th Kyu", 1L);
		student.addPracticeTime(practiceTime);
		
		try {
			promotionService.applyForTest(testRank, student);
			fail();
		} catch (Exception e) {
			assertEquals(
					PromotionService.NO_TEST_REQUIRED_FOR_RANK, e.getMessage());
		}
	}

	@Test
	void testWhenRankNotRequireTestShouldNotApplyForTestR5() {
		List<PersonType> studentPersonTypes = new ArrayList<>();
		studentPersonTypes.add(PersonType.STUDENT);
		
		Rank testRank = new Rank("4th Kyu", 75L, false);
		testRank.setId(testRank.getName());
		
		Rank studentRank = new Rank("6th Kyu", 0L, false);
		studentRank.setNextRankName("5th Kyu");
		studentRank.setId(studentRank.getName());
		
		Student student = new Student(
				"Student 1", studentRank, studentPersonTypes);
		
		LocalDate practiceDate = LocalDate.of(2022, 1, 10);
		PracticeTime practiceTime = 
								new PracticeTime(practiceDate, "6th Kyu", 80L);
		student.addPracticeTime(practiceTime);
		
		practiceDate = LocalDate.of(2022, 1, 12);
		practiceTime = new PracticeTime(practiceDate, "6th Kyu", 5L);
		student.addPracticeTime(practiceTime);
		
		practiceDate = LocalDate.of(2022, 1, 14);
		practiceTime = new PracticeTime(practiceDate, "5th Kyu", 1L);
		student.addPracticeTime(practiceTime);
		
		try {
			promotionService.applyForTest(testRank, student);
			fail();
		} catch (Exception e) {
			assertEquals(
					PromotionService.NO_TEST_REQUIRED_FOR_RANK, e.getMessage());
		}
	}

	@Test
	void testWhenRankNotRequireTestShouldNotApplyForTestR6() {
		List<PersonType> studentPersonTypes = new ArrayList<>();
		studentPersonTypes.add(PersonType.STUDENT);
		
		Rank testRank = new Rank("4th Kyu", 75L, false);
		testRank.setId(testRank.getName());
		
		Rank studentRank = new Rank("6th Kyu", 0L, false);
		studentRank.setNextRankName("5th Kyu");
		studentRank.setId(studentRank.getName());
		
		Student student = new Student(
				"Student 1", studentRank, studentPersonTypes);
		
		LocalDate practiceDate = LocalDate.of(2022, 1, 10);
		PracticeTime practiceTime = 
								new PracticeTime(practiceDate, "6th Kyu", 1L);
		student.addPracticeTime(practiceTime);
		
		practiceDate = LocalDate.of(2022, 1, 12);
		practiceTime = new PracticeTime(practiceDate, "6th Kyu", 5L);
		student.addPracticeTime(practiceTime);
		
		practiceDate = LocalDate.of(2022, 1, 14);
		practiceTime = new PracticeTime(practiceDate, "5th Kyu", 1L);
		student.addPracticeTime(practiceTime);
		
		try {
			promotionService.applyForTest(testRank, student);
			fail();
		} catch (Exception e) {
			assertEquals(
					PromotionService.NO_TEST_REQUIRED_FOR_RANK, e.getMessage());
		}
	}

	@Test
	void testWhenRankNotRequireTestShouldNotApplyForTestR7() {
		List<PersonType> studentPersonTypes = new ArrayList<>();
		studentPersonTypes.add(PersonType.STUDENT);
		
		Rank testRank = new Rank("5th Kyu", 75L, false);
		testRank.setId(testRank.getName());
		
		Rank studentRank = new Rank("6th Kyu", 0L, false);
		studentRank.setNextRankName("5th Kyu");
		studentRank.setId(studentRank.getName());
		
		Student student = new Student(
				"Student 1", studentRank, studentPersonTypes);
		
		LocalDate practiceDate = LocalDate.of(2022, 1, 10);
		PracticeTime practiceTime = 
								new PracticeTime(practiceDate, "6th Kyu", 90L);
		student.addPracticeTime(practiceTime);
		
		practiceDate = LocalDate.of(2022, 1, 12);
		practiceTime = new PracticeTime(practiceDate, "6th Kyu", 5L);
		student.addPracticeTime(practiceTime);
		
		practiceDate = LocalDate.of(2022, 1, 14);
		practiceTime = new PracticeTime(practiceDate, "5th Kyu", 1L);
		student.addPracticeTime(practiceTime);
		
		try {
			promotionService.applyForTest(testRank, student);
			fail();
		} catch (Exception e) {
			assertEquals(
					PromotionService.NO_TEST_REQUIRED_FOR_RANK, e.getMessage());
		}
	}

	@Test
	void testWhenRankNotInformedNotApplyForTestR8() {
		List<PersonType> studentPersonTypes = new ArrayList<>();
		studentPersonTypes.add(PersonType.STUDENT);
		
		Rank testRank = null;
		
		Rank studentRank = new Rank("6th Kyu", 0L, false);
		studentRank.setNextRankName("5th Kyu");
		studentRank.setId(studentRank.getName());
		
		Student student = new Student(
				"Student 1", studentRank, studentPersonTypes);
		
		LocalDate practiceDate = LocalDate.of(2022, 1, 10);
		PracticeTime practiceTime = 
								new PracticeTime(practiceDate, "6th Kyu", 90L);
		student.addPracticeTime(practiceTime);
		
		practiceDate = LocalDate.of(2022, 1, 12);
		practiceTime = new PracticeTime(practiceDate, "6th Kyu", 5L);
		student.addPracticeTime(practiceTime);
		
		practiceDate = LocalDate.of(2022, 1, 14);
		practiceTime = new PracticeTime(practiceDate, "5th Kyu", 1L);
		student.addPracticeTime(practiceTime);
		
		try {
			promotionService.applyForTest(testRank, student);
			fail();
		} catch (Exception e) {
			assertEquals(
					PromotionService.TEST_RANK_MANDATORY, e.getMessage());
		}
	}
}
