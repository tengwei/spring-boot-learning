package org.tw.mybatis.service;

import org.tw.mybatis.domain.UserInfo;

import java.util.List;


public interface UserService {

	/**
	 * findAll
	 * @return
	 */
	List<UserInfo> findAll();
	
	UserInfo findOne(int id);

	void insert(UserInfo userInfo);
}
