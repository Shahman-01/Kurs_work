package org.example.model;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
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

	private Date date_of_creating;

	public void setId(Long id) {
		this.id = id;
	}

	@Id
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

	public Date getDate_of_creating() {
		return date_of_creating;
	}

	public void setDate_of_creating(Date date_of_creating) {
		this.date_of_creating = date_of_creating;
	}
}
