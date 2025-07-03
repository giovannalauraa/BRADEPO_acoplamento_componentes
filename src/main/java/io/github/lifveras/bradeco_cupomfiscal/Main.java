package io.github.lifveras.bradeco_cupomfiscal;

import io.github.lifveras.bradeco_cupomfiscal.internal.model.Book;
import io.github.lifveras.bradeco_cupomfiscal.provided.BookComponenteInterface;
import io.github.lifveras.bradeco_cupomfiscal.provided.interfaces.BookProvidedInterface;
import io.github.lifveras.bradeco_cupomfiscal.required.auth.AuthComponentInterface;
import io.github.lifveras.bradeco_cupomfiscal.required.book.BookComponentInterface;
import io.github.lifveras.bradeco_cupomfiscal.required.cart.CartComponentInterface;
import br.com.bazarbooks.components.cart.provided.interfaces.ShoppingCartProvidedInterface;
import io.github.anaangelieri.bradeco_authenticationComponent.internal.UserAuthService;
import io.github.anaangelieri.bradeco_authenticationComponent.internal.model.User;

public class Main {
    public static void main(String[] args) {
        // Criação e inicialização dos componentes
        BookComponentInterface bookComponent = new BookComponentInterface();
        AuthComponentInterface authComponent = new AuthComponentInterface();
        CartComponentInterface cartComponent = new CartComponentInterface(null, null, null);

        bookComponent.initialize();
        // authComponent.initialize();
        
        // Conectando componentes entre si via required interfaces
        bookComponent.connect("logger", new Object());
        // cartComponent.connect("bookOps", bookComponent.getProvidedInterface("bookOpsPort"));
        // cartComponent.connect("logger", new Object());
        // authComponent.connect("logger", new Object());

        // Acessa as interfaces públicas dos componentes (provided)
        BookProvidedInterface bookPort = (BookProvidedInterface) bookComponent.getProvidedInterface("bookOpsPort");
        // ShoppingCartProvidedInterface cartPort = (ShoppingCartProvidedInterface) cartComponent.getProvidedInterface("shoppingCartOpsPort");

        // Autenticação do usuário
        User usuario = authComponent.login("admin", "senha123");
        System.out.println("Usuário autenticado? " + (usuario != null));

        // Criando livros
        Book livro1 = bookPort.createBook("1984", "George Orwell");
        Book livro2 = bookPort.createBook("Clean Code", "Robert C. Martin");

        // Adicionando livros ao carrinho
        // cartPort.addItem(livro1.getTitle(), 2);
        // cartPort.addItem(livro2.getTitle(), 1);
        // cartPort.listItems();

        // Mostra todos os livros do sistema
        System.out.println("\nTodos os livros cadastrados:");
        for (Book b : bookPort.getAllBooks()) {
            System.out.println(b.getId() + ": " + b.getTitle() + " - " + b.getAuthor());
        }

        // Desconectando os componentes
        bookComponent.disconnect("logger");
        // authComponent.disconnect("logger");
        // cartComponent.disconnect("logger");
        // cartComponent.disconnect("bookOps");
    }
}
