	package edu.neu.project;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import edu.neu.project.DAO.OrderDao;
import edu.neu.project.model.Order;
import edu.neu.project.model.Payment;
import edu.neu.project.model.UserAccount;


@Controller
public class OrderConrtoller {
	@Autowired
	private OrderDao orderDao;
	
	@RequestMapping(value="addToCart.htm", method=RequestMethod.POST)
	public String addProductToCart(Model model,HttpServletRequest request, HttpServletResponse response, HttpSession session1) throws IOException{
		UserAccount user = (UserAccount)session1.getAttribute("user");
		if(user==null){
			return "403";
		}
		if(!user.getRole().equals("customer")){
			return "403";
		}
		HttpSession session=request.getSession();
		long productID=Long.parseLong(request.getParameter("productID"));
		int productQuantity=Integer.parseInt(request.getParameter("quantity"));
		Order customerOrder=orderDao.addProductToCart(productID,productQuantity,session);
		model.addAttribute("cart",customerOrder);
		return "AddToCart";
	}
	
	@RequestMapping(value="deleteOrderItem.htm", method=RequestMethod.GET)
	public String deleteOrderItemFromCart(Model model,HttpServletRequest request, HttpSession session)
	{
		//HttpSession session=request.getSession();
		UserAccount user = (UserAccount)session.getAttribute("user");
		if(user==null){
			return "403";
		}
		if(!user.getRole().equals("customer")){
			return "403";
		}
		long orderItemID=Long.parseLong(request.getParameter("orderItemid"));
		long orderID=Long.parseLong(request.getParameter("orderID"));
		
		Order customerOrder=orderDao.deleteOrderItem(orderItemID,orderID);
		model.addAttribute("cart", customerOrder);
		return "AddToCart";
	}
	
	@RequestMapping(value="proceedforpayment.htm", method=RequestMethod.GET)
	public String proceedForPayment(HttpServletRequest request, HttpServletResponse response, Model model, HttpSession session) throws IOException{
		UserAccount user = (UserAccount)session.getAttribute("user");
		if(user==null){
			return "403";
		}
		if(!user.getRole().equals("customer")){
			return "403";
		}
		String orderId = request.getParameter("orderId");
		model.addAttribute("orderId", orderId);
		if(orderDao.checkOrderForPayment(orderId)){
			String result = orderDao.checkForQuantity(orderId);
			if(result.equals("true")){
				return "makePayment";
			}else{
				model.addAttribute("productNotAvailable", result);
				return "NotSufficientQuantity";
			}
		}else{
			return "PaymentAlreadyMade";
		}
		
	}
	
	@RequestMapping(value="makepayment.htm", method=RequestMethod.POST)
	public String makePayement(HttpServletRequest request, HttpServletResponse response, Model model, HttpSession session) throws IOException{
		UserAccount user = (UserAccount)session.getAttribute("user");
		if(user==null){
			return "403";
		}
		if(!user.getRole().equals("customer")){
			return "403";
		}
		String orderId = request.getParameter("orderId");
		if(orderDao.checkOrderForPayment(orderId)){
			String name = request.getParameter("name");
			String[] card  = request.getParameterValues("cardNumber");
			String cardNumber = "";
			for(String c : card){
				cardNumber = cardNumber+c;
			}
			String month = request.getParameter("month");
			String year = request.getParameter("year");
			
			String date  = year+"/"+month;
			int cvv = Integer.parseInt(request.getParameter("cvv"));
			
			Payment payment = new Payment();
			payment.setCvv(cvv);
			payment.setDate(date);
			payment.setNumber(cardNumber);
			orderDao.makePayment(payment, orderId);
			
			String paymentId = String.valueOf(payment.getPaymentId());
			model.addAttribute("paymentId", orderId);
			model.addAttribute("orderId", orderId);
			
			return "PaymentSuccessful";
		}else{
			return "PaymentAlreadyMade";
		}
	}
	
	@RequestMapping(value="checkforquantity.do", method=RequestMethod.POST)
	public void checkQuantity(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException{
		int quantity = Integer.parseInt(request.getHeader("quantity"));
		String productId = request.getHeader("productId");
		UserAccount user = (UserAccount)session.getAttribute("user");
		
		PrintWriter out = response.getWriter();
		if(quantity<=0){
			out.print("false");
		}else
		if(orderDao.checkQuantity(productId, quantity, user)){
			out.print("true");
		}else{
			out.print("false");
		}
	}
	
	@RequestMapping(value = "viewPdf.do", method = RequestMethod.GET)
	public ModelAndView home(Locale locale, ModelMap model, HttpServletRequest request, HttpServletResponse response, HttpSession session)
			throws Exception {
		String orderId = request.getParameter("orderId");
		String paymentId = orderId;
		Order order = (Order)orderDao.getOrderObj(orderId);
		model.addAttribute("order", order);
		
		Payment payment = (Payment)orderDao.getPaymentObj(paymentId);
		model.addAttribute("payment", payment);
		
		float orderTotal = order.getOrderTotal();
		model.addAttribute("total", orderTotal);
		return new ModelAndView(new PDFView(), model);
	}
	
	@RequestMapping(value="updatequantity.htm", method=RequestMethod.GET)
	public String updateQuantity(HttpServletRequest request, Model model, HttpSession session, HttpServletResponse response) throws IOException{
		UserAccount user = (UserAccount)session.getAttribute("user");
		if(user==null){
			return "403";
		}
		if(!user.getRole().equals("customer")){
			return "403";
		}
		String orderItemId = request.getParameter("orderItemId");
		String quant = request.getParameter(orderItemId);
		String orderId = request.getParameter("orderId");
		int quantity = Integer.parseInt(quant);
		Order ord = orderDao.updateQuantity(quantity, orderItemId, orderId);
		
		if(ord==null){
			return "quantityNotAvailable";
		}else{
			model.addAttribute("cart", ord);
			return "AddToCart";
		}		
	}
	
	@RequestMapping(value="viewmycart.htm", method=RequestMethod.GET)
	public String viewMyCart(HttpSession session, Model model, HttpServletResponse response) throws IOException{
		UserAccount user = (UserAccount)session.getAttribute("user");
		if(user==null){
			return "403";
		}
		if(!user.getRole().equals("customer")){
			return "403";
		}
		String personId = String.valueOf(user.getPerson().getPersonID());
		Order cart = orderDao.getCartItem(personId);
		model.addAttribute("cart", cart);
		return "AddToCart";
	}
	
}