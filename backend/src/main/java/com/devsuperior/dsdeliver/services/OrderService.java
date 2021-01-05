package com.devsuperior.dsdeliver.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dsdeliver.dto.OrderDto;
import com.devsuperior.dsdeliver.entities.Order;
import com.devsuperior.dsdeliver.repositories.OrderRepository;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Transactional(readOnly = true)
	public List<OrderDto> findAll() {

		List<Order> listOrder = orderRepository.findAll();

		return listOrder.stream().map(x -> new OrderDto(x)).collect(Collectors.toList());

	}

	public List<OrderDto> findOrdersWithProducts() {

		List<Order> listOrder = orderRepository.findOrdersWithProducts();

		return listOrder.stream().map(x -> new OrderDto(x)).collect(Collectors.toList());

	}
}
