package com.takeaway.task.gameofthree;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.takeaway.task.controllers.UserController;
import com.takeaway.task.models.GameModel;
import com.takeaway.task.producers.Producer;
import com.takeaway.task.utils.TaskUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GameofthreeApplicationTests {

	@Mock
	private Producer producer;

	@Autowired
	private RabbitTemplate rabbitTemplate;

	@Autowired
	private MockMvc mvc;

	@MockBean
	private UserController controller;

	/*
	 * @Autowired private TestRestTemplate restTemplate;
	 */
	@Test
	public void testValidation() {
		GameModel model = new GameModel();
		GameModel modelTrue = new GameModel();
		modelTrue.setResultNumber(50);
		assertFalse("validation faild", TaskUtils.validateModel(null));
		assertFalse("validation faild", TaskUtils.validateModel(model));
		assertTrue("validation faild", TaskUtils.validateModel(modelTrue));

	}

	@Test
	public void testPlaying() {

		GameModel model = new GameModel();
		model.setResultNumber(50);
		GameModel modelTest = TaskUtils.playing(model);
		assertEquals(1, modelTest.getAddedNumber());
		assertEquals(17L, modelTest.getResultNumber());
	}

	@Test
	public void testPlayingNegative() {

		GameModel model = new GameModel();
		model.setResultNumber(-50);
		GameModel modelTest = TaskUtils.playing(model);
		assertEquals(1, modelTest.getAddedNumber());
		assertEquals(17L, modelTest.getResultNumber());
	}

	@Test(expected = NullPointerException.class)
	public void testPlayingNullException() {
		TaskUtils.playing(null);

	}

	@Test
	public void testPlayingZero() {
		GameModel model = new GameModel();
		model.setResultNumber(0);
		GameModel modelTest = TaskUtils.playing(model);
		assertEquals(0, modelTest.getAddedNumber());
		assertEquals(0, modelTest.getResultNumber());
	}

	@Test
	public void testGenerateRandomNumber() {
		assertNotNull("testGenerateRandomNumber faild", TaskUtils.generateRandomNumber());
	}

	@Test
	public void integrationTestPLayerOne() {
		GameModel model = new GameModel();
		model.setResultNumber(50);
		rabbitTemplate.convertAndSend("queueone", model);
	}

	@Test
	public void integrationTestPLayerTwo() {
		GameModel model = new GameModel();
		model.setResultNumber(50);
		rabbitTemplate.convertAndSend("queueTwo", model);
	}

}
