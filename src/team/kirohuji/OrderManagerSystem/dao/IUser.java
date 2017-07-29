package team.kirohuji.OrderManagerSystem.dao;

import team.kirohuji.OrderManagerSystem.entity.User;

public interface IUser {
	
    int deleteById(Integer id);

    int insert(User record);

    User selectById(Integer id);

    int updateById(User record);
    
	boolean selectUserByCodeAndPassword(User user);
}