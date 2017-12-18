package introsde.assignment3.dao;
import introsde.assignment3.model.Person;

public interface PersonDao extends Dao<Person, Long>{
	public Person update(Person toPerson);
}
