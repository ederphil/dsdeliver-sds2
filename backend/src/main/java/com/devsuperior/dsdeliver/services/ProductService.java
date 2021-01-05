package com.devsuperior.dsdeliver.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dsdeliver.dto.ProductDto;
import com.devsuperior.dsdeliver.entities.Product;
import com.devsuperior.dsdeliver.repositories.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Transactional(readOnly = true)
	public List<ProductDto> findAll() {

		List<Product> listProduct = productRepository.findAllByOrderByNameAsc();

		return listProduct.stream().map(x -> new ProductDto(x)).collect(Collectors.toList());

	}

}
