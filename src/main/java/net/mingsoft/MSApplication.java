package net.mingsoft;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

 
@SpringBootApplication
@ComponentScan(basePackages = {"net.mingsoft"})
//@MapperScan(basePackages={"**.dao","**.mapper"})
@ServletComponentScan(basePackages = {"net.mingsoft"})
@EnableSwagger2
@EnableScheduling //开启定时任务
@EnableTransactionManagement//开启事务管理
public class MSApplication {
	public static void main(String[] args) {
		SpringApplication.run(MSApplication.class, args);
	}

}