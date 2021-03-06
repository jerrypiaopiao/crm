package com.atguigu.crm.mappers;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.atuigu.crm.entity.Contact;
import com.atuigu.crm.entity.Customer;

/**
 *  create by 宁华勇
 * @author Administrator
 *
 */
public interface CustomerMapper {
	
	
	
	/**
	 *@Description:显示编辑页面  修改客户消息
	 *@Author:shanshan
	 *@Version:
	 *@CreateDate:29 Mar 2016
	 */
	public Customer getBeanById(@Param("customerId")long customerId);
	public void updateById(Customer customer);
	
	public List<Contact> getManagersById(@Param("customerId")long customerId);
	
	/**
	 * @author: ning_huayong
	 */
	public void add(Customer customer);
	public void delete(@Param("id")Integer id);
	public void update(Customer customer);
	public Customer getEntityById(@Param("id")Integer id);
	public List<Customer> getAll();
	
	/**
	 *@Description:获取所有的customer
	 *@Author:du_minchao
	 *@Version:
	 *@CreateDate:29 Mar 2016
	 *@return:List<Customer>
	 */
	public List<Customer> getAllCustomer();

	/**
	 *@Description: 获取数据库中所有customer的数量
	 *@Author:du_minchao
	 *@Version:
	 *@CreateDate:30 Mar 2016
	 *@return:int
	 */
	public int getAllCustomerNum();
	
	/**
	 *@Description:不带查询条件  分页显示客户信息
	 *@Author:du_minchao
	 *@Version:
	 *@CreateDate:30 Mar 2016
	 *@return:void
	 */
	public List<Customer> getPageWithoutCondition(@Param("firstIndex")int firstIndex, 
										@Param("lastIndex")int lastIndex);
	
	/**
	 *@Description: 根据查询条件获得所有的Customer数量
	 *@Author:du_minchao
	 *@CreateDate:30 Mar 2016
	 *@return:int
	 */
	public int getTotalNumCondition(Map<String, Object> mybatisParams);
	
	/**
	 *@Description:查询页面内容
	 *@Author:du_minchao
	 *@CreateDate:30 Mar 2016
	 *@return:List<Customer>
	 */
	public List<Customer> getContentCondition(Map<String, Object> mybatisParams);
	/**
	 * Description:将用户的状态status设置为删除，不是真的删除
	 * Author:ning
	 */
	public void setDeleteStatus(Customer customer);

}
