package produto.produto.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import produto.produto.model.User;
import produto.produto.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository rep;
	
	public User salvar(User u) {
		
			return rep.save(u);
		
	}

}
