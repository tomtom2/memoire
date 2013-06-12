package com.github.tomtom2.services;

public interface ObjectModelObservable {

	public void update(String pathToParse);
	public void addObserver(ObjectModelObserver observer);
	public void removeObservers();
}
