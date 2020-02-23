package org.payara.microprofile.rest.person;

import org.payara.microprofile.rest.person.entity.Person;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@ApplicationScoped
public class PersonRepository {

    @PersistenceContext(unitName = "JPADatasourceExamplePU")
    private EntityManager em;

    public void create(Person person) {
        em.persist(person);
    }

    public Person edit(Person person) {
        return em.merge(person);
    }

    public void remove(Person person) {
        em.remove(em.merge(person));
    }

    public Person find(Long id) {
        return em.find(Person.class, id);
    }

    public List<Person> findAll() {
        return em.createQuery("SELECT p FROM Person p", Person.class).getResultList();
    }

}
