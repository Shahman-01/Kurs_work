package org.example.service;

import org.example.model.User;
import org.example.repo.UserRepo;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Component
public class UserServiceImpl implements UserService {

	final
	UserRepo UserRepo;

	public UserServiceImpl(UserRepo UserRepo) {
		this.UserRepo = UserRepo;
	}

	//возвращает пользователя по id
	@Override
	public Optional<User> findById(Long id) {
		return UserRepo.findById(id);
	}

	//возвращает пользователя по login
	@Override
	public Optional<User> findOneByLogin(String login) {
		return UserRepo.findByLogin(login);
	}

	//возвращает пользователя по email
	@Override
	public Optional<User> findByEmail(String email) {
		return UserRepo.findByEmail(email);
	}

	//сохраняет пользователя
	@Override
	public void save(User User) {
		UserRepo.save(User);
	}

	//удаляет пользователя по id
	@Override
	public void delete(Long id) {
		UserRepo.deleteById(id);
	}

	//возвращает всех пользователей
	@Override
	public List<User> getAll() {
		return UserRepo.findAll();
	}

	//возвращает всех пользователей в убывания их даты регистрации
	@Override
	public List<User> filterByDateOfRegistration() {
		return UserRepo.findAll().stream()
				.sorted(Comparator.comparing(User::getDateOfRegistration))
				.collect(Collectors.toList());
	}

	//возвращает всех активных пользователей
	@Override
	public List<User> getAllActive() {
		return UserRepo.findAll().stream()
				.filter(User::isActive)
				.collect(Collectors.toList());
	}

	//возвращает всех пользователей, зарегистрировавшихся после даты date
	@Override
	public List<User> filterByDateAfterCertainDate(Date date) {
		return UserRepo.findAll().stream()
				.filter(u -> u.getDateOfRegistration().after(date))
				.sorted(Comparator.comparing(User::getDateOfRegistration))
				.collect(Collectors.toList());
	}

	//возвращает всех пользователей, зарегистрировавшихся до даты date
	@Override
	public List<User> filterByDateBeforeCertainDate(Date date) {
		return UserRepo.findAll().stream()
				.filter(u -> u.getDateOfRegistration().before(date))
				.sorted(Comparator.comparing(User::getDateOfRegistration))
				.collect(Collectors.toList());
	}


}
