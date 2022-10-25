package br.com.afett.aikido4j.validations;

import br.com.afett.aikido4j.entities.Rank;
import br.com.afett.aikido4j.entities.Student;
import br.com.afett.aikido4j.services.StudentService;

public class StudentPromotionTimeOfPracticeInRankValidation 
							implements StudentPromotionValidation{
	
	public static final String STUDENT_NOT_ENOUGH_TIME = 
			"Student does not have enough practice time!";
	
	private StudentService studentService;
	
	public StudentPromotionTimeOfPracticeInRankValidation() {
		studentService = new StudentService();
	}

	@Override
	public void validate(Rank rankTest, Student student) throws Exception {
		Long studentPracticeTimeInCurrentRank = 
				studentService.getPracticeTimeInCurrentRank(student);

		if (studentPracticeTimeInCurrentRank < rankTest.getRequiredTime()) {
			throw new Exception(STUDENT_NOT_ENOUGH_TIME);
		}
	}

}
