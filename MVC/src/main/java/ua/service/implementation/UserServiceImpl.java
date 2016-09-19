package ua.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ua.entity.User;
import ua.repository.UserRepository;
import ua.service.UserService;

public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public void save(String name) {
		User user = new User();
		user.setName(name);
		userRepository.save(user);
	}

	@Override
	public void delete(int id) {
		userRepository.delete(id);
	}

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public User findOne(int id) {
		return userRepository.findOne(id);
	}

}
