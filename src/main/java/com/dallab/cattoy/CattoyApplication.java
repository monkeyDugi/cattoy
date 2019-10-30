package com.dallab.cattoy;

import com.github.dozermapper.core.DozerBeanMapper;
import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CattoyApplication {

	public static void main(String[] args) {
		SpringApplication.run(CattoyApplication.class, args);
	}

//	자동으로 Dozer 생성
	@Bean
	public Mapper modelmapper() {
		return DozerBeanMapperBuilder.buildDefault();
	}
}
