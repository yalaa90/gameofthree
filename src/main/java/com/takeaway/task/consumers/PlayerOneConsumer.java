package com.takeaway.task.consumers;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

import com.takeaway.task.models.GameModel;
import com.takeaway.task.utils.TaskUtils;

/**
 * first consumer based on queueone
 * 
 * @author yahia
 *
 */
@RabbitListener(queues = "queueone")
public class PlayerOneConsumer extends AbsConsumer {

	/**
	 * handling each request logging it and play the game and send it back to the second player 
	 * @param model
	 */
	@RabbitHandler
	public void process(GameModel model) {
		TaskUtils.log(String.valueOf(model), this);
		producer.play(TaskUtils.playing(model), TaskUtils.QUEUETWO);
	}
}
