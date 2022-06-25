package by.savich.project.springboot.controller;

import by.savich.project.springboot.entity.Iphone;
import by.savich.project.springboot.repository.IphoneRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static by.savich.project.springboot.service.IphoneService.IPHONES;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class IphoneControllerWithDatabaseIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private IphoneRepository iphoneRepository;

    @Autowired
    private TestRestTemplate restTemplate;

    private String host;

    @BeforeEach
    void init() {
        host = "http://localhost:" + port;
    }

    @BeforeEach
    void clearDatabase() {
        iphoneRepository.deleteAll();
    }

    @Test
    void shouldGenerateRandomIphone() throws InterruptedException {

        Iphone iphone = this.restTemplate.getForObject(host + "/iphone/addIphone", Iphone.class);

        iphoneRepository.save(iphone);

        int id=iphone.getId();
        Iphone iphone1=iphoneRepository.findById(id).get();

        assertThat(IPHONES).contains(iphone.getModel());
        assertThat(iphone.getReleaseDate()).isNotNull();
        assertThat(iphone.getReleaseDate()).isPositive();
        assertThat(iphone.getReleaseDate()).isGreaterThan(1);

        assertThat(iphone1.getId()).isEqualTo(iphone.getId());
        assertThat(iphone1.getModel()).isEqualTo(iphone.getModel());
        assertThat(iphone1.getReleaseDate()).isEqualTo(iphone.getReleaseDate());
        assertThat(iphone1.isRef()).isEqualTo(iphone.isRef());


    }

    @Test
    void shouldGetIphoneById() {
        Iphone iphone = new Iphone();
        iphone.setModel("SE");
        iphone.setRef(true);
        iphone.setReleaseDate(2017);

        iphoneRepository.save(iphone);

        int id = iphone.getId();

        Iphone result = this.restTemplate.getForObject(host + "/iphone/get/" + id, Iphone.class);

        assertThat(result).isEqualTo(iphone);
    }

    @Test
    void shouldGetAllIphones() {
        List<Iphone> iphoneList = new ArrayList<>();

        Iphone iphone1 = new Iphone();
        iphone1.setModel("SE");
        iphone1.setRef(false);
        iphone1.setReleaseDate(2017);
        Iphone iphone2 = new Iphone();
        iphone2.setModel("SE");
        iphone2.setRef(false);
        iphone2.setReleaseDate(2017);
        Iphone iphone3 = new Iphone();
        iphone3.setModel("7");
        iphone3.setRef(true);
        iphone3.setReleaseDate(2016);
        Iphone iphone4 = new Iphone();
        iphone4.setModel("SE");
        iphone4.setRef(true);
        iphone4.setReleaseDate(2017);
        Iphone iphone5 = new Iphone();
        iphone5.setModel("8");
        iphone5.setRef(false);
        iphone5.setReleaseDate(2018);

        iphoneList.add(iphone1);
        iphoneList.add(iphone2);
        iphoneList.add(iphone3);
        iphoneList.add(iphone4);
        iphoneList.add(iphone5);

        iphoneRepository.saveAll(iphoneList);

        List<Iphone> resultList = this.restTemplate.exchange(host + "/iphone", HttpMethod.GET,
                null, new ParameterizedTypeReference<List<Iphone>>() {
                }).getBody();

        assertThat(resultList).isEqualTo(iphoneList);
    }

    @Test
    void shouldGetIphoneByModel() {
        List<Iphone> iphoneList = new ArrayList<>();
        List<Iphone> checkIphoneList = new ArrayList<>();

        Iphone iphone1 = new Iphone();
        iphone1.setModel("SE");
        iphone1.setRef(false);
        iphone1.setReleaseDate(2017);
        Iphone iphone2 = new Iphone();
        iphone2.setModel("SE");
        iphone2.setRef(false);
        iphone2.setReleaseDate(2017);
        Iphone iphone3 = new Iphone();
        iphone3.setModel("7");
        iphone3.setRef(true);
        iphone3.setReleaseDate(2016);
        Iphone iphone4 = new Iphone();
        iphone4.setModel("SE");
        iphone4.setRef(true);
        iphone4.setReleaseDate(2017);
        Iphone iphone5 = new Iphone();
        iphone5.setModel("8");
        iphone5.setRef(false);
        iphone5.setReleaseDate(2018);

        iphoneList.add(iphone1);
        iphoneList.add(iphone2);
        iphoneList.add(iphone3);
        iphoneList.add(iphone4);
        iphoneList.add(iphone5);

        checkIphoneList.add(iphone1);
        checkIphoneList.add(iphone2);
        checkIphoneList.add(iphone4);

        iphoneRepository.saveAll(iphoneList);

        List<Iphone> resultList = this.restTemplate.exchange(host + "/iphone/getModel/SE", HttpMethod.GET,
                null, new ParameterizedTypeReference<List<Iphone>>() {
                }).getBody();

        assertThat(resultList).containsExactlyElementsOf(checkIphoneList);
    }

    @Test
    void shouldGetIphoneByRefTrueAndModel() {
        List<Iphone> iphoneList = new ArrayList<>();
        List<Iphone> checkIphoneList = new ArrayList<>();

        Iphone iphone1 = new Iphone();
        iphone1.setModel("SE");
        iphone1.setRef(true);
        iphone1.setReleaseDate(2017);
        Iphone iphone2 = new Iphone();
        iphone2.setModel("SE");
        iphone2.setRef(false);
        iphone2.setReleaseDate(2017);
        Iphone iphone3 = new Iphone();
        iphone3.setModel("7");
        iphone3.setRef(true);
        iphone3.setReleaseDate(2016);
        Iphone iphone4 = new Iphone();
        iphone4.setModel("SE");
        iphone4.setRef(true);
        iphone4.setReleaseDate(2017);
        Iphone iphone5 = new Iphone();
        iphone5.setModel("8");
        iphone5.setRef(false);
        iphone5.setReleaseDate(2018);

        iphoneList.add(iphone1);
        iphoneList.add(iphone2);
        iphoneList.add(iphone3);
        iphoneList.add(iphone4);
        iphoneList.add(iphone5);

        checkIphoneList.add(iphone1);
        checkIphoneList.add(iphone4);

        iphoneRepository.saveAll(iphoneList);
        List<Iphone> resultIphoneList = this.restTemplate.exchange(host + "/iphone/findRefTrue/SE", HttpMethod.GET,
                null, new ParameterizedTypeReference<List<Iphone>>() {
                }).getBody();
        assertThat(resultIphoneList).containsExactlyElementsOf(checkIphoneList);
    }

    @Test
    void shouldUpdateIphone() {
        Iphone iphone = new Iphone();
        iphoneRepository.save(iphone);

        iphone.setModel("6");
        iphone.setRef(true);
        iphone.setReleaseDate(2016);

        int id=iphone.getId();
        HttpEntity<Iphone> httpEntity = new HttpEntity<>(iphone);
        ResponseEntity<Void> response = this.restTemplate.exchange(host + "/iphone/update",
                HttpMethod.PUT, httpEntity, Void.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        Iphone iphone1=iphoneRepository.findById(id).get();

        assertThat(iphone1.getId()).isEqualTo(iphone.getId());
        assertThat(iphone1.getModel()).isEqualTo(iphone.getModel());
        assertThat(iphone1.getReleaseDate()).isEqualTo(iphone.getReleaseDate());
        assertThat(iphone1.isRef()).isEqualTo(iphone.isRef());
    }

    @Test
    void shouldDeleteIphone() {

        Iphone iphone = new Iphone();
        iphone.setModel("SE");
        iphone.setRef(true);
        iphone.setReleaseDate(2017);
        iphoneRepository.save(iphone);

        int id = iphone.getId();

        HttpEntity<Iphone> httpEntity = new HttpEntity<>(iphone);
        ResponseEntity<Void> response = this.restTemplate.exchange(host + "/iphone/delete/"+id,
                HttpMethod.DELETE, httpEntity, Void.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void shouldGetIphoneByReleaseDateLessThan() {
        List<Iphone> iphoneList = new ArrayList<>();
        List<Iphone> checkIphoneList = new ArrayList<>();

        Iphone iphone1 = new Iphone();
        iphone1.setModel("SE");
        iphone1.setRef(true);
        iphone1.setReleaseDate(2014);
        Iphone iphone2 = new Iphone();
        iphone2.setModel("SE");
        iphone2.setRef(false);
        iphone2.setReleaseDate(2015);
        Iphone iphone3 = new Iphone();
        iphone3.setModel("7");
        iphone3.setRef(true);
        iphone3.setReleaseDate(2016);
        Iphone iphone4 = new Iphone();
        iphone4.setModel("SE");
        iphone4.setRef(true);
        iphone4.setReleaseDate(2017);
        Iphone iphone5 = new Iphone();
        iphone5.setModel("8");
        iphone5.setRef(false);
        iphone5.setReleaseDate(2018);

        iphoneList.add(iphone1);
        iphoneList.add(iphone2);
        iphoneList.add(iphone3);
        iphoneList.add(iphone4);
        iphoneList.add(iphone5);

        checkIphoneList.add(iphone4);
        checkIphoneList.add(iphone5);

        iphoneRepository.saveAll(iphoneList);
        List<Iphone> resultIphoneList = this.restTemplate.exchange(host + "/iphone/findModelThan?releaseDate=2016", HttpMethod.GET,
                null, new ParameterizedTypeReference<List<Iphone>>() {
                }).getBody();
        assertThat(resultIphoneList).containsExactlyElementsOf(checkIphoneList);
    }


    @Test
    void shouldCallGetreversEndpointWithCorrectParameters() {
        String result = this.restTemplate.getForObject(host + "/test/reverse", String.class);
        assertThat(result).isEqualTo("tac");
    }



}