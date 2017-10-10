package agendaonline.com.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import agendaonline.com.models.Convenio;
import agendaonline.com.repositories.ConvenioRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "Convênios Resource") //Diz ao Swagger que esse é um endpoint e REST deve ser documentado
@RestController
@RequestMapping("/agendaonline/convenios")
public class ConveniosController {

	@Autowired
	private ConvenioRepository cr;
	
	@ApiOperation(value = "Mostra lista de convênios" )
	@GetMapping(produces="application/json")
	public @ResponseBody Iterable<Convenio> listaConvenios(){
		Iterable<Convenio> listaConvenios = cr.findAll();
		return listaConvenios;
	}
	
	@ApiOperation(value = "Salva convênio" )
	@PostMapping()
	public Convenio cadastroConvenio(@RequestBody Convenio convenio){
		return cr.save(convenio);
	}
	
	@ApiOperation(value = "Deleta convênio" ) //Diz ao Swagger que essa operação REST deve ser documentado
	@DeleteMapping()
	public Convenio deletaConvenio(@RequestBody Convenio convenio){
		cr.delete(convenio);
		return convenio;
	}
}
