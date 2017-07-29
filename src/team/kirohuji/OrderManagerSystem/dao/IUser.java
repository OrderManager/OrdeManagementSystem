package team.kirohuji.OrderManagerSystem.dao;

import team.kirohuji.OrderManagerSystem.entity.User;

public interface IUser {

	int deleteById(Integer id);

	int insert(User record);

	int selectId();

	User selectById(Integer id);

	int updateById(User record);

	User selectUserByCodeAndPassword(User user);

	String selectByCodeGainRule(User user);

	User selectByCodeGainStatus(String code);
}