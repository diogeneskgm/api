package br.com.melo.api.services;

import br.com.melo.api.model.Category;
import br.com.melo.api.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CategoryService {
    @Autowired
    CategoryRepository repositorio;
    public List<Category> retornarTodasAsCategorias(){
        return repositorio.findAll();
    }

    public Category retornarCategoriaPorId(Long id){
        return repositorio.findById(id).orElseThrow();
    }

    public  void salvarCategoria(Category categoria){
        repositorio.save(categoria);
    }

    public void removeCategoria(Long id){
        repositorio.deleteById(id);
    }
}
