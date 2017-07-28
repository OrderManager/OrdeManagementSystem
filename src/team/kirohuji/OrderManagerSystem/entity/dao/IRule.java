package team.kirohuji.OrderManagerSystem.entity.dao;

import team.kirohuji.OrderManagerSystem.entity.Rule;

public interface IRule {
    int deleteById(Integer id);

    int insert(Rule record);

    Rule selectById(Integer id);

    int updateById(Rule record);
}