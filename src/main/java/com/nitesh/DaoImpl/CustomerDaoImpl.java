package com.nitesh.DaoImpl;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.nitesh.Dao.AbstractDao;
import com.nitesh.Dao.CustomerDao;
import com.nitesh.entity.Customer;

@Repository("customerDao")
public class CustomerDaoImpl extends AbstractDao<Customer> implements CustomerDao {

	public List<Customer> getCustomerList() {
		String query = "FROM Customer";
		@SuppressWarnings("unchecked")
		TypedQuery<Customer> queryObject = getSession().createQuery(query);
		return queryObject.getResultList();
		
//		List<Customer> customer = getSession().createQuery("FROM Customer").list();
//		return customer;
	}
	
	

}
