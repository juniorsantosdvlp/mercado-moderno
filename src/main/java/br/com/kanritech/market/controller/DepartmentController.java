package br.com.kanritech.market.controller;

import br.com.kanritech.market.model.Department;
import br.com.kanritech.market.repository.DepartmentRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
@NoArgsConstructor
@RestController
@RequestMapping("departments")
public class DepartmentController {
    protected @Autowired
    DepartmentRepository departmentRepository;

    @PostMapping
    public ResponseEntity<EntityModel<Department>> insert(@RequestBody Department department) {
        departmentRepository.save(department);
        return ResponseEntity.status(HttpStatus.CREATED).body(EntityModel.of(department));
    }
    @GetMapping
    public CollectionModel<Department> findAll(){
        final Iterable<Department> all = departmentRepository.findAll();
        return CollectionModel.of(all,
                linkTo(methodOn(DepartmentController.class).findAll()).withRel("Departamentos"));

    }
}
