package team.kirohuji.OrderManagerSystem.entity.dao;

import java.util.Iterator;

import org.apache.ibatis.session.SqlSession;

public interface IContainer<T> {
	void insert(T x);
	void remove(T x);
	boolean isEmpty();
	void makeEmpty();
	boolean contains(T x);
	Iterator<T> iterator();
	int size();
	T getItem(int index);
	void importFromDatabase(SqlSession sqlSession);
}
