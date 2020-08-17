package com.itmuch.cloud.feign;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.itmuch.cloud.config.FeignConfiguration;
import com.itmuch.cloud.model.User;

import feign.hystrix.FallbackFactory;

@FeignClient(name="microservice-simple-provider-user",
configuration=FeignConfiguration.class,fallbackFactory=UserFeignClient.UserFeignClientImpl.class)
public interface UserFeignClient {
	
	
	
	@GetMapping(value="/user/{id}",produces="application/json;charset=utf-8")
	public User getUser(@PathVariable("id")Integer id);
	
	
	class UserFeignClientImpl implements FallbackFactory<UserFeignClient>{

		private static final Logger LOGGER = LoggerFactory.getLogger(UserFeignClientImpl.class);
		@Override
		public UserFeignClient create(Throwable cause) {
			return new UserFeignClient() {
				@Override
				public User getUser(Integer id) {
					LOGGER.info("fallback; reason was:", cause);
					User user = new User();
					user.setId(id);
					user.setAge(18);
					user.setName("断路器用户");
					return user;
				}
			};
		}

		
	}

}
