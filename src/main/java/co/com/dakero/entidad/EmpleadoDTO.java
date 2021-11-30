package co.com.dakero.entidad;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmpleadoDTO {

	private Integer idEmpleado;
	private String nombre;
	private String apellido;
	private String tipoDocumento;
	private String numero_documento;
	private LocalDateTime fecha_nacimiento;
	private LocalDateTime fecha_vinculacion;
	private String cargo;
	private Double salario;

	private String edadActual;
	private String tiempoCompañia;

	public EmpleadoDTO(EmpleadoEntity empleado) {
		super();
		this.idEmpleado = empleado.getIdEmpleado();
		this.nombre = empleado.getNombre();
		this.apellido = empleado.getApellido();
		this.tipoDocumento = empleado.getTipoDocumento();
		this.numero_documento = empleado.getNumero_documento();
		this.fecha_nacimiento = empleado.getFecha_nacimiento();
		this.fecha_vinculacion = empleado.getFecha_vinculacion();
		this.cargo = empleado.getCargo();
		this.salario = empleado.getSalario();

		Period pEdad = Period.between(empleado.getFecha_nacimiento().toLocalDate(), LocalDate.now());
		Period pCompañia = Period.between(empleado.getFecha_vinculacion().toLocalDate(), LocalDate.now());

		this.edadActual = String.format("%d años, %d meses y %d días", pEdad.getYears(), pEdad.getMonths(),
				pEdad.getDays());
		this.tiempoCompañia = String.format("%d años, %d meses y %d días", pCompañia.getYears(), pCompañia.getMonths(),
				pCompañia.getDays());
	}

}
