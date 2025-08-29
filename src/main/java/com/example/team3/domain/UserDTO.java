package com.example.team3.domain;

import lombok.Data;

/*
 * 프론트 userInfo에 비밀번호는 들어가지 않는 게 좋을 것 같아서
 * password 제외한 DTO 만들었음
 */

@Data
public class UserDTO {
	
	private Integer id;
	private String username;
	private String nickname;
	private String email;
	private RoleType role;
	private OAuthType oauth;
	
	public UserDTO(User user) {
		this.id = user.getId();
		this.username = user.getUsername();
		this.nickname = user.getNickname();
		this.email = user.getEmail();
		this.role = user.getRole();
		this.oauth = user.getOauth();
	}
	
}
