package com.jtudy.java8_lambdas.testmodel;

public class Salary {

	private Double salary;
	private Integer period;

	public Salary(Double salary, Integer period) {
		this.salary = salary;
		this.period = period;
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	public Integer getPeriod() {
		return period;
	}

	public void setPeriod(Integer period) {
		this.period = period;
	}
}
