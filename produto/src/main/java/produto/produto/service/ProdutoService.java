package produto.produto.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import produto.produto.model.Produto;
import produto.produto.repository.ProdutoRepository;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository repository;
	
	
	public Produto Salvar (Produto p) {
		return repository.save(p);
	}
	
	public void apagar(long id) {
		repository.deleteById(id);
	}

}
