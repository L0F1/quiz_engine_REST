package engine.service;

import engine.model.User;
import engine.repository.UserRepository;
import engine.security.AuthenticationFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final AuthenticationFacade authenticationFacade;
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder encoder,
                           AuthenticationFacade authenticationFacade) {

        this.authenticationFacade = authenticationFacade;
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    @Override
    public boolean saveUser(User user) {

        // check what the email is free
        if (userRepository.findUserByEmail(user.getEmail()) != null)
            return false;

        user.setPassword(encoder.encode(user.getPassword()));
        user.setRole("ROLE_USER");
        userRepository.save(user);
        return true;
    }

    public User getCurrentAuthUser() {

        Object principal = authenticationFacade.getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }
        return userRepository.findUserByEmail(username);
    }
}
