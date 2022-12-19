package com.globale.book.Service;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;

@Component

public class PriceSchedule {
	Logger log = LoggerFactory.getLogger(PriceSchedule.class);

	@Scheduled(fixedRate = 20099990)
	// @Scheduled(cron = "@hourly")
	//@SchedulerLock(name = "bookComputePrice")
	@Async
	public void computePrice() throws InterruptedException {

		Thread.sleep(4000);

		log.info("compute price >> " + LocalDateTime.now());
	}
	@Scheduled(fixedRate = 20999900)
	// @Scheduled(cron = "@hourly")
	//@SchedulerLock(name = "bookComputeDiscount")
	//@Async
	public void computeDiscount() throws InterruptedException {

		Thread.sleep(4000);

		log.info("compute Discount >> " + LocalDateTime.now());
	}
}
