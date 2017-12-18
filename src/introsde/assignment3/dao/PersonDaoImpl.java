package introsde.assignment3.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import introsde.assignment3.model.Person;

public class PersonDaoImpl extends JpaDao<Person, Long> implements PersonDao{

	public PersonDaoImpl() {
		super(Person.class);
	}

	@Override
	public Person update(Person toPerson) {		
		EntityManager em = this.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.merge(toPerson);
		tx.commit();

		return toPerson;
	}
}
