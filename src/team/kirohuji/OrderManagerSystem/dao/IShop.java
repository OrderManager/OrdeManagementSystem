package team.kirohuji.OrderManagerSystem.dao;

import java.util.List;

import team.kirohuji.OrderManagerSystem.entity.Shop;

public interface IShop {
	int deleteById(Integer id);

	int insert(Shop record);

	Shop selectById(Integer id);

	int updateById(Shop record);

	List<Shop> selectAll();

	public Shop selectByName(String name);

	public void updateByNameOpen(String name);

	public void updateByNameClose(String name);
}