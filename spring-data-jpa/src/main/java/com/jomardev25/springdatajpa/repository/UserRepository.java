package com.jomardev25.springdatajpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.util.Pair;

import com.jomardev25.springdatajpa.entity.Student;
import com.jomardev25.springdatajpa.entity.User;
import com.jomardev25.springdatajpa.repository.interfaces.IUsersWithStudents;

public interface UserRepository extends JpaRepository<User, Integer> {

	// Implement the custom query in UserRepositoryImpl
	Pair<Long, List<Student>> findUserWithStudents(Integer id);

	// native query need interface to map the field to property
	// ex. is_active need map to isActive - camel case in interface
	// isActive is interface property
	@Query(
			value = "select distinct u.id, u.first_name as firstName, u.last_name, u.username, u.password, u.is_active as isActive "
					+ "from users u join students s on s.user_id = u.id where  u.id = ?1 and u.username = ?2",
			nativeQuery = true
	)
	List<IUsersWithStudents> findUsersWithStudents(Integer id, String username);

	// for non native query need to match the from clause table to Entity name
	// ex. users table = User Entity
	@Query( value = "select distinct u from User u join Student s on s.user.id = u.id", nativeQuery = false)
	List<User> findUserWithStudentQuery();


}
