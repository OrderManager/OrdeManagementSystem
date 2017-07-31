package team.kirohuji.OrderManagerSystem.entity.dao;

import team.kirohuji.OrderManagerSystem.entity.Bill;

public interface IBill {
    int deleteById(Integer id);

    int insert(Bill record);

    Bill selectById(Integer id);

    int updateById(Bill record);
}