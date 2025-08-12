package Proyecto.Proyecto.service;

import Proyecto.Proyecto.domain.Reservas;
import java.util.List;

public interface ReservasService {

    // Se obtiene un listado de categorias en un List
    public List<Reservas> getReservas(boolean activo);
    
    // Se obtiene un Categoria, a partir del id de un categoria
    public Reservas getReservas(Reservas reservas);
    
    // Se inserta un nuevo categoria si el id del categoria esta vacío
    // Se actualiza un categoria si el id del categoria NO esta vacío
    public void save(Reservas reservas);
    
    // Se elimina el categoria que tiene el id pasado por parámetro
    public void delete(Reservas reservas);
    
    // Metodo para la consulta de reserva por id 
    public List<Reservas> findByIdBetweenOrderByNombre(Long idInicio, Long idFin);
    
    public List<Reservas> findByIdIs(Long idIs);
}
