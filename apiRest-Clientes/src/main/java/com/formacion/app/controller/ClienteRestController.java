package com.formacion.app.controller;




import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



import com.formacion.app.entity.Cliente;
import com.formacion.app.service.ClienteService;


@RestController
@RequestMapping("/api")
public class ClienteRestController {

	@Autowired
	private ClienteService servicio;
	
	@GetMapping({"/clientes","/todos"})
	public List<Cliente> index(){
		return servicio.findAll();
	}
	
	@GetMapping("/clientes/{id}")
	public ResponseEntity<?>  findClienteById(@PathVariable Long id) {
		Cliente cliente = null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			
			cliente = servicio.findById(id);
			
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar consulta a base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		
		if(cliente == null) {
			response.put("mensaje", "El cliente ID: " +id.toString()+" no existe en la base de datos");
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Cliente>(cliente,HttpStatus.OK);
		
	}
	
	@PostMapping("/clientes")
	public ResponseEntity<?> saveCliente(@RequestBody Cliente cliente) {
		 Cliente clienteNew= null;
		 Map<String, Object> response = new HashMap<>();
		 
		 try {
			
			 clienteNew = servicio.save(cliente);
			
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar un insert a base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		 
		 response.put("mensaje", "El cliente ha sido creado con éxito!");
		 response.put("cliente",clienteNew);
		 
		 return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);		 
		 
	}	
	
	@PutMapping("/clientes/{id}")
	public ResponseEntity<?> updateCliente(@RequestBody Cliente cliente, @PathVariable Long id) {
		Cliente clienteActual = servicio.findById(id);
		
		Map<String, Object> response = new HashMap<>();
		
		if(clienteActual == null) {
			response.put("mensaje","Error: no se pudo editar, el cliente con ID: "+id.toString()+" no existe en la base de datos");
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
		}
		
		try {
			clienteActual.setNombre(cliente.getNombre());
			clienteActual.setApellido(cliente.getApellido());
			clienteActual.setEmail(cliente.getEmail());
			clienteActual.setTelefono(cliente.getTelefono());
			clienteActual.setNacimiento(cliente.getNacimiento());
			
			servicio.save(clienteActual);
			
		}catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar un update a base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		
		 response.put("mensaje", "El cliente ha sido actualizado con éxito!");
		 response.put("cliente",clienteActual);
		 
		 return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
	}
	
	
	@DeleteMapping("/clientes/{id}")
	public ResponseEntity<?> deleteCliente(@PathVariable Long id) {
		
		Cliente clienteActual = servicio.findById(id);
		
		Map<String, Object> response = new HashMap<>();
		
		if(clienteActual == null) {
			response.put("mensaje","Error: no se pudo eliminar, el cliente con ID: "+id.toString()+" no existe en la base de datos");
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
		}
		
		try {
			
			servicio.delete(id);							
			
		}catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar un delete a base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		
		
		 response.put("mensaje", "El cliente ha sido eliminado con éxito!");
		 response.put("cliente",clienteActual);
		 
		 return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
		
	}
	
	
	
}
