package edu.neu.project.DAO;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import edu.neu.project.model.Admin;
import edu.neu.project.model.DrugCategory;
import edu.neu.project.model.Supplier;
import edu.neu.project.model.UserAccount;
import edu.neu.project.model.UserRole;

public class AdminDao extends DAO{
	
	public void checkAdminCreated() throws Exception {

		try {
			// begin();
			Session session = getSession();
			Query q = session.createQuery("from Admin");
			if (q.list().size() == 0) {
				Admin admin = new Admin();
				admin.setFirstName("admin fname");
				admin.setLastName("admin lname");

				UserAccount userAccount = new UserAccount();
				userAccount.setUsername("admin");
				userAccount.setPassword("a");
				userAccount.setPerson(admin);
				userAccount.setRole(UserRole.ROLE_ADMIN);

				session.beginTransaction();
				session.save(admin);
				session.save(userAccount);

				session.getTransaction().commit();
				session.close();

			}
			// commit();
		} catch (HibernateException e) {
			// rollback();
			throw new Exception("Could not get user" + e);
		}

	}

	public void addSupplierToDb(String firstname, String lastname,
			String username, String password) throws Exception {
		try {

			// begin();
			Session session = getSession();
			session.beginTransaction();

			Supplier supplier = new Supplier();
			supplier.setSupplierName(firstname +" "+ lastname);

			UserAccount userAccount = new UserAccount();
			userAccount.setUsername(username);
			userAccount.setPassword(password);
			userAccount.setRole(UserRole.ROLE_SUPPLIER);

			userAccount.setPerson(supplier);

			session.save(userAccount);
			session.save(supplier);

			session.getTransaction().commit();
			session.close();
			// commit();
		} catch (HibernateException e) {

			rollback();
			throw new Exception("Could not....supp!");
		}
	}

	public void addDrugCategoryToDb(DrugCategory drugCategory) throws Exception {
		try {

			// begin();
			Session session = getSession();
			session.beginTransaction();

			session.save(drugCategory);

			session.getTransaction().commit();
			session.close();

			// commit();
		} catch (HibernateException e) {

			rollback();
			throw new Exception("Could not....supp!");
		}
	}
	
}
