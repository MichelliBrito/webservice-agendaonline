package agendaonline.com.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import agendaonline.com.models.Convenio;
import agendaonline.com.repositories.ConvenioRepository;

@RestController
public class ConveniosController {

	@Autowired
	private ConvenioRepository cr;
	
	@GetMapping(value="/convenios", produces="application/json")
	public @ResponseBody Iterable<Convenio> listaConvenios(){
		Iterable<Convenio> listaConvenios = cr.findAll();
		return listaConvenios;
	}
	
	
	@PostMapping(value="/convenios")
	public Convenio cadastroConvenio(@RequestBody Convenio convenio){
		return cr.save(convenio);
	}
}
