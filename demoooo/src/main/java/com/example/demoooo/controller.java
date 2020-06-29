package com.example.demoooo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bezkoder.spring.data.jpa.pagingsorting.model.Tutorial;



@RestController
public class controller {
	
	@GetMapping("/get")
	public String countryy() {
		return "Home";
	}
	@Autowired
	ProductService productService;
	
	
	@GetMapping("/products")
	public List<Product> getAllProducts(){
		
		return productService.getAllProducts();
		
	}
	@GetMapping("/tutorials/published")
	  public ResponseEntity<Map<String, Object>> findByPublished(
	      @RequestParam(defaultValue = "0") int page,
	      @RequestParam(defaultValue = "3") int size) {
	    
	    try {
	      List<Tutorial> tutorials = new ArrayList<Tutorial>();
	      Pageable paging = PageRequest.of(page, size);

	      Page<Tutorial> pageTuts = tutorialRepository.findByPublished(true, paging);
	      tutorials = pageTuts.getContent();

	      if (tutorials.isEmpty()) {
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	      }

	      Map<String, Object> response = new HashMap<>();
	      response.put("tutorials", tutorials);
	      response.put("currentPage", pageTuts.getNumber());
	      response.put("totalItems", pageTuts.getTotalElements());
	      response.put("totalPages", pageTuts.getTotalPages());

	      return new ResponseEntity<>(response, HttpStatus.OK);
	    } catch (Exception e) {
	      return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
	    }
	  }

	  @GetMapping("/t/{id}")
	  public ResponseEntity<Tutorial> getTutorialById(@PathVariable("id") long id) {
	    Optional<Tutorial> tutorialData = tutorialRepository.findById(id);

	    if (tutorialData.isPresent()) {
	      return new ResponseEntity<>(tutorialData.get(), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }

	  @PostMapping("/t")
	  public ResponseEntity<Tutorial> createTutorial(@RequestBody Tutorial tutorial) {
	    try {
	      Tutorial _tutorial = tutorialRepository.save(new Tutorial(tutorial.getTitle(), tutorial.getDescription(), false));
	      return new ResponseEntity<>(_tutorial, HttpStatus.CREATED);
	    } catch (Exception e) {
	      return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
	    }
	  }

	  @PutMapping("/t/{id}")
	  public ResponseEntity<Tutorial> updateTutorial(@PathVariable("id") long id, @RequestBody Tutorial tutorial) {
	    Optional<Tutorial> tutorialData = tutorialRepository.findById(id);

	    if (tutorialData.isPresent()) {
	      Tutorial _tutorial = tutorialData.get();
	      _tutorial.setTitle(tutorial.getTitle());
	      _tutorial.setDescription(tutorial.getDescription());
	      _tutorial.setPublished(tutorial.isPublished());
	      return new ResponseEntity<>(tutorialRepository.save(_tutorial), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }

}
