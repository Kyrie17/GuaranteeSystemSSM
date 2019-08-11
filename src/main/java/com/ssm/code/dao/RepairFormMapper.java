package com.ssm.code.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface RepairFormMapper {
    int judgeFinshInform(String stuId);
}
