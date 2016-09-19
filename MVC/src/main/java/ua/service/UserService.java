package ua.service;

import java.util.List;

import ua.entity.User;

public interface UserService {

	public void save(String name);
	public void delete(int id);
	public List<User> findAll();
	public User findOne(int id);
}
