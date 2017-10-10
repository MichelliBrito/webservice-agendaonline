package agendaonline.com.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


import agendaonline.com.models.Paciente;
import agendaonline.com.models.Prontuario;

import agendaonline.com.repositories.PacienteRepository;
import agendaonline.com.repositories.ProntuariosRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "Pacientes Resource")
@RestController
@RequestMapping("/agendaonline/pacientes")
public class PacientesController {//terminar, colocar remove e edite

	@Autowired
	private PacienteRepository pr;
	
	@Autowired
	private ProntuariosRepository prr;
	
	@ApiOperation(value = "Lista de pacientes" )
	@GetMapping(produces="application/json")
	public @ResponseBody Iterable<Paciente> listaPacientes(){
		Iterable<Paciente> listaPacientes = pr.findAll();	
		return listaPacientes;
	}
	
	@ApiOperation(value = "Salva paciente" )
	@PostMapping()
	public Paciente listaPacientes(@RequestBody Paciente paciente){
		return pr.save(paciente);
	}
	
	@ApiOperation(value = "Deleta paciente" )
	@DeleteMapping(value="/{nome}")
	public Paciente deletaPacientes(@PathVariable("nome") String nome){
		Paciente paciente = pr.findByNome(nome);
		pr.delete(paciente);
		return paciente;
	}
	
	@ApiOperation(value = "Detalhes do paciente - lista de prontuários" )
	@GetMapping(value="/prontuarios/{nome}", produces="application/json")
	public @ResponseBody Iterable<Prontuario> detalhes(@PathVariable("nome") String nome){//rever pathvariable!!
		Paciente paciente = pr.findOne(nome);
		Iterable<Prontuario> prontuarios = prr.findByPaciente(paciente);
		System.out.print("executou essa url");
		return prontuarios;
	}
	
	@ApiOperation(value = "Deleta prontuario" )
	@DeleteMapping(value="/prontuarios/{data}")
	public Prontuario deletaProntuario(@PathVariable("data") String data){
		Prontuario prontuario = prr.findByData(data);
		prr.delete(prontuario);
		return prontuario;
	}
}
