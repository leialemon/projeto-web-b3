package tech.ada.jjh.homebroker.controller;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import tech.ada.jjh.homebroker.dto.AppUserDTO;
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
    public List<AppUserDTO> getAll(){
        return fetchUserService.fetchAll();

    }

    @GetMapping("/cpf/{cpf}")
    public Optional<AppUserDTO> findUserByCpf(@PathVariable String cpf){
        return fetchUserService.fetchByCpf(cpf);
    }

    @PostMapping()
    public AppUserDTO createUser(@Valid @RequestBody AppUserDTO appUserDTO){
        return createUserService.execute(appUserDTO);
    }
}
