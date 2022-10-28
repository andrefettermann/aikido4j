package br.com.afett.aikido4j.daos;

import java.util.ArrayList;
import java.util.List;

import br.com.afett.aikido4j.entities.PersonType;
import br.com.afett.aikido4j.entities.Rank;
import br.com.afett.aikido4j.entities.Student;

public class StudentsMockDao implements Dao<Student>{
	
	private List<Student> data = new ArrayList<>();
	
	private Student setBean(
			String name, Rank rank, List<PersonType> personTypes) {

		Student bean = new Student(name, rank, personTypes);
		bean.setId(name);
	
		return bean;
	}
	
	public StudentsMockDao() {
		Rank rank = new Rank("6th Kyu", 0L, false);
		List<PersonType> personTypes = new ArrayList<>();
		personTypes.add(PersonType.STUDENT);
		
		data.add(this.setBean("Student", rank , personTypes));
		
		rank = new Rank("2nd Kyu", 0L, false);
		personTypes = new ArrayList<>();
		personTypes.add(PersonType.STUDENT);
		
		data.add(this.setBean("2nd Student", rank , personTypes));
	}

	@Override
	public Student get(String id) {
		Student student = null;
		for (Student s:data) {
			if (s.getId().equals(id)) student = s;
		}
		return student;
	}

	@Override
	public List<Student> getAll() {
		return data;
	}

	@Override
	public void save(Student s) {
		s.setId(s.getName());
		data.add(s);
	}

	@Override
	public void update(Student s, String[] params) {
	}

	@Override
	public void delete(Student s) {
	}

}
