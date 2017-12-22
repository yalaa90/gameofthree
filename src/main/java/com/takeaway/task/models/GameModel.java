package com.takeaway.task.models;

import java.io.Serializable;

/**
 * 
 * @author yahia
 *
 */
public class GameModel implements Serializable {

	private static final long serialVersionUID = -6537388877697790081L;

	private int addedNumber;

	private long resultNumber;

	/**
	 * @return the addedNumber
	 */
	public int getAddedNumber() {
		return addedNumber;
	}

	/**
	 * @param addedNumber
	 *            the addedNumber to set
	 */
	public void setAddedNumber(int addedNumber) {
		this.addedNumber = addedNumber;
	}

	/**
	 * @return the resultNumber
	 */
	public long getResultNumber() {
		return resultNumber;
	}

	/**
	 * @param resultNumber
	 *            the resultNumber to set
	 */
	public void setResultNumber(long resultNumber) {
		this.resultNumber = resultNumber;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "GameModel [addedNumber=" + addedNumber + ", resultNumber=" + resultNumber + "]";
	}
}
