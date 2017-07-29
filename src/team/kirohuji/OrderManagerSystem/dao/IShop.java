package team.kirohuji.OrderManagerSystem.dao;

import team.kirohuji.OrderManagerSystem.entity.Shop;

public interface IShop {
    int deleteById(Integer id);

    int insert(Shop record);

    Shop selectById(Integer id);

    int updateById(Shop record);
}