package be.abis.exercise;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import be.abis.exercise.model.Person;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ExB1ApplicationTests {

	@Autowired
	private RestTemplate rt;
	private String baseUrl = "http://localhost:8085/exercise/api/persons";

	@Test
	public void contextLoads() {
	}

	@Test
	public void testFindPersonById() {
		int id = 2;
		Person p = rt.getForObject(baseUrl + "/" + id, Person.class);
		System.out.println(p.getFirstName()+" "+p.getLastName());
		assertEquals("Mary", p.getFirstName());
	}
}
