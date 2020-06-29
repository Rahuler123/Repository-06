package com.example.demoooo;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import com.bezkoder.spring.data.jpa.pagingsorting.model.Tutorial;


@Repository
public interface restRepo extends CrudRepository<Product, Long>{

	
	Page<Tutorial> findByPublished(boolean published, Pageable pageable);

	  Page<Tutorial> findByTitleContaining(String title, Pageable pageable);
	  
	  List<Tutorial> findByTitleContaining(String title, Sort sort);
}
