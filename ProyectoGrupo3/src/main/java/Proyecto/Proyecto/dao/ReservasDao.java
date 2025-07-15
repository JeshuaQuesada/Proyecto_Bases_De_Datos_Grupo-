package Proyecto.Proyecto.dao;

import Proyecto.Proyecto.domain.Reservas;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReservasDao extends JpaRepository <Reservas, Long>{

    // Metodo para la consulta por id 
    public List<Reservas> findByIdBetweenOrderByNombre(Long idInicio, Long idFin);

    public List<Reservas> findByIdIs(Long idIs);
    
}
