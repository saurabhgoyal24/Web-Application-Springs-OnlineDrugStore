package edu.neu.project;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.neu.project.DAO.AdminDao;
import edu.neu.project.DAO.CustomerDao;
import edu.neu.project.DAO.SupplierDao;
import edu.neu.project.DAO.UserDao;
import edu.neu.project.model.Customer;
import edu.neu.project.model.DrugCategory;
import edu.neu.project.model.Supplier;
import edu.neu.project.model.UserAccount;
import edu.neu.project.model.UserRole;


/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	
	@Autowired
	@Qualifier("homeValidator")
	private Validator validator;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private AdminDao adminDao;
	
	@Autowired
	private SupplierDao supplierDao;
	
	@Autowired
	private CustomerDao customerDao;
	/*
	 * This is to initialize webDataBinder,set its
	 * validator as we specify.
	 */
	@InitBinder
	private void initBinder (WebDataBinder binder){
		binder.setValidator(validator);
	}
	
	
	
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String initUserLoginForm(Locale locale, Model model, HttpServletRequest request, HttpSession session) throws Exception {
//		User user = new User();
//		
//		model.addAttribute("user", user);
		
//		userDao.addPerson();   //USE THIS FOR SIGN UP
		
//		return "home";
		
		adminDao.checkAdminCreated();
		
//		if(session.getAttribute("user")!=null){
//			
//			User user = (User)session.getAttribute("user");
//			String userName = user.getUsername();
//			String password = user.getPassword();
//			
//			User u = userDao.queryUserByNameAndPassword(userName, password);
//			if(u!=null){
//				model.addAttribute("user", u);
//				session.setAttribute("user", u);
//				return "userHomePage";
//			}
//			else{
//			session.removeAttribute("user");
//			return "home";
//			}
//		}
		
		Cookie[] cookies = request.getCookies();     
		String userName = null;
		String password = null;

		boolean foundCookie = false;
		if(cookies!=null)
		{
			for(int i = 0; i < cookies.length; i++)
			{ 
				Cookie c = cookies[i];
				if (c.getName().equals("userid"))
					{
					userName = c.getValue();
					model.addAttribute("userName", userName);
					}
				if (c.getName().equals("pwd"))
					{
					password = c.getValue();
					model.addAttribute("password", password);
					}

//				User user = new User();
//				model.addAttribute("foundCookie",true);
				foundCookie = true;
				UserAccount u = userDao.queryUserByNameAndPassword(userName, password);
				if(u!=null){
					if(u.getRole().equals(UserRole.ROLE_ADMIN))
					{
						session.setAttribute("user", u);
						return "adminWorkArea";
					}
					
					if(u.getRole().equals(UserRole.ROLE_SUPPLIER))
					{
						session.setAttribute("user", u);
						List<DrugCategory> drugCategoryList = supplierDao.getListOfDrugCategory();
						model.addAttribute("listDrugCategory", drugCategoryList);
						return "supplierWorkArea";
					}
					
					if(u.getRole().equals(UserRole.ROLE_CUSTOMER))
					{
						session.setAttribute("user", u);
						List<DrugCategory> drugCategoryList = supplierDao.getListOfDrugCategory();
						model.addAttribute("listDrugCategory", drugCategoryList);
						
						List<String> companyList = customerDao.getCompanyList();
						model.addAttribute("companyList", companyList);
						
						Customer customer=(Customer)u.getPerson();
						long customerID=customer.getPersonID();
						model.addAttribute("customerID",customerID);
						
						return "userHomePage";		
					}
					
					
//					model.addAttribute("user", u);
//					session.setAttribute("user", u);
//					return "userHomePage";
				}
				}
			UserAccount userAccount = new UserAccount();
			model.addAttribute("userAccount", userAccount);
			return "home";
//			return "login";
			
//			return "userHomePage";
		}
		
//		if(!foundCookie){
		else{
			UserAccount userAccount = new UserAccount();
			model.addAttribute("user", userAccount);
			model.addAttribute("foundCookie",false);
			return "home";
		}
		
		
	}
	
	
	@RequestMapping(value="login.do" ,method=RequestMethod.POST)
	public String submitForm(Model model, @Validated UserAccount user, BindingResult result, HttpSession session,HttpServletRequest request, HttpServletResponse response){
		
		model.addAttribute("userAccount",user);
		String returnVal = "userHomePage";
		if(session.getAttribute("user")!=null){
			session.removeAttribute("user");
		}
		if (result.hasErrors()){
			
			return "home";
		}else{
			try {
				UserAccount u = userDao.queryUserByNameAndPassword(user.getUsername(), user.getPassword());
				if (u != null){
					model.addAttribute("user", u);
//					session.setAttribute("user", u);
					boolean remember_me_is_checked;
					if(request.getParameter("rememberMe")!=null)
					{
						remember_me_is_checked = true;
					if(remember_me_is_checked)
					{
					    Cookie c1 = new Cookie("userid", user.getUsername());
					    Cookie c2 = new Cookie("pwd", user.getPassword());
					    c1.setMaxAge(7*24*60*60);
					    c2.setMaxAge(7*24*60*60);
					    response.addCookie(c1);
					    response.addCookie(c2);
						}
					}
					if(u.getRole().equals(UserRole.ROLE_ADMIN))
					{
						session.setAttribute("user", u);
						return "adminWorkArea";
					}
					
					if(u.getRole().equals(UserRole.ROLE_SUPPLIER))
					{
						session.setAttribute("user", u);
						List<DrugCategory> drugCategoryList = supplierDao.getListOfDrugCategory();
						model.addAttribute("listDrugCategory", drugCategoryList);
						return "supplierWorkArea";
					}
					
					if(u.getRole().equals(UserRole.ROLE_CUSTOMER))
					{
						session.setAttribute("user", u);
						List<DrugCategory> drugCategoryList = supplierDao.getListOfDrugCategory();
						model.addAttribute("listDrugCategory", drugCategoryList);
						
						List<String> companyList = customerDao.getCompanyList();
						model.addAttribute("companyList", companyList);
						
						Customer customer=(Customer)u.getPerson();
						long customerID=customer.getPersonID();
						model.addAttribute("customerID",customerID);
						
						return returnVal;		
					}		
				}
				else return "home";
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return "home";
	
	
}
	
	@RequestMapping(value="goToSignUp.do")
	public String gotToSignUpPage(Model model){
		return "signUp";	
	}
	
	@RequestMapping (value="signup.do", method=RequestMethod.POST)
	public String signUpUser(Model model, HttpServletRequest request, HttpSession session) throws Exception{
		Customer customer = new Customer();
		customer.setFirstName(request.getParameter("firstName"));
		customer.setLastName(request.getParameter("lastName"));
		customer.setAddress(request.getParameter("address"));
		customer.setDateOfBirth(request.getParameter("dateOfBirth"));
		customer.setEmail(request.getParameter("email"));
		customer.setGender(request.getParameter("gender"));
		customer.setPhoneNo(Long.parseLong(request.getParameter("phoneNo")));
		
		UserAccount userAccount = new UserAccount();
		userAccount.setUsername(request.getParameter("username"));
		userAccount.setPassword(request.getParameter("password"));
		userAccount.setRole(UserRole.ROLE_CUSTOMER);
		
		userAccount.setPerson(customer);
		
		userDao.addCustomerToDb(userAccount);
		session.setAttribute("user", userAccount);
		model.addAttribute("user",userAccount);
		List<DrugCategory> drugCategoryList = supplierDao.getListOfDrugCategory();
		model.addAttribute("listDrugCategory", drugCategoryList);
		return "userHomePage";
	}
	
	
	@RequestMapping(value="addDrugProductToCategory.do", method=RequestMethod.POST)
	public String addDrugProductTodrugCategory(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception{
		UserAccount user = (UserAccount)session.getAttribute("user");
		if(user==null){
			return "403";
		}
		if(!user.getRole().equals("supplier")){
			return "403";
		}
		String selectedDrugCategory = request.getParameter("selectedDrugCategory");
		String name = request.getParameter("name");
		String desc = request.getParameter("description");
		Float price = Float.parseFloat(request.getParameter("price"));
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		String companyName = request.getParameter("companyName");
		String path = request.getParameter("putImage");

		//UPLOAD PICTURE CODE//
		String sample = "sample hai ye";
		//UPLOAD MUSIC SAMPLE//
		
				
//		Map<String, String> musicSet = new HashMap<String, String>();
//		musicSet.put("name", name);
//		musicSet.put("desc", description);
//		musicSet.put("price", String.valueOf(price));
//		musicSet.put("quantity", String.valueOf(quantity));
				
		supplierDao.addDrugToDb(selectedDrugCategory, name, price, quantity, desc, path, sample, companyName);
		return "drugProductAdded";
	}
	
	
	
	@RequestMapping(value="addSupplier.do", method=RequestMethod.POST)
	public String RegisterMusicProducer(Model model, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception{
		UserAccount user = (UserAccount)session.getAttribute("user");
		if(user==null){
			return "403";
		}
		if(!user.getRole().equals("admin")){
			return "403";
		}
		Supplier supplier = new Supplier();
		String firstname= request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
			
		String username=request.getParameter("username");
		String password = request.getParameter("password");
			
						
		adminDao.addSupplierToDb(firstname, lastname, username, password);
			
			return "supplierAdded";
	}
	
	@RequestMapping(value="addDrugCategory.do", method=RequestMethod.POST)
	public String addDrugCategoryToDb(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception{
		UserAccount user = (UserAccount)session.getAttribute("user");
		if(user==null){
			return "403";
		}
		if(!user.getRole().equals("admin")){
			return "403";
		}
		String newDrugCategory = request.getParameter("newDrugCategory");
		
		DrugCategory drugCategory = new DrugCategory();
		drugCategory.setName(newDrugCategory);
		adminDao.addDrugCategoryToDb(drugCategory);;
		
		return "drugCategoryAdded";
	}
	
	@RequestMapping(value="checkforusername.do", method=RequestMethod.POST)
	public void checkUserName(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String username = request.getHeader("username");
		
		PrintWriter out = response.getWriter();
		boolean b = userDao.checkUserName(username);
		if(b)
			out.print("true");
		else
			out.print("false");
	}
	
	@RequestMapping(value = "goToChangePassword.do", method = RequestMethod.POST)
	public String goToChangePassword(){
		return "changePassword";		
	}
	
	@RequestMapping(value = "changePassword.do", method = RequestMethod.POST)
	public String updatePassword(HttpServletRequest request, HttpSession session){
		UserAccount userAccount = (UserAccount)session.getAttribute("user");
		long id = userAccount.getUserID();
		String newPassword = request.getParameter("newPwdTF");
		userDao.updatePassword(id, newPassword);
		return "passwordSuccessful";		
	}
	
	@RequestMapping(value = "checkPassword.do", method = RequestMethod.POST)
	public void verifyPassword(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException{
		PrintWriter out = response.getWriter();
		UserAccount userAccount = (UserAccount)session.getAttribute("user");
		
		String userName = userAccount.getUsername();
		
		String oldPassword = request.getHeader("oldPassword");
		if(userDao.queryByPassword(userName, oldPassword)){	
			out.print("true");
		}else{
			out.print("false");
		}
	}
	
	@RequestMapping(value="logMeOut.do", method=RequestMethod.GET)
	public void logout(HttpSession session, HttpServletResponse response) throws IOException{
		session.removeAttribute("user");
		Cookie c1 = new Cookie("userid", null);
		Cookie c2 = new Cookie("pwd", null);
		c1.setMaxAge(0);
		c2.setMaxAge(0);
		response.addCookie(c1);
		response.addCookie(c2);
		response.sendRedirect("");
	}
	
	@RequestMapping(value="gotohomepage.htm", method=RequestMethod.GET)
	public String goToHomePage(HttpSession session, Model model) throws Exception{
		
		UserAccount u = (UserAccount)session.getAttribute("user");
		List<DrugCategory> drugCategoryList = supplierDao.getListOfDrugCategory();
		model.addAttribute("listDrugCategory", drugCategoryList);
		
		List<String> companyList = customerDao.getCompanyList();
		model.addAttribute("companyList", companyList);
		
		Customer customer=(Customer)u.getPerson();
		long customerID=customer.getPersonID();
		model.addAttribute("customerID",customerID);
		
		return "userHomePage";	
	}
	
	@RequestMapping(value="goToSupplierHome.do", method=RequestMethod.GET)
	public String goToSupplierHomePage(HttpSession session, Model model) throws Exception{
		UserAccount u = (UserAccount)session.getAttribute("user");

		Supplier supplier = (Supplier)u.getPerson();
		String supplierName = supplier.getSupplierName();
		model.addAttribute("supplierName", supplierName);
		
		List<DrugCategory> listDrugCategory = supplierDao.getListOfDrugCategory();
		model.addAttribute("listGenre", listDrugCategory);
		session.setAttribute("user", u);
		return "supplierWorkArea";
	}
	
	@RequestMapping(value="goToAdminHome.do", method=RequestMethod.GET)
	public String goToAdminHomePage(HttpSession session, Model model) throws Exception{
		UserAccount u = (UserAccount)session.getAttribute("user");

		session.setAttribute("user", u);
		return "adminWorkArea";
	}
	
	
}
