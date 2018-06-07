package com.trizic.apiportfolio.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.trizic.apiportfolio.domain.Model;
import com.trizic.apiportfolio.domain.ModelRepository;
import com.trizic.apiportfolio.model.ModelCreateRequest;
import com.trizic.apiportfolio.model.ModelReq;
import com.trizic.apiportfolio.model.ModelRes;

import org.springframework.http.HttpStatus;


@RestController
public class ModelController {
	
	@Autowired
	private ModelRepository modelRepository;
	
	@Autowired
	ConversionService conversionService;
	
	 	@GetMapping("/models")
	    public List<Model> index() {
	 		
	 		Page<Model> cities=modelRepository.findAll(new PageRequest(0, 10));
	 		
	 		return cities.getContent();
	 	}

	    @GetMapping("/models/{id}")
	    public Model show(String id) {
	    	return null;
	    }

	    @PostMapping("/models")
	    @ResponseStatus(HttpStatus.CREATED)
	    public ModelRes create(@RequestBody ModelReq request) {

	    	Model model= conversionService.convert(request, Model.class);
	    	modelRepository.save(model);
	    	ModelRes modelRes=new ModelRes();
	    	
	    	
	    	
	    	return modelRes;
	    }

	    @PutMapping("/models/{id}")
	    public Model update() {
	    	return null;
	    }

	    @DeleteMapping("/models/{id}")
	    public void delete(String id) {
	    	
	    }
}
