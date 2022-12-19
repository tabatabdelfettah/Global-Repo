package com.globale.book.config;

import java.util.concurrent.Executor;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import net.javacrumbs.shedlock.core.LockProvider;
import net.javacrumbs.shedlock.provider.jdbctemplate.JdbcTemplateLockProvider;
import net.javacrumbs.shedlock.spring.annotation.EnableSchedulerLock;

@Configuration
@EnableScheduling
@ConditionalOnProperty(name = "scheduler.enabled", matchIfMissing = true)
@EnableSchedulerLock(defaultLockAtMostFor = "10m")
@EnableAsync
public class SchedulerConfig implements AsyncConfigurer {
	
	
	  @Bean
	  public LockProvider lockProvider(DataSource dataSource) {
	    return new JdbcTemplateLockProvider(
	        JdbcTemplateLockProvider.Configuration.builder()
	        .withJdbcTemplate(new JdbcTemplate(dataSource))
	        .usingDbTime() // Works on Postgres, MySQL, MariaDb, MS SQL, Oracle, DB2, HSQL and H2
	        .build()
	    );
	  }
	  
	  // form method level 
	  @Bean(name = "threadPoolTaskExecutor")
	  public Executor asyncExecutor() {
	     ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
	     executor.setCorePoolSize(4);
	     executor.setMaxPoolSize(4);
	     executor.setQueueCapacity(50);
	     executor.setThreadNamePrefix("AsynchThread::");
	     executor.initialize();
	     return executor;
	  }
	  
	  
	  // for application level
	  @Override
	  public Executor getAsyncExecutor() {
	   ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
	   taskExecutor.setCorePoolSize(4);
	   taskExecutor.setMaxPoolSize(4);
	   taskExecutor.setQueueCapacity(50);
	   taskExecutor.initialize();
	   return taskExecutor;
	  }

}
