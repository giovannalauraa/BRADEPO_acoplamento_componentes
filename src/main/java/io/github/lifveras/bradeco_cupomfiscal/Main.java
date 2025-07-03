package io.github.lifveras.bradeco_cupomfiscal;

import io.github.lifveras.bradeco_cupomfiscal.internal.ImpressoraCupomFiscal;
import io.github.lifveras.bradeco_cupomfiscal.internal.model.Book;
import io.github.lifveras.bradeco_cupomfiscal.provided.BookComponenteInterface;
import io.github.lifveras.bradeco_cupomfiscal.provided.CupomFiscalConcreteInterfacePort;
import io.github.lifveras.bradeco_cupomfiscal.provided.interfaces.BookProvidedInterface;
import io.github.lifveras.bradeco_cupomfiscal.required.auth.AuthComponentInterface;
import io.github.lifveras.bradeco_cupomfiscal.required.book.BookComponentInterface;
import io.github.lifveras.bradeco_cupomfiscal.required.cart.CartComponentInterface;
import br.com.bazarbooks.components.cart.internal.adapter.CupomFiscalAdapter;
import br.com.bazarbooks.components.cart.provided.interfaces.ShoppingCartProvidedInterface;
import br.com.bazarbooks.components.cart.required.interfaces.BookServiceInterface;
import br.com.bazarbooks.components.cart.required.interfaces.UserServiceInterface;
import br.doubles.StubBookService;
import br.doubles.StubUserService;
import io.github.anaangelieri.bradeco_authenticationComponent.internal.UserAuthService;
import io.github.anaangelieri.bradeco_authenticationComponent.internal.model.User;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Iniciando sistema BRADEPO ===");

        // 1. Componente de Livros
        BookComponentInterface bookComponent = new BookComponentInterface();
        bookComponent.initialize();
        bookComponent.connect("logger", new Object());
        BookProvidedInterface bookPort = (BookProvidedInterface) bookComponent.getProvidedInterface("bookOpsPort");

        // 2. Componente de Autenticação
        AuthComponentInterface authComponent = new AuthComponentInterface();
        // authComponent.initialize();
        User usuario = authComponent.login("user1", "senha123");
        System.out.println("Usuário autenticado? " + (usuario != null));

        // 3. Criação de Livros
        Book livro1 = bookPort.createBook("1984", "George Orwell");
        Book livro2 = bookPort.createBook("Clean Code", "Robert C. Martin");

        // 4. Cupom Fiscal: criação da porta concreta e adaptação
        CupomFiscalConcreteInterfacePort cupomPort = new CupomFiscalConcreteInterfacePort("cupom1");
        cupomPort.setCupomFiscal(new ImpressoraCupomFiscal());
        CupomFiscalAdapter cupomFiscal = new CupomFiscalAdapter(cupomPort);

        // 5. Serviços stub para Book e User (simulações simples)
        BookServiceInterface bookService = new StubBookService();
        UserServiceInterface userService = new StubUserService();

        // 6. Componente de Carrinho com dependências injetadas
        CartComponentInterface cartComponent = new CartComponentInterface(bookService, userService, cupomFiscal);
        ShoppingCartProvidedInterface cartPort = cartComponent.getService();

        // 7. Adiciona livros ao carrinho
        // cartPort.addItemToCart("user1", String.valueOf(livro1.getId()), 2);
        // cartPort.addItemToCart("user1", String.valueOf(livro2.getId()), 1);

        // 8. Consultando carrinho
        System.out.println("\nTotal de itens no carrinho: " + cartPort.getTotalItemsInCart("user1"));
        // cartPort.clearCart("user1");
        System.out.println("Carrinho está vazio? " + cartPort.isCartEmpty("user1"));

        // // 9. Limpa o carrinho
        // cartPort.clearCart("user1");
        // System.out.println("Carrinho está vazio? " + cartPort.isCartEmpty("user1"));

        // 9. Exibindo livros disponíveis
        System.out.println("\nLivros disponíveis:");
        for (Book b : bookPort.getAllBooks()) {
            System.out.println(b.getId() + ": " + b.getTitle() + " - " + b.getAuthor());
        }

        System.out.println("\n Visualizar detalhes de um livro: ");
        System.out.println(livro1.getId() + ": " + livro1.getTitle() + " - " + livro1.getAuthor() + "\n");

        // 10. Desconectando interfaces
        bookComponent.disconnect("logger");
        System.out.println("\nSistema finalizado com sucesso.");
    }
}
