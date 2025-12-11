package cat.itacademy.s04.t01.userapi.controllers;

import cat.itacademy.s04.t01.userapi.exceptions.UserNotFoundException;
import cat.itacademy.s04.t01.userapi.model.User;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    public UserController(){
        //metodo de comprobación
        System.out.println("UserController is Ok");
    }

    private static final List<User> users = new ArrayList<>();

    @GetMapping
    public List<User> getUsers(@RequestParam(required = false) String name){
        if (name == null || name.isEmpty()){
            return users;
        }
        return users.stream().filter(user -> user.getName().toLowerCase()
                .contains(name.toLowerCase()))
                .toList();
    }


    @GetMapping("/{id}")
    public User getUserById(@PathVariable UUID id) throws UserNotFoundException  {

        return users.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new UserNotFoundException("User with ID not found"));
    }

    @PostMapping
    public User createUser(@RequestBody CreateUserRequest request){
        UUID id = UUID.randomUUID();
        User newUser = new User(id, request.name(), request.email());
        users.add(newUser);
        return newUser;
    }

}
