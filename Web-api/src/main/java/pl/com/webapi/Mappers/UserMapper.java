package pl.com.webapi.Mappers;

import pl.com.data.Entities.User;
import pl.com.webapi.Contracts.UserDTO;

public class UserMapper {
    public static UserDTO userToUserDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setEmail(user.getEmail());
        dto.setIfAdult(user.getIfAdult());
        dto.setIfSeller(user.getIfSeller());
        dto.setBalance(user.getBalance());
        return dto;
    }
    public static User userDTOToUser(UserDTO dto) {
        User user = new User();
        user.setId(dto.getId());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setEmail(dto.getEmail());
        user.setIfAdult(dto.getIfAdult());
        user.setIfSeller(dto.getIfSeller());
        user.setBalance(dto.getBalance());
        return user;
    }
}
