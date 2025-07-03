package io.github.lifveras.bradeco_cupomfiscal.required.cart;

import br.com.bazarbooks.components.cart.ShoppingCartComponent;
import br.com.bazarbooks.components.cart.ShoppingCartComponentInterface;
import br.com.bazarbooks.components.cart.provided.interfaces.ShoppingCartProvidedInterface;
import br.com.bazarbooks.components.cart.required.interfaces.BookServiceInterface;
import br.com.bazarbooks.components.cart.required.interfaces.CupomFiscalInterface;
import br.com.bazarbooks.components.cart.required.interfaces.UserServiceInterface;

public class CartComponentInterface extends ShoppingCartComponent implements ShoppingCartComponentInterface {

    public CartComponentInterface(BookServiceInterface bookService, UserServiceInterface userService,
            CupomFiscalInterface cupomFiscal) {
        super(bookService, userService, cupomFiscal);
        //TODO Auto-generated constructor stub
    }
    // Pode customizar aqui se quiser no futuro

    public void connect(String string, Object object) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'connect'");
    }

    public ShoppingCartProvidedInterface getProvidedInterface(String string) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getProvidedInterface'");
    }

    public void disconnect(String string) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'disconnect'");
    }
}
