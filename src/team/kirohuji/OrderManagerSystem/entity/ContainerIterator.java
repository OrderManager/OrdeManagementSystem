package team.kirohuji.OrderManagerSystem.entity;

import java.util.Iterator;

public class ContainerIterator<T> implements Iterator<T>{
	private int current=0;
	private Container<T> container;
	ContainerIterator(Container<T> c){
		container=c;
	}
	@Override
	public boolean hasNext() {
		return current<container.getItems().size();
	}

	@Override
	public T next() {
		// TODO Auto-generated method stub
		return container.getItem(current++);
	}

}
