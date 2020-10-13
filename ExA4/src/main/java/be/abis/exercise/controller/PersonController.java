package be.abis.exercise.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import be.abis.exercise.exception.PersonCanNotBeDeletedException;
import be.abis.exercise.model.Login;
import be.abis.exercise.model.Person;
import be.abis.exercise.service.PersonService;

@RestController
@RequestMapping("persons")
public class PersonController {

	@Autowired
	PersonService personService;
	
	@GetMapping("{id}")
	public Person findPerson(@PathVariable("id") int id) {
		return personService.findPerson(id);
	}

    @PostMapping("/login")
    public Person login(@RequestBody Login login) {
        return personService.findPerson(login.getEmailAddress(),login.getPassword());
    }
	
	@GetMapping(path="")
	public List<Person> getAllPersons(){
		return personService.getAllPersons();
	}
	
	@PostMapping(path="")
	public void addPerson(@RequestBody Person person){
		try {
			personService.addPerson(person);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@DeleteMapping("{id}")
	public void deletePerson(@PathVariable("id") int id){
		try {
			personService.deletePerson(id);
		} catch (PersonCanNotBeDeletedException e) {
			e.printStackTrace();
		}
	}

    @PutMapping("{id}")
    public void changePassword(@PathVariable("id") int id, @RequestBody Person person) {
        try {
            personService.changePassword(personService.findPerson(id), person.getPassword());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("findbycompname")
    public List<Person> findPersonsByCompanyName(@RequestParam("compname") String compName) {
    	return personService.findPersonsByCompanyName(compName);
    }
}
