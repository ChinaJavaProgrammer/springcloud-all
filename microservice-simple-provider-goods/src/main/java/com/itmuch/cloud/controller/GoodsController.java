package com.itmuch.cloud.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.FeignClientsConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itmuch.cloud.feign.UserFeignClient;
import com.itmuch.cloud.model.Goods;
import com.itmuch.cloud.model.User;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;


@RestController
@RequestMapping("/goods")
@RefreshScope
public class GoodsController    {


	
	private UserFeignClient userFeignClient;
	@Autowired
	public void setUserFeignClient(UserFeignClient userFeignClient) {
		this.userFeignClient = userFeignClient;
	}
	
	@GetMapping("/getGoods")
	public String getGoods() {
		return "goods-server";
	}
	
	@GetMapping(value="/{id}", produces="application/json;charset=utf-8")
	public User getUser(@PathVariable("id")Integer id) {
		return userFeignClient.getUser(id);
	}
	
	@HystrixCommand(fallbackMethod="hystrix")
	@GetMapping("/testHystrix/{id}")
	public Goods testHystrix(@PathVariable("id")Integer id) {
		if(id%2==0) {
			int i = 1/0;
		}
		Goods goods = new Goods();
		goods.setGoodsName("测试商品");
		goods.setPrice(new BigDecimal(200.8989899898));
		return goods;
	}
	
	public Goods hystrix(@PathVariable("id")Integer id) {
		Goods goods = new Goods();
		goods.setGoodsName("断路器默认商品");
		goods.setPrice(new BigDecimal(200.8989899898));
		return goods;
	}
}
