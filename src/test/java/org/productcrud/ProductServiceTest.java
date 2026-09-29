package org.productcrud;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.productcrud.Application.Service.ProductService;
import org.productcrud.Domain.Model.Product;
import org.productcrud.Domain.Repository.ProductRepository;
import org.productcrud.Presentation.Dto.ProductDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository repository;

    @InjectMocks
    private ProductService service;

    @Test
    void shouldReturnProductWhenIdExists(){

        UUID id = UUID.randomUUID();

        Product product = new Product();
        product.setId(id);
        product.setDescription("Vaio Z");
        product.setPrice(8000F);

        when(repository.findById(id))
                .thenReturn(Optional.of(product));

        Optional<ProductDTO> result = service.getProductById(id);

        assertTrue(result.isPresent());
        assertEquals("Vaio Z", result.get().description());
        assertEquals(8000F, result.get().price());
    }

    @Test
    void shouldReturnEmptyWhenProductDoesNotExist(){

        UUID id = UUID.randomUUID();

        when(repository.findById(id))
                .thenReturn(Optional.empty());

        Optional<ProductDTO> result = service.getProductById(id);

        assertTrue(result.isEmpty());
    }

    @Test
    void shouldReturnAllProducts(){

        Product product001 = new Product();
        product001.setDescription("Macbook air");
        product001.setPrice(6000F);

        Product product002 = new Product();
        product002.setDescription("Samsung Galaxy Tab S10");
        product002.setPrice(2300F);

        Product product003 = new Product();
        product003.setDescription("AMD Ryzen 7");
        product003.setPrice(900F);

        Product product004 = new Product();
        product004.setDescription("Keychron Keyboard");
        product004.setPrice(600F);

        when(repository.findAll())
                .thenReturn(List.of(product001, product002, product003, product004));

        List<ProductDTO> result = service.getProducts();

        assertEquals(4, result.size());

        assertEquals("Macbook air", result.get(0).description());
        assertEquals(6000F, result.get(0).price());

        assertEquals("Samsung Galaxy Tab S10", result.get(1).description());
        assertEquals(2300F, result.get(1).price());

        assertEquals("AMD Ryzen 7", result.get(2).description());
        assertEquals(900F, result.get(2).price());

        assertEquals("Keychron Keyboard", result.get(3).description());
        assertEquals(600F, result.get(3).price());
    }

    @Test
    void shouldReturnEmptyListWhenThereAreNoProducts(){

        when(repository.findAll())
                .thenReturn(List.of());

        List<ProductDTO> result = service.getProducts();

        assertTrue(result.isEmpty());
    }

    @Test
    void shouldUpdateProduct(){

        UUID id = UUID.randomUUID();

        Product product = new Product();
        product.setId(id);
        product.setDescription("Macbook air");
        product.setPrice(6000F);

        ProductDTO dto = new ProductDTO(
                "Macbook Pro",
                      8000F
        );

        when(repository.findById(id))
                .thenReturn(Optional.of(product));

        when(repository.save(product))
                .thenReturn(product);

        ProductDTO result = service.updateProduct(id, dto);

        assertEquals("Macbook Pro", result.description());
        assertEquals(8000F, result.price());
    }

    @Test
    void ShouldThrowExceptionWhenUpdatingNonExistingProduct(){

        UUID id = UUID.randomUUID();

        ProductDTO dto = new ProductDTO(
                "Macbook air",
                      6000F
        );

        when(repository.findById(id))
                .thenReturn(Optional.empty());

        assertThrows(
                RuntimeException.class,
                () -> service.updateProduct(id, dto)
        );
    }

    @Test
    void shouldDeleteProduct(){

        UUID id = UUID.randomUUID();

        service.deleteProduct(id);

        verify(repository).deleteById(id);
    }
}
