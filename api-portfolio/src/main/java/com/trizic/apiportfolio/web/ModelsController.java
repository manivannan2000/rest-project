package com.trizic.apiportfolio.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
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
import com.trizic.apiportfolio.domain.ModelType;
import com.trizic.apiportfolio.domain.RebalanceFrequency;
import com.trizic.apiportfolio.exception.AdvisorNotFoundException;
import com.trizic.apiportfolio.exception.ErrorDetails;
import com.trizic.apiportfolio.exception.InvalidTotalAllocationException;
import com.trizic.apiportfolio.model.AdvisorCreateRequest;
import com.trizic.apiportfolio.model.AssetAllocation;
import com.trizic.apiportfolio.model.ModelCreateRequest;
import com.trizic.apiportfolio.model.ModelReq;
import com.trizic.apiportfolio.model.ModelRes;
import com.trizic.apiportfolio.model.ModelsRes;
import com.trizic.apiportfolio.service.ModelService;
import com.trizic.apiportfolio.validator.AllocationTotalValidator;


@RestController
public class ModelsController {
	
	@Autowired
	private AdvisorRepository advisorRepository;
	
	@Autowired
	private ModelRepository modelRepository;
	
	@Autowired
	ConversionService conversionService;
	
    private final AllocationTotalValidator allocationTotalValidator;
    
    private final ModelService modelService;    

    @Autowired
    public ModelsController(ModelService modelService, AllocationTotalValidator allocationTotalValidator) {
        this.modelService=modelService;
        this.allocationTotalValidator = allocationTotalValidator;
    }
	
 	@GetMapping("/admin/api/v1/advisors")
    public List<Advisor> indexAdvisors() {
 		return modelService.indexAdvisors();
 	}
 	
    @PostMapping("/admin/api/v1/advisors")
    @ResponseStatus(HttpStatus.CREATED)
    public Advisor createAdvisors(HttpServletRequest request, @RequestBody AdvisorCreateRequest advisorCreateRequest) {
    	return modelService.createAdvisors(advisorCreateRequest.getName(), advisorCreateRequest.getLoginId());
    }
 	
    @GetMapping("/api/v1/advisor/{advisorId}/model")
    public ResponseEntity<?> showModels(@PathVariable String advisorId, @RequestParam(required = false) Integer pageNumber, @RequestParam(required = false) Integer pageSize) {
    	
    	ModelsRes modelsRes=modelService.findModels(advisorId, pageNumber, pageSize);
    	
    	return new ResponseEntity<>(modelsRes,HttpStatus.OK);
    }
    
    
    
    @PutMapping("/api/v1/advisor/{advisorId}/model")
    public ResponseEntity<?> createOrUpdateModel(HttpServletRequest request,  @PathVariable String advisorId, @RequestBody ModelReq modelReq,  Errors errors) {
    	allocationTotalValidator.validate(modelReq, errors);
    	if (errors.hasErrors()) {
    		throw new InvalidTotalAllocationException("allocation.percentage.total.invalid");
    	}

    	ModelRes modelRes= modelService.createOrUpdateModel(advisorId, modelReq);
    	
    	return new ResponseEntity<>(modelRes,HttpStatus.OK);
    	
    }

}
