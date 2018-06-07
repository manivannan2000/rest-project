package com.trizic.apiportfolio.converter;

import org.springframework.core.convert.converter.Converter;

import com.trizic.apiportfolio.domain.Model;
import com.trizic.apiportfolio.model.ModelRes;

public class ModelToModelResConverter implements Converter<Model, ModelRes> {

	@Override
	public ModelRes convert(Model model) {
		ModelRes modelRes= new ModelRes();
		modelRes.setGuid(model.getGuid());
		return modelRes;
	}

}
