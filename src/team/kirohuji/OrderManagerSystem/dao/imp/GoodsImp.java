package team.kirohuji.OrderManagerSystem.dao.imp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import team.kirohuji.OrderManagerSystem.dao.IGoods;
import team.kirohuji.OrderManagerSystem.entity.Goods;
import team.kirohuji.OrderManagerSystem.util.JdbcUtil;

public class GoodsImp implements IGoods{
	private JdbcUtil jdbc = null;
	private SqlSession sqlSession;

	public GoodsImp() {
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
	public int insert(Goods record) {
		sqlSession = jdbc.getSqlSessionFactory().openSession();
		int rule=sqlSession.insert("team.kirohuji.OrderManagerSystem.mapping.GoodsMapper.insert", record);
		sqlSession.commit();
		sqlSession.close();
		return rule;
	}

	@Override
	public Goods selectById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateById(Goods record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int selectId() {
		sqlSession = jdbc.getSqlSessionFactory().openSession();
		int id=sqlSession.selectOne("team.kirohuji.OrderManagerSystem.mapping.GoodsMapper.selectId");
		sqlSession.commit();
		sqlSession.close();
		return id;
	}

	public int deleteByName(String name) {
		sqlSession = jdbc.getSqlSessionFactory().openSession();
		int rule=sqlSession.update("team.kirohuji.OrderManagerSystem.mapping.GoodsMapper.deleteByName", name);
		sqlSession.commit();
		sqlSession.close();
		return rule;
	}

	public ArrayList<Goods> selectAllByShopName(String address) {
		sqlSession = jdbc.getSqlSessionFactory().openSession();
		List<Goods> rule=sqlSession.selectList("team.kirohuji.OrderManagerSystem.mapping.GoodsMapper.selectAllByShopName", address);
		sqlSession.commit();
		sqlSession.close();
		return (ArrayList<Goods>) rule;
	}



}
