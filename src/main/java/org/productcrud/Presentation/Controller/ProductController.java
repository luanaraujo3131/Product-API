package org.productcrud.Presentation.Controller;

import lombok.AllArgsConstructor;
import org.productcrud.Application.Service.ProductService;
import org.productcrud.Domain.Model.Product;
import org.productcrud.Presentation.Dto.ProductDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/product")
@AllArgsConstructor
public class ProductController {

    private ProductService service;

    @GetMapping
    public ResponseEntity<List<ProductDTO>> getProduct(){
        return ResponseEntity.ok(service.getProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable UUID id){
        return service.getProductById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(
            @RequestBody ProductDTO dto
    ){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.createProduct(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable UUID id){
        service.deleteProduct(id);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> updateProduct(
            @PathVariable UUID id,
            @RequestBody ProductDTO dto
    ) {
        ProductDTO updatedProduct = service.updateProduct(id, dto);

        return ResponseEntity.ok(updatedProduct);
    }
}
