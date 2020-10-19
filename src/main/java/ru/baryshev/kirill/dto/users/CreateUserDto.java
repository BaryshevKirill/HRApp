package ru.baryshev.kirill.dto.users;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.lang.NonNull;
import ru.baryshev.kirill.models.Users;

@Setter
@Getter
public class CreateUserDto {

    @NonNull
    private String userName;

    @NonNull
    private String userLogin;

    @NonNull
    private String userPassword;

    @NonNull
    private String userRole;

    @Builder.Default
    private Boolean isActive = true;

    public static Converter CONVERTER = Mappers.getMapper(Converter.class);

    @Mapper
    public interface Converter {
        @Mapping(target = "userName")
        @Mapping(target = "userLogin")
        @Mapping(target = "userPassword")
        @Mapping(target = "userRole")
        @Mapping(target = "isActive")
        CreateUserDto from(Users user);
    }
}