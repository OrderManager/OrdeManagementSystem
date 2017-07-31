package team.kirohuji.OrderManagerSystem.dao;

import java.util.ArrayList;

import team.kirohuji.OrderManagerSystem.entity.Goods;
import team.kirohuji.OrderManagerSystem.entity.ShopAndGoods;

public interface IGoods {
	int deleteByName(String name);

	int deleteById(Integer id);

	int insert(Goods record);

	Goods selectById(Integer id);

	int updateById(Goods record);

	int selectId();
	ArrayList<Goods> selectAllByShopName(String address);
}