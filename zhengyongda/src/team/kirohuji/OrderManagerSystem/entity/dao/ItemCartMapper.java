package team.kirohuji.OrderManagerSystem.entity.dao;

import team.kirohuji.OrderManagerSystem.entity.ItemCart;

public interface ItemCartMapper {
    int deleteById(Integer id);

    int insert(ItemCart record);

    ItemCart selectById(Integer id);

    int updateById(ItemCart record);
}