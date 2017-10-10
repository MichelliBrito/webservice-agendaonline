package agendaonline.com.controllers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
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
import agendaonline.com.repositories.ProntuariosRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "Agenda Resource") 
@RestController
@RequestMapping("/agendaonline/consulta")
public class AgendaController {
	
	@Autowired
	private ConsultaRepository cr;
	
	@Autowired
	private ProntuariosRepository prr;
	
	@ApiOperation(value = "Lista de consultas" )
	@GetMapping(produces="application/json")
	public @ResponseBody Iterable<Consulta> MontaAgenda() {
		Iterable<Consulta> listaConsultas = cr.findAll();
		return listaConsultas;
	}
	
	@ApiOperation(value = "Salva consulta" )
	@PostMapping()
	public Consulta MontaAgenda(@RequestBody Consulta consulta){
		return cr.save(consulta);
	}
	
	@ApiOperation(value = "Detalhes consulta" )
	@GetMapping(value="/{codigo}", produces="application/json")
	public @ResponseBody Consulta detalhesConsulta(@PathVariable("codigo") long codigo){
		Consulta consulta = cr.findByCodigo(codigo);
		return consulta;
	}
	
	@ApiOperation(value = "Salva prontuário" )
	@PostMapping(value="/{codigo}")
	public Prontuario formProntuarioPost(@PathVariable("codigo") long codigo,  @RequestBody Prontuario prontuario){
	
		Consulta consulta = cr.findByCodigo(codigo);
		
		
		Paciente paciente = consulta.getPaciente();
		prontuario.setPaciente(paciente);
		
		Procedimento procedimento = consulta.getProcedimento();
		prontuario.setProcedimento(procedimento);
		
		LocalDateTime now = LocalDateTime.now();
		String data = now.toString();
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		String newData = formatter.format(now);
		
		prontuario.setData(newData);
		
		return prr.save(prontuario);
	}
	
}
