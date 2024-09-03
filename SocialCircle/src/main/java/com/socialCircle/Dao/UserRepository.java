package com.socialCircle.Dao;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.socialCircle.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{
	
	Optional<User>  findByUserNameOrEmail(String userName,String email);
	
	Optional<User>  findByUserName(String userName);
	
	boolean existsByEmail(String email);
	
	boolean existsByUserName(String username);
	
	
}
