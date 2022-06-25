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
class IphoneControllerIntegrationTest {
    @LocalServerPort
    private int port;

    @MockBean
    private IphoneRepository iphoneRepository;

    @Autowired
    private TestRestTemplate restTemplate;

    private String host;

    @BeforeEach
    void init() {
        host = "http://localhost:" + port;
    }

    @Test
    void shouldGenerateRandomIphone() {
        when(iphoneRepository.save(any(Iphone.class))).thenAnswer(invocation -> {
            return invocation.getArguments()[0];
        });

        Iphone iphone = this.restTemplate.getForObject(host + "/iphone/addIphone", Iphone.class);

        assertThat(IPHONES).contains(iphone.getModel());
        assertThat(iphone.getReleaseDate()).isNotNull();
        assertThat(iphone.getReleaseDate()).isPositive();
        assertThat(iphone.getReleaseDate()).isGreaterThan(1);

        verify(iphoneRepository).save(iphone);
    }

    @Test
    void shouldGetIphoneById() {
        Iphone iphone = new Iphone();

        when(iphoneRepository.findById(anyInt())).thenReturn(Optional.of(iphone));

        Iphone result = this.restTemplate.getForObject(host + "/iphone/get/4", Iphone.class);

        assertThat(result).isEqualTo(iphone);
    }


    @Test
    void shouldGetAllIphones() {
        List<Iphone> iphoneList = new ArrayList<>();

        when(iphoneRepository.findAll()).thenReturn(iphoneList);

        List<Iphone> resultList = this.restTemplate.exchange(host + "/iphone", HttpMethod.GET,
                null, new ParameterizedTypeReference<List<Iphone>>() {
                }).getBody();

        assertThat(resultList).isEqualTo(iphoneList);
    }

    @Test
    void shouldGetIphoneByModel() {
        List<Iphone> iphoneList = new ArrayList<>();
        Iphone iphone1 = new Iphone();
        iphoneList.add(iphone1);
        when(iphoneRepository.getIphonesByModel(anyString())).thenReturn(iphoneList);

        List<Iphone> resultList = this.restTemplate.exchange(host + "/iphone/getModel/SE", HttpMethod.GET,
                null, new ParameterizedTypeReference<List<Iphone>>() {
                }).getBody();
        assertThat(resultList).isEqualTo(iphoneList);
    }

    @Test
    void shouldGetIphoneByRefTrueAndModel() {
        List<Iphone> iphoneList = new ArrayList<>();
        Iphone iphone1 = new Iphone();
        iphoneList.add(iphone1);
        when(iphoneRepository.findIphoneByRefTrueAndModel(anyString())).thenReturn(iphoneList);

        List<Iphone> resultIphoneList = this.restTemplate.exchange(host + "/iphone/findRefTrue/6", HttpMethod.GET,
                null, new ParameterizedTypeReference<List<Iphone>>() {
                }).getBody();
        assertThat(resultIphoneList).isEqualTo(iphoneList);
    }

    @Test
    void shouldUpdateIphone() {
        Iphone iphone = new Iphone();
        HttpEntity<Iphone> httpEntity = new HttpEntity<>(iphone);
        ResponseEntity<Void> response = this.restTemplate.exchange(host + "/iphone/update",
                HttpMethod.PUT, httpEntity, Void.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        verify(iphoneRepository).save(iphone);
    }

    @Test
    void shouldDeleteIphone() {
        ResponseEntity<Void> response = this.restTemplate.exchange(host + "/iphone/delete/1",
                HttpMethod.DELETE,null, Void.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        verify(iphoneRepository).deleteById(1);
    }

    @Test
    void shouldCallGetreversEndpointWithCorrectParameters() {
        String result = this.restTemplate.getForObject(host + "/test/reverse", String.class);
        assertThat(result).isEqualTo("tac");
        System.out.println();
    }


}