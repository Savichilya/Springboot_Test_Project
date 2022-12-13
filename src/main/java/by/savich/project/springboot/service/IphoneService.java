package by.savich.project.springboot.service;

import by.savich.project.springboot.entity.Iphone;
import by.savich.project.springboot.repository.IphoneRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Service
public class IphoneService {

    public static final List<String> IPHONES = Arrays.asList("4", "4s", "5", "5s", "6", "6s", "7", "7s", "8", "8s",
            "SE", "SE2020", "SE2022");
    private final Random random = new Random();
    private final IphoneRepository iphoneRepository;


    public IphoneService(IphoneRepository iphoneRepository) {
        this.iphoneRepository = iphoneRepository;
    }

    public Iphone addRandomIphone() {
        Iphone iphone = new Iphone();
        iphone.setModel(IPHONES.get(random.nextInt(IPHONES.size())));
        iphone.setRef(random.nextBoolean());
        iphone.setReleaseDate(random.nextInt(2022));
        return iphoneRepository.save(iphone);
    }

    public void saveIphone(Iphone iphone) {
        iphoneRepository.save(iphone);
    }

    public Iphone getIphoneById(int id) {
        if (iphoneRepository.findById(id) == null) {
            throw new RuntimeException("No iphone with id " + id + " was found!");
        }
        return iphoneRepository.findById(id).get();
    }

    public List<Iphone> getAllIphones() {
        List<Iphone> iphoneList = new ArrayList<>();
        iphoneRepository.findAll().forEach(iphone -> iphoneList.add(iphone));
        return iphoneList;
    }

    public void deleteIphoneByID(int id) {
        iphoneRepository.deleteById(id);
    }

    public void updateIphone(Iphone iphone) {
        iphoneRepository.save(iphone);
    }

    public List<Iphone> getIphoneByModel(String model) {
        return iphoneRepository.getIphonesByModel(model);
    }

    public List<Iphone> getIphoneByRefTrueAndModel(String model) {
        return iphoneRepository.findIphoneByRefTrueAndModel(model);
    }

    public List<Iphone> getIphoneByReleaseDateLessThan(int releaseDate) {
        return iphoneRepository.findIphoneByReleaseDateLessThan(releaseDate);
    }

    public List<Iphone> getIphoneByReleaseDateLessThan2(int releaseDate) {
        return iphoneRepository.findIphoneByReleaseDateLessThan2(releaseDate);
    }
}
