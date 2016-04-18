package edu.neu.project;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.neu.project.DAO.AdminDao;
import edu.neu.project.model.UserAccount;

@Controller
public class TransferController {

	@Autowired
	private AdminDao adminDao;
	
	
	
	@RequestMapping(value="goToAddSupplier.do", method=RequestMethod.POST)	
	public String GoToAddSupplier(HttpSession session, HttpServletResponse response) throws IOException {
		UserAccount user = (UserAccount)session.getAttribute("user");
		if(user==null){
			return "403";
		}
		if(!user.getRole().equals("admin")){
			return "403";
		}
		return "addSupplier";	
	}

	@RequestMapping(value="goToAddDrugCategory.do", method=RequestMethod.POST)	
	public String GoToAddDrugCategory(HttpSession session, HttpServletResponse response) throws IOException {
		UserAccount user = (UserAccount)session.getAttribute("user");
		if(user==null){
			return "403";
		}
		if(!user.getRole().equals("admin")){
			return "403";
		}
		return "addDrugCategory";	
	}
	
//	@RequestMapping(value="addMusicToCategory.do", method=RequestMethod.GET)	
//	public String GoToAddMusic() {
//		return "addMusic";	
//			}
	
}