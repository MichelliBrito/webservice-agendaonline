package agendaonline.com.controllers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import agendaonline.com.models.Consulta;
import agendaonline.com.models.Paciente;
import agendaonline.com.models.Procedimento;
import agendaonline.com.models.Prontuario;
import agendaonline.com.repositories.ConsultaRepository;
import agendaonline.com.repositories.ProntuariosRepository;

@RestController
public class AgendaController {
	
	@Autowired
	private ConsultaRepository cr;
	
	@Autowired
	private ProntuariosRepository prr;
	
	
	@GetMapping(value="/consulta", produces="application/json")
	public @ResponseBody Iterable<Consulta> MontaAgenda() {
		Iterable<Consulta> listaConsultas = cr.findAll();
		return listaConsultas;
	}
	
	@PostMapping("/consulta")
	public Consulta MontaAgenda(@RequestBody Consulta consulta){
		return cr.save(consulta);
	}
	
	@GetMapping(value="/consulta/{codigo}", produces="application/json")
	public @ResponseBody Consulta detalhesConsulta(@PathVariable("codigo") long codigo){
		Consulta consulta = cr.findByCodigo(codigo);
		return consulta;
	}
	
	
	@PostMapping(value="/consulta/{codigo}")
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
