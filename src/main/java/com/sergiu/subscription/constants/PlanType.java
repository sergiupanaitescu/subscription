package com.sergiu.subscription.constants;

public enum PlanType {

	MONTHLY(30),
	QUARTERLY(90),
	HALF_YEAR(180),
	YEAR(360);
	
	public final int daysValue;
	
	private PlanType(int daysValue) {
		this.daysValue = daysValue;
	}
}
