package org.example.controller;

import org.example.model.User;
import org.example.service.UserService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/api/users")
public class MainController {

	final
	UserService userService;

	public MainController(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping(value = "", method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<User> saveUser(@RequestBody @Valid User user) {
		HttpHeaders headers = new HttpHeaders();

		if (user == null)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

		this.userService.save(user);
		return new ResponseEntity<>(user, headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "", method = RequestMethod.PUT,
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<User> updateUser(@RequestBody @Valid User user, UriComponentsBuilder builder) {
		HttpHeaders headers = new HttpHeaders();

		if (user == null)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

		this.userService.save(user);

		return new ResponseEntity<>(user, headers, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE,
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<User> deleteUser(Long id) {
		User user = this.userService.getById(id);

		if (user == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		this.userService.delete(id);

		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@GetMapping()
	public ResponseEntity<List<User>> getAllUsers() {

		List<User> userList = userService.getAll();

		if (userList.isEmpty())
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		return new ResponseEntity<>(userList, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<User> getUser(@PathVariable("id") Long userId) {
		if (userId == null)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

		User user = userService.getById(userId);

		if (user == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	@GetMapping("hello")
	public String hello() {
		return "hello world";
	}

}
