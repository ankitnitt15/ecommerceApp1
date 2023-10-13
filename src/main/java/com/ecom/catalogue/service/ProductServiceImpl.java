package com.ecom.catalogue.service;

import com.ecom.catalogue.exception.ProductNotFoundException;
import com.ecom.catalogue.model.Product;
import com.ecom.catalogue.model.ProductES;
import com.ecom.catalogue.repository.ElasticSearchRepository;
import com.ecom.catalogue.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository repository;

    @Autowired
    private ElasticSearchRepository elasticSearchRepository;

    @Override
    public Product createProduct(Product product) throws IOException {
        Product prod = repository.save(product);
        elasticSearchRepository.createOrUpdateDocument(convertToESDocument(product));
        return prod;
    }

    private ProductES convertToESDocument(Product product) {
        ProductES productES = new ProductES();
        productES.setProductId(String.valueOf(product.getProductId()));
        productES.setProductName(product.getProductName());
        productES.setCategory(product.getCategory());
        productES.setBrand(product.getBrand());
        productES.setDescription(product.getDescription());
        productES.setSellPrice(product.getSellPrice());
        productES.setMaxOQ(product.getMaxOQ());
        productES.setMinOQ(product.getMinOQ());
        productES.setStock(product.getStock());
        return productES;
    }

    @Override
    public Product updateProduct(Product product) throws IOException {
        Optional<Product> dbProduct = this.repository.findById(product.getProductId());

        if(dbProduct.isPresent()){
            Product newProduct = dbProduct.get();
            newProduct.setProductName(product.getProductName());
            newProduct.setCategory(product.getCategory());
            newProduct.setBrand(product.getBrand());
            newProduct.setDescription(product.getDescription());
            newProduct.setMaxOQ(product.getMaxOQ());
            newProduct.setMinOQ(product.getMinOQ());
            newProduct.setMrp(product.getMrp());
            newProduct.setSellPrice((product.getSellPrice()));
            newProduct.setProductId(product.getProductId());
            newProduct.setStock(product.getStock());
            repository.save(newProduct);
            elasticSearchRepository.createOrUpdateDocument(convertToESDocument(product));
            return newProduct;
        }
        else{
            throw new ProductNotFoundException("Product Id "+product.getProductId()+" not found in catalogue");
        }
    }

    @Override
    public Product processProductFeed(Product product) throws IOException {
        Optional<Product> dbProduct = this.repository.findById(product.getProductId());

        if(dbProduct.isPresent()){
            return updateProduct(product);
        }
        else{
            return createProduct(product);
        }
    }

    @Override
    public List<Product> getProductsByCategory(String category) {
        List<Product> dbProduct = this.repository.findByCategory(category);

        if(dbProduct != null && dbProduct.size() > 0){
            return dbProduct;
        }
        else{
            throw new ProductNotFoundException("No products found with category = "+category);
        }
    }

    @Override
    public List<Product> getProductsByName(String name) {
        List<Product> dbProduct = this.repository.findByProductName(name);

        if(dbProduct != null && dbProduct.size() > 0){
            return dbProduct;
        }
        else{
            throw new ProductNotFoundException("No products found with name = "+name);
        }
    }

    @Override
    public void deleteProduct(Long productId) {
        Optional<Product> dbProduct = this.repository.findById(productId);

        if(dbProduct.isPresent()){
            this.repository.delete(dbProduct.get());
        }
        else{
            throw new ProductNotFoundException("Product Id "+productId+" not found in DB");
        }

    }
}
