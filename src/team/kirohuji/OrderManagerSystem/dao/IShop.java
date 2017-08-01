package team.kirohuji.OrderManagerSystem.dao;

import java.util.ArrayList;
import java.util.List;

import team.kirohuji.OrderManagerSystem.entity.Shop;

public interface IShop {
	int deleteById(Integer id);

	int insert(Shop record);

	Shop selectById(Integer id);

	int updateById(Shop record);

	List<Shop> selectAll();

	Shop selectByName(String name);

	int updateByNameOpen(String name);

	int updateByNameClose(String name);

	ArrayList<Shop> selectAllByUserId(Integer integer);
}