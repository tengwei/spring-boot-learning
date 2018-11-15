package org.tw.mybatis.mapper;

import org.apache.ibatis.annotations.*;
import org.tw.mybatis.domain.UserInfo;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

import static org.apache.ibatis.type.JdbcType.TIMESTAMP;


@Mapper
public interface UserMapper {
	
	/**
	 * findOne
	 * @param id
	 * @return
	 */
	@Select(value = "select * from boot_user where id=#{id}")
	@Results({
			@Result(column = "create_time", property = "createTime", jdbcType = TIMESTAMP),
			@Result(column = "update_time", property = "updateTime", jdbcType = TIMESTAMP)
	})
    UserInfo findOne(int id);

	/**
	 * findAll
	 * @return
	 */
	List<UserInfo> findAll();

	@Insert(value = "insert into boot_user (name,tel,create_time,update_time) values(#{name},#{tel},#{createTime,jdbcType=TIMESTAMP},#{updateTime,jdbcType=TIMESTAMP})")
	void insert(UserInfo userInfo);
}
