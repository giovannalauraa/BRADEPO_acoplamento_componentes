package io.github.lifveras.bradeco_cupomfiscal;

import br.com.bazarbooks.components.cart.internal.adapter.CupomFiscalAdapter;
import br.com.bazarbooks.components.cart.provided.interfaces.ShoppingCartProvidedInterface;
import br.com.bazarbooks.components.cart.required.interfaces.BookServiceInterface;
import br.com.bazarbooks.components.cart.required.interfaces.UserServiceInterface;
import br.doubles.StubBookService;
import br.doubles.StubUserService;
import io.github.anaangelieri.userAuthComponent.internal.model.User;
import io.github.anaangelieri.userAuthComponent.internal.service.UserAuthService;
import io.github.anaangelieri.userAuthComponent.provided.UserAuthConcreteInterfacePort;
import io.github.lifveras.bradeco_cupomfiscal.internal.ImpressoraCupomFiscal;
import io.github.lifveras.bradeco_cupomfiscal.internal.model.Book;
import io.github.lifveras.bradeco_cupomfiscal.provided.CupomFiscalConcreteInterfacePort;
import io.github.lifveras.bradeco_cupomfiscal.provided.interfaces.BookProvidedInterface;
import io.github.lifveras.bradeco_cupomfiscal.required.book.BookComponentInterface;
import io.github.lifveras.bradeco_cupomfiscal.required.cart.CartComponentInterface;

public class Main {

    public static void main(String[] args) {
        System.out.println("=== Iniciando sistema BRADEPO ===");

        // 1. Componente de Livros
        BookComponentInterface bookComponent = new BookComponentInterface();
        bookComponent.initialize();
        bookComponent.connect("logger", new Object());
        BookProvidedInterface bookPort = (BookProvidedInterface) bookComponent.getProvidedInterface("bookOpsPort");

        // 2. Componente de Autenticação
        UserAuthService userAuthService = new UserAuthService();
        UserAuthConcreteInterfacePort userAuthPort = new UserAuthConcreteInterfacePort(userAuthService);

        // 3. Exibir a splash screen
        userAuthPort.showSplashScreen();

        // 4. Exibir o onBoarding
        userAuthPort.passOnboarding();

        // 5. Registrar usuários
        User user1 = userAuthPort.registerNewUser("Ana", "ana@gmail.com", "senha123");
        User user2 = userAuthPort.registerNewUser("Giovanna", "giovanna@gmail.com", "senha234");
        User user3 = userAuthPort.registerNewUser("Évelin", "evelin@gmail.com", "senha345");
        User user4 = userAuthPort.registerNewUser("Soraya", "soraya@gmail.com", "senha456");

        System.out.println("Usuário registrado: " + user1.getNome() + " (" + user1.getEmail() + ")");
        System.out.println("Usuário registrado: " + user2.getNome() + " (" + user2.getEmail() + ")");
        System.out.println("Usuário registrado: " + user3.getNome() + " (" + user3.getEmail() + ")");
        System.out.println("Usuário registrado: " + user4.getNome() + " (" + user4.getEmail() + ")");
        
        // 6. Obter todos os usuários
        System.out.println("\nTodos os usuários registrados:");
        for (User user : userAuthPort.getAllUsers()) {
            System.out.println(user.getId() + ": " + user.getNome() + " - " + user.getEmail());
        }

        // 7. Obter usuário por ID
        User userById = userAuthPort.getUserById(user3.getId());
        System.out.println("\nUsuário: " + userById.getNome() + "; ID: " + userById.getId());

        // 8. Deletar um usuário
        boolean deleted = userAuthPort.deleteUser(user1.getId());
        if (deleted){
            System.out.println("\nUsuário deletado com sucesso");
        } else {
            System.out.println("\nErro ao deletar");
        }

        // 9. Criação de Livros
        Book livro1 = bookPort.createBook("1984", "George Orwell");
        Book livro2 = bookPort.createBook("Clean Code", "Robert C. Martin");

        // 10. Cupom Fiscal: criação da porta concreta e adaptação
        CupomFiscalConcreteInterfacePort cupomPort = new CupomFiscalConcreteInterfacePort("cupom1");
        cupomPort.setCupomFiscal(new ImpressoraCupomFiscal());
        CupomFiscalAdapter cupomFiscal = new CupomFiscalAdapter(cupomPort);

        // 11. Serviços stub para Book e User (simulações simples)
        BookServiceInterface bookService = new StubBookService();
        UserServiceInterface userService = new StubUserService();

        // 12. Componente de Carrinho com dependências injetadas
        CartComponentInterface cartComponent = new CartComponentInterface(bookService, userService, cupomFiscal);
        ShoppingCartProvidedInterface cartPort = cartComponent.getService();

        // 13. Adiciona livros ao carrinho
        // cartPort.addItemToCart("user1", String.valueOf(livro1.getId()), 2);
        // cartPort.addItemToCart("user1", String.valueOf(livro2.getId()), 1);
        // 14. Consultando carrinho
        System.out.println("\nTotal de itens no carrinho: " + cartPort.getTotalItemsInCart("user1"));
        // cartPort.clearCart("user1");
        System.out.println("Carrinho está vazio? " + cartPort.isCartEmpty("user1"));

        // // 15. Limpa o carrinho
        // cartPort.clearCart("user1");
        // System.out.println("Carrinho está vazio? " + cartPort.isCartEmpty("user1"));
        // 16. Exibindo livros disponíveis
        System.out.println("\nLivros disponíveis:");
        for (Book b : bookPort.getAllBooks()) {
            System.out.println(b.getId() + ": " + b.getTitle() + " - " + b.getAuthor());
        }

        System.out.println("\n Visualizar detalhes de um livro: ");
        System.out.println(livro1.getId() + ": " + livro1.getTitle() + " - " + livro1.getAuthor() + "\n");

        // 17. Desconectando interfaces
        bookComponent.disconnect("logger");
        System.out.println("\nSistema finalizado com sucesso.");
    }
}
