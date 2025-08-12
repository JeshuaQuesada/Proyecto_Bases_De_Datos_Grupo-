package Proyecto.Proyecto.dao;

import Proyecto.Proyecto.domain.Reservas;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReservasDao extends JpaRepository<Reservas, Long> {
    
    // Este método está bien
    List<Reservas> findByIdBetweenOrderByNombre(Long idInicio, Long idFin);
    
    // CORRIGE ESTE: Agrega la query explícita
    @Query("SELECT r FROM Reservas r WHERE r.id = :idIs")
    List<Reservas> findByIdIs(@Param("idIs") Long idIs);
}