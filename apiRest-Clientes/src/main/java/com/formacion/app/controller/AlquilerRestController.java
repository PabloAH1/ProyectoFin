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

import com.formacion.app.entity.Alquiler;
import com.formacion.app.entity.Cliente;
import com.formacion.app.entity.Coche;
import com.formacion.app.service.AlquilerService;


@RestController
@RequestMapping("/api")
public class AlquilerRestController {


	@Autowired
	private AlquilerService servicio;
	
	@GetMapping({"/alquileres","/todos"})
	public List<Alquiler> index(){
		return servicio.findAll();
	}
	
	@GetMapping("/alquileres/{id}")
	public ResponseEntity<?>  findClienteById(@PathVariable Long id) {
		Alquiler alquiler = null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			
			alquiler = servicio.findById(id);
			
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar consulta a base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		
		if(alquiler == null) {
			response.put("mensaje", "El alquiler ID: " +id.toString()+" no existe en la base de datos");
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Alquiler>(alquiler,HttpStatus.OK);
		
	}
	
	@PostMapping("/alquileres")
	public ResponseEntity<?> saveCliente(@RequestBody Alquiler alquiler) {
		Alquiler alquilereNew= null;
		 Map<String, Object> response = new HashMap<>();
		 
		 try {
			
			 alquilereNew = servicio.save(alquiler);
			
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar un insert a base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		 
		 response.put("mensaje", "El alquiler ha sido creado con ??xito!");
		 response.put("alquiler",alquilereNew);
		 
		 return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);		 
		 
	}	
	
	@PutMapping("/alquileres/{id}")
	public ResponseEntity<?> updateAlquiler(@RequestBody Alquiler alquiler, @PathVariable Long id) {
		Alquiler alquilerActual = servicio.findById(id);
		
		Map<String, Object> response = new HashMap<>();
		
		if(alquilerActual == null) {
			response.put("mensaje","Error: no se pudo editar, el alquiler con ID: "+id.toString()+" no existe en la base de datos");
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
		}
		
		try {
			alquilerActual.setCoche(alquiler.getCoche());
			alquilerActual.setCliente(alquiler.getCliente());
			alquilerActual.setFechaIni(alquiler.getFechaIni());
			alquilerActual.setFechaFin(alquiler.getFechaFin());			
			
			servicio.save(alquilerActual);
			
		}catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar un update a base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		
		 response.put("mensaje", "El alquiler ha sido actualizado con ??xito!");
		 response.put("alquiler",alquilerActual);
		 
		 return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
	}
	
	
	@DeleteMapping("/alquileres/{id}")
	public ResponseEntity<?> deleteAlquiler(@PathVariable Long id) {
		
		Alquiler alquilerActual = servicio.findById(id);
		
		Map<String, Object> response = new HashMap<>();
		
		if(alquilerActual == null) {
			response.put("mensaje","Error: no se pudo eliminar, el alquiler con ID: "+id.toString()+" no existe en la base de datos");
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
		}
		
		try {
			
			servicio.delete(id);							
			
		}catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar un delete a base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		
		
		 response.put("mensaje", "El alquiler ha sido eliminado con ??xito!");
		 response.put("alquiler",alquilerActual);
		 
		 return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
		
	}
	
	@GetMapping("alquileres/coches")
	public List<Coche> listarCoches(){
		return servicio.findAllCoches();		
	}
	@GetMapping("alquileres/clientes")
	public List<Cliente> listarClientes(){
		return servicio.findAllClientes();		
	}
	
	
	
}


