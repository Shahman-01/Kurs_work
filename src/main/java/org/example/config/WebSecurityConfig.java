package org.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.sql.DataSource;

public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	private final DataSource dataSource;

	public WebSecurityConfig(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication()
				.dataSource(dataSource)
				.usersByUsernameQuery("select username,password,enabled "
						+ "from users where username=?")
				.authoritiesByUsernameQuery("select username, authority "
						+ "from authorities where username=?")
				.passwordEncoder(passwordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
					.antMatchers("/admin/**").hasRole("ADMIN")
					.antMatchers("/teacher/**").hasRole("TEACHER")
					.antMatchers("/student/**").hasRole("STUDENT")
				.and()
					.formLogin()
				.and()
					.logout()
					.logoutSuccessUrl("/");
	}
}
