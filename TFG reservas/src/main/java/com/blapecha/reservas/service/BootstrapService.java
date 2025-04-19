package com.blapecha.reservas.service;

import com.blapecha.reservas.entity.*;
import com.blapecha.reservas.repository.*;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;

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

    @PostConstruct
    public void init() {

        var locales = localRepository.findAll();
        Duenyo duenyo = new Duenyo();
        duenyo.setUsername("admin");
        duenyo.setPassword("admin");
        duenyo.setNombre("admin");
        duenyo.setApellidos("admin");
        duenyo.setEmail("admin@admin.com");
        duenyoRepository.save(duenyo);

        Cliente cliente = new Cliente();
        cliente.setUsername("cliente");
        cliente.setPassword("cliente");
        cliente.setNombre("Blanca");
        cliente.setApellidos("Pérez Chavarría");
        cliente.setEmail("cliente@cliente.com");
        clienteRepository.save(cliente);


        if (locales.isEmpty()) {
            locales = new ArrayList<>();
            for (int i = 0; i < 2; i++) {
                Local local = new Local();
                local.setDireccion("Direccion " + i);
                local.setNombre("Nombre " + i);
                local.setMunicipio("Valencia");
                local.setPrecioManyana(100);
                local.setPrecioTarde(150);
                local.setDuenyo(duenyo);
                local.setTipo(TipoLocalEnum.FIESTAS);
                locales.add(local);
            }
            for (int i = 2; i < 4; i++) {
                Local local = new Local();
                local.setDireccion("Direccion " + i);
                local.setNombre("Nombre " + i);
                local.setMunicipio("Benetusser");
                local.setPrecioManyana(100);
                local.setPrecioTarde(150);
                local.setDuenyo(duenyo);
                local.setTipo(TipoLocalEnum.PARQUE_INFANTIL);
                locales.add(local);
            }
            for (int i = 4; i < 6; i++) {
                Local local = new Local();
                local.setDireccion("Direccion " + i);
                local.setNombre("Nombre " + i);
                local.setMunicipio("Aldaia");
                local.setPrecioManyana(100);
                local.setPrecioTarde(150);
                local.setDuenyo(duenyo);
                local.setTipo(TipoLocalEnum.SALON_DE_EVENTOS);
                locales.add(local);
            }

            localRepository.saveAll(locales);

            Resenya resenya = new Resenya();
            resenya.setCliente(cliente);
            resenya.setTitulo("Lo pasamos genial");
            resenya.setLocal(locales.get(0));
            resenya.setPuntuacion(5);
            resenya.setFecha(LocalDate.now());
            resenya.setMensaje("Pasamos una tarde super divertida, local muy recomendable");

            resenyaRepository.save(resenya);

            resenya = new Resenya();
            resenya.setCliente(cliente);
            resenya.setTitulo("Justo lo que esperabamos");
            resenya.setLocal(locales.get(0));
            resenya.setPuntuacion(4);
            resenya.setFecha(LocalDate.now());
            resenya.setMensaje("El local en general bastante bien, aunque el dueño tardo un poco en aparecer");

            resenyaRepository.save(resenya);

            Reserva reserva = new Reserva();
            reserva.setCliente(cliente);
            reserva.setFecha(LocalDate.now());
            reserva.setLocal(locales.get(0));
            reserva.setTarde(true);

            reservaService.guardar(reserva);


            duenyo = new Duenyo();
            duenyo.setUsername("admin1");
            duenyo.setPassword("admin1");
            duenyo.setNombre("admin1");
            duenyo.setApellidos("admin1");
            duenyo.setEmail("admin1@admin.com");
            duenyoRepository.save(duenyo);
            for (int i = 10; i < 12; i++) {
                Local local = new Local();
                local.setDireccion("Direccion " + i);
                local.setNombre("Nombre " + i);
                local.setMunicipio("Valencia");
                local.setPrecioManyana(100);
                local.setPrecioTarde(150);
                local.setDuenyo(duenyo);
                local.setTipo(TipoLocalEnum.FIESTAS);
                locales.add(local);
            }
            localRepository.saveAll(locales);


        }
    }
}