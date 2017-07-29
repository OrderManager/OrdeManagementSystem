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
		// TODO Auto-generated method stub
		return 0;
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
	public boolean selectUserByCodeAndPassword(User user) {
		sqlSession = jdbc.getSqlSessionFactory().openSession();
		User temp=sqlSession.selectOne("team.kirohuji.OrderManagerSystem.mapping.UserMapper.selectUserByCodeAndPassword", user);
		sqlSession.commit();

		sqlSession.close();
		if(temp==null){
			return false;
		}
		return true;
	}


}
