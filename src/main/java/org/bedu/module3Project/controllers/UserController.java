package org.bedu.module3Project.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.bedu.module3Project.models.UserModel;
import org.bedu.module3Project.repository.IUserRepository;
import org.bedu.module3Project.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {
    private UserServices userServices;
    private final IUserRepository iUserRepository;

    @Autowired
    public UserController(UserServices userServices,
                          IUserRepository iUserRepository){
        this.userServices = userServices;
        this.iUserRepository = iUserRepository;
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserModel>> getAllUsers(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email
                                                ){
        try {
            List<UserModel> usersData = new ArrayList<UserModel>();
            if (name == null && email == null){
                userServices.userList().forEach(usersData::add);
            }
            if ( name != null && email == null){
                userServices.getUserByName(name).forEach(usersData::add);
            }
            if ( name== null && email != null ){
                usersData.add(userServices.getUserByEmail(email));
            }

            if ( name != null && email != null ){
                usersData.add(userServices.getUserByEmail(email));
            }
            if ( usersData.isEmpty() ){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(usersData, HttpStatus.OK);

        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/users/{id}")
    @ResponseBody
    public UserModel getUserById(@PathVariable long id, HttpServletRequest request){
        return  userServices.getUserById(id);
    }

    @PatchMapping("/users/{id}")
    public ResponseEntity<String> updateUser(@PathVariable Long id,
                                             @RequestParam(required = false) String name,
                                             @RequestParam(required = false) String lastName,
                                             @RequestParam(required = false) String email,
                                             @RequestParam(required = false) String password) throws Exception {

        try {
            UserModel user = iUserRepository.findById(id);


            if (name != "") {
                user.setName(name);
            }
            if (lastName != "") {
                user.setLastName(lastName);
            }
            if (email != "") {
                user.setEmail(email);
            }
            if (password != "") {
                user.setPassword(password);
            }

            iUserRepository.save(user);

            return new ResponseEntity<>("OKIS", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Registro no actualizado, ha ocurrido un error", HttpStatus.NOT_MODIFIED);
        }
    }

    @PostMapping("/users")
    public ResponseEntity<String> createUser(@Valid @RequestParam String name,
                                             HttpServletRequest request
                                             ) throws Exception{
        try{
            UserModel entity = new UserModel();
            entity.setName(name);
            entity.setLastName(request.getParameter("lastName"));
            entity.setEmail(request.getParameter("email"));
            entity.setPassword(request.getParameter("password"));

            userServices.saveUser(entity);

            return new ResponseEntity<>("Usuario Creado",HttpStatus.ACCEPTED);
        }catch(Exception e){
            return new ResponseEntity<>("Usuario No Creado",HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @GetMapping("/admin")
    public String getAdmin(){
        return "Admin path";
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserModel>> getAll(){
        try {
            List<UserModel> usersData = new ArrayList<UserModel>();
            userServices.userList().forEach(usersData::add);
            if ( usersData.isEmpty() ){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(usersData, HttpStatus.OK);
        }catch (Exception e){
            return  new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/login")
    public String getLogin(){
        return "login path";
    }


    @DeleteMapping("/users/email/{email}")
    public ResponseEntity<String> deleteUserByEmail(@PathVariable String email){
        try{
            UserModel user = iUserRepository.findByEmail(email);
            iUserRepository.delete(user);
            return new ResponseEntity<>("Registro eliminado correctamente", HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>("Registro no eliminado correctamente", HttpStatus.NOT_MODIFIED);
        }
    }
}
