package org.tw.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.tw.mybatis.domain.UserInfo;


@Mapper
public interface UserMapper {
	
	/**
	 * findOne
	 * @param id
	 * @return
	 */
	@Select(value="select *from boot_user where id=#{id}")
    UserInfo findOne(int id);

	/**
	 * findAll
	 * @return
	 */
	List<UserInfo> findAll();
}
