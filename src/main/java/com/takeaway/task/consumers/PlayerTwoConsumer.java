package com.takeaway.task.consumers;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

import com.takeaway.task.models.GameModel;
import com.takeaway.task.utils.TaskUtils;

@RabbitListener(queues = "queuetwo")
public class PlayerTwoConsumer extends AbsConsumer {

	@RabbitHandler
	public void process(GameModel model) {
		TaskUtils.log(String.valueOf(model), this);
		producer.play(TaskUtils.playing(model), TaskUtils.QUEUEONE);
	}
}
