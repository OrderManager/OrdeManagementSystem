package team.kirohuji.OrderManagerSystem.entity.dao;

import team.kirohuji.OrderManagerSystem.entity.Instruct;

public interface InstructMapper {
    int deleteById(Integer id);

    int insert(Instruct record);

    Instruct selectById(Integer id);

    int updateById(Instruct record);
}