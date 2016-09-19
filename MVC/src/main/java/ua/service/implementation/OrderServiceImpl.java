package ua.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ua.entity.Order;
import ua.entity.Smartphone;
import ua.entity.User;
import ua.repository.OrderRepository;
import ua.service.OrderService;

public class OrderServiceImpl implements OrderService{
	
	@Autowired
	private OrderRepository orderRepository;

	@Override
	public void save(User user, Smartphone smartphone) {
		Order order = new Order();
		order.setSmartphone(smartphone);
		order.setUser(user);
		orderRepository.save(order);
	}

	@Override
	public void delete(int id) {
		orderRepository.delete(id);
	}

	@Override
	public List<Order> findAll() {
		return orderRepository.findAll();
	}

	@Override
	public Order findOne(int id) {
		return orderRepository.findOne(id);
	}

}
