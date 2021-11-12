package com.sha.springbootbooksat333.service;

import com.sha.springbootbooksat333.model.User;

public interface IAuthenticationService {
    User signInAndReturnJWT(User signInRequest);
}
