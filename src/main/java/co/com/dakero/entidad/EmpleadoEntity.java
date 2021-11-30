package co.com.dakero.entidad;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author Dakero
 *
 */
@Getter
@Setter
//@NoArgsConstructor
@Entity
@Table(name = "empleado")
public class EmpleadoEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idEmpleado;

	@Schema(description = "nombres empleado")
	@Size(min = 4, message = "{nombre.size}")
	@NotBlank(message = "{nombre.nulo}")
	@Column(name = "nombre", nullable = false, length = 70)
	@NotBlank(message = "La cuenta no puede ser nula y no se puede completar")
	private String nombre;

	@Schema(description = "apellidos del empleado")
	@Size(min = 4, message = "{apellido.size}")
	@NotBlank(message = "{apellido.nulo}")
	@Column(name = "apellido", nullable = false, length = 70)
	private String apellido;

	@Schema(description = "tipoDocumento del empleado")
	@Size(min = 2, message = "{tipoDocumento.size}")
	@NotBlank(message = "{tipoDocumento.nulo}")
	@Column(name = "tipoDocumento", nullable = false, length = 2)
	private String tipoDocumento;

	@Size(min = 8, max = 8, message = "numero de identificación debe tener 8 caracteres")
	@Column(name = "numeroDocumento", nullable = false, length = 8, unique = true)
	private String numero_documento;

	@Schema(description = "fecha nacimiento")
//	@Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "{fechaNacimiento.format}")
//	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd")
//	@Pattern(regexp = "^ [0-9] {4} - [0-9] {2} - [0-9] {2} $", message = "el formato de la fecha de nacimiento es incorrecto")
	@NotNull(message = "{fechaNacimiento.nulo}")
	@Column(name = "fechaNacimiento", nullable = false)
	private LocalDateTime fecha_nacimiento;

	@Schema(description = "fecha ingreso compañia")
//	@Pattern(regexp = "^ [0-9] {4} - [0-9] {2} - [0-9] {2} $", message = "el formato de la fecha de nacimiento es incorrecto")
	@Column(name = "fechaVinculacion", nullable = false)
	@NotNull(message = "{fechaVinculacion.nulo}")
	private LocalDateTime fecha_vinculacion;

	@Schema(description = "Cargo vinculacion")
	@Column(name = "cargo", nullable = false)
	private String cargo;

	@Transient
	@Min(value = 18, message = "{edad.minima}")
	private Integer edad;

	@Column(name = "salario", nullable = false)
	private Double salario;

	public EmpleadoEntity(Integer idEmpleado,
			@Size(min = 4, message = "{nombre.size}") @NotBlank(message = "{nombre.nulo}") @NotBlank(message = "La cuenta no puede ser nula y no se puede completar") String nombre,
			@Size(min = 4, message = "{apellido.size}") @NotBlank(message = "{apellido.nulo}") String apellido,
			@Size(min = 2, message = "{tipoDocumento.size}") @NotBlank(message = "{tipoDocumento.nulo}") String tipoDocumento,
			@Size(min = 8, max = 8, message = "numero de identificación debe tener 8 caracteres") String numero_documento,
			LocalDateTime fecha_nacimiento, LocalDateTime fecha_vinculacion, String cargo,
			@Min(value = 18, message = "{edad.minima}") Integer edad, Double salario) {
		super();
		this.idEmpleado = idEmpleado;
		this.nombre = nombre;
		this.apellido = apellido;
		this.tipoDocumento = tipoDocumento;
		this.numero_documento = numero_documento;
		this.fecha_nacimiento = fecha_nacimiento;
		this.fecha_vinculacion = fecha_vinculacion;
		this.cargo = cargo;
		this.edad = Period.between(fecha_nacimiento.toLocalDate(), LocalDate.now()).getYears();
		this.salario = salario;
	}

}
