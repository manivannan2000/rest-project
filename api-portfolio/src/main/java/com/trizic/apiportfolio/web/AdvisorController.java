package com.trizic.apiportfolio.web;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.trizic.apiportfolio.domain.Advisor;
import com.trizic.apiportfolio.domain.AdvisorRepository;
import com.trizic.apiportfolio.domain.Model;
import com.trizic.apiportfolio.domain.ModelRepository;
import com.trizic.apiportfolio.exception.AdvisorNotFoundException;
import com.trizic.apiportfolio.model.AdvisorCreateRequest;
import com.trizic.apiportfolio.model.ModelCreateRequest;


@RestController
public class AdvisorController {
	
	@Autowired
	private AdvisorRepository advisorRepository;
	
	@Autowired
	private ModelRepository modelRepository;
	
 	@GetMapping("/api/v1/advisors")
    public List<Advisor> indexAdvisors() {
 		
 		Page<Advisor> advisors=advisorRepository.findAll(new PageRequest(0, 10));

 		return advisors.getContent();
 	}
 	
    @GetMapping("/api/v1/advisor/{advisorId}")
    public Model show(@PathVariable String advisorId) {
    	
    	if(true){
    		throw new AdvisorNotFoundException("advisor.not.found");
    	}else {
    		return null;	
    	}
    }
    
    @GetMapping("/api/v1/advisor/{advisorId}/model")
    public Model showModels(@PathVariable String advisorId, @RequestParam(required = false) int pageNumber, @RequestParam(required = false) int pageSize) {
    	
    	if(true){
    		throw new AdvisorNotFoundException("advisor.not.found");
    	}else {
    		return null;	
    	}
    	
    }
    
    
    @PostMapping("/api/v1/advisors")
    @ResponseStatus(HttpStatus.CREATED)
    public Advisor create(HttpServletRequest request, @RequestBody AdvisorCreateRequest advisorCreateRequest) {
    	Advisor advisor= new Advisor(advisorCreateRequest.getName(), request.getRemoteUser());
    	advisorRepository.save(advisor);
    	return advisor;
    }
    
    @PutMapping("/api/v1/advisor/{advisorId}/model")
    public Advisor createCity(HttpServletRequest request,  @PathVariable String advisorId, @RequestBody ModelCreateRequest modelCreateRequest) {
    	List<Advisor> advisorList= advisorRepository.findByPlannerId(advisorId);
    	
    	Model city= new Model(modelCreateRequest.getName());
    	modelRepository.save(city);
    	
    	Advisor advisor= advisorList.get(0);
    	
    	
    	advisor.getCities().add(city);
    	
    	advisorRepository.save(advisor);
    	
    	return advisor;
    }

}
