package main;

import junit.framework.TestCase;
import main.model.Person;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest extends TestCase {
    @Autowired
    private UserController userController;
    private final Person person1 = setInfo();
    private final Person person2 = setInfo();
    private final Person person3 = setInfo();

    @Test
    public void testAdd() {
        userController.add(person1);
        userController.add(person2);
        userController.add(person3);

        Assert.assertEquals(1, person1.getId());
        Assert.assertEquals(2, person2.getId());
        Assert.assertEquals(3, person3.getId());
    }

    @Test
    public void testList() {
        int sizeList = userController.list().size();
        if(sizeList == 0){
            userController.add(person1);
            userController.add(person2);
            userController.add(person3);
        }

        Assert.assertEquals(3, userController.list().size());
    }

    @Test
    public void testGet() {
        userController.clearList();
        userController.add(person1);
        userController.add(person2);
        userController.add(person3);

        ResponseEntity expected = new ResponseEntity(person1, HttpStatus.OK);
        ResponseEntity actual = userController.get(person1.getId());

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testReplacementUserById() {
        userController.clearList();
        userController.add(person1);
        userController.add(person2);
        userController.add(person3);
        Person person = setInfo();

        ResponseEntity expected = new ResponseEntity(person, HttpStatus.OK);
        ResponseEntity actual = userController.replacementUserById(person2.getId(), person);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testDelete() {
        int sizeList = userController.list().size();
        if(sizeList == 0){
            userController.add(person1);
            userController.add(person2);
            userController.add(person3);
        }
        userController.delete(person3.getId());
        sizeList = userController.list().size();
        Assert.assertEquals(2, sizeList);
    }

    @Test
    public void testClearList() {
        int sizeList = userController.list().size();
        if(sizeList == 0){
            userController.add(person1);
            userController.add(person2);
            userController.add(person3);
            sizeList = userController.list().size();
            Assert.assertEquals(3, sizeList);
        }
        userController.clearList();
        sizeList = userController.list().size();
        Assert.assertEquals(0, sizeList);
    }

    private Person setInfo(){
        Person person = new Person();
        person.setSurname("Иванов");
        person.setName("Иван");
        person.setPatronymic("Иванович");
        person.setBirthday("01.01.2000");
        person.setEmail("email@email.com");
        person.setPhoneNumber("+79998887766");
        return person;
    }
}