package by.savich.project.springboot.repository;

import by.savich.project.springboot.entity.Iphone;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IphoneRepository extends CrudRepository<Iphone, Integer> {
    List<Iphone> getIphonesByModel(String model);

    List<Iphone> findIphoneByRefTrueAndModel(String model);

    @Query("SELECT i FROM Iphone i where i.releaseDate > :releaseDate")
    List<Iphone> findIphoneByReleaseDateLessThan(@Param("releaseDate") int releaseDate);

    @Query(value = "SELECT * FROM iphone WHERE release_date >:releaseDate", nativeQuery = true)
    List<Iphone> findIphoneByReleaseDateLessThan2(@Param("releaseDate") int releaseDate);

}
