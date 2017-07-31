package team.kirohuji.OrderManagerSystem.dao.imp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import team.kirohuji.OrderManagerSystem.dao.IGoodsHasShop;
import team.kirohuji.OrderManagerSystem.entity.GoodsHasShop;
import team.kirohuji.OrderManagerSystem.entity.ShopAndGoods;
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

	public ArrayList<ShopAndGoods> selectShopAndAllGoods(String name) {
		sqlSession = jdbc.getSqlSessionFactory().openSession();
		List<ShopAndGoods> lists= sqlSession.selectList("team.kirohuji.OrderManagerSystem.mapping.GoodsHasShopMapper.selectShopAndAllGoods", name);
		sqlSession.commit();
		sqlSession.close();
		return (ArrayList<ShopAndGoods>) lists;
	}

	public ArrayList<ShopAndGoods> selectAllShopAndGoods() {
		sqlSession = jdbc.getSqlSessionFactory().openSession();
		List<ShopAndGoods> lists= sqlSession.selectList("team.kirohuji.OrderManagerSystem.mapping.GoodsHasShopMapper.selectAllShopAndAllGoods");
		sqlSession.commit();
		sqlSession.close();
		return (ArrayList<ShopAndGoods>) lists;
	}

}
