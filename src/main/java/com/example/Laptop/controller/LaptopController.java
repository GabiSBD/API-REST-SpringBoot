package com.example.Laptop.controller;

import com.example.Laptop.entity.Laptop;
import com.example.Laptop.repository.LaptopRepository;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;
import java.lang.reflect.MalformedParametersException;
import java.util.List;
import java.util.Optional;

@RestController
public class LaptopController {
    public LaptopController(LaptopRepository laptopRepository){this.laptopRepository = laptopRepository;}

    /**
     * lista todos los laptops persistidos en la bbdd H2
     * @return ResponseEntity<List<Laptop>>
     */
    @GetMapping("/api/get/laptops")
    @ApiOperation("lista todos los registros Laptop persistidos en la base de datos")
    public ResponseEntity<List<Laptop>> findAll(){

        List<Laptop> list =  laptopRepository.findAll();

        if(list.size()==0)return ResponseEntity.notFound().build();
        else return ResponseEntity.ok(list);

    }

    /**
     * Obtiene un objeto laptop guardado en la base de datos, por el id como criterio de busqueda
     * @param id: cave primaria del la entidad Laptop
     * @return ResponseEntity<Laptop>
     */
    @GetMapping("/api/get/laptops/{id}")
    @ApiOperation("muestra el registro bucado por su clave primaria (id)")
    public ResponseEntity<Laptop> findOneById(@ApiParam("Clave primaria de la entidad Laptop tipo Long") @PathVariable Long id){
       Optional<Laptop> opt = laptopRepository.findById(id);
       if (opt.isPresent())return ResponseEntity.ok(opt.get());
       else return ResponseEntity.notFound().build();
    }

    /**
     * introduce un objeto Laptop en la bbdd H2
     * @param laptop: obtenido del body de la peticion en formato Json
     * @return Laptop
     */
    @PostMapping("/api/post/laptops")
    @ApiOperation("crea un nuevo registro en la tabla Laptops")
    public Laptop create(@ApiParam("objeto Laptop en formato Json") @RequestBody Laptop laptop){
        return laptopRepository.save(laptop);
    }

    /**
     * Actualiza la informacion almacenada en un registro de Laptop
     * @param updateLaptop: objeto laptop introducido desde la peticion en formato Json
     * @return ResponseEntity<Laptop>
     */
    @PutMapping("/api/put/laptops")
    @ApiOperation("actualiza un registro de la tabla Laptops")
    public ResponseEntity<Laptop> update(@RequestBody Laptop updateLaptop){
        try{
            if(updateLaptop.getId()==null) throw new MalformedParametersException();

            Laptop laptop = laptopRepository.getReferenceById(updateLaptop.getId());

            if(laptop==null)throw new javax.persistence.EntityNotFoundException();


            if(laptop.getManufacturer() != updateLaptop.getManufacturer() && updateLaptop.getManufacturer() != null)
                laptop.setManufacturer(updateLaptop.getManufacturer());

            if(laptop.getModel() != updateLaptop.getModel() && updateLaptop.getModel() != null)
                laptop.setModel(updateLaptop.getModel());


            Laptop update = laptopRepository.save(laptop);

            return ResponseEntity.ok(update);


        }catch (javax.persistence.EntityNotFoundException e){
            log.error("esta intentando actualizar un registro inexistente ");
            System.out.println(e.getMessage());
            return ResponseEntity.notFound().build();
        }catch (MalformedParametersException i){
            log.error("no se introdujo el campo id el el body de la peticion");
            return ResponseEntity.badRequest().build();
        }


    }

    /**
     * Borra todo los registros de la tabla Laptops
     * @return ResponseEntity<Laptop>
     */
    @DeleteMapping("/api/delete/laptops/all")
    @ApiIgnore
    public ResponseEntity<Laptop> deleteAll(){
        laptopRepository.deleteAll();
        return ResponseEntity.ok().build();
    }

    /**
     * Borra un registro buscado selecionado por su id
     * @return ResponseEntity<Laptop>
     */
    @DeleteMapping("api/delete/laptops/{id}")
    @ApiIgnore
    public ResponseEntity<Laptop> deleteById(@PathVariable Long id){
        try{
            if(laptopRepository.findById(id)==null) throw new EmptyResultDataAccessException((int) laptopRepository.count());
            laptopRepository.deleteById(id);
            return ResponseEntity.ok().build();
            
        }catch (IllegalArgumentException e){
            log.error(e.getMessage());

            return ResponseEntity.badRequest().build();

        }catch (EmptyResultDataAccessException i){
            log.error(i.getMessage());

            return ResponseEntity.notFound().build();
        }
        
    }

    private final Logger log = LoggerFactory.getLogger(LaptopController.class);
    private LaptopRepository laptopRepository;
}
