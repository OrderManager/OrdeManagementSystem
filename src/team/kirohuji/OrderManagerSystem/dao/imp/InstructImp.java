package team.kirohuji.OrderManagerSystem.dao.imp;

import java.io.IOException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import team.kirohuji.OrderManagerSystem.entity.Instruct;
import team.kirohuji.OrderManagerSystem.entity.dao.IInstruct;
import team.kirohuji.OrderManagerSystem.util.JdbcUtil;

public class InstructImp implements IInstruct{
	private JdbcUtil jdbc = null;
	private SqlSession sqlSession;

	public InstructImp() {
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
	public int insert(Instruct record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Instruct selectById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateById(IInstruct record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Instruct selectByName(String command) {
		sqlSession = jdbc.getSqlSessionFactory().openSession();
		Instruct instruct=sqlSession.selectOne("team.kirohuji.OrderManagerSystem.mapping.InstructMapper.selectByName", command);
		sqlSession.commit();
		sqlSession.close();
		return instruct;
	}

	@Override
	public List<Instruct> selectAll() {
		sqlSession = jdbc.getSqlSessionFactory().openSession();
		List<Instruct> instructs=sqlSession.selectList("team.kirohuji.OrderManagerSystem.mapping.InstructMapper.selectAll");
		sqlSession.commit();
		sqlSession.close();
		return instructs;
	}

}
