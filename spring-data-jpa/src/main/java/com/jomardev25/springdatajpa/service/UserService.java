package com.jomardev25.springdatajpa.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import com.jomardev25.springdatajpa.dto.UserDTO;
import com.jomardev25.springdatajpa.entity.Student;
import com.jomardev25.springdatajpa.entity.User;
import com.jomardev25.springdatajpa.exception.ResourceNotFound;
import com.jomardev25.springdatajpa.repository.UserRepository;
import com.jomardev25.springdatajpa.repository.interfaces.IUsersWithStudents;
import com.jomardev25.springdatajpa.response.UserResponse;
import com.jomardev25.springdatajpa.response.UserWithStudentsResponse;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class UserService {

	private UserRepository userRepo;

	public List<UserResponse> findAllUsers() {
		return userRepo.findAll().stream().map(user -> mapToResponse(user)).collect(Collectors.toList());
	}

	public UserResponse saveUser(UserDTO userDTO) {
		return mapToResponse(userRepo.save(mapToEntity(userDTO)));
	}

	public UserResponse updateUser(Integer id, UserDTO userDTO) {
		User user = userRepo.findById(id).orElseThrow(() -> new ResourceNotFound("User", "id", id));
		user.setFirstName(userDTO.getFirstName());
		user.setLastName(userDTO.getLastName());
		user.setActive(userDTO.isActive());
		return mapToResponse(userRepo.save(user));
	}

	public UserWithStudentsResponse findUserWithStudents(Integer id) {
		Pair<Long, List<Student>> userWithStudents =  userRepo.findUserWithStudents(id);
		UserWithStudentsResponse userWithStudentsDTO = new UserWithStudentsResponse();
		userWithStudentsDTO.setTotalCount(userWithStudents.getFirst());
		userWithStudentsDTO.setStudents(userWithStudents.getSecond());
		return userWithStudentsDTO;
	}

	public List<IUsersWithStudents> findUsersWithStudents(Integer id, String username) {
		return userRepo.findUsersWithStudents(id, username);
	}

	public List<User> findUserWithStudentQuery() {
		return userRepo.findUserWithStudentQuery();
	}


	private User mapToEntity(UserDTO userDTO) {

		return User.builder()
				.firstName(userDTO.getFirstName())
				.lastName(userDTO.getLastName())
				.username(userDTO.getUsername())
				.password(userDTO.getPassword())
				.isActive(userDTO.isActive())
				.build();

	}

	private UserResponse mapToResponse(User user) {

		return UserResponse.builder()
					.id(user.getId())
					.firstName(user.getFirstName())
					.lastName(user.getLastName())
					.username(user.getUsername())
					.isActive(user.isActive())
					.build();

	}

}
