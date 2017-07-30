package team.kirohuji.OrderManagerSystem.dao.imp;

import java.io.IOException;

import org.apache.ibatis.session.SqlSession;

import team.kirohuji.OrderManagerSystem.dao.IGoodsHasShop;
import team.kirohuji.OrderManagerSystem.entity.GoodsHasShop;
import team.kirohuji.OrderManagerSystem.util.JdbcUtil;

public class GoodsHasShopImp implements IGoodsHasShop {
	private JdbcUtil jdbc = null;
	private SqlSession sqlSession;

	public GoodsHasShopImp() {
		try {
			jdbc = JdbcUtil.getInstance();
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public int deleteByPrimaryKey(GoodsHasShop key) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(GoodsHasShop record) {
		sqlSession = jdbc.getSqlSessionFactory().openSession();
		int rule = sqlSession.insert("team.kirohuji.OrderManagerSystem.mapping.GoodsHasShopMapper.insert", record);
		sqlSession.commit();
		sqlSession.close();
		return rule;
	}

	@Override
	public int insertSelective(GoodsHasShop record) {
		// TODO Auto-generated method stub
		return 0;
	}

}
