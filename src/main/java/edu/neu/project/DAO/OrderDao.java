package edu.neu.project.DAO;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.hibernate.Query;
import org.hibernate.Session;

import edu.neu.project.model.Customer;
import edu.neu.project.model.DrugProduct;
import edu.neu.project.model.Order;
import edu.neu.project.model.OrderItem;
import edu.neu.project.model.Payment;
import edu.neu.project.model.UserAccount;

public class OrderDao extends DAO {

	public Order addProductToCart(long ProductID, int quantity,
			HttpSession hSession) {
		Session session = getSession();
		session.beginTransaction();
		Query q = session
				.createQuery("from DrugProduct where drugId = :productID");
		q.setString("productID", String.valueOf(ProductID));
		DrugProduct selectedProduct = (DrugProduct) q.uniqueResult();

		OrderItem orderItem = new OrderItem();
		orderItem.setDrugProduct(selectedProduct);
		orderItem.setQuantity(quantity);
		orderItem.setTotalCost(quantity*selectedProduct.getPrice());
		
		
		UserAccount user = (UserAccount) hSession.getAttribute("user");
		Customer customer = (Customer) user.getPerson();
		String personId = String.valueOf(customer.getPersonID());

		Query q1 = session.createQuery("from Order where personID = :personId");
		q1.setString("personId", personId);

		List<Order> orders = q1.list();
		boolean b = true;
		for (Order o : orders) {
			if (o.getOrderStatus().equals("current")) {
				for(OrderItem oi : o.getOrderItem()){
					if(oi.getDrugProduct().equals(selectedProduct)){
						int newQuan = oi.getQuantity() + quantity;
						oi.setQuantity(newQuan);
						oi.setTotalCost(selectedProduct.getPrice() * newQuan);
						o.setOrderTotal(selectedProduct.getPrice() * quantity);
						session.save(oi);
						session.save(o);
						session.getTransaction().commit();
						
						session.close();
						b = false;
						return o;
					}
				}
				o.getOrderItem().add(orderItem);
				orderItem.setOrder(o);
				o.setOrderTotal(orderItem.getTotalCost());
				
				session.save(orderItem);
				session.save(o);
				// session.save(customer);
				session.getTransaction().commit();
				session.close();
				b = false;
				return o;
			}
		}

		if (b) {
			Order placedOrder = new Order();
			placedOrder.getOrderItem().add(orderItem);
			placedOrder.setOrderStatus("current");
			placedOrder.setCustomer(customer);
			placedOrder.setOrderTotal(orderItem.getTotalCost());
			session.save(placedOrder);
			customer.getOrder().add(placedOrder);
			session.save(customer);

			orderItem.setOrder(placedOrder);
			session.save(orderItem);
			session.save(placedOrder);
			session.getTransaction().commit();
			session.close();
			return placedOrder;
		}

		// session.save(customer);
		// session.save(orders);
		return null;

	}

	public Order deleteOrderItem(long orderItemID, long orderID) {
		Session session = getSession();
		session.beginTransaction();

		 Query q1 = session.createQuery("from Order where orderId=:orderID");
		 q1.setString("orderID", String.valueOf(orderID));
		 Order dbOrder=(Order)q1.uniqueResult();

		Query q2 = session
				.createQuery("from OrderItem where orderItemId = :orderItemID");
		q2.setString("orderItemID", String.valueOf(orderItemID));
		OrderItem dbOrderItem = (OrderItem) q2.uniqueResult();
		
		dbOrder.getOrderItem().remove(dbOrderItem);
		dbOrder.setOrderTotal(-1*dbOrderItem.getTotalCost());
		session.save(dbOrder);
		Query q = session
				.createQuery("delete OrderItem where orderItemId = :orderItemID");
		q.setString("orderItemID", String.valueOf(orderItemID));
		q.executeUpdate();

		
		session.getTransaction().commit();
		session.close();
		return dbOrder;
	}
	
	public boolean checkOrderForPayment(String orderId){
		Session session = getSession();
		session.beginTransaction();
		Query q = session.createQuery("from Order where orderId = :orderId");
		q.setString("orderId", orderId);
		Order order = (Order)q.uniqueResult();
		if(order.getOrderStatus().equals("current")){
			session.getTransaction().commit();
			session.close();
			return true;
		}else{
			session.getTransaction().commit();
			session.close();
			return false;
		}
		
	}
	
	public String checkForQuantity(String orderId){
		Session session = getSession();
		session.beginTransaction();
		Query q = session.createQuery("from Order where orderId = :orderId");
		q.setString("orderId", orderId);
		Order order = (Order)q.uniqueResult();
		
		String result = " ";
		
		for(OrderItem oi : order.getOrderItem()){
			if(oi.getQuantity()>oi.getDrugProduct().getQuantity()){
				result = result + oi.getDrugProduct().getName();
			}
		}
		if(result.equals(" ")){
			return "true";
		}else{
			return result;
		}
	}
	
	public void makePayment(Payment payment, String orderId){
		Session session = getSession();
		session.beginTransaction();
		Query q = session.createQuery("from Order where orderId = :orderId");
		q.setString("orderId", orderId);
		Order order = (Order)q.uniqueResult();
		order.setOrderStatus("paid");
		payment.setOrder(order);
		order.setPayment(payment);
		for(OrderItem oi : order.getOrderItem()){
			int oldQuantity = oi.getDrugProduct().getQuantity();
			int newQuantity = oldQuantity - oi.getQuantity();
			oi.getDrugProduct().setQuantity(newQuantity);
		}
		session.save(order);
		session.save(payment);
		session.getTransaction().commit();
		session.close();
	}
	
	
	
	public Order getOrderObj(String orderID) {
		Session session = getSession();
		session.beginTransaction();
		 Query q1 = session.createQuery("from Order where orderId=:orderID");
		 q1.setString("orderID", orderID);
		 Order orderObj = (Order)q1.uniqueResult();
		 
		 session.getTransaction().commit();
		 session.close();
		 return orderObj;
		 
	}
	
	public Payment getPaymentObj(String paymentID) {
		Session session = getSession();
		session.beginTransaction();
		Query q1 = session.createQuery("from Payment where paymentId=:orderID");
		q1.setString("orderID", paymentID);
		Payment paymentObj = (Payment)q1.uniqueResult();
		 session.getTransaction().commit();
		 session.close();
		return paymentObj;
	}
	
	public boolean checkQuantity(String productId, int quantity, UserAccount user){
		Session session = getSession();
		session.beginTransaction();
		Customer customer = (Customer)user.getPerson();
		Query q = session.createQuery("from DrugProduct where drugId = :drugId");
		q.setString("drugId", productId);
		DrugProduct drugProduct = (DrugProduct)q.uniqueResult();
		
		for(Order o : customer.getOrder()){
			if(o.getOrderStatus().equals("current")){
				for(OrderItem oi : o.getOrderItem()){
					if(oi.getDrugProduct().getDrugId()==(drugProduct.getDrugId())){
						int quan = oi.getQuantity();
						if(quantity+quan>drugProduct.getQuantity()){
							return false;
						}
					}
				}
			}
		}
		if(quantity<=drugProduct.getQuantity()&&quantity>=0){
			session.close();
			return true;
		}else{
			session.close();
			return false;
		}
	}
	
	public boolean checkQuantity1(String productId, int quantity){
		Session session = getSession();
		session.beginTransaction();
		Query q = session.createQuery("from DrugProduct where drugId = :drugId");
		q.setString("drugId", productId);
		DrugProduct drugProduct = (DrugProduct)q.uniqueResult();
		
		if(quantity<=drugProduct.getQuantity()&&quantity>=0){
			session.close();
			return true;
		}else{
			session.close();
			return false;
		}
	}
	
	
	public Order updateQuantity(int quantity, String orderItemId, String orderId){
		
		Session session = getSession();
		session.beginTransaction();
		
		Query q1 = session.createQuery("from Order where orderId=:orderID");
		q1.setString("orderID", orderId);
		Order orderObj = (Order)q1.uniqueResult();
		
		
		Query q2 = session.createQuery("from OrderItem where orderItemId = :orderItemID");
		q2.setString("orderItemID", orderItemId);
		OrderItem dbOrderItem = (OrderItem) q2.uniqueResult();
		String productId = String.valueOf(dbOrderItem.getDrugProduct().getDrugId());
		
		session.getTransaction().commit();
		session.close();
		
		if(quantity==0){
			Long ordItemId = Long.parseLong(orderItemId);
			deleteOrderItem(ordItemId, orderObj.getOrderId());
			return orderObj;
		}
		boolean b = checkQuantity1(productId, quantity);
		if(b){
			Session session1 = getSession();
			session1.beginTransaction();
			float orderTotal = orderObj.getOrderTotal();
			float oldOrderItemTotal = dbOrderItem.getTotalCost();
			float newOrderItemTotal = dbOrderItem.getDrugProduct().getPrice()*quantity;
			
			float newOrderTotal = orderTotal - oldOrderItemTotal + newOrderItemTotal;
			
			Query q3 = session1.createQuery("from OrderItem where orderItemId = :orderItemID");
			q3.setString("orderItemID", orderItemId);
			
			OrderItem dbOrderItem1 = (OrderItem) q3.uniqueResult();
			
			Query q4 = session1.createQuery("from Order where orderId=:orderID");
			q4.setString("orderID", orderId);
			Order orderObj1 = (Order)q4.uniqueResult();
			
			
			
			
			dbOrderItem1.setQuantity(quantity);
			dbOrderItem1.setTotalCost(newOrderItemTotal);
			orderObj1.setOrderTotal(newOrderItemTotal - oldOrderItemTotal);
			session1.save(dbOrderItem1);
			session1.save(orderObj1);
			session1.getTransaction().commit();
			session1.close();
			return orderObj1;
		}else{
			return null;
		}
	}
	
	public Order getCartItem(String personId){
		
		Session session = getSession();
		session.beginTransaction();
		Query q1 = session.createQuery("from Order where customer =:personID and orderStatus = :orderStatus");
		q1.setString("personID", personId);
		q1.setString("orderStatus", "current");
		Order orderObj = (Order)q1.uniqueResult();
		
		session.getTransaction().commit();
		session.close();
		return orderObj;
		
	}
	
}
