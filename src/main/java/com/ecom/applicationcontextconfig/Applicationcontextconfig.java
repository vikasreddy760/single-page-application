package com.ecom.applicationcontextconfig;

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
import com.ecom.dao.*;
import com.ecom.model.*;

@Configuration
@ComponentScan("com.ecom")
@EnableTransactionManagement
public class Applicationcontextconfig{
	
	
	
	@Bean(name = "datasource")
	public DataSource getDataSource() {
		DriverManagerDataSource datasource = new DriverManagerDataSource();
		datasource.setDriverClassName("org.h2.Driver");
		datasource.setUrl("jdbc:h2:tcp://localhost/~/test");
		datasource.setUsername("sa");
		datasource.setPassword("");
		System.out.println("Datasource");
		return datasource;

	}

	private Properties getHibernateProperties() {
		Properties properties = new Properties();
		properties.put("hibernate.show_sql", "true");
		properties.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
		/*properties.put("hbm2ddl.auto", "create");
		properties.put("hbm2ddl.auto", "update");*/
		System.out.println("Hibernate Properties");
		return properties;

	}

	@Autowired
	@Bean(name = "sessionFactory")
	public SessionFactory getSessionFactory(DataSource datasource) {
		LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(datasource);
		sessionBuilder.addProperties(getHibernateProperties());
		/*sessionBuilder.addAnnotatedClasses(Product.class);*/
		sessionBuilder.addAnnotatedClasses(Category.class);
		/*sessionBuilder.addAnnotatedClasses(Supplier.class);*/
		System.out.println("Session");
		
		return sessionBuilder.buildSessionFactory();
		
	}

	@Autowired
	@Bean(name = "transactionManager")
	public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory);
		System.out.println("Transaction");
		return transactionManager;
	}
	
    /*@Autowired
	@Bean(name = "productDAO")
	public ProductDAO getProductDao(SessionFactory sessionFactory) {
			return new ProductDAOimpl(sessionFactory);
	}
	*/

	@Autowired
	@Bean(name = "categoryDAO")
	public CategoryDAO getCategoryDao(SessionFactory sessionFactory) {
		return new CategoryDAOimpl(sessionFactory);
	   }
}

	/*  @Autowired
	@Bean(name = "supplierDAO")
	public SupplierDAO getSupplierDao(SessionFactory sessionFactory) {
			return new SupplierDAOimpl(sessionFactory);		
	     }
	  }
	*/  

	  
	/*@Autowired
	@Bean(name = "userDetailsDAO")
	public UserDetailsDAO getUserDetailsDAO(SessionFactory sessionFactory) {
			return new UserDetailsDAOImpl(sessionFactory);
	}
	@Autowired
	@Bean(name = "cartDAO")
	public CartDAO getCartDAO(SessionFactory sessionFactory) {
			return new CartDAOImpl(sessionFactory);
	}
    }*/