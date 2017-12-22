package com.takeaway.task;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;

import com.takeaway.task.consumers.PlayerOneConsumer;
import com.takeaway.task.consumers.PlayerTwoConsumer;
import com.takeaway.task.producers.Producer;
import com.takeaway.task.utils.TaskUtils;

/**
 * 
 * @author yahia
 *
 */
@SpringBootApplication()
public class GameOfThreeApplication {

	@Autowired
	Environment environment;

	/**
	 * 
	 * @return
	 */
	@Bean
	public Queue queueOne() {
		return new Queue(TaskUtils.QUEUEONE);
	}

	/**
	 * 
	 * @return
	 */
	@Bean
	public Queue queueTwo() {
		return new Queue(TaskUtils.QUEUETWO);
	}

	/**
	 * 
	 * @return
	 */
	@Bean
	@Profile("playone")
	public PlayerOneConsumer playerOneConsumer() {
		return new PlayerOneConsumer();
	}

	/**
	 * 
	 * @return
	 */
	@Bean
	@Profile("playtwo")
	public PlayerTwoConsumer playerTwoConsumer() {
		return new PlayerTwoConsumer();
	}

	/**
	 * 
	 * @return
	 */

	@Bean
	public Producer producer() {
		return new Producer();
	}

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(GameOfThreeApplication.class, args);
	}

}
