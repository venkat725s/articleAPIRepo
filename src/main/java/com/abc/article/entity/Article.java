package com.abc.article.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="article")
public class Article implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long articleId;

    private String title;



    private Date date;
    private String body;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "fk_article_id", referencedColumnName = "articleId")
    private Set<Tag> tags = new HashSet<>();

    //YYYYddMM format
    @JsonIgnoreProperties
    private String createdDate;

    //Custom method
    public void setDate(Date date) {
        this.date = date;
        DateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        this.createdDate = formatter.format(date);
    }

}
