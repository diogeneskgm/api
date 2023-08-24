package br.com.melo.api.services;

import br.com.melo.api.model.Category;
import br.com.melo.api.model.Product;
import br.com.melo.api.model.Review;
import br.com.melo.api.repository.ProductRepository;
import br.com.melo.api.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductRepository repositorio;
    @Autowired
    CategoryRepository  categoryrepositorio;

    public List<Product> retornarTodosOsProdutos(){
        List<Product> products = repositorio.findAll();
        for(Product product : products){
            calcularNotaDoProduto(product);
        }
        return repositorio.findAll();
    }

    public Product retornarProdutoPorId(Long id){
        Product product = repositorio.findById(id).orElseThrow();
        calcularNotaDoProduto(product);
        return product;
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

    public Product calcularNotaDoProduto(Product product){
        double nota = 0;
        if( !CollectionUtils.isEmpty(product.getReviews())){
            for( Review review : product.getReviews() ){
                nota += review.getRate();
            }

            nota /= (double)product.getReviews().size();
        }
        product.setReviewRate(nota);
        return product;
    }

}
