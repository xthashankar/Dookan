/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.kist.bit.dookan.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author shankar xtha
 */
@Entity
@Table(name = "userdetail", catalog = "dookan", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Userdetail.findAll", query = "SELECT u FROM Userdetail u")
    , @NamedQuery(name = "Userdetail.findById", query = "SELECT u FROM Userdetail u WHERE u.id = :id")
    , @NamedQuery(name = "Userdetail.findByFirstName", query = "SELECT u FROM Userdetail u WHERE u.firstName = :firstName")
    , @NamedQuery(name = "Userdetail.findByLastName", query = "SELECT u FROM Userdetail u WHERE u.lastName = :lastName")
    , @NamedQuery(name = "Userdetail.findByEmail", query = "SELECT u FROM Userdetail u WHERE u.email = :email")
    , @NamedQuery(name = "Userdetail.findByAddress", query = "SELECT u FROM Userdetail u WHERE u.address = :address")
    , @NamedQuery(name = "Userdetail.findByPhoneNum", query = "SELECT u FROM Userdetail u WHERE u.phoneNum = :phoneNum")
    , @NamedQuery(name = "Userdetail.findByUsername", query = "SELECT u FROM Userdetail u WHERE u.username = :username")
    , @NamedQuery(name = "Userdetail.findByPassword", query = "SELECT u FROM Userdetail u WHERE u.password = :password")
    , @NamedQuery(name = "Userdetail.findByRole", query = "SELECT u FROM Userdetail u WHERE u.role = :role")
    , @NamedQuery(name = "Userdetail.findByCreatedate", query = "SELECT u FROM Userdetail u WHERE u.createdate = :createdate")
    , @NamedQuery(name = "Userdetail.findByImage", query = "SELECT u FROM Userdetail u WHERE u.image = :image")})
public class Userdetail implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "email")
    private String email;
    @Column(name = "address")
    private String address;
    @Column(name = "phone_num")
    private BigInteger phoneNum;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "role")
    private String role;
    @Column(name = "createdate")
    @Temporal(TemporalType.DATE)
    private Date createdate;
    @Basic(optional = false)
    @Column(name = "image")
    private String image;
    @OneToMany(mappedBy = "userdetailid")
    private List<Product> productList;
    @OneToMany(mappedBy = "buyerid")
    private List<Cart> cartList;

    public Userdetail() {
    }

    public Userdetail(Integer id) {
        this.id = id;
    }

    public Userdetail(Integer id, String image) {
        this.id = id;
        this.image = image;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public BigInteger getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(BigInteger phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @XmlTransient
    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    @XmlTransient
    public List<Cart> getCartList() {
        return cartList;
    }

    public void setCartList(List<Cart> cartList) {
        this.cartList = cartList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Userdetail)) {
            return false;
        }
        Userdetail other = (Userdetail) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.kist.bit.dookan.entity.Userdetail[ id=" + id + " ]";
    }
    
    
}
