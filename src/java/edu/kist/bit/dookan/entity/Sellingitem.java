/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.kist.bit.dookan.entity;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author shankar xtha
 */
@Entity
@Table(name = "sellingitem", catalog = "dookan", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Sellingitem.findAll", query = "SELECT s FROM Sellingitem s")
    , @NamedQuery(name = "Sellingitem.findById", query = "SELECT s FROM Sellingitem s WHERE s.id = :id")
    , @NamedQuery(name = "Sellingitem.findByItemselleruserid", query = "SELECT s FROM Sellingitem s WHERE s.itemselleruserid = :itemselleruserid")
    , @NamedQuery(name = "Sellingitem.findByStatus", query = "SELECT s FROM Sellingitem s WHERE s.status = :status")
    , @NamedQuery(name = "Sellingitem.findByPriceafteroffer", query = "SELECT s FROM Sellingitem s WHERE s.priceafteroffer = :priceafteroffer")
    , @NamedQuery(name = "Sellingitem.findByCreatedate", query = "SELECT s FROM Sellingitem s WHERE s.createdate = :createdate")})
public class Sellingitem implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "itemselleruserid")
    private Integer itemselleruserid;
    @Column(name = "status")
    private String status;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "priceafteroffer")
    private Float priceafteroffer;
    @Column(name = "createdate")
    @Temporal(TemporalType.DATE)
    private Date createdate;
    @JoinColumn(name = "productid", referencedColumnName = "id")
    @ManyToOne
    private Product productid;
    @OneToMany(mappedBy = "addedtocartitem")
    private List<Cart> cartList;

    public Sellingitem() {
    }

    public Sellingitem(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getItemselleruserid() {
        return itemselleruserid;
    }

    public void setItemselleruserid(Integer itemselleruserid) {
        this.itemselleruserid = itemselleruserid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Float getPriceafteroffer() {
        return priceafteroffer;
    }

    public void setPriceafteroffer(Float priceafteroffer) {
        this.priceafteroffer = priceafteroffer;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public Product getProductid() {
        return productid;
    }

    public void setProductid(Product productid) {
        this.productid = productid;
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
        if (!(object instanceof Sellingitem)) {
            return false;
        }
        Sellingitem other = (Sellingitem) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.kist.bit.dookan.entity.Sellingitem[ id=" + id + " ]";
    }
    
}
