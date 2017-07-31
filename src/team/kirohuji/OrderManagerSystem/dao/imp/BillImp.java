package team.kirohuji.OrderManagerSystem.dao.imp;

import java.io.IOException;

import org.apache.ibatis.session.SqlSession;

import team.kirohuji.OrderManagerSystem.dao.IBill;
import team.kirohuji.OrderManagerSystem.entity.Bill;
import team.kirohuji.OrderManagerSystem.util.JdbcUtil;

public class BillImp implements IBill{
	private JdbcUtil jdbc = null;
	private SqlSession sqlSession;

	public BillImp() {
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
	public int insert(Bill record) {
		sqlSession = jdbc.getSqlSessionFactory().openSession();
		int id = sqlSession.insert("team.kirohuji.OrderManagerSystem.mapping.BillMapper.insert",record);
		sqlSession.commit();
		sqlSession.close();
		return id;
	}

	@Override
	public Bill selectById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int selectId() {
		sqlSession = jdbc.getSqlSessionFactory().openSession();
		int id = sqlSession.selectOne("team.kirohuji.OrderManagerSystem.mapping.BillMapper.selectId");
		sqlSession.commit();
		sqlSession.close();
		return id;
	}

	@Override
	public int updateById(Bill record) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
