package org.example.controller;

import org.example.model.User;
import org.example.service.UserService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("/api/Users")
public class UserController {

	final
	UserService UserService;

	public UserController(UserService UserService) {
		this.UserService = UserService;
	}

	//получаем пользователя по id
	@GetMapping("/{id}")
	public ResponseEntity<User> getUser(@PathVariable("id") Long UserId) {
		//если пользователя нет возвращает статус BAD_REQUEST
		if (UserId == null)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

		//получаем пользователя по id
		User User = UserService.findById(UserId).orElse(null);

		//если пользователя нет возвращает статус NOT_FOUND
		if (User == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		//возвращает пользователя и статус OK
		return new ResponseEntity<>(User, HttpStatus.OK);
	}

	//добавляем пользователя
	@PostMapping()
	public ResponseEntity<User> saveUser(@RequestBody @Valid User User) {
		HttpHeaders headers = new HttpHeaders();

		//если пользователя нет возвращает статус NOT_FOUND
		if (User == null)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

		//сохраняет пользователя в базу данных
		UserService.save(User);
		//возвращает пользователя и статус CREATED
		return new ResponseEntity<>(User, headers, HttpStatus.CREATED);
	}

	//обновляем пользователя
	@PutMapping()
	public ResponseEntity<User> updateUser(@RequestBody @Valid User User, UriComponentsBuilder builder) {
		HttpHeaders headers = new HttpHeaders();

		//если пользователя нет возвращает статус BAD_REQUEST
		if (User == null)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

		//сохраняет пользователя в базу данных
		UserService.save(User);

		//возвращает всех пользователей и статус OK
		return new ResponseEntity<>(User, headers, HttpStatus.OK);
	}

	//удаляем пользователя по id
	@DeleteMapping("/{id}")
	public ResponseEntity<User> deleteUser(@PathVariable("id") Long id) {
		User User = UserService.findById(id).orElse(null);

		//если пользователя нет возвращает статус NOT_FOUND
		if (User == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		//удаляет пользователя из базы данных
		UserService.delete(id);

		//возвращает статус NO_CONTENT
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	//получаем список всех пользователей
	@GetMapping()
	public ResponseEntity<List<User>> getAllUsers() {

		//достает всех пользователей и кладем в массив
		List<User> UserList = UserService.getAll();

		//если массив пуст возвращает статус NOT_FOUND
		if (UserList.isEmpty())
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		//возвращает всех пользователей и статус OK
		return new ResponseEntity<>(UserList, HttpStatus.OK);
	}

	@GetMapping("/filter")
	public ResponseEntity<List<User>> filterByDateOfRegistration() {
		//достает всех пользователей и кладем в массив
		List<User> UserList = UserService.filterByDateOfRegistration();

		//если массив пуст возвращает статус NOT_FOUND
		if (UserList.isEmpty())
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		//возвращает всех пользователей и статус OK
		return new ResponseEntity<>(UserList, HttpStatus.OK);
	}

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

	@GetMapping("/filterDateAfter")
	public ResponseEntity<List<User>> filterByDateAfterCertainDate(@PathVariable("date") Date date) {
		//достает всех пользователей и кладем в массив
		List<User> UserList = UserService.filterByDateAfterCertainDate(date);

		//если массив пуст возвращает статус NOT_FOUND
		if (UserList.isEmpty())
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		//возвращает всех пользователей и статус OK
		return new ResponseEntity<>(UserList, HttpStatus.OK);
	}

	@GetMapping("/filterDateBefore")
	public ResponseEntity<List<User>> filterByDateBeforeCertainDate(@PathVariable("date") Date date) {
		//достает всех пользователей и кладем в массив
		List<User> UserList = UserService.filterByDateBeforeCertainDate(date);

		//если массив пуст возвращает статус NOT_FOUND
		if (UserList.isEmpty())
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		//возвращает всех пользователей и статус OK
		return new ResponseEntity<>(UserList, HttpStatus.OK);
	}

}
