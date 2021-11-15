/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rws;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ReturnData implements Serializable {
    private String name;
    private String family;
    private int age;

    public ReturnData() {
        
        name = "Boris";
        family = "Johnson";
        age = 57;
    }

    @Override
    public String toString() {
        return "ReturnData{" + "name=" + name + ", family=" + family + ", age=" + age + '}';
    }
    

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the family
     */
    public String getFamily() {
        return family;
    }

    /**
     * @param family the family to set
     */
    public void setFamily(String family) {
        this.family = family;
    }

    /**
     * @return the age
     */
    public int getAge() {
        return age;
    }

    /**
     * @param age the age to set
     */
    public void setAge(int age) {
        this.age = age;
    }
    
}
