package main.model;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository //репозиторий
public interface UserRepository extends CrudRepository<Person, Integer> //обеспечивает основными
                                                                        // операциями по поиску,
                                                                        // сохранению и удалению данных
{
}
