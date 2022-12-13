package by.savich.project.springboot.service;

import by.savich.project.springboot.entity.Iphone;
import by.savich.project.springboot.repository.IphoneRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import javax.security.auth.Subject;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static by.savich.project.springboot.service.IphoneService.IPHONES;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class IphoneServiceTest {

    IphoneService iphoneService;
    IphoneRepository iphoneRepository;

    @BeforeEach
    void init() {
        iphoneRepository = Mockito.mock(IphoneRepository.class);
        iphoneService = new IphoneService(iphoneRepository);
    }

    @Test
    void shouldReturnIphoneFromRepositoryWhenCalGetById() {
        Iphone iphone = new Iphone();
        int id = 3;
        when(iphoneRepository.findById(anyInt())).thenReturn(Optional.of(iphone));

        Iphone resultIphone = iphoneService.getIphoneById(id);
        assertThat(resultIphone).isEqualTo(iphone);
    }

    @Test
    void shouldThrowRuntimeExceptionWhenCallGetByIdIfNoResultsWereFound() {
        int id = 77;
        when(iphoneRepository.findById(anyInt())).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> iphoneService.getIphoneById(id));
    }

    @Test
    void shouldCreateRandomIphoneWhenCallGenerateRandomIphoneMethod() {
        when(iphoneRepository.save(any(Iphone.class))).thenAnswer(invocation -> invocation.getArguments()[0]);

        Iphone iphone = iphoneService.addRandomIphone();

        assertThat(IPHONES).contains(iphone.getModel());
        assertThat(iphone.getReleaseDate()).isNotNull();
        assertThat(iphone.getReleaseDate()).isPositive();
        assertThat(iphone.getReleaseDate()).isGreaterThan(1);
    }

    @Test
    void shouldUpdateIphoneWhenCallUpdateMethod() {
        Iphone newIphone=new Iphone();
        iphoneService.updateIphone(newIphone);
        verify(iphoneRepository).save(newIphone);
    }

    @Test
    void shouldDeleteIphoneWhenCallDeleteMethod() {
        int id=5;
        iphoneService.deleteIphoneByID(id);
        verify(iphoneRepository).deleteById(5);
    }

    @Test
    void shouldReturnIphoneFromRepositoryWhenCallGetByModel() {
        List <Iphone>iphoneList=new ArrayList<>();
        String model="SE";
        when(iphoneRepository.getIphonesByModel(anyString())).thenReturn(iphoneList);

        List<Iphone> resultIphoneList = iphoneService.getIphoneByModel(model);
        assertThat(resultIphoneList).isEqualTo(iphoneList);
    }

    @Test
    void shouldReturnIphoneFromRepositoryWhenCallIphoneByRefTrueAndModel() {
        List <Iphone>iphoneList=new ArrayList<>();
        String model="SE";
        when(iphoneRepository.findIphoneByRefTrueAndModel(anyString())).thenReturn(iphoneList);

        List<Iphone> resultIphoneList = iphoneService.getIphoneByRefTrueAndModel(model);
        assertThat(resultIphoneList).isEqualTo(iphoneList);
    }

    @Test
    void shouldReturnIphoneFromRepositoryWhenCallIphoneByReleaseDateLessThan() {
        List <Iphone>iphoneList=new ArrayList<>();
        int date=500;
        when(iphoneRepository.findIphoneByReleaseDateLessThan(anyInt())).thenReturn(iphoneList);

        List<Iphone> resultIphoneList = iphoneService.getIphoneByReleaseDateLessThan(date);
        assertThat(resultIphoneList).isEqualTo(iphoneList);
    }

    @Test
    void shouldReturnIphoneFromRepositoryWhenCallIphoneByReleaseDateLessThan2() {
        List <Iphone>iphoneList=new ArrayList<>();
        int date=500;
        when(iphoneRepository.findIphoneByReleaseDateLessThan2(anyInt())).thenReturn(iphoneList);

        List<Iphone> resultIphoneList = iphoneService.getIphoneByReleaseDateLessThan2(date);
        assertThat(resultIphoneList).isEqualTo(iphoneList);
    }
}