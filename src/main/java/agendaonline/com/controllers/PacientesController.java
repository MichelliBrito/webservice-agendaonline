package agendaonline.com.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


import agendaonline.com.models.Paciente;
import agendaonline.com.models.Prontuario;

import agendaonline.com.repositories.PacienteRepository;
import agendaonline.com.repositories.ProntuariosRepository;

@RestController
public class PacientesController {//terminar, colocar remove e edite

	@Autowired
	private PacienteRepository pr;
	
	@Autowired
	private ProntuariosRepository prr;
	
	@GetMapping(value="/pacientes", produces="application/json")
	public @ResponseBody Iterable<Paciente> listaPacientes(){
		Iterable<Paciente> listaPacientes = pr.findAll();	
		return listaPacientes;
	}
	
	@PostMapping(value="/pacientes")
	public Paciente listaPacientes(@RequestBody Paciente paciente){
		return pr.save(paciente);
	}
	
	@GetMapping(value="/paciente/{nome}", produces="application/json")
	public @ResponseBody Iterable<Prontuario> detalhes(@PathVariable("nome") String nome){//rever pathvariable!!
		Paciente paciente = pr.findOne(nome);
		Iterable<Prontuario> prontuarios = prr.findByPaciente(paciente);
		System.out.print("executou essa url");
		return prontuarios;
	}
}
