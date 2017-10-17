package agendaonline.com.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//Adicione a anotação @JsonIgnoreProperties
@JsonIgnoreProperties(ignoreUnknown = true)

@Entity
public class Paciente extends ResourceSupport implements Serializable{//Extenda ResourceSupport

	private static final long serialVersionUID = 1L;
	
	@GeneratedValue(strategy= GenerationType.AUTO)
	private long codigo;
	
	@Id//o id tera que ser a chave estrangeira da outra coluna, por isso é importante definir certo!
	@NotBlank(message="Este campo não pode estar em branco.")
	@NotNull(message="Este campo não pode ser nulo.")
	private String nome;
	
	@OneToMany
	private List<Consulta> consultas;
	
	@NotNull(message="Este campo não pode ser nulo.")
	@DateTimeFormat(pattern="dd-mm-yyyy")
	private String nascimento;
	
	@NotNull(message="Este campo não pode ser nulo.")
	@Size(min=0, max=20, message="Este campo deve ter no máximo 20 caracteres.")
	private String sexo;
	
	@ManyToOne //Muitos pacientes para um convenio, ou seja, Many=paciente to One=convenio.
	private Convenio convenio;
	
	@OneToMany
	private List<Prontuario> prontuarios;
	
	@Size(min=0, max=30, message="Este campo deve ter no máximo 30 caracteres.")
	@NotNull(message="Este campo não pode ser nulo.")
	private String endereco;
	

	@NotNull(message="Este campo não pode ser nulo.")
	private int numero;
	
	@Size(min=0, max=20, message="Este campo deve ter no máximo 20 caracteres.")
	@NotNull(message="Este campo não pode ser nulo.")
	private String bairro;
	
	@Size(min=0, max=20, message="Este campo deve ter no máximo 20 caracteres.")
	@NotNull(message="Este campo não pode ser nulo.")
	private String cidade;
	

	@NotNull(message="Este campo não pode ser nulo.")
	private long cep;
	
	@Size(min=2, max=2, message="Este campo deve ter 2 caracteres.")
	@NotNull(message="Este campo não pode ser nulo.")
	private String estado;
	

	@NotNull(message="Este campo não pode ser nulo.")
	private long telefone;
	
	@Size(min=0, max=20, message="Este campo deve ter no máximo 20 caracteres.")
	@NotNull(message="Este campo não pode ser nulo.")
	private String email;
	
	@Size(min=0, max=30, message="Este campo deve ter no máximo 30 caracteres.")
	@NotNull(message="Este campo não pode ser nulo.")
	private String observacao;
	
	
	public long getCodigo() {
		return codigo;
	}
	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public long getCep() {
		return cep;
	}
	public void setCep(long cep) {
		this.cep = cep;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public long getTelefone() {
		return telefone;
	}
	public void setTelefone(long telefone) {
		this.telefone = telefone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getObservacao() {
		return observacao;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	public String getNascimento() {
		return nascimento;
	}
	public void setNascimento(String nascimento) {
		this.nascimento = nascimento;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public Convenio getConvenio() {
		return convenio;
	}
	public void setConvenio(Convenio convenio) {
		this.convenio = convenio;
	}

	
}
