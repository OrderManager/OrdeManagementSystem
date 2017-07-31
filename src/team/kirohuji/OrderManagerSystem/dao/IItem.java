package team.kirohuji.OrderManagerSystem.dao;

import team.kirohuji.OrderManagerSystem.entity.Item;

public interface IItem {
    int deleteById(Integer id);

    int insert(Item record);

    Item selectById(Integer id);
    
    int updateById(Item record);

	int selectId();
}