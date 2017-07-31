package team.kirohuji.OrderManagerSystem.dao.imp;

import java.io.IOException;

import org.apache.ibatis.session.SqlSession;

import team.kirohuji.OrderManagerSystem.dao.IItem;
import team.kirohuji.OrderManagerSystem.entity.Item;
import team.kirohuji.OrderManagerSystem.util.JdbcUtil;

public class ItemImp implements IItem{
	private JdbcUtil jdbc = null;
	private SqlSession sqlSession;

	public ItemImp() {
		try {
			jdbc = JdbcUtil.getInstance();
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public int deleteById(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(Item record) {
		sqlSession = jdbc.getSqlSessionFactory().openSession();
		int rule = sqlSession.insert("team.kirohuji.OrderManagerSystem.mapping.ItemMapper.insert", record);
		sqlSession.commit();
		sqlSession.close();
		return rule;
	}

	@Override
	public Item selectById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateById(Item record) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public int selectId() {
		sqlSession = jdbc.getSqlSessionFactory().openSession();
		int id = sqlSession.selectOne("team.kirohuji.OrderManagerSystem.mapping.ItemMapper.selectId");
		sqlSession.commit();
		sqlSession.close();
		return id;
	}
}
