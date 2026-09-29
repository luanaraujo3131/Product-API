package org.productcrud.Application.Service;

import lombok.AllArgsConstructor;
import org.productcrud.Domain.Model.Product;
import org.productcrud.Domain.Repository.ProductRepository;
import org.productcrud.Presentation.Dto.ProductDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository repository;

   public List<ProductDTO> getProducts(){
       return repository.findAll()
               .stream()
               .map(this::convertModelToDto)
               .toList();
   }

   public Optional<ProductDTO> getProductById(UUID id){
       return repository.findById(id)
               .map(this::convertModelToDto);
   }

   public ProductDTO createProduct(ProductDTO dto){
       return  convertModelToDto(repository.save(convertDtoToModel(dto)));
   }

   public void deleteProduct(UUID id) {
       if (!repository.existsById(id)){
           throw new RuntimeException("Product Not Found");
       }

       repository.deleteById(id);
   }

   public ProductDTO updateProduct(UUID id, ProductDTO dto){

       Product product = repository.findById(id)
               .orElseThrow(() -> new RuntimeException("Product not found"));

       product.setDescription(dto.description());
       product.setPrice(dto.price());

       Product updatedProduct = repository.save(product);

       return convertModelToDto(updatedProduct);
   }



    private Product convertDtoToModel(ProductDTO dto){
        Product model = new Product();
        model.setDescription(dto.description());
        model.setPrice(dto.price());
        return model;
    }

    private ProductDTO convertModelToDto(Product entity){
        return new ProductDTO(
                entity.getDescription(),
                entity.getPrice()
        );
    }
}
