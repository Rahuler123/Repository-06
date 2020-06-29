package com.example.demoooo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;



@Service
public class ProductService {
	
	public List<Product> getAllProducts(){
		
		List<Product> listOfProducts = new ArrayList<>();
		listOfProducts.add(new Product(1,"Note1",20000));
		listOfProducts.add(new Product(2,"Note2",18000));
		listOfProducts.add(new Product(3,"Note3",18000));
		listOfProducts.add(new Product(4,"Note4",19000));
		
		return listOfProducts;
		
		
		
			}	
	

}
