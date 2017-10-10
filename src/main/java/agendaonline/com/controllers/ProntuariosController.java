package agendaonline.com.controllers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
import agendaonline.com.models.Procedimento;
import agendaonline.com.models.Prontuario;
import agendaonline.com.repositories.ConsultaRepository;
import agendaonline.com.repositories.PacienteRepository;
import agendaonline.com.repositories.ProntuariosRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "Prontuario Resource") 
@RestController
@RequestMapping("/agendaonline/prontuario")
public class ProntuariosController {

	@Autowired
	private ConsultaRepository cr;
	
	@Autowired
	private ProntuariosRepository prr;
	
	@Autowired
	private PacienteRepository pr;
	
	
	@ApiOperation(value = "Lista de prontuários" )
	@GetMapping(value="/{nome}", produces="application/json")
	public @ResponseBody Iterable<Prontuario> listaProntuarios(@PathVariable("nome") String nome){//rever pathvariable!!
		Paciente paciente = pr.findOne(nome);
		Iterable<Prontuario> prontuarios = prr.findByPaciente(paciente);
		System.out.print("executou essa url");
		return prontuarios;
	}
	
	@ApiOperation(value = "Salva prontuário" )
	@PostMapping()
	public ResponseEntity<?> cadastroProntuario(long codigoPaciente,  @RequestBody @Valid Prontuario prontuario, BindingResult result){
		
		if (result.hasErrors()) {
			 return ResponseEntity.badRequest().body(result.getFieldError());
		 }
		
		Consulta consulta = cr.findByCodigo(codigoPaciente);
		
		
		Paciente paciente = consulta.getPaciente();
		prontuario.setPaciente(paciente);
		
		Procedimento procedimento = consulta.getProcedimento();
		prontuario.setProcedimento(procedimento);
		
		LocalDateTime now = LocalDateTime.now();
		String data = now.toString();
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		String newData = formatter.format(now);
		
		prontuario.setData(newData);
		
		prr.save(prontuario);
		
		return ResponseEntity.ok(prontuario);
	}
	
	@ApiOperation(value = "Deleta prontuario" )
	@DeleteMapping(value="/{data}")
	public Prontuario deletaProntuario(@PathVariable("data") String data){
		Prontuario prontuario = prr.findByData(data);
		prr.delete(prontuario);
		return prontuario;
	}
}
