package org.example.controller;

import org.example.model.User;
import org.example.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/teachers")
public class TeacherController {
	final UserService UserService;

	public TeacherController(org.example.service.UserService userService) {
		UserService = userService;
	}

	@GetMapping()
	public ResponseEntity<List<User>> getAllUsers() {

		//достает всех пользователей и кладем в массив
		List<User> UserList = UserService.getAll();

		//если fмассив пуст возвращает статус NOT_FOUND
		if (UserList.isEmpty())
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		//возвращает всех пользователей и статус OK
		return new ResponseEntity<>(UserList, HttpStatus.OK);
	}

	//возвращает всех пользователей, отсортированных по дате регистрации
	@GetMapping("/sorted")
	public ResponseEntity<List<User>> filterByDateOfRegistration() {
		//достает всех пользователей и кладем в массив
		List<User> UserList = UserService.filterByDateOfRegistration();

		//если массив пуст возвращает статус NOT_FOUND
		if (UserList.isEmpty())
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		//возвращает всех пользователей и статус OK
		return new ResponseEntity<>(UserList, HttpStatus.OK);
	}

	//возвращает всех активных пользователей
	@GetMapping("/getAllActive")
	public ResponseEntity<List<User>> getAllActive() {
		//достает всех активных пользователей и кладем в массив
		List<User> UserList = UserService.getAllActive();

		//если массив пуст возвращает статус NOT_FOUND
		if (UserList.isEmpty())
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		//возвращает всех пользователей и статус OK
		return new ResponseEntity<>(UserList, HttpStatus.OK);
	}

	//возвращает всех пользователей зарегистрировавшихся после даты date
	@GetMapping("/filterDateAfter")
	public ResponseEntity<List<User>> filterByDateAfterCertainDate(@PathVariable("date") Date date) {
		//достает всех пользователей зарегистрировавшихся после даты date и кладем в массив
		List<User> UserList = UserService.filterByDateAfterCertainDate(date);

		//если массив пуст возвращает статус NOT_FOUND
		if (UserList.isEmpty())
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		//возвращает всех пользователей и статус OK
		return new ResponseEntity<>(UserList, HttpStatus.OK);
	}

	//возвращает всех пользователей зарегистрировавшихся до даты date
	@GetMapping("/filterDateBefore")
	public ResponseEntity<List<User>> filterByDateBeforeCertainDate(@PathVariable("date") Date date) {
		//достает всех пользователей, зарегистрировавшихся до даты date и кладем в массив
		List<User> UserList = UserService.filterByDateBeforeCertainDate(date);

		//если массив пуст возвращает статус NOT_FOUND
		if (UserList.isEmpty())
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		//возвращает всех пользователей и статус OK
		return new ResponseEntity<>(UserList, HttpStatus.OK);
	}
}
