package edu.neu.project.DAO;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import edu.neu.project.model.DrugCategory;
import edu.neu.project.model.DrugProduct;

public class SupplierDao extends DAO{
	
public List getListOfDrugCategory() throws Exception{

		
		try {
        	
//        	begin();
            Session session = getSession();
            session.beginTransaction();
            
            Query q = session.createQuery("from DrugCategory");
            
            List<DrugCategory> drugCategoryList = new ArrayList<DrugCategory>();
            drugCategoryList=q.list();
            
            
            session.getTransaction().commit();
            session.close();
    		return drugCategoryList;
            
//            commit();
        } catch (HibernateException e) {
        	
            rollback();
            throw new Exception("Could not....supp!");
        }
}

public void addDrugToDb(String selectedDrugCategory, String name, Float price, Integer quantity, String desc, String path, String sample, String companyName) throws Exception{
	
	try {
    	
//    	begin();
        Session session = getSession();
        session.beginTransaction();
        
        Query q = session.createQuery("from DrugCategory where name = :drugcategoryname");
        q.setString("drugcategoryname", selectedDrugCategory);
        DrugCategory drugCategory = (DrugCategory)q.list().get(0);
        
        DrugProduct drugProduct = new DrugProduct();
//		music.setName(musicMap.get("name"));
//		music.setPrice(Float.parseFloat(musicMap.get("price")));
//		music.setQuantity(Integer.parseInt(musicMap.get("quantity")));
//		music.setDescription(musicMap.get("desc"));
//		//set fileuploadPath//


		drugProduct.setName(name);
		drugProduct.setPrice(price);
		drugProduct.setQuantity(quantity);
		drugProduct.setDescription(desc);
		drugProduct.setPath(path);
		drugProduct.setCompanyName(companyName);
		//set fileuploadPath//

        
        drugProduct.setDrugCategory(drugCategory);
        
        session.save(drugProduct);
        
        session.getTransaction().commit();
        session.close();
//        commit();
    } catch (HibernateException e) {
    	
        rollback();
        throw new Exception(e);
    
    }

	
}


}
