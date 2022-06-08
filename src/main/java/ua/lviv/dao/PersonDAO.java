package ua.lviv.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ua.lviv.models.Person;

import java.util.List;
import java.util.Optional;

@Component
public class PersonDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> showPeople(){
        return jdbcTemplate.query("SELECT * FROM Person", new PersonMapper());
    }

    public void createPerson(Person person){
        jdbcTemplate.update("INSERT INTO Person(full_name, year_of_born) VALUES (?, ?)",
                person.getFullName(), person.getYearOfBorn());
    }

    public Optional<Person> showPerson(String fullName){
        return jdbcTemplate.query("SELECT * FROM Person WHERE full_name = ?", new Object[]{fullName},
                new PersonMapper()).stream().findAny();
    }

    public Person showPerson(int id){
        return jdbcTemplate.query("SELECT * FROM Person WHERE person_id = ?", new Object[]{id},
                new PersonMapper()).stream().findAny().orElse(null);
    }

    public void deletePerson(int id) {
        jdbcTemplate.update("DELETE FROM Person WHERE person_id=?", id);
    }

    public void editPerson(int id, Person person){
        jdbcTemplate.update("UPDATE Person SET full_name = ?, year_of_born = ? WHERE person_id= ?",
                person.getFullName(), person.getYearOfBorn(), id);
    }
}
