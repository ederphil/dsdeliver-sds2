package com.devsuperior.dsdeliver.services;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dsdeliver.dto.OrderDto;
import com.devsuperior.dsdeliver.dto.ProductDto;
import com.devsuperior.dsdeliver.entities.Order;
import com.devsuperior.dsdeliver.entities.OrderStatus;
import com.devsuperior.dsdeliver.entities.Product;
import com.devsuperior.dsdeliver.repositories.OrderRepository;
import com.devsuperior.dsdeliver.repositories.ProductRepository;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private ProductRepository productRepository;

	@Transactional(readOnly = true)
	public List<OrderDto> findAll() {

		List<Order> listOrder = orderRepository.findAll();

		return listOrder.stream().map(x -> new OrderDto(x)).collect(Collectors.toList());

	}

	@Transactional(readOnly = true)
	public List<OrderDto> findOrdersWithProducts() {

		List<Order> listOrder = orderRepository.findOrdersWithProducts();

		return listOrder.stream().map(x -> new OrderDto(x)).collect(Collectors.toList());

	}

	@Transactional
	public OrderDto insert(OrderDto dto) {

		Order order = new Order(null, dto.getAddress(), dto.getLatitude(), dto.getLongitude(), Instant.now(),
				OrderStatus.PENDING);

		for (ProductDto p : dto.getProducts()) {
			Product product = productRepository.getOne(p.getId());
			order.getProducts().add(product);
		}
		order = orderRepository.save(order);
		return new OrderDto(order);

	}

}
