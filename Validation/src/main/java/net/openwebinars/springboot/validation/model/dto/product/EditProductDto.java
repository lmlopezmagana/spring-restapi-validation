package net.openwebinars.springboot.validation.model.dto.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.openwebinars.springboot.validation.model.Product;

@Data
@NoArgsConstructor @AllArgsConstructor
@Builder
public class EditProductDto {

    private String name;
    private double price;
    private String image;
    private String desc;
    private String supplier;

    public static Product toProduct(EditProductDto dto) {
        return Product.builder()
                .name(dto.getName())
                .price(dto.getPrice())
                .image(dto.getImage())
                .desc(dto.getDesc())
                .supplier(dto.getSupplier())
                .build();
    }

}
