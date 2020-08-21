package com.itmuch.cloud.feign;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name="microservice-simple-provider-goods")
public interface GoodsFeign {

}
