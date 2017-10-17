package agendaonline.com.controllers;


import java.util.ArrayList;

import javax.validation.Valid;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

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
	public @ResponseBody ArrayList<Paciente> listaPacientes(){
		Iterable<Paciente> listaPacientes = pr.findAll();	
		ArrayList<Paciente> pacienteReturn = new ArrayList<Paciente>();
		for(Paciente paciente : listaPacientes){
			String nome = paciente.getNome();
			paciente.add(linkTo(methodOn(PacientesController.class).especificaPaciente(nome)).withSelfRel()); // Adicione uma referencia ao método especificaPaciente do controller passando o ID como parâmetro
            // isso é feito para todos os elementos da lista
			pacienteReturn.add(paciente);
		}
		return pacienteReturn;
	}

	@ApiOperation(value = "Mostra paciente específico" )
	@GetMapping(value="/{nome}", produces="application/json")
	public @ResponseBody Paciente especificaPaciente(@PathVariable(value = "nome") String nome) {
		Paciente paciente = pr.findByNome(nome);
		paciente.add(linkTo(methodOn(PacientesController.class).listaPacientes()).withRel("Lista de Pacientes")); //Adicione uma auto referencia ao método get do controller passando o ID como parâmetro
		return paciente;
	}

	@ApiOperation(value = "Salva paciente" )
	@PostMapping()
	public ResponseEntity<?> listaPacientes(@RequestBody @Valid Paciente paciente, BindingResult result){
		if(result.hasErrors()){
			return ResponseEntity.badRequest().body(result.getFieldError());
		}
		pr.save(paciente);
		String nome = paciente.getNome();
		//adiciona uma referencia ao paciente que foi criado e salvo no banco de dados.
		paciente.add(linkTo(methodOn(PacientesController.class).especificaPaciente(nome)).withSelfRel());
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
