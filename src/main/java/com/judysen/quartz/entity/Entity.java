package com.judysen.quartz.entity;

import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;

public class Entity implements Serializable{

	/** serialVersionUID*/
	private static final long serialVersionUID = 1L;

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
