package produto.produto.repository;


import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import produto.produto.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	
	String findByEmail(String email);
	
	ArrayList<User> findByNomeContaining(String nome);

}
