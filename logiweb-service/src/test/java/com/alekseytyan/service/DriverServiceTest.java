package com.alekseytyan.service;

import com.alekseytyan.logiweb.dao.implementation.DriverDaoImpl;
import com.alekseytyan.logiweb.dto.*;
import com.alekseytyan.logiweb.entity.Driver;
import com.alekseytyan.logiweb.entity.enums.DriverState;
import com.alekseytyan.logiweb.service.implementation.DriverServiceImpl;
import com.alekseytyan.logiweb.util.pathfinding.Route;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import static org.mockito.ArgumentMatchers.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class DriverServiceTest {

    @InjectMocks
    DriverServiceImpl service;

    @Mock
    DriverDaoImpl dao;

    @Mock
    ModelMapper mapper;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void saveTest() {
        service.save(null);

        verify(dao, times(1)).save(null);
    }

    @Test
    public void updateTest() {
        service.update(null);

        verify(dao, times(1)).update(null);
    }

    @Test
    public void findAllTest() {
        List<Driver> list = new ArrayList<>();

        list.add(new Driver());
        list.add(new Driver());
        list.add(new Driver());

        when(dao.findAll()).thenReturn(list);

        //test
        List<Driver> empList = service.convertToEntity(service.findAll());

        assertEquals(3, empList.size());
        verify(dao, times(1)).findAll();
    }

    @Test
    public void findByIdTest() {
        when(dao.findById(1L)).thenReturn(
                new Driver(1L, "A","T",1, DriverState.DRIVING,
                        null,null,null,null)
        );

        Driver driver = dao.findById(1L);

        assertEquals("A", driver.getFirstName());
        assertEquals("T", driver.getLastName());
        assertEquals(1, driver.getHoursWorked());
        assertEquals(DriverState.DRIVING, driver.getState());
    }

    @Test
    public void findDriverByUserTest() {
        service.findDriverByUser("email");
        verify(dao, times(1)).findDriverByUser("email");
    }

    @Test
    public void findCoDriversTest() {
        service.findCoDrivers(1L);
        verify(dao, times(1)).findCoDrivers(1L);
    }

    @Test
    public void findSuitableDriversTest() {
        LorryDTO lorryDTO = new LorryDTO();
        CityDTO cityDTO = new CityDTO();
        cityDTO.setName("Angarsk");
        lorryDTO.setCity(cityDTO);

        Route route = new Route();
        route.setPossible(true);

        service.findSuitableDrivers(new OrderDTO(), route, lorryDTO);
        verify(dao, times(1)).findSuitableDrivers(anyString(), anyInt());
    }

    @Test
    public void findSuitableDriversTest2() {
        LorryDTO lorryDTO = new LorryDTO();
        CityDTO cityDTO = new CityDTO();
        cityDTO.setName("Angarsk");
        lorryDTO.setCity(cityDTO);

        Route route = new Route();
        route.setPossible(false);

        service.findSuitableDrivers(new OrderDTO(), route, lorryDTO);
        verify(dao, times(1)).findSuitableDrivers(cityDTO.getName(), 177);
    }

    @Test
    public void findWithoutUserTest() {
        service.findWithoutUser();
        verify(dao, times(1)).findWithoutUser();
    }

    @Test
    public void getStatisticsTest() {
        assertDoesNotThrow(() -> {
            service.getStatistics();
        });
    }

    @Test
    public void deleteTest() {
        DriverDTO driverDTO = new DriverDTO();
        driverDTO.setOrder(new OrderDTO());

        assertThrows(NullPointerException.class, () -> service.delete(driverDTO));
    }

    @Test
    public void deleteTest2() {
        DriverDTO driverDTO = new DriverDTO();
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setDrivers(new ArrayList<>(Collections.singletonList(new DriverDTO())));
        driverDTO.setOrder(orderDTO);

        assertDoesNotThrow(() -> {
            service.delete(driverDTO);
        });
    }

    @Test
    public void deleteTest3() {
        assertDoesNotThrow(() -> {
            service.delete(new DriverDTO());
        });
    }

    @Test
    public void deleteByIdTest() {
        assertThrows(NullPointerException.class, () -> service.deleteById(-1L));
    }
}