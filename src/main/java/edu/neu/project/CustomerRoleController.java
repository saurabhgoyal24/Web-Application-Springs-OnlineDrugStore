package edu.neu.project;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.neu.project.DAO.CustomerDao;
import edu.neu.project.DAO.SupplierDao;
import edu.neu.project.model.Customer;
import edu.neu.project.model.DrugProduct;
import edu.neu.project.model.Order;
import edu.neu.project.model.UserAccount;

@Controller
public class CustomerRoleController 
{
	@Autowired
	private CustomerDao customerDao;
	
	@Autowired
	private SupplierDao supplierDao;
	
	
	
	
	@RequestMapping(value = "getProducts.htm", method = RequestMethod.POST)
	public String getproducts(Model model,HttpServletRequest request, HttpSession session, HttpServletResponse response) throws Exception{
		UserAccount user = (UserAccount)session.getAttribute("user");
		if(user==null){
			return "403";
		}
		if(!user.getRole().equals("customer")){
			return "403";
		}
		String category=request.getParameter("category");
		String searchProduct=request.getParameter("productName");
		if(searchProduct==""){
		   List<DrugProduct> productList=customerDao.getProductList(category);
		   model.addAttribute("productList",productList);
		}
		else{
		  List<DrugProduct>getproductList=customerDao.getProduct(category,searchProduct);
		  model.addAttribute("productList",getproductList);
		}
		return "ProductDisplay";
		
	}
	
	@RequestMapping(value = "quickSearch.do", method = RequestMethod.POST)
	public String quickSearch(Model model, HttpServletRequest request, HttpSession session, HttpServletResponse response)
			throws Exception {
		UserAccount user = (UserAccount)session.getAttribute("user");
		if(user==null){
			return "403";
		}
		if(!user.getRole().equals("customer")){
			return "403";
		}
		String drugName = request.getParameter("drugName");
		float priceFrom;
		float priceTo;
		String pricef = request.getParameter("priceFrom");
		if (pricef.equals("")) {
			priceFrom = 0;
		} else {
			priceFrom = Float.parseFloat(pricef);
		}
		String pricet = request.getParameter("priceTo");
		if (pricet.equals("")) {
			priceTo = 0;
		} else {
			priceTo = Float.parseFloat(pricet);
		}
		String category = request.getParameter("category");
		System.out.println(category);
		String companyName = request.getParameter("company");
		// searchDao.quickSearch(chocolateName, category, priceFrom, priceTo,
		// companyName);
		model.addAttribute("companyList", customerDao.getCompanyList());
		model.addAttribute("categoryList", supplierDao.getListOfDrugCategory());
		List<DrugProduct> searchResult = customerDao.quickSearch(drugName, category, priceFrom, priceTo, companyName);
		model.addAttribute("productList", searchResult);
		return "ProductDisplay";
	}
	
	@RequestMapping(value = "getOrderHistory.htm", method = RequestMethod.POST)
	public String getOrderHistory(Model model,HttpServletRequest request, HttpServletResponse response,HttpSession session) throws Exception{
		UserAccount user = (UserAccount)session.getAttribute("user");
		if(user==null){
			return "403";
		}
		if(!user.getRole().equals("customer")){
			return "403";
		}
		String customerID=request.getParameter("customerID");
		List<Order>orderList=customerDao.getCustomerOrderHistory(customerID);
		model.addAttribute("orderList", orderList);
		if(orderList.size()==0)
		{
			return "NoOrderHistory";
		}
		return "OrderHistory";
		
	}
}