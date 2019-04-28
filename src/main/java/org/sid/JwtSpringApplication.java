package org.sid;

import org.sid.dao.TaskRepository;
import org.sid.entities.AppRole;
import org.sid.entities.AppUser;
import org.sid.entities.Task;
import org.sid.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication(exclude={SecurityAutoConfiguration.class})
public class JwtSpringApplication implements CommandLineRunner {
// 
	@Autowired
	private TaskRepository taskRepository;
	@Autowired
	private AccountService accountService;

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	public static void main(String[] args) {
		SpringApplication.run(JwtSpringApplication.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {
		taskRepository.save(new Task("T1"));
		taskRepository.save(new Task("T2"));
		taskRepository.save(new Task("T3"));
		accountService.saveRole(new AppRole("USER"));
		accountService.saveRole(new AppRole("ADMIN"));
		accountService.saveUser(new AppUser("user", "1234", null));
		accountService.saveUser(new AppUser("admin", "1234", null));
		accountService.addRoleToUser("user", "USER");
		accountService.addRoleToUser("admin", "USER");
		accountService.addRoleToUser("admin", "ADMIN");
		
		taskRepository.findAll().forEach(
				t->System.out.println(t.getTaskName()));
	}

}