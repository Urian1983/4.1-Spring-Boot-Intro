package cat.itacademy.s04.t01.userapi.controllers;

import cat.itacademy.s04.t01.userapi.model.User;

import java.util.List;

public record UserResponse(List<User> users) {
}
