package net.openwebinars.springboot.validation.service;

import lombok.RequiredArgsConstructor;
import net.openwebinars.springboot.validation.model.Product;
import net.openwebinars.springboot.validation.model.dto.product.EditProductDto;
import net.openwebinars.springboot.validation.repo.ProductRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repository;

    public List<Product> findAll() {

        List<Product> result = repository.findAll();

        if (result.isEmpty())
            throw new EntityNotFoundException("No products with this search criteria");

        return result;
    }

    public Product findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No product with id: " + id));
    }

    public Product save(EditProductDto editProductDto) {
        return repository.save(EditProductDto.toProduct(editProductDto));
    }

    public Product edit(Long id, EditProductDto editProductDto) {

        return repository.findById(id)
                .map(product -> {
                    product.setName(editProductDto.getName());
                    product.setPrice(editProductDto.getPrice());
                    product.setImage(editProductDto.getImage());
                    product.setDesc(editProductDto.getDesc());
                    return repository.save(product);
                }).orElseThrow(() -> new EntityNotFoundException("No product with id: " + id));

    }

    public void delete(Long id) {
        if (repository.existsById(id))
            repository.deleteById(id);
    }


    public List<Product> productsWithMinPrice(double price) {

        List<Product> result = repository.findByPriceGreaterThan(price);

        if (result.isEmpty())
            throw new EntityNotFoundException("No products with this search criteria");

        return result;

    }

    public List<Product> productsBySupplier(List<String> suppliers) {
        List<Product> result = repository.findBySupplierIn(suppliers);

        if (result.isEmpty())
            throw new EntityNotFoundException("No products with this search criteria");

        return result;
    }



}
