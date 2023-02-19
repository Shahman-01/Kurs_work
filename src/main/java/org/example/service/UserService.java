package org.example.service;

import org.example.model.User;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface UserService {
	Optional<User> findById(Long id);

	Optional<User> findOneByLogin(String login);

	void save(User User);

	void delete(Long id);

	List<User> getAll();

	List<User> filterByDateOfRegistration();

	List<User> getAllActive();

	List<User> filterByDateAfterCertainDate(Date date);

	List<User> filterByDateBeforeCertainDate(Date date);
}
