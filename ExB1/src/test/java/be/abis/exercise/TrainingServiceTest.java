package be.abis.exercise;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import be.abis.exercise.model.Person;
import be.abis.exercise.service.TrainingService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TrainingServiceTest {

	@Autowired
	private TrainingService trainingService;

	@Test
	public void contextLoads() {
	}

	@Test
	public void testFindPersonById() {
		int id = 2;
		Person p = trainingService.findPerson(id);
		assertEquals("Mary", p.getFirstName());
	}
	/*
	 * @Test public void testGetAllPersons() { ArrayList<Person> persons =
	 * trainingService.getAllPersons(); assertEquals("Mary",
	 * persons.get(1).getFirstName()); }
	 */
}
