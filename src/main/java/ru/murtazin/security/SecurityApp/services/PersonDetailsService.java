package ru.murtazin.security.SecurityApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.murtazin.security.SecurityApp.models.Person;
import ru.murtazin.security.SecurityApp.repositories.PeopleRepository;
import ru.murtazin.security.SecurityApp.security.PersonDetails;

import java.util.Optional;

@Service
public class PersonDetailsService implements UserDetailsService {

    private final PeopleRepository peopleRepository;

    @Autowired
    public PersonDetailsService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<Person>person = peopleRepository.findByUsername(s);

        if(person.isEmpty())
            throw new UsernameNotFoundException("UserNeNaiden");

        return new PersonDetails(person.get());


    }
}
