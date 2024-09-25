package tech.ada.jjh.homebroker.controller;

import org.springframework.web.bind.annotation.*;
import tech.ada.jjh.homebroker.model.AppUser;
import tech.ada.jjh.homebroker.service.create.CreateUserService;
import tech.ada.jjh.homebroker.service.fetch.FetchUserService;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/users")
public class UserController {
    private final CreateUserService createUserService;
    private final FetchUserService fetchUserService;

    public UserController(CreateUserService createUserService, FetchUserService fetchUserService){
        this.createUserService = createUserService;
        this.fetchUserService = fetchUserService;
    }

    @GetMapping()
    public List<AppUser> getAll(){
        return fetchUserService.fetchAll();
    }

    @GetMapping("/cpf/{cpf}")
    public Optional<AppUser> findUserByCpf(@PathVariable String cpf){
        return fetchUserService.fetchByCpf(cpf);
    }

    @GetMapping(path = {"/{id}"})
    public Optional<AppUser> findUserById(@PathVariable long id){
        return fetchUserService.fetchById(id);
    }

    @PostMapping()
    public AppUser createUser(@RequestBody AppUser appUser){
        return createUserService.createUser(appUser);
    }
}
