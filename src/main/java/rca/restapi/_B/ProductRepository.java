package rca.restapi._B;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Page<Product> findByPrice(double price, Pageable pageable);

    Page<Product> findByProdName(String prodName, Pageable pageable);

    @Override
    Page<Product> findAll(Pageable pageable);
}