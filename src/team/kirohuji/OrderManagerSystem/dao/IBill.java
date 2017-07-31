package team.kirohuji.OrderManagerSystem.dao;

import team.kirohuji.OrderManagerSystem.entity.Bill;

public interface IBill {
    int deleteById(Integer id);

    int insert(Bill record);

    Bill selectById(Integer id);
    int selectId();
    int updateById(Bill record);
}