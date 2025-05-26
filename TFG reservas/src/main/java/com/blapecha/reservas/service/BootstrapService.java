package com.blapecha.reservas.service;

import com.blapecha.reservas.entity.*;
import com.blapecha.reservas.repository.*;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class BootstrapService {

    @Autowired
    private LocalRepository localRepository;

    @Autowired
    private DuenyoRepository duenyoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ResenyaRepository resenyaRepository;

    @Autowired
    private ReservaService reservaService;

    @Autowired
    private ResourceLoader resourceLoader;

    @PostConstruct
    public void init() {
        var cliente = crearCliente1();
        clienteRepository.save(cliente);
        var cliente2 = crearCliente2();
        clienteRepository.save(cliente2);
        var duenyo = crearDuenyo1();
        duenyoRepository.save(duenyo);
        var local = crearLocal1();
        local.setDuenyo(duenyo);
        localRepository.save(local);

        var resenya = crearResenya1();
        resenya.setLocal(local);
        resenya.setCliente(cliente);
        resenyaRepository.save(resenya);

        resenya = crearResenya2();
        resenya.setLocal(local);
        resenya.setCliente(cliente2);
        resenyaRepository.save(resenya);

        var reserva = crearReserva1();
        reserva.setLocal(local);
        reserva.setCliente(cliente);
        this.reservaService.guardar(reserva);
        reserva = crearReserva1();
        reserva.setFecha(reserva.getFecha().plusDays(1));
        reserva.setLocal(local);
        reserva.setCliente(cliente2);
        this.reservaService.guardar(reserva);
        reserva = crearReserva1();
        reserva.setFecha(reserva.getFecha().plusDays(-1));
        reserva.setTarde(false);
        reserva.setLocal(local);
        reserva.setCliente(cliente2);
        this.reservaService.guardar(reserva);
        reserva = crearReserva1();
        reserva.setFecha(reserva.getFecha().plusDays(-1));
        reserva.setTarde(true);
        reserva.setLocal(local);
        reserva.setCliente(cliente2);
        this.reservaService.guardar(reserva);

        local = crearLocal2();
        local.setDuenyo(duenyo);
        localRepository.save(local);

        local = crearLocal3();
        local.setDuenyo(duenyo);
        localRepository.save(local);

        duenyo = crearDuenyo2();
        duenyoRepository.save(duenyo);
        local = crearLocal4();
        local.setDuenyo(duenyo);
        localRepository.save(local);

        local = crearLocal5();
        local.setDuenyo(duenyo);
        localRepository.save(local);

    }

    private Reserva crearReserva1() {
        Reserva reserva = new Reserva();
        reserva.setCodigo("BOT-1");
        reserva.setTarde(true);
        reserva.setFecha(LocalDate.now());
        return reserva;
    }

    private Cliente crearCliente1(){
        Cliente cliente = new Cliente();
        cliente.setUsername("c1");
        cliente.setPassword("c1");
        cliente.setNombre("Alicia");
        cliente.setApellidos("Marti Guillem");
        return cliente;
    }

    private Cliente crearCliente2(){
        Cliente cliente = new Cliente();
        cliente.setUsername("c2");
        cliente.setPassword("c2");
        cliente.setNombre("Vicky");
        cliente.setApellidos("Garcia DeOñate");
        return cliente;
    }

    private Duenyo crearDuenyo1() {
        Duenyo duenyo = new Duenyo();
        duenyo.setUsername("d1");
        duenyo.setPassword("d1");
        duenyo.setNombre("Francisco");
        duenyo.setApellidos("Vacas Galayev");
        return duenyo;
    }

    private Duenyo crearDuenyo2() {
        Duenyo duenyo = new Duenyo();
        duenyo.setUsername("d2");
        duenyo.setPassword("d2");
        duenyo.setNombre("Denys");
        duenyo.setApellidos("Carme Gonzalez");
        return duenyo;
    }

    private Resenya crearResenya1() {
        Resenya resenya = new Resenya();
        resenya.setFecha(LocalDate.now());
        resenya.setTitulo("Muy sorprendente");
        resenya.setMensaje("Mucho mejor de lo que esperabamos, nos gusto mucho y sin duda repetiremos");
        resenya.setPuntuacion(5);
        return resenya;
    }

    private Resenya crearResenya2() {
        Resenya resenya = new Resenya();
        resenya.setFecha(LocalDate.now());
        resenya.setTitulo("Aceptable");
        resenya.setMensaje("Un local normal, nada a destacar");
        resenya.setPuntuacion(3);
        return resenya;
    }

    private Local crearLocal1() {
        Local local = new Local();
        try {
            local.setImagen(resourceLoader.getResource("classpath:fachada2.png").getContentAsByteArray());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        local.setDireccion("Calle Ter 12 Bajo");
        local.setNombre("Festi-Party");
        local.setTipo(TipoLocalEnum.FIESTAS);
        local.setPrecioManyana(50);
        local.setPrecioTarde(60);
        local.setMunicipio("Valencia");
        local.setBloqueado(false);
        return local;
    }

    private Local crearLocal2() {
        Local local = new Local();
        try {
            local.setImagen(resourceLoader.getResource("classpath:fachada8.png").getContentAsByteArray());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        local.setDireccion("Calle Rio Bidasoa 4 Bajo");
        local.setNombre("Mini Fiestas");
        local.setTipo(TipoLocalEnum.FIESTAS);
        local.setPrecioManyana(50);
        local.setPrecioTarde(60);
        local.setMunicipio("Valencia");
        local.setBloqueado(false);
        return local;
    }

    private Local crearLocal3() {
        Local local = new Local();
        try {
            local.setImagen(resourceLoader.getResource("classpath:fachada9.png").getContentAsByteArray());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        local.setDireccion("Calle Margarita 14 Bajo");
        local.setNombre("Cine");
        local.setTipo(TipoLocalEnum.FIESTAS);
        local.setPrecioManyana(35);
        local.setPrecioTarde(45);
        local.setMunicipio("Valencia");
        local.setBloqueado(false);
        return local;
    }

    private Local crearLocal4() {
        Local local = new Local();
        try {
            local.setImagen(resourceLoader.getResource("classpath:fachada4.png").getContentAsByteArray());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        local.setDireccion("Calle Amapola 14 Bajo");
        local.setNombre("Sali parque");
        local.setTipo(TipoLocalEnum.PARQUE_INFANTIL);
        local.setPrecioManyana(65);
        local.setPrecioTarde(75);
        local.setMunicipio("Burjassot");
        local.setBloqueado(false);
        return local;
    }

    private Local crearLocal5() {
        Local local = new Local();
        try {
            local.setImagen(resourceLoader.getResource("classpath:fachada10.png").getContentAsByteArray());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        local.setDireccion("Calle Rosa 25 Bajo");
        local.setNombre("He Or She");
        local.setTipo(TipoLocalEnum.SALON_DE_EVENTOS);
        local.setPrecioManyana(120);
        local.setPrecioTarde(150);
        local.setMunicipio("Catarroja");
        local.setBloqueado(false);
        return local;
    }
}