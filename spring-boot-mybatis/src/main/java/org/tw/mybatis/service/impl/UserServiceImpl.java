package org.tw.mybatis.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tw.mybatis.domain.UserInfo;
import org.tw.mybatis.mapper.UserMapper;
import org.tw.mybatis.service.UserService;


@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserMapper userMapper;

	@Override
	public List<UserInfo> findAll() {
		return userMapper.findAll();
	}

	@Override
	public UserInfo findOne(int id) {
		return userMapper.findOne(id);
	}

	@Override
	public void insert(UserInfo userInfo) {
		userMapper.insert(userInfo);
	}

}
