package edu.neu.project.DAO;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import edu.neu.project.model.DrugCategory;
import edu.neu.project.model.DrugProduct;


public class DrugProductDao extends DAO {
	
	public DrugProduct getSelectedProduct(long productID) throws Exception{
		
		Session session = getSession();
		try {
            session.beginTransaction();
            Query q = session.createQuery("from DrugProduct where drugId = :productID");
            q.setString("productID", String.valueOf(productID));
            DrugProduct selectedProduct=(DrugProduct)q.uniqueResult();
            session.getTransaction().commit();
            return selectedProduct;
         } catch (HibernateException e) {
        	rollback();
            throw new Exception(e);
        }
		finally{
			session.close();
		}
	}
	
	public String getProductSuggestions(String category, String productName){
		
		Session session = getSession();
		session.beginTransaction();
		Query q = session.createQuery("from DrugCategory where name = :category");
		q.setString("category", category);
		DrugCategory dc = (DrugCategory)q.uniqueResult();
		
		String drugCatId = String.valueOf(dc.getDrugCategoryId());
		
		Query q1 = session.createQuery("from DrugProduct where drugCategory = :catId and name like '"+productName+"%'");
		q1.setString("catId", drugCatId);
		List<DrugProduct> products = q1.list();
		String suggestion = "";
		
		for(DrugProduct dp : products){
			
			suggestion = suggestion + dp.getName() +"\r\n";
		}
		
		return suggestion;
	}
	
}
