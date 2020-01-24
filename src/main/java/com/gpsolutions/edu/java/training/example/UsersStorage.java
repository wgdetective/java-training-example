package com.gpsolutions.edu.java.training.example;

/**
 * @author Wladimir Litvinov
 */
//@Component
//@RequiredArgsConstructor
//public class UsersStorage {
//
//    private final PasswordEncoder passwordEncoder;
//
//    private final Map<String, String> inMemoryUsers = new HashMap<>();
//
//    public void saveUser(final String username, final String password) {
//        inMemoryUsers.put(username, passwordEncoder.encode(password));
//    }
//
//    public UserDetails getByEmail(final String username) {
//        if (inMemoryUsers.get(username) == null) {
//            return null;
//        } else {
//            return new User(username, inMemoryUsers.get(username), List.of(new SimpleGrantedAuthority("ROLE_" + STUDENT.name())));
//        }
//    }
//}
