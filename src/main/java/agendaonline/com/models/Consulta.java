package agendaonline.com.models;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Consulta implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private long codigo;
	
	@ManyToOne
	private Paciente paciente;
	
	@ManyToOne
	private Procedimento procedimento;
	
	@NotNull(message="Este campo não pode ser nulo.")
	private boolean status;
	
	@NotBlank(message="Este campo não pode estar em branco.")
	@DateTimeFormat(pattern="dd-mm-yyyy")
	private String data;
	
	@NotBlank(message="Este campo não pode estar em branco.")
	@DateTimeFormat(pattern="00:00")
	private String horarioInicio;
	
	@NotBlank(message="Este campo não pode estar em branco.")
	@DateTimeFormat(pattern="00:00")
	private String horarioTermino;
	
	@NotNull(message="Este campo não pode ser nulo.")
	@Size(max=50, message="Este campo deve conter no máximo 50 caracteres")
	private String observacao;
	
	
	public long getCodigo() {
		return codigo;
	}
	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}
	public Paciente getPaciente() {
		return paciente;
	}
	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}
	public Procedimento getProcedimento() {
		return procedimento;
	}
	public void setProcedimento(Procedimento procedimento) {
		this.procedimento = procedimento;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	
	public String getHorarioInicio() {
		return horarioInicio;
	}
	public void setHorarioInicio(String horarioInicio) {
		this.horarioInicio = horarioInicio;
	}
	public String getHorarioTermino() {
		return horarioTermino;
	}
	public void setHorarioTermino(String horarioTermino) {
		this.horarioTermino = horarioTermino;
	}
	public String getObservacao() {
		return observacao;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	
	
	

}
