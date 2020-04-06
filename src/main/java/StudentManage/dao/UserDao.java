package StudentManage.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import StudentManage.pojo.Person;

public interface UserDao extends MongoRepository<Person, String>{
	
}
