package com.itmuch.cloud.base.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class BaseController {

	
	protected <T> ResponseEntity<T> response(T t){
		ResponseEntity<T> responseEntity = new ResponseEntity<T>(t, HttpStatus.OK);
		return responseEntity;
	}
}
