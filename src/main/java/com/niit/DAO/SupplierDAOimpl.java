package com.niit.DAO;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.model.Supplier;

@SuppressWarnings("deprecation")
	@Repository("supplierDAO")
	public class SupplierDAOimpl implements SupplierDAO {
		
		@Autowired
		private SessionFactory sessionFactory;
		public SupplierDAOimpl(SessionFactory sessionFactory){
			this.sessionFactory = sessionFactory;
		}
		@Transactional
		public void saveOrUpdate(Supplier supplier){
			sessionFactory.getCurrentSession().saveOrUpdate(supplier);
		} 
		@Transactional
		public void delete(String id){
		     Supplier supplier = new Supplier();
			supplier.setId(id);
			sessionFactory.getCurrentSession().delete(supplier);
		}
		@Transactional
		public Supplier get(String id){
			String hql = "from Supplier where id=" + "'" +id +"'";
			Query query = (Query)sessionFactory.getCurrentSession().createQuery(hql);
			List<Supplier> listSupplier = (List<Supplier>)query.list();
			if(listSupplier!= null && !listSupplier.isEmpty()){
				return listSupplier.get(0);
			}
			return null;
			}
		
		@Transactional
      public List<Supplier> list(){
			@SuppressWarnings("unchecked")
			List<Supplier> listSupplier = (List<Supplier>)
			sessionFactory.getCurrentSession()
			.createCriteria(Supplier.class)
			.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
			return listSupplier;
		}
}
