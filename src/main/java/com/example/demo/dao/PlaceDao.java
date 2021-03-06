package com.example.demo.dao;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Place;
@Repository
@Transactional
public class PlaceDao {
	@Autowired
	SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sf) 
	{
		this.sessionFactory = sf;
	}
	 public void SaveOrUpdate(Place place)
	  {
		  sessionFactory.getCurrentSession().saveOrUpdate(place);  
	  }
	 @SuppressWarnings("unchecked")
		public Map<String, String> getPlaceList()
		 {
			 String hql ="from Place";
			 Query query=sessionFactory.getCurrentSession().createQuery(hql); 
			 List<Place> list=query.list();
			
			 
			 Map<String,String> placeMap = new  LinkedHashMap<String,String>();
			 for(Place p:list)
			 {
				 placeMap.put(String.valueOf(p.getId()), p.getName());
				 
				 
			 } 
			 
			 
			 
			return placeMap;					 
		 }
}
