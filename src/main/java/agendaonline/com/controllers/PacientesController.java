package agendaonline.com.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


import agendaonline.com.models.Paciente;

import agendaonline.com.repositories.PacienteRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "Pacientes Resource")
@RestController
@RequestMapping("/agendaonline/pacientes")
public class PacientesController {//terminar, colocar remove e edite

	@Autowired
	private PacienteRepository pr;
	
	
	@ApiOperation(value = "Lista de pacientes" )
	@GetMapping(produces="application/json")
	public @ResponseBody Iterable<Paciente> listaPacientes(){
		Iterable<Paciente> listaPacientes = pr.findAll();	
		return listaPacientes;
	}
	
	@ApiOperation(value = "Salva paciente" )
	@PostMapping()
	public ResponseEntity<?> listaPacientes(@RequestBody @Valid Paciente paciente, BindingResult result){
		if(result.hasErrors()){
			return ResponseEntity.badRequest().body(result.getFieldError());
		}
		pr.save(paciente);
		return ResponseEntity.ok(paciente);
	}
	
	@ApiOperation(value = "Deleta paciente" )
	@DeleteMapping(value="/{nome}")
	public Paciente deletaPacientes(@PathVariable("nome") String nome){
		Paciente paciente = pr.findByNome(nome);
		pr.delete(paciente);
		return paciente;
	}
	
}
