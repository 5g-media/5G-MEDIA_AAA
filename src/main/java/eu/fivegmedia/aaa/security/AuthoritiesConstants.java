package eu.fivegmedia.aaa.security;

/**
 * Constants for Spring Security authorities.
 */
public final class AuthoritiesConstants {

    public static final String ADMIN = "ROLE_ADMIN";
    public static final String TENANT_ADMIN = "ROLE_TENANT_ADMIN";

    public static final String USER = "ROLE_USER";
    
    public static final String ACCOUNTING = "ROLE_ACCOUNTING";
    public static final String CATALOG_INTERNAL = "ROLE_CATALOG_INTERNAL";
    public static final String SERVICE_COMPOSER = "ROLE_SERVICE_COMPOSER";
    public static final String SERVICE_DEVELOPER = "ROLE_SERVICE_DEVELOPER";
    
    public static final String ANONYMOUS = "ROLE_ANONYMOUS";

    private AuthoritiesConstants() {
    }
}
