package team.kirohuji.OrderManagerSystem.dao;

import java.util.ArrayList;

import team.kirohuji.OrderManagerSystem.entity.GoodsHasShop;
import team.kirohuji.OrderManagerSystem.entity.ShopAndGoods;

public interface IGoodsHasShop {
    int deleteByPrimaryKey(GoodsHasShop key);

    int insert(GoodsHasShop record);

    int insertSelective(GoodsHasShop record);
    
    ArrayList<ShopAndGoods> selectShopAndAllGoods(String name);
    
    ArrayList<ShopAndGoods> selectAllShopAndGoods();
}