package br.com.afett.aikido4j.services;

import java.util.List;

import br.com.afett.aikido4j.daos.Dao;
import br.com.afett.aikido4j.daos.PromotionTestsMockDao;
import br.com.afett.aikido4j.entities.PromotionTest;
import br.com.afett.aikido4j.entities.Rank;
import br.com.afett.aikido4j.entities.Student;

public class PromotionTestService {
	
	public static final String PROMOTION_TEST_MANDATORY = 
			"Promotion test is mandatory!";
	
	public static final String STUDENT_MANDATORY = 
			"Student is mandatory!";

	public static final String RANKS_MANDATORY = 
			"Ranks are mandatory!";

	public static final String NO_TEST_REQUIRED_FOR_RANK = 
						"No test required for the rank!";

	public static final String STUDENT_RANK_NOT_REGISTERED = 
			"Student rank not registered for the promotion test!";
	
	public static final String STUDENT_NOT_ENOUGH_TIME = 
			"Student does not have enough practice time!";

	private Dao<PromotionTest> promotionTestDao;
	
	private StudentService studentService;

	public PromotionTestService() {
		studentService = new StudentService();
		promotionTestDao = new PromotionTestsMockDao();
	}
	
	private void validate(PromotionTest promotionTest) throws Exception {
		if (promotionTest == null) {
			throw new Exception(PROMOTION_TEST_MANDATORY);
		}

		for (Rank rank:promotionTest.getRanks()){
			if (!rank.isTestRequired()) {
				throw new Exception(NO_TEST_REQUIRED_FOR_RANK);
			}
		};
	}
	
	
	public void insert(PromotionTest promotionTest) throws Exception {
		validate(promotionTest);
		promotionTestDao.save(promotionTest);
	}
	
	public PromotionTest read(String id) {
		return promotionTestDao.get(id);
	}
	
	private void checkStudentRequirements(
			PromotionTest promotionTest, Student student) throws Exception {
		String studentNextRank = studentService.getNextRank(student);
		List<Rank> promotionTestRanks = promotionTest.getRanks();
		Rank testRank = null;

		for (Rank rank:promotionTestRanks){
			if (rank.getName().equals(studentNextRank)) {
				testRank = rank;
			}
		};
		if (testRank == null) 
			throw new Exception(STUDENT_RANK_NOT_REGISTERED);

		Long studentPracticeTimeInCurrentRank = 
				studentService.getPracticeTimeInCurrentRank(student);
		if (studentPracticeTimeInCurrentRank < testRank.getRequiredTime()) {
			throw new Exception(STUDENT_NOT_ENOUGH_TIME);
		}

	}
	
	public int apply(PromotionTest promotionTest, Student student) 
			throws Exception {
		if (promotionTest == null) {
			throw new Exception(PROMOTION_TEST_MANDATORY);
		} 
		
		if (student == null) {
			throw new Exception(STUDENT_MANDATORY);
		}
		
		if (promotionTest.getRanks().isEmpty()) {
			throw new Exception(RANKS_MANDATORY);
		}
		
		checkStudentRequirements(promotionTest, student);
		
		int applicationId = 1;
		
		return applicationId;
	}

}
