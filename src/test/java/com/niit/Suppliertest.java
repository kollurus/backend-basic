package com.niit;


import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.DAO.SupplierDAO;
import com.niit.model.Supplier;;

public class Suppliertest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		@SuppressWarnings("resource")
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.scan("com.niit");
		context.refresh();
		SupplierDAO supplierDAO = (SupplierDAO) context.getBean("supplierDAO");
		Supplier supplier = (Supplier) context.getBean("supplier");
		supplier.setId("225hyd");
		supplier.setName("product");
		supplier.setAddress("hyderabad");
		supplierDAO.saveOrUpdate(supplier);
     //   supplierDAO.delete("");
	//System.out.println(	"No of suppliers" + supplierDAO.list().list;
		context.close();
	}

}

