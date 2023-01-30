package org.bedu.module3Project.services;

import org.apache.catalina.User;
import org.bedu.module3Project.models.UserModel;
import org.bedu.module3Project.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServices {
    IUserRepository iUserRepository;

    @Autowired
    public UserServices( IUserRepository iUserRepository ){
        this.iUserRepository = iUserRepository;
    }

    public Iterable<UserModel> userList(){
        return iUserRepository.findAll();
    }

    public UserModel getUserById(Long id){
        return iUserRepository.findById(id);
    }

    public Iterable<UserModel> getUserByName(String name){
        return iUserRepository.findByName(name);
    }

    public UserModel saveUser(UserModel model){
         return iUserRepository.save(model);
    }
    public UserModel getUserByEmail(String email){
        return iUserRepository.findByEmail(email);
    }
}
