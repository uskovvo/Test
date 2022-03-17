package main;

import main.model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import main.model.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController // ярлык для совместного использования аннотаций @Controller и @ResponseBody
public class UserController {

    @Autowired
    private UserRepository userRepository;

    //
    @GetMapping("/persons/") //запрос на получение списка из БД
    public List<Person> list(){
        Iterable<Person> userIterable = userRepository.findAll();
        ArrayList<Person> people = new ArrayList<>();
        userIterable.forEach(people::add);
        return people;
    }

    @PostMapping("/persons/") //запрос на добавление сущности в БД
    public int add(Person person){
        Person newPerson = userRepository.save(person);
        return newPerson.getId();
    }

    @GetMapping("/persons/{id}") //запрос на получение сущности из БД по Id
    public ResponseEntity get(@PathVariable int id){ // @PathVariable позволяет искать в БД по указаным полям
                                                     // в данном варианте по полю Id
        Optional<Person> optionalPerson = userRepository.findById(id);
        return optionalPerson.map(person -> new ResponseEntity(person, HttpStatus.OK))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    @PutMapping("/persons/{id}")//запрос на замену сущности в БД по Id
    public ResponseEntity replacementUserById(@PathVariable int id, Person newPerson){
        Optional<Person> optionalPerson = userRepository.findById(id);
        return optionalPerson.map(person -> new ResponseEntity(userRepository.save(newPerson), HttpStatus.OK))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    @DeleteMapping("/persons/{id}") //запрос на удаление сущности из БД по Id
    public void delete(@PathVariable int id){
        userRepository.deleteById(id);
    }

    @DeleteMapping("/persons/") //запрос на очистку всех данных в БД
    public void clearList () {
        userRepository.deleteAll();
    }
}
