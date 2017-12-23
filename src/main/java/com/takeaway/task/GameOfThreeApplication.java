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
 * this class is starting point of the application and configuration to run it
 * use java -jar jarname or mvn spring-boot:run
 * 
 * @author yahia
 *
 */
@SpringBootApplication()
public class GameOfThreeApplication {

	@Autowired
	Environment environment;

	/**
	 * declare first queue
	 * 
	 * @return Queue
	 */
	@Bean
	public Queue queueOne() {
		return new Queue(TaskUtils.QUEUEONE);
	}

	/**
	 * declare second queue
	 * 
	 * @return Queue
	 */
	@Bean
	public Queue queueTwo() {
		return new Queue(TaskUtils.QUEUETWO);
	}

	/**
	 * declare the first player consumer with profile name playone so u can run
	 * it independent
	 * 
	 * @return PlayerOneConsumer
	 */
	@Bean
	@Profile("playone")
	public PlayerOneConsumer playerOneConsumer() {
		return new PlayerOneConsumer();
	}

	/**
	 * declare the second player consumer with profile name playtwo so u can run
	 * it independent
	 * 
	 * @return PlayerTwoConsumer
	 */
	@Bean
	@Profile("playtwo")
	public PlayerTwoConsumer playerTwoConsumer() {
		return new PlayerTwoConsumer();
	}

	/**
	 * it's a open and close pattern so it can be player one or two
	 * 
	 * @return Producer
	 */

	@Bean
	public Producer producer() {
		return new Producer();
	}

	/**
	 * starting point of the application
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(GameOfThreeApplication.class, args);
	}

}
