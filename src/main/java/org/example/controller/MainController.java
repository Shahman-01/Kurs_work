package org.example.controller;

import org.example.model.User;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class MainController {

	final
	UserService userService;

	public MainController(UserService userService) {
		this.userService = userService;
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

	@PostMapping()
	public ResponseEntity<User> saveUser(@RequestBody @Valid User user) {
		HttpHeaders headers = new HttpHeaders();

		if (user == null)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

		this.userService.save(user);
		return new ResponseEntity<>(user, headers, HttpStatus.CREATED);
	}
}
