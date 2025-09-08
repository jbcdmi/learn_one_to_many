package com.learn.learn_one_to_many.dto;


import java.util.List;

public record UserResponse(Long id, String name, String email, List<AddressDto> addresses) {
}