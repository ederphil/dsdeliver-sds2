package com.devsuperior.dsdeliver.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.devsuperior.dsdeliver.dto.OrderDto;
import com.devsuperior.dsdeliver.services.OrderService;

@RestController
@RequestMapping(value = "/orders")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@GetMapping
	public ResponseEntity<List<OrderDto>> findAll() {

		List<OrderDto> listOrders = orderService.findOrdersWithProducts();
		return ResponseEntity.ok().body(listOrders);

	}

	@PostMapping
	public ResponseEntity<OrderDto> insert(@RequestBody OrderDto dto) {

		dto = orderService.insert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}}").buildAndExpand(dto.getId()).toUri();
		// return ResponseEntity.ok().body(dto);
		return ResponseEntity.created(uri).body(dto);

	}

	@PutMapping("/{id}/delivered")
	public ResponseEntity<OrderDto> setDeliverd(@PathVariable Long id) {
		
		OrderDto orderDto = orderService.setDelivered(id);
		return ResponseEntity.ok().body(orderDto);
	}
}
