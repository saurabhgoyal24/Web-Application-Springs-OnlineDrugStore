package edu.neu.project.DAO;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import edu.neu.project.model.DrugCategory;
import edu.neu.project.model.DrugProduct;
import edu.neu.project.model.Order;

public class CustomerDao extends DAO{
	
	public List getProduct(String category,String product) throws Exception{
		Session session = getSession();
		try {
        	
            session.beginTransaction();
            Query q = session.createQuery("from DrugCategory where name = :categoryname");
            q.setString("categoryname", category);
            
            DrugCategory drugCategory=(DrugCategory)q.uniqueResult();
            long drugCategoryId = drugCategory.getDrugCategoryId();
            
            Query q1 = session.createQuery("from DrugProduct where drugCategory =:categoryId  and name= :product");
            q1.setString("categoryId", String.valueOf(drugCategoryId));
            q1.setString("product",product);
            List<DrugProduct> productList=new ArrayList<DrugProduct>();
            productList=q1.list();
            session.getTransaction().commit();
            return productList;
            
         }catch (HibernateException e){
            rollback();
            throw new Exception(e);
          }finally{
			session.close();
		}
     }
	
	public List getProductList(String category) throws Exception{
		Session session = getSession();
		try {
            session.beginTransaction();
            Query q = session.createQuery("from DrugCategory where name = :categoryname");
            q.setString("categoryname", category);
            
            DrugCategory drugCategory=(DrugCategory)q.uniqueResult();
            long categoryId=drugCategory.getDrugCategoryId();
            
            Query q1=session.createQuery("from DrugProduct where drugCategory =:categoryId ");
            q1.setString("categoryId", String.valueOf(categoryId));
            List<DrugProduct> productList=new ArrayList<DrugProduct>();
            productList=q1.list();
            return productList;
		}catch (HibernateException e){
          rollback();
          throw new Exception(e);
         }finally{
			session.close();
		}
    }
	
	public List<DrugProduct> quickSearch(String drugName, String category, float priceFrom, float priceTo, 
			String companyName)throws Exception {
		Session session=null;
		try {
			// begin();
			session = getSession();
			session.beginTransaction();
			long categoryId=0;
			if (category != "") {
				Query q = session
						.createQuery("from DrugCategory where name = :categoryName");
				q.setString("categoryName", category);
				DrugCategory dbCategory = (DrugCategory) q.uniqueResult();
				categoryId = dbCategory.getDrugCategoryId();
			}
//				Criteria cr = session.createCriteria(Chocolate.class);
//				cr.add(Restrictions.like("companyName", companyName + "%"));
//				cr.add(Restrictions.like("category", category + "%"));
				Criteria cr;
				if (drugName.equals("") && category.equals("")
						&& priceFrom == 0 && priceTo == 0
						&& companyName.equals("")) {
					cr = session.createCriteria(DrugProduct.class);
				} else {
					cr = session.createCriteria(DrugProduct.class);
					if (drugName != "") {
						cr.add(Restrictions.like("name", drugName
								+ "%"));
					}
//					if (category != "") {
//						cr.add(Restrictions.like("category", categoryId));
//					}
					if (companyName != "") {
						cr.add(Restrictions.like("companyName", companyName
								+ "%"));
					}
					if (priceFrom == 0 && priceTo == 0) {
						cr.add(Restrictions.gt("price", priceFrom));
					}
					if (priceFrom == 0 && priceTo != 0) {
						cr.add(Restrictions
								.between("price", priceFrom, priceTo));
					}
					
					if(priceFrom != 0 && priceTo != 0){
						cr.add(Restrictions
								.between("price", priceFrom, priceTo));
					}
				}
			
			session.getTransaction().commit();
			List<DrugProduct> drugProductList = cr.list();
			System.out.println("erdswfe");
			return drugProductList;
		} catch (HibernateException e) {
			// rollback();
			throw new Exception(e);
		}
		finally {
			session.close();
		}
	}
	
	public List<String> getCompanyList() throws Exception {
		Session session=null;
		try{
		session = getSession();
		session.beginTransaction();
		Query q = session.createQuery("select distinct companyName from DrugProduct");
		session.getTransaction().commit();
		return q.list();
		}
		catch (HibernateException e) {
			rollback();
			throw new Exception(e);
		}
		finally {
			session.close();
		}
	}
	
	public List getCustomerOrderHistory(String customerID) throws Exception{
		Session session = getSession();
		try {
        	session.beginTransaction();
            Query q = session.createQuery("from Order where customer = :customerID and orderStatus = :paid");
            q.setString("customerID", customerID);
            q.setString("paid", "paid");
            List<Order>orderList=q.list();
            session.getTransaction().commit();
            return orderList;
		}catch (HibernateException e) {
				rollback();
				throw new Exception(e);
		}finally {
			session.close();
		}
		
	}
}
