package com.example.team3.service;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.team3.domain.User;
import com.example.team3.domain.UserDTO;
import com.example.team3.domain.exception.UserException;
import com.example.team3.config.WebConfig;
import com.example.team3.domain.RoleType;

import com.example.team3.repository.UserRepository;


import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	public User getUser(String username) {
		User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username + " 사용자를 찾을 수 없습니다."));
		
		 return user;
	}
	
	@Transactional
	public void insert(User user) {
		
		
		if(user.getNickname()==null || user.getNickname().isEmpty()) {
			throw new IllegalArgumentException("닉네임은 필수입니다."); //IllegalArgumentException : 
			//메서드나 생성자에 전달된 인자가 부적절하거나 잘못되었을때 발생 (잘못된입력값을 감지함)
		}
		
		if (userRepository.existsByNickname(user.getNickname())) { // 중복 체크
	        throw new IllegalArgumentException("이미 사용중인 닉네임입니다");
	    }
		
		user.setPassword(passwordEncoder.encode(user.getPassword())); 
		
		user.setRole(RoleType.USER);
		
		
		userRepository.save(user);
	}
	
	@Transactional
	public String updateUser(String username, User user) {
	    User findUser = userRepository.findByUsername(username).orElseThrow(() -> {
	        return new UserException(user.getId() + "번 회원은 없음");
	    });
	    findUser.setNickname(user.getNickname()); // 여분의 괄호 제거
	    if (user.getPassword() != null && !user.getPassword().isEmpty()) {
	        findUser.setPassword(passwordEncoder.encode(user.getPassword()));
	    }
	    findUser.setEmail(user.getEmail());
	    userRepository.save(findUser);
	    return "회원정보 수정 완료";
	}
	
	@Transactional
	public void deleteUser(Integer id) {
		User user = userRepository.findById(id)
				.orElseThrow(()->new UserException(id+"번 회원은 없음"));
		userRepository.delete(user);
		System.out.println("User deleted:"+id);
	}
}
