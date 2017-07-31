package team.kirohuji.OrderManagerSystem.entity.dao;

import team.kirohuji.OrderManagerSystem.entity.GoodsHasShopKey;

public interface GoodsHasShopMapper {
    int deleteByPrimaryKey(GoodsHasShopKey key);

    int insert(GoodsHasShopKey record);

    int insertSelective(GoodsHasShopKey record);
}