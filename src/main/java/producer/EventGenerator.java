package producer;

import com.github.javafaker.Faker;
import producer.enums.Color;
import producer.enums.DesignType;
import producer.enums.ProductType;
import producer.enums.UserId;
import producer.models.Event;
import producer.models.Product;
import producer.models.User;

public class EventGenerator
{
    private Faker faker = new Faker();

    public Event generateEvent()
    {

        return Event.builder()
                .user( generateRandomUser() )
                .product( generateRandomObject() )
                .build();
    }

    private User generateRandomUser()
    {
        return User.builder()
                .userId( faker.options().option(UserId.class))
                .userName(faker.name().lastName())
                .dateOfBirth(faker.date().birthday())
                .build();
    }

    private Product generateRandomObject()
    {
        return Product.builder()
                .color( faker.options().option(Color.class))
                .type(faker.options().option(ProductType.class))
                .designType(faker.options().option(DesignType.class))
                .build();
    }
}
