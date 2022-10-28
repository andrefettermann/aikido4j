package br.com.afett.aikido4j.services;

import br.com.afett.aikido4j.daos.Dao;
import br.com.afett.aikido4j.daos.StudentsMockDao;
import br.com.afett.aikido4j.entities.PracticeTime;
import br.com.afett.aikido4j.entities.Rank;
import br.com.afett.aikido4j.entities.Student;

public class StudentService {
	
	private Dao<Student> dao;
	
	public StudentService() {
		dao = new StudentsMockDao();
	}
	
	public Student read(String id) {
		return dao.get(id);
	}

	public Long getPracticeTimeInCurrentRank(Student student) {
		Long practiceTimeInCurrentRank = 0L;
		
		for (PracticeTime practiceTime:student.getPracticeTimes()) {
			if (practiceTime.getRankId().equals(
					student.getRank().getId())) {
				practiceTimeInCurrentRank += practiceTime.getClassDuration();
			}
		}
		return practiceTimeInCurrentRank;
	}

	public final String getNextRank(Student student) {
		Rank currentRank = student.getRank();
		return currentRank.getNextRankName();
	}
}
