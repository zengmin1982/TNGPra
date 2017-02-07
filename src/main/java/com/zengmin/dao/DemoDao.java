package com.zengmin.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.zengmin.common.Mapper;

@Mapper
public interface DemoDao {
    List<Map<String, Object>> getType(@Param(value = "type") String type);

}
