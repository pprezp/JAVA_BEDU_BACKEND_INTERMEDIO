package org.bedu.module3Project.repository;
import org.apache.catalina.User;
import org.bedu.module3Project.models.UserModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface IUserRepository extends CrudRepository<UserModel, String> {
    UserModel findById(@Param("id") Long id);
    UserModel findByEmail(@Param("email") String email);
    Iterable<UserModel> findByName(@Param("name") String name);
}
