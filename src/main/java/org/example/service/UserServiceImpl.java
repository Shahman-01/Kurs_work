package org.example.service;

import org.example.model.User;
import org.example.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Component
public class UserServiceImpl implements UserService {

	final
	UserRepo userRepo;

	public UserServiceImpl(UserRepo userRepo) {
		this.userRepo = userRepo;
	}

	@Override
	public User getById(Long id) {
		return userRepo.findById(id).get();
	}

	@Override
	public void save(User user) {
		userRepo.save(user);
	}

	@Override
	public void delete(Long id) {
		userRepo.deleteById(id);
	}

	@Override
	public List<User> getAll() {
		return userRepo.findAll();
	}
}
