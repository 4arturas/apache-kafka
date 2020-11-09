package producer.models;

import lombok.Builder;
import lombok.Data;
import producer.enums.Color;
import producer.enums.DesignType;
import producer.enums.ProductType;

@Data
@Builder
public class Product
{
    private Color color;
    private ProductType type;
    private DesignType designType;
}
