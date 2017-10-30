/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hello.pojo;

public class From {

    private String id;

    private String aadObjectId;

    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAadObjectId() {
        return aadObjectId;
    }

    public void setAadObjectId(String aadObjectId) {
        this.aadObjectId = aadObjectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ClassPojo [id = " + id + ", aadObjectId = " + aadObjectId + ", name = " + name + "]";
    }
}
