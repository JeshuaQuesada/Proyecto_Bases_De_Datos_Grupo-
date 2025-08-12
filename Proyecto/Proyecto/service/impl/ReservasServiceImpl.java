package Proyecto.Proyecto.service.impl;

// Importaciones necesarias
import Proyecto.Proyecto.dao.ReservasDao;
import Proyecto.Proyecto.domain.Reservas;
import Proyecto.Proyecto.service.ReservasService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class ReservasServiceImpl implements ReservasService {

    // Dependencias inyectadas
    @Autowired
    private ReservasDao reservasDao;
    
    @PersistenceContext // Usar @PersistenceContext en lugar de @Autowired para EntityManager
    private EntityManager entityManager;

    // --- Métodos de la interfaz ReservasService ---
    
    @Override
    @Transactional(readOnly=true)
    public List<Reservas> getReservas(boolean activo) {
        var lista = reservasDao.findAll();
        if (activo) {
            lista.removeIf(e -> "N".equals(e.getActivo()));
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
        System.out.println("=== ANTES DE GUARDAR ===");
        System.out.println("Reserva: " + reservas.toString());
        
        Reservas resultado = reservasDao.save(reservas);
        
        // FUERZA EL FLUSH Y COMMIT INMEDIATO
        entityManager.flush();
        entityManager.clear();
        
        System.out.println("=== DESPUÉS DE GUARDAR ===");
        System.out.println("ID generado: " + resultado.getId());
        System.out.println("========================");
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