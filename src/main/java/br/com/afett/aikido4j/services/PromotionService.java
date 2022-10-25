package br.com.afett.aikido4j.services;

import br.com.afett.aikido4j.entities.Rank;
import br.com.afett.aikido4j.entities.Student;

public class PromotionService {
	
	public static final String STUDENT_NOT_ALLOWED_FOR_RANK = 
						"Student not allowed to be promoted to this rank";

	public static final String STUDENT_NOT_ENOUGH_TIME = 
						"Student does not have enough practice time!";
	
	public static final String NO_TEST_REQUIRED_FOR_RANK = 
						"No test required for the rank!";

	public static final String TEST_RANK_MANDATORY = 
			"Test rank mandatory!";

	private StudentService studentService;
	
	public PromotionService() {
		studentService = new StudentService();
	}
	
	private void checkTestRank(Rank rank) throws Exception {
		if (rank == null) throw new Exception(TEST_RANK_MANDATORY);
		if (!rank.isTestRequired()) {
			throw new Exception(NO_TEST_REQUIRED_FOR_RANK);
		}
	}
	
	private void checkStudentRank(Rank rankTest, Rank studentRank) 
			throws Exception {
		if (!rankTest.getName().equals(studentRank.getNextRankName())) {
			throw new Exception(STUDENT_NOT_ALLOWED_FOR_RANK);
		}
	}
	
	private void checkStudentPracticeTime(Rank rankTest, Student student) 
			throws Exception {
		Long studentPracticeTimeInCurrentRank = 
				studentService.getPracticeTimeInCurrentRank(student);

		if (studentPracticeTimeInCurrentRank < rankTest.getRequiredTime()) {
			throw new Exception(STUDENT_NOT_ENOUGH_TIME);
		}
	}
	
	public void applyForTest(Rank rankTest, Student student) throws Exception {
		try {
			checkTestRank(rankTest);
			checkStudentRank(rankTest, student.getRank());
			checkStudentPracticeTime(rankTest, student);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		
		//apply for test
	}

}
