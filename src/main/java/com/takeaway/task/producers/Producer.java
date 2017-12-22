package com.takeaway.task.producers;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.takeaway.task.models.GameModel;
import com.takeaway.task.utils.TaskUtils;

/**
 * 
 * @author yahia
 *
 */
@Component
public class Producer implements CommandLineRunner {

	@Autowired
	private RabbitTemplate rabbitTemplate;

	/**
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
	 * 
	 */
	private void firstGame() {
		GameModel model = new GameModel();
		model.setResultNumber(TaskUtils.generateRandomNumber());
		play(model, TaskUtils.QUEUETWO);
	}
}
