package rca.restapi._B;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository repository;

    public List<Product> getAllProducts() {
        return repository.findAll();
    }

    public void createProduct(Product product) {
        repository.save(product);
    }

    public Optional<Product> getProduct(Long prodId) {
        return repository.findById(prodId);
    }

    public List<Product> getProductsByPrice(double price) {
        return repository.findByPrice(price);
    }

    public List<Product> getProductsByName(String prodName) {
        return repository.findProductByProdName(prodName);
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