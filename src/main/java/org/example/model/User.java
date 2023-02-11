package org.example.model;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Size(min = 2, max = 128)
	@Column(name = "name", nullable = false)
	protected String name;

	@Column(name = "email", nullable = false, unique = true)
	@Email
	@NotBlank
	@Size(max = 128)
	private String email;

	@Column(name = "password", nullable = false)
	@NotBlank
	@Size(min = 5, max = 128)
	private String password;

	private Date registered;

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getRegistered() {
		return registered;
	}

	public void setRegistered(Date date_of_creating) {
		this.registered = date_of_creating;
	}
}
