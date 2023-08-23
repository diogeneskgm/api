package br.com.melo.api.controller;

import br.com.melo.api.model.Product;
import br.com.melo.api.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/products")
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping("/")
    public void adicionarProduto(@RequestBody Product product) {
        productService.salvarProduto(product);
    }

    @GetMapping("")
    public List<Product> retornarTodosOsProdutos() {
        return productService.retornarTodosOsProdutos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> buscarPorId(@PathVariable Long id) {
        try {
            Product product = productService.retornarProdutoPorId(id);
            return new ResponseEntity<>(product, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public void removerProduct(@PathVariable Long id) {
        productService.removeProduto(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> atualizarProduto(@RequestBody Product product, @PathVariable Long id) {
        try {
            Product produtoNoBancoDeDados = productService.retornarProdutoPorId(id);
            product.setId(id);
            productService.salvarProduto(product);
            return new ResponseEntity<>(product, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
