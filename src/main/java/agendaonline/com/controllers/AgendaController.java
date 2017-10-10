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

import agendaonline.com.models.Consulta;
import agendaonline.com.models.Paciente;
import agendaonline.com.repositories.ConsultaRepository;
import agendaonline.com.repositories.PacienteRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "Agenda Resource") 
@RestController
@RequestMapping("/agendaonline/consulta")
public class AgendaController {
	
	@Autowired
	private ConsultaRepository cr;
	
	@Autowired
	private PacienteRepository pr;
	
	@ApiOperation(value = "Lista de consultas" )
	@GetMapping(produces="application/json")
	public @ResponseBody Iterable<Consulta> listaConsultas() {
		Iterable<Consulta> listaConsultas = cr.findAll();
		return listaConsultas;
	}
	
	@ApiOperation(value = "Salva consulta" )
	@PostMapping()
	public ResponseEntity<?> cadastraConsulta(@RequestBody @ Valid Consulta consulta, BindingResult result, String  pacienteNome){
		if (result.hasErrors()) {
			 return ResponseEntity.badRequest().body(result.getFieldError());
		 }
		Paciente paciente = pr.findByNome(pacienteNome);
		consulta.setPaciente(paciente);
		cr.save(consulta);
		return ResponseEntity.ok(consulta);
	}
	
	@ApiOperation(value = "Detalhes consulta" )
	@GetMapping(value="/{codigo}", produces="application/json")
	public @ResponseBody Consulta detalhesConsulta(@PathVariable("codigo") long codigo){
		Consulta consulta = cr.findByCodigo(codigo);
		return consulta;
	}
	
	@ApiOperation(value = "Deleta consulta" )
	@DeleteMapping(value="/{codigo}")
	public Consulta deletaConsulta(@PathVariable("codigo") long codigo){
		Consulta consulta = cr.findByCodigo(codigo);
		cr.delete(consulta);
		return consulta;
	}
	
}	