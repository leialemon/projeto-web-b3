package tech.ada.jjh.homebroker.controller;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import tech.ada.jjh.homebroker.dto.AppUserDTOGet;
import tech.ada.jjh.homebroker.dto.AppUserDTOPost;
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
    public List<AppUserDTOGet> getAll(){
        return fetchUserService.fetchAll();

    }

    @GetMapping("/cpf/{cpf}")
    public Optional<AppUserDTOGet> findUserByCpf(@PathVariable String cpf){
        return fetchUserService.fetchByCpf(cpf);
    }

    @PostMapping()
    public AppUserDTOGet createUser(@Valid @RequestBody AppUserDTOPost appUserDTOPost){
        return createUserService.create(appUserDTOPost);
    }
}
