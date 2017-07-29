package team.kirohuji.OrderManagerSystem.entity.dao;

import team.kirohuji.OrderManagerSystem.entity.Instruct;

public interface IInstruct {
    int deleteById(Integer id);

    int insert(IInstruct record);

    IInstruct selectById(Integer id);

    int updateById(IInstruct record);
}