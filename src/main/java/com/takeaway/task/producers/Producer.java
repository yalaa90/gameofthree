package com.takeaway.task.producers;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.takeaway.task.models.GameModel;
import com.takeaway.task.utils.TaskUtils;

/**
 * this class is the producer or player based on queue so it can be both. and it
 * contain the first game
 * 
 * @author yahia
 *
 */
@Component
public class Producer implements CommandLineRunner {

	@Autowired
	private RabbitTemplate rabbitTemplate;

	/**
	 * validate the data and play the game
	 * 
	 * @param model
	 * @param queueName
	 */
	public void play(GameModel model, String queueName) {
		if (TaskUtils.validateModel(model)) {
			rabbitTemplate.convertAndSend(queueName, model);
		}
	}

	/**
	 * game start
	 */
	@Override
	public void run(String... arg0) throws Exception {

		firstGame();
	}

	/**
	 * first game
	 */
	private void firstGame() {
		GameModel model = new GameModel();
		model.setResultNumber(TaskUtils.generateRandomNumber());
		play(model, TaskUtils.QUEUETWO);
	}
}
