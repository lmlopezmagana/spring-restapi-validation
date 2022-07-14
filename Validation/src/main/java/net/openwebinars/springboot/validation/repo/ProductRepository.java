package net.openwebinars.springboot.validation.repo;

import net.openwebinars.springboot.validation.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
