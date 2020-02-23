package org.payara.microprofile.rest.service;

import org.payara.microprofile.rest.entity.Person;
import org.payara.microprofile.rest.repository.PersonRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import static javax.transaction.Transactional.TxType.REQUIRED;

@ApplicationScoped
public class PersonService {

    private static final Logger LOG = Logger.getLogger(PersonService.class.getName());

    @Inject
    private PersonRepository personRepository;

    @Transactional(REQUIRED)
    public void create(Person person) {
        LOG.log(Level.INFO, "SERVICE call to save Person : {0}", person);
        personRepository.create(person);
    }

    @Transactional(REQUIRED)
    public Person edit(Person person) {
        LOG.log(Level.INFO, "SERVICE call to update Person : {0}", person);
        return personRepository.edit(person);
    }

    @Transactional(REQUIRED)
    public void remove(Person person) {
        LOG.log(Level.INFO, "SERVICE call to delete Person : {0}", person.getId());
        personRepository.remove(person);
    }

    public Optional<Person> find(Long id) {
        LOG.log(Level.INFO, "SERVICE call to get Person : {0}", id);
        return Optional.ofNullable(personRepository.find(id));
    }

    public List<Person> findAll() {
        LOG.log(Level.INFO, "SERVICE call to get all People");
        return personRepository.findAll();
    }

}
