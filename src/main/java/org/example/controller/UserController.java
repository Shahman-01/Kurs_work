package org.example.controller;

import org.example.model.User;
import org.example.service.UserService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/api/Users")
public class UserController {

	final
	UserService UserService;

	public UserController(UserService UserService) {
		this.UserService = UserService;
	}

	//получаем клиента по id
	@GetMapping("/{id}")
	public ResponseEntity<User> getUser(@PathVariable("id") Long UserId) {
		//если клиента нет возвращает статус BAD_REQUEST
		if (UserId == null)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

		//получаем клиента по id
		User User = UserService.getById(UserId);

		//если клиента нет возвращает статус NOT_FOUND
		if (User == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		//возвращает клиента и статус OK
		return new ResponseEntity<>(User, HttpStatus.OK);
	}

	//добавляем клиента
	@PostMapping()
	public ResponseEntity<User> saveUser(@RequestBody @Valid User User) {
		HttpHeaders headers = new HttpHeaders();

		//если клиента нет возвращает статус NOT_FOUND
		if (User == null)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

		//сохраняет клиента в базу данных
		UserService.save(User);
		//возвращает клиента и статус CREATED
		return new ResponseEntity<>(User, headers, HttpStatus.CREATED);
	}

	//обновляем клиента
	@PutMapping()
	public ResponseEntity<User> updateUser(@RequestBody @Valid User User, UriComponentsBuilder builder) {
		HttpHeaders headers = new HttpHeaders();

		//если клиента нет возвращает статус BAD_REQUEST
		if (User == null)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

		//сохраняет клиента в базу данных
		UserService.save(User);

		//возвращает всех клиентов и статус OK
		return new ResponseEntity<>(User, headers, HttpStatus.OK);
	}

	//удаляем клиента по id
	@DeleteMapping("/{id}")
	public ResponseEntity<User> deleteUser(@PathVariable("id") Long id) {
		User User = UserService.getById(id);

		//если клиента нет возвращает статус NOT_FOUND
		if (User == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		//удаляет клиента из базы данных
		UserService.delete(id);

		//возвращает статус NO_CONTENT
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	//получаем список всех клиентов
	@GetMapping()
	public ResponseEntity<List<User>> getAllUsers() {

		//достает всех клиентов и кладем в массив
		List<User> UserList = UserService.getAll();

		//если массив пуст возвращает статус NOT_FOUND
		if (UserList.isEmpty())
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		//возвращает всех клиентов и статус OK
		return new ResponseEntity<>(UserList, HttpStatus.OK);
	}

	@GetMapping("/filter")
	public ResponseEntity<List<User>> filterByDateOfRegistration() {
		//достает всех клиентов и кладем в массив
		List<User> UserList = UserService.filterByDateOfRegistration();

		//если массив пуст возвращает статус NOT_FOUND
		if (UserList.isEmpty())
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		//возвращает всех клиентов и статус OK
		return new ResponseEntity<>(UserList, HttpStatus.OK);
	}

}
