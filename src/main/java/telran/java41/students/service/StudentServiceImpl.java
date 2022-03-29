package telran.java41.students.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import telran.java41.students.dao.StudentRepository;
import telran.java41.students.dto.ScoreDto;
import telran.java41.students.dto.StudentCredentialsDto;
import telran.java41.students.dto.StudentDto;
import telran.java41.students.dto.UpdateStudentDto;
import telran.java41.students.model.Student;

@Service
@AllArgsConstructor
public class StudentServiceImpl implements StudentService {
	
	StudentRepository studentRepository;

	@Override
	public boolean addStudent(StudentCredentialsDto studentCredentialsDto) {
		Student student = studentRepository.findById(studentCredentialsDto.getId()).orElse(null);
		if(student != null) {
			return false;
		}
		student = new Student(studentCredentialsDto.getId(), studentCredentialsDto.getName(),
				studentCredentialsDto.getPassword());
		studentRepository.save(student);
		return true;
	}

	@Override
	public StudentDto findStudent(Integer id) {
		Student student = studentRepository.findById(id).orElse(null);
		if(student == null) {
			return null;
		}		
		return StudentDto.builder()
							.id(id)
							.name(student.getName())
							.scores(student.getScores())
							.build();
	
	}

	@Override
	public StudentDto deleteStudent(Integer id) {
		Student student = studentRepository.deleteById(id).orElse(null);
		if(student == null) {  
			return null;
		}		
		return StudentDto.builder()
				
							.id (id)
							.name(student.getName())
							.scores(student.getScores())
							.build();
	}

	@Override
	public StudentCredentialsDto updateStudent(Integer id, UpdateStudentDto updateStudentDto) {
		Student student = studentRepository.findById(id).orElse(null);
		if(student == null) {
			return null;
		}
		student.setName(updateStudentDto.getName());
		student.setPassword(updateStudentDto.getPassword());
		return (StudentCredentialsDto.builder())
							.Id(student.getId())
							.name(student.getName())
							.password(student.getPassword())
							.build();
	}

	@Override
	public boolean addScore(Integer id, ScoreDto scoreDto) {
		Student student = studentRepository.findById(id).orElseThrow(null);
		if(student == null) {
			return false;
	}
		return student.addScore(scoreDto.getExamName(), scoreDto.getScore());

	}

	@Override
	public List<StudentDto> findStudentsByName(String name) {
		return studentRepository.findAll().stream()
								.filter(s -> name.equalsIgnoreCase(s.getName()))
								.map(s -> new StudentDto(s.getId(), s.getName(), s.getScores()))
								.collect(Collectors.toList());
	}

	@Override
	public long getStudentsNamesQuantity(List<String> names) {
		return studentRepository.findAll().stream()
				.filter(s -> names.contains(s.getName()))
				.count();
	}

	@Override
	public List<StudentDto> getStudentsByExamScore(String exam, int score) {
		return studentRepository.findAll().stream()
								.filter(s -> s.getScores().containsKey(exam) && s.getScores().get(exam) > score)
								.map(s -> new StudentDto (s.getId(), s.getName(), s.getScores()))
								.collect(Collectors.toList());
	}

}
