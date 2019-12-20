/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.kist.bit.dookan.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author shankar xtha
 */
@Entity
@Table(name = "cart", catalog = "dookan", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cart.findAll", query = "SELECT c FROM Cart c")
    , @NamedQuery(name = "Cart.findById", query = "SELECT c FROM Cart c WHERE c.id = :id")
    , @NamedQuery(name = "Cart.findByTotalqantity", query = "SELECT c FROM Cart c WHERE c.totalqantity = :totalqantity")
    , @NamedQuery(name = "Cart.findByTotalprice", query = "SELECT c FROM Cart c WHERE c.totalprice = :totalprice")})
public class Cart implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "totalqantity")
    private BigInteger totalqantity;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "totalprice")
    private Float totalprice;
    @OneToMany(mappedBy = "cartid")
    private List<Orders> ordersList;
    @JoinColumn(name = "buyerid", referencedColumnName = "id")
    @ManyToOne
    private Userdetail buyerid;
    @JoinColumn(name = "addedtocartitem", referencedColumnName = "id")
    @ManyToOne
    private Sellingitem addedtocartitem;

    public Cart() {
    }

    public Cart(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigInteger getTotalqantity() {
        return totalqantity;
    }

    public void setTotalqantity(BigInteger totalqantity) {
        this.totalqantity = totalqantity;
    }

    public Float getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(Float totalprice) {
        this.totalprice = totalprice;
    }

    @XmlTransient
    public List<Orders> getOrdersList() {
        return ordersList;
    }

    public void setOrdersList(List<Orders> ordersList) {
        this.ordersList = ordersList;
    }

    public Userdetail getBuyerid() {
        return buyerid;
    }

    public void setBuyerid(Userdetail buyerid) {
        this.buyerid = buyerid;
    }

    public Sellingitem getAddedtocartitem() {
        return addedtocartitem;
    }

    public void setAddedtocartitem(Sellingitem addedtocartitem) {
        this.addedtocartitem = addedtocartitem;
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
        if (!(object instanceof Cart)) {
            return false;
        }
        Cart other = (Cart) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.kist.bit.dookan.entity.Cart[ id=" + id + " ]";
    }
    
}
