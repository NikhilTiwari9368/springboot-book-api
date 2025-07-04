package com.bootapi.restapi.entities;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class Author {
  @Id
  @GeneratedValue(strategy = jakarta.persistence.GenerationType.AUTO)
  private int authorid;

  @Column(name = "first_name")
  private String firstname;

  private String lastname;

  private String language;
  
  @OneToOne(mappedBy = "author")
  @JsonBackReference
  private Book book;  

  public int getAuthorid() {
    return authorid;
  }   

  public void setAuthorid(int authorid) {
    this.authorid = authorid;
  }

  public String getFirstname() {
    return firstname;
  }

  public void setFirstname(String firstname) {
    this.firstname = firstname;
  }

  public String getLastname() {
    return lastname;
  }

  public void setLastname(String lastname) {
    this.lastname = lastname;
  }

  public String getLanguage() {
    return language;
  }

  public void setLanguage(String language) {
    this.language = language;
  }

  public Author(int authorid, String firstname, String lastname, String language) {
    this.authorid = authorid;
    this.firstname = firstname;
    this.lastname = lastname;
    this.language = language;
  }

  public Author() {
  } 

  @Override 
  public String toString() {
    return "Author [authorid=" + authorid + ", firstname=" + firstname + ", lastname=" + lastname + ", language="
        + language + "]";
  } 

  public Book getBook() {
    return book;
  }  

  public void setBook(Book book) {
    this.book = book;
  }
  
}