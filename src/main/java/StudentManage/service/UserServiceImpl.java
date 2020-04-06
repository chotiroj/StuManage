package StudentManage.service;

import java.util.List;
import java.util.regex.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;


import StudentManage.dao.UserDao;
import StudentManage.pojo.Person;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private MongoTemplate mongoTemplate;
	@Override
	public String addUser(Person person) {
		userDao.save(person);
		return "forward:/selectInfo";
	}


	@Override
	public String findAllUser(Model model,String act,Integer page) {	
		List<Person>list=userDao.findAll();
		int temp=list.size();
		int totalPage=0;
		if(temp==0) {
			totalPage=0;
		}
		else {
			totalPage=(int)Math.ceil((double)temp/10);
		}
		if(page==null) {
			page=1;
		}
		Query query=new Query().skip((page-1)*10).limit(10);
		list=mongoTemplate.find(query, Person.class);
		model.addAttribute("totalCount", temp);
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("pageCur", page);
		model.addAttribute("list", list);
		if("deleteSelect".equals(act)) {
			return "deleteInfo";
		}
		else if ("updateSelect".equals(act)) {
			return "updateSelect";
		}
		else {
			return "selectInfo";
		}
		
	}


	@Override
	public String deleteAuser(String id) {
		//Query query=new Query(Criteria.where("id").is(id));
		Query query=new Query(new Criteria("id").is(id));
		mongoTemplate.remove(query,Person.class);
		return "forward:/selectInfo?act=deleteSelect";
	}


	@Override
	public String fingOneById(String id,Model model) {
		Query query=new Query(new Criteria("id").is(id));
		Person person=mongoTemplate.findOne(query,Person.class);
		model.addAttribute("person", person);
		return "updateInfo";
	}


	@Override
	public String updatedateById(Person person) {
		Query query=new Query(new Criteria("id").is(person.getId()));
		Update update=new Update();
		update.set("Sno",person.getSno());
		update.set("Sname",person.getSname());
		update.set("Ssex",person.getSsex());
		update.set("Sphone",person.getSphone());
		update.set("Sclass",person.getSclass());
		update.set("Saddress",person.getSaddress());
		mongoTemplate.updateMulti(query, update,Person.class);
		return "forward:/selectInfo?act=updateSelect";
	}


	@Override
	public String findUserbyName(Model model,String Sname) {
		Pattern pattern=Pattern.compile(Sname,Pattern.MULTILINE);
		Query query=new Query(new Criteria("Sname").regex(pattern));
		List<Person> person=mongoTemplate.find(query, Person.class);
		model.addAttribute("lists", person);
		return "search";
	}
	

}
