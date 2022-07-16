package net.openwebinars.springboot.validation.model.dto.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.openwebinars.springboot.validation.model.Product;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor @AllArgsConstructor
@Builder
public class EditProductDto {

    @NotEmpty
    private String name;

    @Min(0)
    private double price;

    @URL
    private String image;

    private String desc;

    @NotEmpty
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
