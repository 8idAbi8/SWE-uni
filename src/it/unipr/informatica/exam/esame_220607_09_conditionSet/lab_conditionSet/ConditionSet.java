package it.unipr.informatica.exam.esame_220607_09_conditionSet.lab_conditionSet;

public interface ConditionSet {
	public void add(Object object);
	public Object await() throws InterruptedException;
}
