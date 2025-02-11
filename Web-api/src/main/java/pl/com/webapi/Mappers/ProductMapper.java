package pl.com.webapi.Mappers;

import pl.com.data.Entities.Product;
import pl.com.webapi.Contracts.ProductDTO;

public class ProductMapper {
    public static ProductDTO toDTO(Product product) {
        ProductDTO dto = new ProductDTO();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setDescription(product.getDescription());
        dto.setPrice(product.getPrice());
        dto.setImageId(product.getImageId());
        dto.setCategoryName(product.getCategory() != null ? product.getCategory().getCategoryName() : null);
        dto.setUserId(product.getSeller() != null ? product.getSeller().getId() : null);
        dto.setCreatedAt(product.getCreatedat());
        return dto;
    }

    public static Product toEntity(ProductDTO dto) {
        Product product = new Product();
        product.setId(dto.getId());
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setImageId(dto.getImageId());
        return product;
    }
}
