package br.com.afett.aikidoapi.services;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import br.com.afett.aikido4j.entities.PersonType;
import br.com.afett.aikido4j.entities.PracticeTime;
import br.com.afett.aikido4j.entities.Rank;
import br.com.afett.aikido4j.entities.Student;
import br.com.afett.aikido4j.services.PromotionService;

class PromotionServiceTest {

	/**
	 * <b>Given</b> a student without enough time of practice<br/>
	 * <b>When</b> i check his time of practice<br/>
	 * <b>Then</b> he should not be allowed to take the promotion test
	 */
	@Test
	void testShouldNotAllowPromotionTest() {
/*
		List<PersonType> personTypes = new ArrayList<>();
		personTypes.add(PersonType.STUDENT);
		Rank rank = new Rank(Rank.SIXTH_KYU, 60L, false);
		Student student = new Student("Student 1", rank, personTypes);

		
		LocalDate practiceDate = LocalDate.now();
		PracticeTime practiceTime = 
				new PracticeTime(
						practiceDate, student.getRank(), 1L);
		
		student.addPracticeTime(practiceTime);
		PromotionService service = new PromotionService();	
		//assertTrue(service.isStudentAbleForTest(student));
		 * 
		 */
	}

}
