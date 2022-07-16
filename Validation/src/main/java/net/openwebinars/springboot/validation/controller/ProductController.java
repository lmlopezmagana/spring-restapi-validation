package net.openwebinars.springboot.validation.controller;

import lombok.RequiredArgsConstructor;
import net.openwebinars.springboot.validation.model.Product;
import net.openwebinars.springboot.validation.model.dto.product.EditProductDto;
import net.openwebinars.springboot.validation.service.ProductService;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
@Validated
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public List<Product> getAll() {
        return productService.findAll();
    }

    @GetMapping("/suppliers/")
    public List<Product> getBySupplier(@RequestParam
                       @UniqueElements(message = "Proveedores duplicados") List<String> list) {
        return productService.productsBySupplier(list);
    }

    @GetMapping("/price/min/{value}")
    public List<Product> getByMinPrice(@PathVariable @Min(value = 0, message = "{editProductDto.price.min}") Double value) {
        return productService.productsWithMinPrice(value);
    }




    @GetMapping("/{id}")
    public Product getById(@PathVariable Long id) {
        return productService.findById(id);
    }

    @PostMapping
    public ResponseEntity<Product> newProduct(@Valid @RequestBody EditProductDto productDto) {
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
