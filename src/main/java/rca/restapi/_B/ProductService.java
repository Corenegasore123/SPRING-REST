package rca.restapi._B;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class ProductService {
    @Autowired
    private ProductRepository repository;

    public Page<Product> getAllProducts(int page, int size, String sortBy, String direction) {
        Sort sort = createSort(sortBy, direction);
        Pageable pageable = PageRequest.of(page, size, sort);
        return repository.findAll(pageable);
    }

    private Sort createSort(String sortBy, String direction) {
        Sort.Direction sortDirection = direction.equalsIgnoreCase("desc") ?
                Sort.Direction.DESC : Sort.Direction.ASC;
        return Sort.by(sortDirection, sortBy);
    }
    public void createProduct(Product product) {
        repository.save(product);
    }

    public Optional<Product> getProduct(Long prodId) {
        return repository.findById(prodId);
    }

    public Page<Product> getProductsByPrice(double price, int page, int size, String sortBy, String direction) {
        Sort sort = createSort(sortBy, direction);
        Pageable pageable = PageRequest.of(page, size, sort);
        return repository.findByPrice(price, pageable);
    }

    public Page<Product> getProductsByName(String prodName, int page, int size, String sortBy, String direction) {
        Sort sort = createSort(sortBy, direction);
        Pageable pageable = PageRequest.of(page, size, sort);
        return repository.findByProdName(prodName, pageable);
    }

    public boolean deleteProduct(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    public Optional<Product> updateProduct(Long id, Product productDetails) {
        Optional<Product> productOptional = repository.findById(id);
        if (productOptional.isPresent()) {
            Product existingProduct = productOptional.get();

            // Update the fields
            existingProduct.setProdName(productDetails.getProdName());
            existingProduct.setDescription(productDetails.getDescription());
            existingProduct.setPrice(productDetails.getPrice());

            // Save the updated product
            repository.save(existingProduct);
            return Optional.of(existingProduct);
        }
        return Optional.empty();
    }
}