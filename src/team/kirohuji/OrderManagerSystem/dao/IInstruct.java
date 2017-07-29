package team.kirohuji.OrderManagerSystem.dao;

import java.util.List;

import team.kirohuji.OrderManagerSystem.entity.Instruct;

public interface IInstruct {
    int deleteById(Integer id);

    int insert(Instruct record);

    Instruct selectById(Integer id);

    int updateById(IInstruct record);
    
    Instruct selectByName(String command);
    List<Instruct> selectAll();
}