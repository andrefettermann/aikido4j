package br.com.afett.aikido4j.services;

import java.util.ArrayList;
import java.util.List;

import br.com.afett.aikido4j.entities.Rank;
import br.com.afett.aikido4j.entities.Student;
import br.com.afett.aikido4j.validations.StudentPromotionTimeOfPracticeInRankValidation;
import br.com.afett.aikido4j.validations.StudentPromotionValidation;
import br.com.afett.aikido4j.validations.StudentPromotionRankValidation;

public class PromotionService {
	
	public static final String NO_TEST_REQUIRED_FOR_RANK = 
						"No test required for the rank!";

	public static final String TEST_RANK_MANDATORY = 
			"Test rank mandatory!";

	private List<StudentPromotionValidation> studentValidations;

	public PromotionService() {
		studentValidations = new ArrayList<>();
		studentValidations.add(
				new StudentPromotionTimeOfPracticeInRankValidation());
		studentValidations.add(new StudentPromotionRankValidation());
	}
	
	private void checkTestRank(Rank rank) throws Exception {
		if (rank == null) throw new Exception(TEST_RANK_MANDATORY);
		if (!rank.isTestRequired()) {
			throw new Exception(NO_TEST_REQUIRED_FOR_RANK);
		}
	}
	
	public void applyForTest(Rank rankTest, Student student) throws Exception {
		try {
			checkTestRank(rankTest);
			for (StudentPromotionValidation validation:studentValidations) {
				validation.validate(rankTest, student);
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		
		//apply for test
	}

}
