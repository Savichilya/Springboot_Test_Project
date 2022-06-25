package by.savich.project.springboot.controller;

import by.savich.project.springboot.entity.Iphone;
import by.savich.project.springboot.service.IphoneService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/iphone")
public class IphoneController {

    private final IphoneService iphoneService;

    public IphoneController(IphoneService iphoneService) {
        this.iphoneService = iphoneService;
    }

    @GetMapping("/addIphone")
    public Iphone addIphone() {
        return iphoneService.addRandomIphone();
    }

    @GetMapping("/get/{id}")
    public Iphone getIphone(@PathVariable(value = "id") int id) {
        return iphoneService.getIphoneById(id);
    }

    @GetMapping
    public List<Iphone> getAllIphones(){
        return iphoneService.getAllIphones();
    }

    @PostMapping("/saveIphone")
    public void saveIphone(@RequestBody Iphone iphone) {
        iphoneService.saveIphone(iphone);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteIphone(@PathVariable(value = "id") int id){
        iphoneService.deleteIphoneByID(id);
    }

    @PutMapping("/update")
    public void updateIphone(@RequestBody Iphone iphone){
        iphoneService.updateIphone(iphone);
    }

    @GetMapping("/getModel/{model}")
    public List<Iphone> getIphoneByModel(@PathVariable(value = "model") String model) {
        return iphoneService.getIphoneByModel(model);
    }

    @GetMapping("/findRefTrue/{model}")
    public List<Iphone> findIphoneByRefTrueAndModel(@PathVariable(value = "model") String model) {
        return iphoneService.getIphoneByRefTrueAndModel(model);
    }

    @GetMapping("/findModelThan")
    public List<Iphone> getIphoneByReleaseDateLessThan(@RequestParam int releaseDate) {
        return iphoneService.getIphoneByReleaseDateLessThan(releaseDate);
    }

    @GetMapping("/findModelThan2")
    public List<Iphone> getIphoneByReleaseDateLessThan2(int releaseDate) {
        return iphoneService.getIphoneByReleaseDateLessThan2(releaseDate);
    }
}
