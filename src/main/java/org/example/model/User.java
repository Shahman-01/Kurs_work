package org.example.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;


@Getter
@Setter
@NoArgsConstructor

@AllArgsConstructor
@Builder
@Entity
@Table(name = "Users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String firstName;
	private String lastName;
	private Integer age;
	private String login;
	private String Password;
	private String email;
	private Date dateOfRegistration;
	private boolean isActive;
	private Role role;
}
