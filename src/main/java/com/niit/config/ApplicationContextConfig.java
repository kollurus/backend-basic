package com.niit.config;


import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.niit.DAO.CategoryDAO;
import com.niit.DAO.CategoryDAOimpl;
import com.niit.model.Category;
import com.niit.model.Product;
import com.niit.model.Supplier;
import com.niit.model.User;

	
	@Configuration
	@ComponentScan ("com.niit")
	@EnableTransactionManagement
	public class ApplicationContextConfig {
		
		@Bean(name = "dataSource")
		public DataSource getDataSource(){
			DriverManagerDataSource dataSource = new DriverManagerDataSource();
			dataSource.setDriverClassName("org.h2.Driver");
			dataSource.setUrl("jdbc:h2:~/project");
			dataSource.setUsername("sa");
			dataSource.setPassword("sa");
			return dataSource;
		}
		private Properties getHibernateProperties(){
			Properties properties = new Properties();
			properties.put("hibernate.show_sql", "true");
			properties.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
			return properties;
		}
		@SuppressWarnings("deprecation")
		@Autowired
		@Bean(name="sessionFactory")
		public SessionFactory getSessionFactory (DataSource h2dataSource){
			LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(h2dataSource);
			sessionBuilder.addProperties(getHibernateProperties());
			sessionBuilder.addAnnotatedClass(Category.class);
			sessionBuilder.addAnnotatedClass(Supplier.class);
			sessionBuilder.addAnnotatedClass(Product.class);
			sessionBuilder.addAnnotatedClass(User.class);
			System.out.println("session");
			return sessionBuilder.buildSessionFactory();
		}
	
		@SuppressWarnings("deprecation")
		@Autowired
		@Bean(name = "transactionManager")
		public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory){
			HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory);
			return transactionManager;
			
		}
		@Autowired
		@Bean(name ="categoryDAO")
		public CategoryDAO getCategoryDAO(SessionFactory sessionFactory){
		return new CategoryDAOimpl(sessionFactory);
	}
}
