package telran.java41.students.dto;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder

public class StudentDto {
	public StudentDto(Integer id2, String name2, Map<String, Integer> scores2) {
		// TODO Auto-generated constructor stub
	}
	Integer id;
	String name;
	Map<String, Integer> scores;
	public static Object builder() {
		// TODO Auto-generated method stub
		return null;
	}
}
