package com.ecom.catalogue.service;

import com.ecom.catalogue.exception.ProductNotFoundException;
import com.ecom.catalogue.model.Product;
import com.ecom.catalogue.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository repository;

    @Override
    public Product createProduct(Product product) {
        return repository.save(product);
    }

    @Override
    public Product updateProduct(Product product) {
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
            return newProduct;
        }
        else{
            throw new ProductNotFoundException("Product Id "+product.getProductId()+" not found in catalogue");
        }
    }

    @Override
    public Product processProductFeed(Product product) {
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
