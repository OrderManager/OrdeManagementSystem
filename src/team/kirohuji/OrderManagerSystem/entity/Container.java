package team.kirohuji.OrderManagerSystem.entity;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import team.kirohuji.OrderManagerSystem.dao.IContainer;
import team.kirohuji.OrderManagerSystem.dao.imp.InstructImp;

public class Container<T> implements IContainer<T>{
	private List<T> items;
	public List<T> getItems() {
		return items;
	}
	private static final int DEFAULT_CAPACITY = 10;
	public Container(){
		items=new ArrayList<T>();
	}
	@Override
	public void insert(T x) {
		items.add(x);
	}

	@Override
	public void remove(T x) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isEmpty() {
		return items.size()==0;
	}

	@Override
	public void makeEmpty() {
		items=null;
	}

	@Override
	public boolean contains(T x) {
		for(int i=0;i<items.size();i++){
			if(x.equals(items.get(i))){
				return true;
			}
		}
		return false;
	}

	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return new ContainerIterator(this);
	}
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return items.size();
	}
	@Override
	public T getItem(int index) {
		return items.get(index);
	}
	
	
}
