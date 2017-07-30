package team.kirohuji.OrderManagerSystem.dao;

import team.kirohuji.OrderManagerSystem.entity.GoodsHasShop;

public interface IGoodsHasShop {
    int deleteByPrimaryKey(GoodsHasShop key);

    int insert(GoodsHasShop record);

    int insertSelective(GoodsHasShop record);
}