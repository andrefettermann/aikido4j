package br.com.afett.aikido4j.validations;

import br.com.afett.aikido4j.entities.Rank;
import br.com.afett.aikido4j.entities.Student;

public class StudentPromotionRankValidation 
							implements StudentPromotionValidation{
	
	public static final String STUDENT_NOT_ALLOWED_FOR_RANK = 
			"Student not allowed to be promoted to this rank";
	
	@Override
	public void validate(Rank rankTest, Student student) throws Exception {
		Rank studentRank = student.getRank();
		if (!rankTest.getName().equals(studentRank.getNextRankName())) {
			throw new Exception(STUDENT_NOT_ALLOWED_FOR_RANK);
		}
	}

}
