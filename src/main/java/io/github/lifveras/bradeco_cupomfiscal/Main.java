package io.github.lifveras.bradeco_cupomfiscal;

import io.github.anaangelieri.bradeco_authenticationComponent.internal.model.User;

public class Main {
    public static void main(String[] args) {
        // Teste do cart-component
        Cart carrinho = new Cart();
        carrinho.addItem("Produto 1", 2);
        carrinho.listItems();

        // Teste do userAuthComponent
        User auth = new UserAuth();
        boolean autenticado = auth.login("admin", "senha123");
        System.out.println("Usuário autenticado? " + autenticado);

        // Teste do book-component
        BookService service = new BookService();
        Book livro = service.createBook("Dom Casmurro", "Machado de Assis");
        System.out.println("Livro criado: " + livro.getTitle() + " por " + livro.getAuthor());
    }
}
