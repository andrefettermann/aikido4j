package br.com.afett.aikido4j.services;

import java.util.HashMap;
import java.util.Map;

import br.com.afett.aikido4j.entities.Rank;
import br.com.afett.aikido4j.entities.Student;

public class PromotionService {
	
	private Map<String, Rank> ranks = new HashMap<>();
	private StudentService studentService;
	
	public PromotionService() {
		studentService = new StudentService();
	}
	
	private boolean hasStudentEnoughPracticeTime(Student student, Rank nextRank) {
		return studentService.getPracticeTimeInCurrentRank(student) 
				>= nextRank.getRequiredTime();
	}
	
	public boolean validateRequirements(Student student) throws Exception {
		Rank nextRank = ranks.get(studentService.getNextRank(student));
		if (nextRank.isTestRequired()) {
			return hasStudentEnoughPracticeTime(student, nextRank);
		} else {
			throw new Exception("No test required for the rank!");
		}
	}

}
