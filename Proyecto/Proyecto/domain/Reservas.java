package Proyecto.Proyecto.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import lombok.Data;

@Data
@Entity
@Table(name = "reservas")
public class Reservas implements Serializable {
    private static final long serialVersionUID = 1L;

    public static final String TIPO_ESTANDAR = "Estandar";
    public static final String TIPO_PREMIUM = "Premium";
    public static final String TIPO_SUITE = "Suite";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    //Atributos
    private String nombre;
    private String apellido;
    private int cedula;
    private int telefono;
    private String correo;
    private String tipoHabitacion;

    // CAMBIO CLAVE AQUÍ: De boolean a String
    @Column(name = "activo") // Asegúrate de que el nombre de la columna sea explícito si es diferente
    private String activo; // Ahora es String para mapear a CHAR(1)

    public Reservas() {
        // Inicializar 'activo' a 'Y' por defecto si es una nueva reserva
        this.activo = "Y";
    }

    // Constructor que tenías, ajustado para String activo
    public Reservas(String nombre, String tipoHabitacion, String activo) {
        this.nombre = nombre;
        this.tipoHabitacion = tipoHabitacion;
        this.activo = activo; // Asegúrate de que el valor pasado sea 'Y' o 'N'
    }

    // Si ya tienes getters y setters generados por Lombok, esto es suficiente.
    // Si no usas Lombok para los getters/setters, asegúrate de tenerlos y considera
    // agregar validación en el setter de activo para solo aceptar 'Y' o 'N'.
}