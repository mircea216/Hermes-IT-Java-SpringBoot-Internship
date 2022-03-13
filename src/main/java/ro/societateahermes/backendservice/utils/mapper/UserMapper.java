package ro.societateahermes.backendservice.utils.mapper;

import ro.societateahermes.backendservice.entities.dto.UserDTO;
import ro.societateahermes.backendservice.entities.User;

import java.util.List;
import java.util.stream.Collectors;

public class UserMapper {
    public static User userDTOtoUser(UserDTO userDTO) {
        User updatedUser = new User();
        updatedUser.setID(userDTO.getID());
        updatedUser.setEmail(userDTO.getEmail());
        updatedUser.setField(userDTO.getField());
        updatedUser.setFirstName(userDTO.getFirstName());
        updatedUser.setUsername(userDTO.getUsername());
        updatedUser.setLanguage(userDTO.getLanguage());
        updatedUser.setLastName(userDTO.getLastName());
         updatedUser.setUniversity(userDTO.getUniversity());
        updatedUser.setYearOfStudy(userDTO.getYearOfStudy());
        return updatedUser;
    }
    public static UserDTO userToUserDTO(User user){
        UserDTO userDTO = new UserDTO();
        userDTO.setID(user.getID());
        userDTO.setEmail(user.getEmail());
        userDTO.setField(user.getField());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setUsername(user.getUsername());
        userDTO.setLanguage(user.getLanguage());
        userDTO.setLastName(user.getLastName());
        userDTO.setUniversity(user.getUniversity());
        userDTO.setYearOfStudy(user.getYearOfStudy());
        return userDTO;
    }
    public static List<UserDTO> usersToUsersDTO(List<User> users){
        return users.stream().map(UserMapper::userToUserDTO).collect(Collectors.toList());
    }
}
