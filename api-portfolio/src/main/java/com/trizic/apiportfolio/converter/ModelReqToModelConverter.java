package com.trizic.apiportfolio.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;

import com.trizic.apiportfolio.domain.Model;
import com.trizic.apiportfolio.domain.ModelType;
import com.trizic.apiportfolio.domain.RebalanceFrequency;
import com.trizic.apiportfolio.model.AssetAllocation;
import com.trizic.apiportfolio.model.ModelReq;

public class ModelReqToModelConverter implements Converter<ModelReq, Model> {

	@Override
	public Model convert(ModelReq modelReq) {
    	Model model= new Model(modelReq.getName());
    	model.setDescription(modelReq.getDescription());
    	model.setCashHoldingPercentage(modelReq.getCashHoldingPercentage());
    	model.setDriftPercentage(modelReq.getDriftPercentage());
    	model.setCreatedOn(modelReq.getCreatedOn());
    	model.setModelType(ModelType.valueOf(modelReq.getModelType().toString()));
    	model.setRebalanceFrequency(RebalanceFrequency.valueOf(modelReq.getRebalanceFrequency().toString()));
    	
    	
    	model.setAssetAllocations(new ArrayList<com.trizic.apiportfolio.domain.AssetAllocation>());
    	for(AssetAllocation assetAllocationModel: modelReq.getAssetAllocations()){
    		com.trizic.apiportfolio.domain.AssetAllocation assetAllocationEntity= new com.trizic.apiportfolio.domain.AssetAllocation(assetAllocationModel.getSymbol(), assetAllocationModel.getPercentage());
    		model.addAssetAllocation(assetAllocationEntity);
    	}
		return model;
	}

}
