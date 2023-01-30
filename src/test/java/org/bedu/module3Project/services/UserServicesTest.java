package org.bedu.module3Project.services;

import org.bedu.module3Project.models.UserModel;
import org.bedu.module3Project.repository.IUserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.springframework.security.core.userdetails.User;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserServicesTest {

    @Mock
    private IUserRepository iUserRepository;

    @InjectMocks
    private UserServices userServices;
    @Test
    void userList() {
        List<UserModel> data = new ArrayList();
    }

    @Test
    void getUserById() {
    }

    @Test
    void getUserByName() {
    }

    @Test
    void saveUser() {
    }

    @Test
    void getUserByEmail() {
    }
}