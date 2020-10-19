package ru.baryshev.kirill.dto.users;

import lombok.Getter;
import lombok.Setter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.baryshev.kirill.models.Users;

@Getter
@Setter
public class UsersDto {

    private Long userId;

    private String userName;

    private String userLogin;

    public static Converter CONVERTER = Mappers.getMapper(Converter.class);

    @Mapper
    public interface Converter {
        @Mapping(target = "userId", source = "id")
        @Mapping(target = "userName")
        @Mapping(target = "userLogin")
        UsersDto from(Users user);
    }
}
