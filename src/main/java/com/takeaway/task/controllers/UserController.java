package com.takeaway.task.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.takeaway.task.models.GameModel;
import com.takeaway.task.producers.Producer;
import com.takeaway.task.utils.TaskUtils;

/**
 * rest controller for user who wanna play the game
 * 
 * @author yahia
 *
 */
@RestController()
public class UserController {

	@Autowired
	private Producer producer;

	/**
	 * post endpoint for the user game
	 * 
	 * @param model
	 * @return String
	 */
	@PostMapping("game")
	public String startGameByUser(@RequestBody GameModel model) {
		if (TaskUtils.validateModel(model)) {
			producer.play(model, TaskUtils.QUEUEONE);
			return "Game Started";
		}

		return "";

	}
}
