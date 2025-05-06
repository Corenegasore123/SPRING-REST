package rca.restapi._B;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByPrice(double price);

    List<Product> findProductByProdName(String prodName);
}