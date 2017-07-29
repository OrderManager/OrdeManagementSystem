package team.kirohuji.OrderManagerSystem.dao.imp;

import java.io.IOException;

import org.apache.ibatis.session.SqlSession;

import team.kirohuji.OrderManagerSystem.dao.IUser;
import team.kirohuji.OrderManagerSystem.entity.Instruct;
import team.kirohuji.OrderManagerSystem.entity.User;
import team.kirohuji.OrderManagerSystem.util.JdbcUtil;

public class UserImp implements IUser{
	private JdbcUtil jdbc = null;
	private SqlSession sqlSession;

	public UserImp() {
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
	public int insert(User record) {
		sqlSession = jdbc.getSqlSessionFactory().openSession();
		int rule=sqlSession.insert("team.kirohuji.OrderManagerSystem.mapping.UserMapper.insert", record);
		sqlSession.commit();
		sqlSession.close();
		return rule;
	}

	@Override
	public User selectById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateById(User record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public User selectUserByCodeAndPassword(User user) {
		sqlSession = jdbc.getSqlSessionFactory().openSession();
		User temp=sqlSession.selectOne("team.kirohuji.OrderManagerSystem.mapping.UserMapper.selectUserByCodeAndPassword", user);
		sqlSession.commit();
		sqlSession.close();
		if(temp==null){
			return null;
		}
		return temp;
	}

	@Override
	public String selectByCodeGainRule(User user) {
		sqlSession = jdbc.getSqlSessionFactory().openSession();
		String rule=sqlSession.selectOne("team.kirohuji.OrderManagerSystem.mapping.UserMapper.selectByCodeGainRule", user);
		sqlSession.commit();
		sqlSession.close();
		return rule;
	}

	@Override
	public User selectByCodeGainStatus(String code) {
		sqlSession = jdbc.getSqlSessionFactory().openSession();
		User temp=sqlSession.selectOne("team.kirohuji.OrderManagerSystem.mapping.UserMapper.selectByCodeGainStatus", code);
		sqlSession.commit();
		sqlSession.close();
		return temp;
	}

	@Override
	public int selectId() {
		sqlSession = jdbc.getSqlSessionFactory().openSession();
		int id=sqlSession.selectOne("team.kirohuji.OrderManagerSystem.mapping.UserMapper.selectId");
		sqlSession.commit();
		sqlSession.close();
		return id;
	}


}
