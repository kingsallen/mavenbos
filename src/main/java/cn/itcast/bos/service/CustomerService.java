package cn.itcast.bos.service;

import cn.itcast.bos.domain.Customer;

import java.util.List;

public interface CustomerService {
	/**
	 * 查询没有关联定区的客户
	 * 
	 * @return
	 */
	public List<Customer> findNoAssociationCustomers();

	/**
	 * 查询已经关联到定区的客户
	 * 
	 * @param decidedZoneId
	 * @return
	 */
	public List<Customer> findHasAssociationCustomers(String decidedZoneId);

	/**
	 * 将这些客户关联 定区上
	 * 
	 * @param customerIds
	 * @param decidedZoneId
	 */
	public void assignCustomersToDecidedZone(String[] customerIds, String decidedZoneId);
}
