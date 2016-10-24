package xyz.trackbuck.security;

/**
 * Created by lex on 09.10.16.
 */
public enum Roles {
    ADMIN("ROLE_ADMIN"),
    USER("ROLE_USER");

    private final String name;


    Roles(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
