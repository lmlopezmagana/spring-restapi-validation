package net.openwebinars.springboot.validation.model.dto.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.openwebinars.springboot.validation.model.Product;
import org.hibernate.validator.constraints.URL;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor @AllArgsConstructor
@Builder
public class EditProductDto {

    @NotEmpty(message = "{editProductDto.name.notempty}")
    private String name;

    @Min(value = 0, message = "{editProductDto.price.min}")
    private double price;

    @URL(message = "{editProductDto.image.url}")
    private String image;

    private String desc;

    @NotEmpty(message = "{editProductDto.supplier.notempty}")
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
