package br.com.melo.api.services;

import br.com.melo.api.model.Category;
import br.com.melo.api.model.Product;
import br.com.melo.api.repository.ProductRepository;
import br.com.melo.api.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductRepository repositorio;
    @Autowired
    CategoryRepository  categoryrepositorio;

    public List<Product> retornarTodosOsProdutos(){
        return repositorio.findAll();
    }

    public Product retornarProdutoPorId(Long id){
        return repositorio.findById(id).orElseThrow();
    }

    public  void salvarProduto(Product product){

        if (product.getCategories() != null){
            List<Category> categoriasAAdicionar = new ArrayList<>();
            for (Category category : product.getCategories()){
                categoriasAAdicionar.add(categoryrepositorio.getReferenceById(category.getId()));
            }
            product.setCategories(categoriasAAdicionar);
        }
        repositorio.save(product);
    }

    public void removeProduto(Long id){
        repositorio.deleteById(id);
    }
}
