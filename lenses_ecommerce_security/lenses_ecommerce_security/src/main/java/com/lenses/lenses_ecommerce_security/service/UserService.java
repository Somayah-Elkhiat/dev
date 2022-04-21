package com.lenses.lenses_ecommerce_security.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lenses.lenses_ecommerce_security.domain.Role;
import com.lenses.lenses_ecommerce_security.domain.User;

public interface UserService {
	User saveUser(User user);
	Role saveRole(Role role);
	void addRoleToUser(String username, String roleName);
	User getUser(String username);
	User getUser(HttpServletRequest request, HttpServletResponse response);
	List<User>getUsers();

}
