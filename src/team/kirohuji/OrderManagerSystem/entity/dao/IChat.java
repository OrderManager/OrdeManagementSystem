package team.kirohuji.OrderManagerSystem.entity.dao;

import team.kirohuji.OrderManagerSystem.entity.Chat;

public interface IChat {
    int deleteById(Integer id);

    int insert(Chat record);

    Chat selectById(Integer id);

    int updateById(Chat record);
}