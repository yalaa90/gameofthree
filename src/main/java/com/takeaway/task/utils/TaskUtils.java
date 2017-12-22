/**
 * 
 */
package com.takeaway.task.utils;

import java.util.Random;
import java.util.logging.Logger;

import com.takeaway.task.models.GameModel;

/**
 * @author yahia
 *
 */
public class TaskUtils {

	public static final String QUEUEONE = "queueone";
	public static final String QUEUETWO = "queuetwo";

	/**
	 * 
	 * @param number
	 * @return
	 */
	public static GameModel playing(GameModel xmodel) {
		long number = Math.abs(xmodel.getResultNumber());
		int added = (int) (-number - 1) % 3 + 1;
		GameModel model = new GameModel();
		model.setAddedNumber(added);
		model.setResultNumber((number + added) / 3);

		return model;
	}

	/**
	 * 
	 * @param model
	 * @return
	 */
	public static boolean validateModel(GameModel model) {
		return (null != model && model.getResultNumber() > 1);
	}

	/**
	 * 
	 * @return
	 */
	public static long generateRandomNumber() {
		Random rn = new Random();
		return rn.nextLong();
	}

	public static void log(String message, Object object) {
		Logger.getLogger(object.getClass().getName()).info(message);
	}

}
