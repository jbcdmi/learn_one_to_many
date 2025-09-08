package com.learn.learn_one_to_many.service;

import com.learn.learn_one_to_many.dto.AddressDto;
import com.learn.learn_one_to_many.dto.CreateUserRequest;
import com.learn.learn_one_to_many.dto.UserResponse;
import com.learn.learn_one_to_many.entity.Address;
import com.learn.learn_one_to_many.entity.User;
import com.learn.learn_one_to_many.exception.ResourceNotFoundException;
import com.learn.learn_one_to_many.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponse createUser(CreateUserRequest req) {
        User u = new User();
        u.setName(req.getName());
        u.setEmail(req.getEmail());
        u.setPassword(req.getPassword());

        if (req.getAddresses() != null) {
            for (CreateUserRequest.AddressInput ai : req.getAddresses()) {
                Address a = new Address();
                a.setCity(ai.getCity());
                a.setState(ai.getState());
                a.setAddress(ai.getAddress());
                u.addAddress(a);
            }
        }

        User saved = userRepository.save(u);
        return toResponse(saved);
    }

    public UserResponse getUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + id));
        return toResponse(user);
    }

    public List<UserResponse> getAllUsers() {
        return userRepository.findAll().stream().map(this::toResponse).collect(Collectors.toList());
    }

    public UserResponse updateUser(Long id, CreateUserRequest req) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + id));

        if (req.getName() != null) user.setName(req.getName());
        if (req.getEmail() != null) user.setEmail(req.getEmail());
        if (req.getPassword() != null) user.setPassword(req.getPassword());

        // naive replace addresses: clear and re-add
        user.getAddresses().clear();
        if (req.getAddresses() != null) {
            for (CreateUserRequest.AddressInput ai : req.getAddresses()) {
                Address a = new Address();
                a.setCity(ai.getCity());
                a.setState(ai.getState());
                a.setAddress(ai.getAddress());
                user.addAddress(a);
            }
        }

        User saved = userRepository.save(user);
        return toResponse(saved);
    }

    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException("User not found with id " + id);
        }
        userRepository.deleteById(id);
    }

    private UserResponse toResponse(User u) {
        List<AddressDto> addresses = u.getAddresses().stream()
                .map(a -> new AddressDto(a.getId(), a.getCity(), a.getState(), a.getAddress()))
                .collect(Collectors.toList());
        return new UserResponse(u.getId(), u.getName(), u.getEmail(), addresses);
    }
}
