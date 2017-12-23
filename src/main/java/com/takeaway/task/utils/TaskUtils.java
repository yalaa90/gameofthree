/**
 * 
 */
package com.takeaway.task.utils;

import java.util.Random;
import java.util.logging.Logger;

import com.takeaway.task.models.GameModel;

/**
 * utils and generic class
 * 
 * @author yahia
 *
 */
public class TaskUtils {

	public static final String QUEUEONE = "queueone";
	public static final String QUEUETWO = "queuetwo";

	/**
	 * game rule accept negative and postive
	 * 
	 * @param number
	 * @return GameModel
	 */
	public static GameModel playing(GameModel xmodel) {
		long number = Math.abs(xmodel.getResultNumber());
		int added = (int) (-number - 1) % 3 + 1;
		System.out.println((-number - 1));
		GameModel model = new GameModel();
		model.setAddedNumber(added);
		model.setResultNumber((number + added) / 3);

		return model;
	}

	/**
	 * game valdiation
	 * 
	 * @param model
	 * @return boolean
	 */
	public static boolean validateModel(GameModel model) {
		return (null != model && model.getResultNumber() > 1);
	}

	/**
	 * ranadom number
	 * 
	 * @return long
	 */
	public static long generateRandomNumber() {
		Random rn = new Random();
		return rn.nextLong();
	}

	/**
	 * logging method
	 * 
	 * @param message
	 * @param object
	 */
	public static void log(String message, Object object) {
		Logger.getLogger(object.getClass().getName()).info(message);
	}

}
