package team.kirohuji.OrderManagerSystem.entity;

import java.util.Iterator;

import org.apache.ibatis.session.SqlSession;

import team.kirohuji.OrderManagerSystem.entity.dao.IContainer;

public class Container<T> implements IContainer<T>{
	private T[] items;
	private int currentSize;
	private int size;
	private static final int DEFAULT_CAPACITY = 10;
	public Container(SqlSession sqlSession){
		importFromDatabase(sqlSession);
	}
	public Container(){
		size=0;
		currentSize=0;
	}
	@Override
	public void insert(T x) {
		items[size++]=x;
	}

	@Override
	public void remove(T x) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isEmpty() {
		return size==0;
	}

	@Override
	public void makeEmpty() {
		items=null;
		size=0;
	}

	@Override
	public void importFromDatabase(SqlSession sqlSession) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean contains(T x) {
		for(int i=0;i<=size;i++){
			if(x.equals(items[i])){
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
		return size;
	}
	@Override
	public T getItem(int index) {
		return items[index];
	}
	
	
}
