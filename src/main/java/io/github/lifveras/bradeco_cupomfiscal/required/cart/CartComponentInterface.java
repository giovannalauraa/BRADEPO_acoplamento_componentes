package io.github.lifveras.bradeco_cupomfiscal.required.cart;

import br.com.bazarbooks.components.cart.ShoppingCartComponent;
import br.com.bazarbooks.components.cart.required.interfaces.BookServiceInterface;
import br.com.bazarbooks.components.cart.required.interfaces.CupomFiscalInterface;
import br.com.bazarbooks.components.cart.required.interfaces.UserServiceInterface;

public class CartComponentInterface extends ShoppingCartComponent {

    public CartComponentInterface(BookServiceInterface bookService, UserServiceInterface userService,
            CupomFiscalInterface cupomFiscal) {
        super(bookService, userService, cupomFiscal);
    }

    public void initialize() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'initialize'");
    }

}
