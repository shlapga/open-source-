package telran.java41.students.dao;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import telran.java41.students.model.Student;

@Repository
public class StudentRepositoryImpl implements StudentRepository {
	Map<Integer, Student> students = new ConcurrentHashMap<>();

	@Override
	public Student save(Student student) {
		students.put(student.getId (), student);
		return student;
	}

	@Override
	public Optional<Student> findById(int id) {
		return Optional.ofNullable(students.get(id));
	}

	@Override
	public Optional<Student> deleteById(int id) {
		return Optional.ofNullable(students.remove(id));
	}

	@Override
	public List<Student> findAll() {
		return students.values().stream().collect(Collectors.toList());
	}

}
