package net.openwebinars.springboot.validation.repo;

import net.openwebinars.springboot.validation.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findBySupplierIn(List<String> supplier);

    List<Product> findByPriceGreaterThan(double price);


}
