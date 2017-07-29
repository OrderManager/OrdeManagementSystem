package team.kirohuji.OrderManagerSystem.dao;

import team.kirohuji.OrderManagerSystem.entity.Goods;

public interface IGoods {
    int deleteById(Integer id);

    int insert(Goods record);

    Goods selectById(Integer id);

    int updateById(Goods record);
}