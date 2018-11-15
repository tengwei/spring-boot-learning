package org.tw.mybatis.web;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.tw.mybatis.domain.UserInfo;
import org.tw.mybatis.service.UserService;

import java.util.Date;

@Controller
public class IndexController {
	@Autowired
	private UserService userService;

	/**
	 * 查询用户
	 * @return
	 */
	@ResponseBody
	@RequestMapping("finduser")
	public String findUser() {
		return JSON.toJSONString(userService.findAll());
	}
	
	/**
	 * findById
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping("user/{id}")
	public String findById(@PathVariable int id) {
		return JSON.toJSONString(userService.findOne(id));
	}

	@GetMapping("/add")
	public void insert() {
		UserInfo userInfo = new UserInfo();
		userInfo.setCreateTime(new Date());
		userInfo.setUpdateTime(new Date());
		userInfo.setName("12");
		userInfo.setTel("23434343");

		userService.insert(userInfo);
	}
}
