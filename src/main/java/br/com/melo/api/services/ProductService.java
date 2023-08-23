package br.com.melo.api.services;

import br.com.melo.api.model.Product;
import br.com.melo.api.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductRepository repositorio;
    public List<Product> retornarTodosOsProdutos(){
        return repositorio.findAll();
    }

    public Product retornarProdutoPorId(Long id){
        return repositorio.findById(id).orElseThrow();
    }

    public  void salvarProduto(Product product){
        repositorio.save(product);
    }

    public void removeProduto(Long id){
        repositorio.deleteById(id);
    }
}
