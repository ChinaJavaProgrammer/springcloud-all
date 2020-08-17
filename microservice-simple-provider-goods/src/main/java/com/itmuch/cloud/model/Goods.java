package com.itmuch.cloud.model;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class Goods {

	
	private Integer id;
	
	private String goodsName;
	
	private BigDecimal price;
}
