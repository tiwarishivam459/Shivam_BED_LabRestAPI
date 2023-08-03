package com.greatlearning.sms.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.greatlearning.sms.model.DomainUserDetails;
import com.greatlearning.sms.repository.UserRepository;

@Service
public class DomainUserDetailsService implements UserDetailsService{

	@Autowired
	private UserRepository repository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		
		return this.repository.findByUsername(username)
				.map(DomainUserDetails::new)
				.orElseThrow(() -> new UsernameNotFoundException("Invalid Username passed."));
	}

}