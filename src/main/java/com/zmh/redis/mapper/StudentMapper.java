package com.zmh.redis.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zmh.redis.entry.Student;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface StudentMapper extends BaseMapper<Student> {
}
