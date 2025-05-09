package User.management.user.management.Service.impl;

import User.management.user.management.Models.DTO.UserChangeRoleDTO;
import User.management.user.management.Models.DTO.UserDetailsDTO;
import User.management.user.management.Models.Entitys.User;
import User.management.user.management.Models.Enum.Role;
import User.management.user.management.Repositories.UserRepository;
import User.management.user.management.Service.Interfaces.ManagerService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ManagerServiceImpl implements ManagerService {

    private  final UserRepository userRepository;

    public ManagerServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserDetailsDTO> getAllUsers(String email) {
        return userRepository.getAllByEmailNotContainingAndRoleIn(email, List.of(Role.RECEPTIONIST,Role.USER)).stream().map(this::mapTo).toList();
    }

    @Override
    @Transactional
    public void changeProfileRole(UserChangeRoleDTO userChangeRoleDTO) {
        Optional<User> byEmail = userRepository.findByEmail(userChangeRoleDTO.getEmail());
        if (byEmail.isPresent()) {
            byEmail.get().setRole(userChangeRoleDTO.getRole());
            userRepository.save(byEmail.get());
        }
    }

    @Override
    @Transactional
    public void deleteProfile(Long id) {
        userRepository.deleteById(id);
    }

    private UserDetailsDTO mapTo(User user) {
        UserDetailsDTO userDetailsDTO = new UserDetailsDTO();
        userDetailsDTO.setEmail(user.getEmail());
        userDetailsDTO.setUsername(user.getUsername());
        userDetailsDTO.setPhone(user.getPhone());
        userDetailsDTO.setAge(user.getAge());
        userDetailsDTO.setRole(user.getRole().toString());
        userDetailsDTO.setId(user.getId());
        return userDetailsDTO;
    }
}
