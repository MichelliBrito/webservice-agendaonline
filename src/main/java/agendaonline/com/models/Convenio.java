package agendaonline.com.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

@Entity
public class Convenio {

	@Id
	@NotBlank(message = "Este campo não pode estar em branco.")
	@Size(max=20, message="Este campo deve ter no máximo 20 caracteres.")
	private String nomeConvenio;
	
	@OneToMany //um convenio para muitos pacientes, ou seja, One=convenio toMany=pacientes.
	private List<Paciente> pacientes;

	public String getNomeConvenio() {
		return nomeConvenio;
	}

	public void setNomeConvenio(String nomeConvenio) {
		this.nomeConvenio = nomeConvenio;
	}
	
	
	
}
