package com.takeaway.task.consumers;

import org.springframework.beans.factory.annotation.Autowired;

import com.takeaway.task.producers.Producer;

/**
 * 
 * @author yahia
 *
 */
public abstract class AbsConsumer {

	@Autowired
	protected Producer producer;

}