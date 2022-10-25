package br.com.afett.aikido4j.validations;

import br.com.afett.aikido4j.entities.Rank;
import br.com.afett.aikido4j.entities.Student;

public interface StudentPromotionValidation {
	
	void validate(Rank rank, Student student) throws Exception;

}
