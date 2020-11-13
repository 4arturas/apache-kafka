import com.github.javafaker.Faker;
import enums.Color;
import enums.DesignType;
import enums.ProductType;
import models.Event;
import models.Product;
import models.User;


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
                .userId( faker.options().option(enums.UserId.class))
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
