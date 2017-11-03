/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hello.pojo.security;

/**
 *
 * @author gcatanese
 */
public class JWK {

    private String e;

    private String[] endorsements;

    private String n;

    private String kty;

    private String use;

    private String[] x5c;

    private String x5t;

    private String kid;

    public String getE() {
        return e;
    }

    public void setE(String e) {
        this.e = e;
    }

    public String[] getEndorsements() {
        return endorsements;
    }

    public void setEndorsements(String[] endorsements) {
        this.endorsements = endorsements;
    }

    public String getN() {
        return n;
    }

    public void setN(String n) {
        this.n = n;
    }

    public String getKty() {
        return kty;
    }

    public void setKty(String kty) {
        this.kty = kty;
    }

    public String getUse() {
        return use;
    }

    public void setUse(String use) {
        this.use = use;
    }

    public String[] getX5c() {
        return x5c;
    }

    public void setX5c(String[] x5c) {
        this.x5c = x5c;
    }

    public String getX5t() {
        return x5t;
    }

    public void setX5t(String x5t) {
        this.x5t = x5t;
    }

    public String getKid() {
        return kid;
    }

    public void setKid(String kid) {
        this.kid = kid;
    }
    
    

}
