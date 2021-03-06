package com.atguigu.crm.services;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atguigu.crm.mappers.UserMapper;
import com.atuigu.crm.entity.SalesChance;
import com.atuigu.crm.entity.User;
import com.atuigu.crm.orm.Page;
import com.atuigu.crm.orm.PropertyFilter;

@Service
public class UserService {

	@Autowired
	private UserMapper userMapper;
	
	public User getUserByUserName(String username){
		return userMapper.getByName(username);
	}
	
	@Transactional(readOnly=true)
	public User login(String name, String password){
		User user = userMapper.getByName(name);
		
		//直接比较密码. 但实际开发中没有这么比较的!
		if(user != null 
				&& user.getEnabled() == 1
				&& user.getPassword().equals(password)){
			return user;
		}
		
		return null;
	}

	public List<User> getAllUser() {
		
		return userMapper.getAllUser();
	}

	public Page<User> getPage(String pageNoStr, Map<String, Object> params) {
		int pageNo=1;
		try {
			pageNo=Integer.parseInt(pageNoStr);
		} catch (NumberFormatException e) {
		}
		
		Map<String, Object> myBatisParam = PropertyFilter.parseRequestParams2MybatisParams(params);
		
		Page<User> page = new Page<>();
		page.setPageNo(pageNo);
		
		//1. 查询 totalElements
		int totalElements = userMapper.getTotalElements(myBatisParam);
		page.setTotalElements(totalElements);
		
		//2. 确定查询 content 需要的 firstIndex 和 endIndex
		int firstIndex = (page.getPageNo() - 1) * page.getPageSize() + 1;
		int endIndex = firstIndex + page.getPageSize();
		
		//3. 把 firstIndex 和 endIndex 加入到 mybatisParams 中
		myBatisParam.put("firstIndex", firstIndex);
		myBatisParam.put("endIndex", endIndex);
		
		//4. 调方法查询 content
		List<User> content = userMapper.getContent(myBatisParam);
		page.setContent(content);
		return page;
	}

	public User getBeanById(Integer userId) {
		return userMapper.getBeanById(userId);
	}
	
	public void delete(Integer id) {
		userMapper.delete(id);
	}

	public void update(User user) {
		userMapper.update(user);
	}

	public void create(User user) {
		userMapper.create(user);
	}
}
