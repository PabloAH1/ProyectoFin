package com.formacion.app.controller;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.formacion.app.entity.Coche;
import com.formacion.app.service.CocheService;


@RestController
@RequestMapping("/api")
public class CocheRestController {

	@Autowired
	private CocheService servicio;
	
	@GetMapping({"/coches","/todos"})
	public List<Coche> index(){
		return servicio.findAll();
	}
	

	
	@GetMapping("/coches/{id}")
	public ResponseEntity<?>  findCocheById(@PathVariable Long id) {
		Coche coche = null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			
			coche = servicio.findById(id);
			
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar consulta a base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		
		if(coche == null) {
			response.put("mensaje", "El coche ID: " +id.toString()+" no existe en la base de datos");
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Coche>(coche,HttpStatus.OK);
		
	}
	

	
	@PostMapping("/coches")
	public ResponseEntity<?> saveCliente(@RequestBody Coche coche) {
		 Coche cocheNew= null;
		 Map<String, Object> response = new HashMap<>();
		 
		 try {
			
			 cocheNew = servicio.save(coche);
			
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar un insert a base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		 
		 response.put("mensaje", "El coche ha sido creado con ??xito!");
		 response.put("coche",cocheNew);
		 
		 return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
		 
		 
	}
	

	
	@PutMapping("/coches/{id}")
	public ResponseEntity<?> updateCoche(@RequestBody Coche coche, @PathVariable Long id) {
		Coche cocheActual = servicio.findById(id);
		
		Map<String, Object> response = new HashMap<>();
		
		if(cocheActual == null) {
			response.put("mensaje","Error: no se pudo editar, el coche con ID: "+id.toString()+" no existe en la base de datos");
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
		}
		
		try {
			cocheActual.setMarca(coche.getMarca());
			cocheActual.setModelo(coche.getModelo());			
			
			servicio.save(cocheActual);
			
		}catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar un update a base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		
		 response.put("mensaje", "El coche ha sido actualizado con ??xito!");
		 response.put("coche",cocheActual);
		 
		 return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
	}
	

	@DeleteMapping("/coches/{id}")
	public ResponseEntity<?> deleteCoche(@PathVariable Long id) {
		
		Coche cocheActual = servicio.findById(id);
		
		Map<String, Object> response = new HashMap<>();
		
		if(cocheActual == null) {
			response.put("mensaje","Error: no se pudo eliminar, el coche con ID: "+id.toString()+" no existe en la base de datos");
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
		}
		
		try {
			
			servicio.delete(id);
			
			String nombreFotoAnterior = cocheActual.getImagen();
			
			if(nombreFotoAnterior != null && nombreFotoAnterior.length() > 0) {
				Path rutaFotoAnterior = Paths.get("uploads").resolve(nombreFotoAnterior).toAbsolutePath();
				File archivoFotoAnterior = rutaFotoAnterior.toFile();
				
				if(archivoFotoAnterior.exists() && archivoFotoAnterior.canRead()) {
					archivoFotoAnterior.delete();
				}
			}
			
		}catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar un delete a base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		
		
		 response.put("mensaje", "El coche ha sido eliminado con ??xito!");
		 response.put("coche",cocheActual);
		 
		 return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
		
	}
	
	
	@PostMapping("/coches/upload")
	public ResponseEntity<?> upload(@RequestParam("archivo") MultipartFile archivo, @RequestParam("id") Long id){
		
		Map<String,Object> response = new HashMap<>();
		
		Coche coche = servicio.findById(id);
		
		if(!archivo.isEmpty()) {
			//String nombreArchivo = archivo.getOriginalFilename();
			String nombreArchivo = UUID.randomUUID().toString()+"_"+archivo.getOriginalFilename().replace(" ", "");
			Path rutaArchivo = Paths.get("uploads").resolve(nombreArchivo).toAbsolutePath();
			
		
		
			try {
				Files.copy(archivo.getInputStream(), rutaArchivo);
				
			} catch (IOException e) {
				
				response.put("mensaje", "Error al subir la imagen");
				response.put("error", e.getMessage().concat(": ").concat(e.getCause().getMessage()));
				return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
			String nombreFotoAnterior = coche.getImagen();
			
			if(nombreFotoAnterior != null && nombreFotoAnterior.length() > 0) {
				Path rutaFotoAnterior = Paths.get("uploads").resolve(nombreFotoAnterior).toAbsolutePath();
				File archivoFotoAnterior = rutaFotoAnterior.toFile();
				
				if(archivoFotoAnterior.exists() && archivoFotoAnterior.canRead()) {
					archivoFotoAnterior.delete();
				}
			}
			
			
			coche.setImagen(nombreArchivo);
			servicio.save(coche);
			
			response.put("coche",coche);
			response.put("mensaje","Has subido correctamente la imagen: "+nombreArchivo);
		
		}
		
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
	}
	
	@GetMapping("/uploads/imagen/{nombreImagen:.+}")
	public ResponseEntity<Resource> verImagen(@PathVariable String nombreImagen){
		
		Path rutaImagen = Paths.get("uploads").resolve(nombreImagen).toAbsolutePath();
		
		Resource recurso= null;
		
		try {
			
			recurso = new UrlResource(rutaImagen.toUri());
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		if(!recurso.exists() && !recurso.isReadable()) {
			throw new RuntimeException("Error no se puede cargar la imagen "+nombreImagen);
		}
		
		HttpHeaders cabecera = new HttpHeaders();
		cabecera.add(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=\" "+recurso.getFilename()+"\"");
		
		return new ResponseEntity<Resource>(recurso,cabecera,HttpStatus.OK);
	}
	

	
	
}
