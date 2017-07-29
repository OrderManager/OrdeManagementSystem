package team.kirohuji.OrderManagerSystem.dao;

import team.kirohuji.OrderManagerSystem.entity.Address;

public interface IAddress {
    int deleteById(Integer id);

    int insert(Address record);
    
    Address selectById(Integer id);

    int updateById(Address record);
}