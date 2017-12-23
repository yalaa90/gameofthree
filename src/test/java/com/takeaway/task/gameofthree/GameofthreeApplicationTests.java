package com.takeaway.task.gameofthree;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import com.takeaway.task.controllers.UserController;
import com.takeaway.task.models.GameModel;
import com.takeaway.task.producers.Producer;
import com.takeaway.task.utils.TaskUtils;

/**
 * unit test and integration test
 * 
 * @author yahia
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class GameofthreeApplicationTests {

	@Mock
	private Producer producer;

	@Autowired
	private RabbitTemplate rabbitTemplate;

	@MockBean
	private UserController controller;

	@Autowired
	TestRestTemplate testRestTemplate;

	@LocalServerPort
	private int port;

	/**
	 * test validation
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

	/**
	 * test real game
	 */
	@Test
	public void testPlaying() {

		GameModel model = new GameModel();
		model.setResultNumber(50);
		GameModel modelTest = TaskUtils.playing(model);
		assertEquals(1, modelTest.getAddedNumber());
		assertEquals(17L, modelTest.getResultNumber());
	}

	/**
	 * test Negative
	 */
	@Test
	public void testPlayingNegative() {

		GameModel model = new GameModel();
		model.setResultNumber(-50);
		GameModel modelTest = TaskUtils.playing(model);
		assertEquals(1, modelTest.getAddedNumber());
		assertEquals(17L, modelTest.getResultNumber());
	}

	/**
	 * test null
	 */
	@Test(expected = NullPointerException.class)
	public void testPlayingNullException() {
		TaskUtils.playing(null);

	}

	/**
	 * test zero
	 */
	@Test
	public void testPlayingZero() {
		GameModel model = new GameModel();
		model.setResultNumber(0);
		GameModel modelTest = TaskUtils.playing(model);
		assertEquals(0, modelTest.getAddedNumber());
		assertEquals(0, modelTest.getResultNumber());
	}

	/**
	 * test Random number
	 */
	@Test
	public void testGenerateRandomNumber() {
		assertNotNull("testGenerateRandomNumber faild", TaskUtils.generateRandomNumber());
	}

	/**
	 * integration test
	 */
	@Test
	public void integrationTestPLayerOne() {
		GameModel model = new GameModel();
		model.setResultNumber(50);
		rabbitTemplate.convertAndSend("queueone", model);
	}

	/**
	 * integration test
	 */
	@Test
	public void integrationTestPLayerTwo() {
		GameModel model = new GameModel();
		model.setResultNumber(50);
		rabbitTemplate.convertAndSend("queueTwo", model);
	}

	/**
	 * web integration test
	 */
	@Test
	public void integrationTestUserGame() {
		GameModel model = new GameModel();
		model.setResultNumber(50);

		assertThat(testRestTemplate.postForObject("http://localhost:" + port + "/game", model, String.class));
	}

}
