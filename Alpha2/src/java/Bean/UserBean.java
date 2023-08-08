/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import POJO.Studenttable;
import Util.NewHibernateUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Sonu JDK 8
 */
@ManagedBean(name = "userBean")
@ViewScoped
public class UserBean implements Serializable{
     private Integer id;
    private String firstName;
    private String lastName;
    private Date dob;
    private String email;
    private List<Studenttable> slist=new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Studenttable> getSlist() {
        return slist;
    }

    public void setSlist(List<Studenttable> slist) {
        this.slist = slist;
    }
  
    private Integer mobile;
     private String address;
     private String city;
     private String admission;
     private String branch;
     private Integer zip;
     
     

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAdmission() {
        return admission;
    }

    public void setAdmission(String admission) {
        this.admission = admission;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public Integer getZip() {
        return zip;
    }

    public void setZip(Integer zip) {
        this.zip = zip;
    }
    

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getMobile() {
        return mobile;
    }

    public void setMobile(Integer mobile) {
        this.mobile = mobile;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
  
    
    
    public void saveRecord() {
         if(mobile != null ){
             System.out.println("<<<<<<<<<<<---Calling save method:::--->>>>>>>>>>");
             Session session = NewHibernateUtil.getSessionFactory().openSession();   // create session
             Transaction tran = session.beginTransaction();                          //Open transaction
             Studenttable test = new Studenttable();                                 // Create object of pojo class
             test.setSfname(firstName);                                              //set the all variable to method of pojo
             test.setSlname(lastName);
             test.setEmail(email);
             test.setMobile(mobile);
             test.setBranch(branch);
             test.setSaddress(address);
             test.setSadmission(admission);
             test.setScity(city);
             test.setSzip(zip);
             test.setAge(dob);
             session.save(test);
             tran.commit();                                                          // commit transaction
             session.close();
             
             FacesContext context = FacesContext.getCurrentInstance();  
context.addMessage(null, new FacesMessage("Saved Successfully")); 
             System.out.println("<<<<<<<<<<---ENDING save method:::--->>>>>>>>>>");
         } else {
         }
        // close session
    } 
   
    public void lookupRecord() {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        String query = "from Studenttable";
        Query q = session.createQuery(query);
        slist = q.list();
        FacesContext context = FacesContext.getCurrentInstance();  
context.addMessage(null, new FacesMessage("Looked Successfully")); 

    }
    
    public void fetchRecord(Studenttable stuObj){
        id = stuObj.getSid();
        firstName = stuObj.getSfname();
        lastName = stuObj.getSlname();
    }
    
    
    
     public void deleteRecord () {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction tran = session.beginTransaction();
             Studenttable stuObj = (Studenttable) session.load(Studenttable.class, id);        /* pojo name                                                                                             & unique id for database*/ 
            Studenttable t = (Studenttable) stuObj;
            
            session.delete(t);                   // delete record
            tran.commit();                      //commit transaction
            session.close();
            FacesContext context = FacesContext.getCurrentInstance();  
context.addMessage(null, new FacesMessage("Deleted Successfully")); 
     }
    public void updateRecord() {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction txn = session.beginTransaction();
        Studenttable stuObj = (Studenttable) session.load(Studenttable.class, id);

        stuObj.setSfname(firstName);
        stuObj.setSlname(lastName);
        stuObj.setAge(dob);
        stuObj.setBranch(branch);
        stuObj.setEmail(email);
        stuObj.setMobile(mobile);
        stuObj.setSaddress(address);
        stuObj.setScity(city);
        stuObj.setSzip(zip);
        txn.commit();
        session.close();
       FacesContext context = FacesContext.getCurrentInstance();  
context.addMessage(null, new FacesMessage("Update Successfully")); 
    }
    
    public void clear(){
       
        firstName = "";
        lastName = "";
        city = "";
        address = "";
        email = "";
        branch = "";
        mobile = null;
        zip = null;
        admission = "";
        dob = null;
        
       FacesContext context = FacesContext.getCurrentInstance();  
context.addMessage(null, new FacesMessage("Clear Successfully"));  
    }

    
   
   
   
    

}
