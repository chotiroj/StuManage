package StudentManage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import StudentManage.pojo.Person;
import StudentManage.service.UserService;

@Controller
public class UserController {
	@Autowired
	private UserService userService;
	
		
	@RequestMapping("toaddUserInfo")
	public String toaddUser() {
		return "addInfo";
	}
	
	@RequestMapping("addUserInfo")
	public String addUser(Person person) {
		System.out.println("==============="+person);
		return userService.addUser(person);
	}
	
	@RequestMapping("selectInfo")
	public String findInfo(Model model,String act,Integer page) {
		return userService.findAllUser(model,act,page);
	}
	
	@RequestMapping("deleteInfo")
	public String deleteInfo(String id){
		return userService.deleteAuser(id);
	}
	
	@RequestMapping("findOne")
	public String findOne(String id,Model model) {
		return userService.fingOneById(id, model);
	}
	
	@RequestMapping("updateInfo")
	public String updateInfo(Person person) {
		return userService.updatedateById(person);
	}
	
	@RequestMapping("search")
	public String searchUser(Model model,String Sname) {
		return userService.findUserbyName(model, Sname);
	}
}
