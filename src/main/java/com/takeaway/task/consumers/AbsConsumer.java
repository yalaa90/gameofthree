package com.takeaway.task.consumers;

import org.springframework.beans.factory.annotation.Autowired;

import com.takeaway.task.producers.Producer;

/**
 * it's an parent to make my code more dry
 * 
 * @author yahia
 *
 */
public abstract class AbsConsumer {

	@Autowired
	protected Producer producer;

}