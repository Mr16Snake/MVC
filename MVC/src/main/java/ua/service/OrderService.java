package ua.service;

import java.util.List;

import ua.entity.Order;
import ua.entity.Smartphone;
import ua.entity.User;

public interface OrderService {

	public void save(User user, Smartphone smartphone);
	public void delete(int id);
	public List<Order> findAll();
	public Order findOne(int id);
}
