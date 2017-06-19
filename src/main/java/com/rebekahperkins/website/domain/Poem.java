package com.rebekahperkins.website.domain;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Poem {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @NotNull
  @Size(min=1, max=255)
  private String name;
  @Column(length = 3000)
  @NotNull
  @Size(min=1, max=3000)
  private String content;
  @Size(max=255)
  private String source;
  @ManyToOne
  @JoinColumn(name = "user_id")
  private UserEntity submittedBy;
  private LocalDateTime dateUploaded = LocalDateTime.now();
  @Transient
  private boolean favorite;
  @Transient
  private boolean abbr;
  @Transient
  private int image;

  public UserEntity getSubmittedBy() {
    return submittedBy;
  }

  public void setSubmittedBy(UserEntity submittedBy) {
    this.submittedBy = submittedBy;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getSource() {
    return source;
  }

  public void setSource(String source) {
    this.source = source;
  }

  public LocalDateTime getDateUploaded() {
    return dateUploaded;
  }

  public void setDateUploaded(LocalDateTime dateUploaded) {
    this.dateUploaded = dateUploaded;
  }

  public boolean isFavorite() {
    return favorite;
  }

  public void setFavorite(boolean favorite) {
    this.favorite = favorite;
  }

  public boolean isAbbr() {
    return abbr;
  }

  public void setAbbr(boolean abbr) {
    this.abbr = abbr;
  }

  public int getImage() {
    return image;
  }

  public void setImage(int image) {
    this.image = image;
  }
}
