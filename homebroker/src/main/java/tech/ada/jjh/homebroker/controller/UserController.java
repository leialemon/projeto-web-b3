package tech.ada.jjh.homebroker.controller;

import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import tech.ada.jjh.homebroker.dto.AppUserDTOResponse;
import tech.ada.jjh.homebroker.dto.AppUserDTORequest;
import tech.ada.jjh.homebroker.service.create.CreateUserService;
import tech.ada.jjh.homebroker.service.fetch.FetchUserService;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "api/v1/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {
    private final CreateUserService createUserService;
    private final FetchUserService fetchUserService;

    public UserController(CreateUserService createUserService, FetchUserService fetchUserService){
        this.createUserService = createUserService;
        this.fetchUserService = fetchUserService;
    }

    @GetMapping()
    public List<AppUserDTOResponse> getAll(){
        return fetchUserService.fetchAll();

    }

    @GetMapping(value = "/cpf/{cpf}")
    public Optional<AppUserDTOResponse> findUserByCpf(@PathVariable String cpf){
        return fetchUserService.fetchByCpf(cpf);
    }

    @PostMapping()
    public AppUserDTOResponse createUser(@Valid @RequestBody AppUserDTORequest appUserDTORequest){
        return createUserService.create(appUserDTORequest);
    }
}
