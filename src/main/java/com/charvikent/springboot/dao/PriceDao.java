package com.charvikent.springboot.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.charvikent.springboot.model.Price;
@Repository
@Transactional
public class PriceDao {
	
	@Autowired
	SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}
	 public void SaveOrUpdate(Price price)
	  {
		  sessionFactory.getCurrentSession().saveOrUpdate(price);
		  
	  }
	 @SuppressWarnings("unchecked")
	public List<Price> getPriceList()
	 {
		 String hql ="from Price";
		 
		 Query query=sessionFactory.getCurrentSession().createQuery(hql);//here persistent class name is Emp  

		 List<Price> list=query.list();
		 for(Price p:list){
		 System.out.println(p.getIname()+" "+p.getIprice());
		 
		 }
		return list;		 
	 }
	public void deletePriceRecordByid(String id) 
	{
		String hql="delete from  Price where id=:i";
		Query que =sessionFactory.getCurrentSession().createQuery(hql);
	    que.setParameter("i",Integer.parseInt(id));
	       
	      int status= que.executeUpdate();
	        if(status ==1)
	        	System.out.println("record deleted");
	        else
	        	System.out.println("record not deleted");	
		

		
	}
	@SuppressWarnings("unchecked")
	public Boolean checkRecordExistsOrNot(Price price) 
	{
		String hql="from Price where iprice=:ip";
		Query que =sessionFactory.getCurrentSession().createQuery(hql);
	    que.setParameter("ip",price.getIprice());
	    List<Price> list=que.list();
	    if(list.size()>0)
		 {
			return true;
		 }
		return false;
	}
}