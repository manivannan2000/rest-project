package com.trizic.apiportfolio.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface ModelRepository extends CrudRepository<Model, Long> {

	Page<Model> findAll(Pageable pageable);

//	City findByNameAndCountryAllIgnoringCase(String name, String country);

}