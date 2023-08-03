package com.greatlearning.sms.config;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.greatlearning.sms.model.Role;
import com.greatlearning.sms.model.Student;
import com.greatlearning.sms.model.User;
import com.greatlearning.sms.repository.StudentRepository;
import com.greatlearning.sms.repository.UserRepository;

@Configuration
public class BootStrapDataApp {
	
	@Autowired
	private StudentRepository repository;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@EventListener(ApplicationReadyEvent.class)
	@Transactional
	public void loadUser(ApplicationReadyEvent event) {
		
		Role user = new Role("ROLE_USER");
		Role admin = new Role("ROLE_ADMIN");
		
		User student = new User("user",this.passwordEncoder.encode("oxfor"));
		User teacher = new User("admin",this.passwordEncoder.encode("oxfor"));
		
		student.addrole(user);
		teacher.addrole(admin);
		
		this.userRepo.save(student);
		this.userRepo.save(teacher);
		
	}
	
	@EventListener(ApplicationReadyEvent.class)
	@Transactional
	public void loadStudents(ApplicationReadyEvent event) {
		
		Student s1 = new Student("Shivam","Tiwari","Btech","India");
		Student s2 = new Student("Prashant", "Meena","Mtech","Pakistan");
		Student s3 = new Student("Aditya","Pradhan","Msc","Japan");
		Student s4 = new Student("Gaurav","Sharma","MBBS","Londan");
		Student s5 = new Student("Grish","Mittal","BArch","Paris");
		Student s6 = new Student("Ashutosh","Dashowara","BCA","Bhutan");
		Student s7 = new Student("Rinku","Meena","Btech","India");
		
		this.repository.save(s1);
		this.repository.save(s2);
		this.repository.save(s3);
		this.repository.save(s4);
		this.repository.save(s5);
		this.repository.save(s6);
		this.repository.save(s7);
		
	}

}
