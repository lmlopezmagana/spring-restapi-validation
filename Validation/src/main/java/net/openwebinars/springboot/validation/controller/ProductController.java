package net.openwebinars.springboot.validation.controller;

import lombok.RequiredArgsConstructor;
import net.openwebinars.springboot.validation.model.Product;
import net.openwebinars.springboot.validation.model.dto.product.EditProductDto;
import net.openwebinars.springboot.validation.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public List<Product> getAll() {
        return productService.findAll();
    }

    @GetMapping("/{id}")
    public Product getById(@PathVariable Long id) {
        return productService.findById(id);
    }

    @PostMapping
    public ResponseEntity<Product> newProduct(@RequestBody EditProductDto productDto) {
        Product created = productService.save(productDto);

        URI createdURI = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getId()).toUri();


        return ResponseEntity
                .created(createdURI)
                .body(created);
    }

    @PutMapping("/{id}")
    public Product editProduct(@PathVariable Long id, @RequestBody EditProductDto productDto) {
        return productService.edit(id, productDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        productService.delete(id);

        return ResponseEntity.noContent().build();
    }

}
