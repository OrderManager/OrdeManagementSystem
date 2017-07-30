package team.kirohuji.OrderManagerSystem.dao.imp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import team.kirohuji.OrderManagerSystem.dao.IShop;
import team.kirohuji.OrderManagerSystem.entity.Instruct;
import team.kirohuji.OrderManagerSystem.entity.Shop;
import team.kirohuji.OrderManagerSystem.entity.User;
import team.kirohuji.OrderManagerSystem.util.JdbcUtil;

public class ShopImp implements IShop{
	private JdbcUtil jdbc = null;
	private SqlSession sqlSession;

	public ShopImp() {
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
	public int insert(Shop record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Shop selectById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateById(Shop record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<Shop> selectAll() {
		sqlSession = jdbc.getSqlSessionFactory().openSession();
		List<Shop> shops=sqlSession.selectList("team.kirohuji.OrderManagerSystem.mapping.ShopMapper.selectAll");
		sqlSession.commit();
		sqlSession.close();
		return (ArrayList<Shop>) shops;
	}
	@Override
	public void updateByNameOpen(String name) {
		sqlSession = jdbc.getSqlSessionFactory().openSession();
		sqlSession.update("team.kirohuji.OrderManagerSystem.mapping.ShopMapper.updateByNameOpen", name);
		sqlSession.commit();
		sqlSession.close();
		
	}
	@Override
	public void updateByNameClose(String name) {
		sqlSession = jdbc.getSqlSessionFactory().openSession();
		sqlSession.update("team.kirohuji.OrderManagerSystem.mapping.InstructMapper.updateByNameClose", name);
		sqlSession.commit();
		sqlSession.close();
		
	}
	@Override
	public Shop selectByName(String name) {
		sqlSession = jdbc.getSqlSessionFactory().openSession();
		Shop temp=sqlSession.selectOne("team.kirohuji.OrderManagerSystem.mapping.ShopMapper.selectByName", name);
		sqlSession.commit();
		sqlSession.close();
		return temp;
	}

}
