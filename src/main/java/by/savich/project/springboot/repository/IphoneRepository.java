package by.savich.project.springboot.repository;

import by.savich.project.springboot.entity.Iphone;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IphoneRepository extends CrudRepository<Iphone, Integer> {
    List<Iphone> getIphonesByModel(String model);

    List<Iphone> findIphoneByRefTrueAndModel(String model);

}
