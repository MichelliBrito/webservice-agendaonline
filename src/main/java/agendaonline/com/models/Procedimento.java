package agendaonline.com.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

@Entity
public class Procedimento {

	@Id
	@NotBlank(message = "Este campo não pode estar em branco.")
	@Size(max=20, message="Este campo deve ter no máximo 20 caracteres.")
	private String tipoProcedimento;
	
	@OneToMany
	private List<Consulta> consultas;
	
	@OneToMany
	private List<Prontuario> prontuario;

	public String getTipoProcedimento() {
		return tipoProcedimento;
	}

	public void setTipoProcedimento(String tipoProcedimento) {
		this.tipoProcedimento = tipoProcedimento;
	}

	
	
	
}
