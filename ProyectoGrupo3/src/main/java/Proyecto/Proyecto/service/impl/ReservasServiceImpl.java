package Proyecto.Proyecto.service.impl;

import Proyecto.Proyecto.dao.ReservasDao;
import Proyecto.Proyecto.domain.Reservas;
import Proyecto.Proyecto.service.ReservasService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReservasServiceImpl implements ReservasService{

    @Autowired
    private ReservasDao reservasDao;

    @Override
    @Transactional(readOnly=true)
    public List<Reservas> getReservas(boolean activo) {
        var lista = reservasDao.findAll();
        if (activo) {
            // CAMBIO CLAVE AQUÍ: Usamos getActivo() y comparamos con "N"
            lista.removeIf(e -> "N".equals(e.getActivo()));
            // O, si quieres ser explícito sobre lo que NO es activo:
            // lista.removeIf(e -> !("Y".equals(e.getActivo())));
        }
        return lista;
    }

    @Override
    @Transactional(readOnly = true)
    public Reservas getReservas(Reservas reservas) {
        return reservasDao.findById(reservas.getId()).orElse(null);
    }

    @Override
    @Transactional
    public void save(Reservas reservas) {
        reservasDao.save(reservas);
    }

    @Override
    @Transactional
    public void delete(Reservas reservas) {
        reservasDao.delete(reservas);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Reservas> findByIdBetweenOrderByNombre(Long idInicio, Long idFin) {
        return reservasDao.findByIdBetweenOrderByNombre(idInicio, idFin);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Reservas> findByIdIs(Long idIs) {
        return reservasDao.findByIdIs(idIs);
    }
}