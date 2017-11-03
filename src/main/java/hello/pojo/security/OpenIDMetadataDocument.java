package hello.pojo.security;

/**
 *
 */
public class OpenIDMetadataDocument {

    private String[] token_endpoint_auth_methods_supported;

    private String issuer;

    private String jwks_uri;

    private String[] id_token_signing_alg_values_supported;

    private String authorization_endpoint;

    public String[] getToken_endpoint_auth_methods_supported() {
        return token_endpoint_auth_methods_supported;
    }

    public void setToken_endpoint_auth_methods_supported(String[] token_endpoint_auth_methods_supported) {
        this.token_endpoint_auth_methods_supported = token_endpoint_auth_methods_supported;
    }

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    public String getJwks_uri() {
        return jwks_uri;
    }

    public void setJwks_uri(String jwks_uri) {
        this.jwks_uri = jwks_uri;
    }

    public String[] getId_token_signing_alg_values_supported() {
        return id_token_signing_alg_values_supported;
    }

    public void setId_token_signing_alg_values_supported(String[] id_token_signing_alg_values_supported) {
        this.id_token_signing_alg_values_supported = id_token_signing_alg_values_supported;
    }

    public String getAuthorization_endpoint() {
        return authorization_endpoint;
    }

    public void setAuthorization_endpoint(String authorization_endpoint) {
        this.authorization_endpoint = authorization_endpoint;
    }

    @Override
    public String toString() {
        return "OpenIDMetadataDocument [token_endpoint_auth_methods_supported = " + token_endpoint_auth_methods_supported + ", issuer = " + issuer + ", jwks_uri = " + jwks_uri + ", id_token_signing_alg_values_supported = " + id_token_signing_alg_values_supported + ", authorization_endpoint = " + authorization_endpoint + "]";
    }

}
