package be.abis.exercise.service;

import java.io.IOException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import be.abis.exercise.exception.PersonCanNotBeDeletedException;
import be.abis.exercise.model.ListPersons;
import be.abis.exercise.model.Login;
import be.abis.exercise.model.Person;

@Service
public class ApiPersonService implements PersonService {

	@Autowired
	private RestTemplate rt;
	private String baseUrl = "http://localhost:8085/exercise/api/persons";

	@Override
	public ArrayList<Person> getAllPersons() {
		ListPersons listPersons = rt.getForObject(baseUrl, ListPersons.class);
		return listPersons.getPersons();
	}

	@Override
	public Person findPerson(int id) {
		Person p = rt.getForObject(baseUrl + "/" + id, Person.class);
		return p;
	}

	@Override
	public Person findPerson(String emailAddress, String passWord) {
		Login login = new Login();
		login.setEmail(emailAddress);
		login.setPassword(passWord);
		Person p = rt.postForObject(baseUrl+"/login", login, Person.class);
		return p;
	}

	@Override
	public void addPerson(Person p) throws IOException {
		rt.postForObject(baseUrl, p, Void.class);
	}

	@Override
	public void deletePerson(int id) throws PersonCanNotBeDeletedException {
		rt.delete(baseUrl+"/"+id);
	}

	@Override
	public void changePassword(Person p, String newPswd) throws IOException {
		p.setPassword(newPswd);
		rt.put(baseUrl+"/"+p.getPersonId(), p);
	}

}
