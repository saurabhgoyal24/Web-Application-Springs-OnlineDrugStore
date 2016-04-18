package edu.neu.project;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.neu.project.DAO.DrugProductDao;
import edu.neu.project.model.DrugProduct;
import edu.neu.project.model.UserAccount;

@Controller
public class ProductController 
{
	@Autowired
	private DrugProductDao drugProductDao;
	
	@RequestMapping(value="showProductDetails.htm", method=RequestMethod.GET)
	public String showProductDetails(Model model,HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception{
		UserAccount user = (UserAccount)session.getAttribute("user");
		if(user==null){
			return "403";
		}
		if(!user.getRole().equals("customer")){
			return "403";
		}
		long productID=Long.parseLong(request.getParameter("productID"));
		DrugProduct drugProduct=drugProductDao.getSelectedProduct(productID);
		model.addAttribute("product",drugProduct);
		return "ProductDetails";
    }
	
	@RequestMapping(value="checkforproduct.doasap", method=RequestMethod.POST)
	public void productSuggestion(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String category = request.getHeader("category");
		String productName = request.getHeader("productName");
		
		String suggestion = drugProductDao.getProductSuggestions(category, productName);
		PrintWriter out = response.getWriter();
		out.print(suggestion);
	}
}
