package io.github.lifveras.bradeco_cupomfiscal.required.auth;

import io.github.anaangelieri.bradeco_authenticationComponent.internal.model.User;
import io.github.anaangelieri.bradeco_authenticationComponent.required.AuthenticationConcreteOutbox;

public class AuthComponentInterface extends AuthenticationConcreteOutbox implements AuthenticationComponentInterface {
    public User login(String username, String password) {
        return new io.github.anaangelieri.bradeco_authenticationComponent.internal.UserAuthService()
                .authenticate(username, password);
    }

    public void initialize() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'initialize'");
    }

    public void connect(String string, Object object) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'connect'");
    }

    public void disconnect(String string) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'disconnect'");
    }
    
}
