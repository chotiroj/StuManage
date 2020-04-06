package StudentManage.service;

import org.springframework.ui.Model;

import StudentManage.pojo.Person;

public interface UserService {
	public String findAllUser(Model model,String act,Integer page);
	public String addUser(Person person);
	public String deleteAuser(String id);
	public String fingOneById(String id,Model model);
	public String updatedateById(Person person);
	public String findUserbyName(Model model,String Sname);
}
