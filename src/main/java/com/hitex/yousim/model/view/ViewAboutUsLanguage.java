package com.hitex.yousim.model.view;

import com.hitex.yousim.dto.request.IRequestData;
import lombok.Data;
import org.springframework.data.annotation.Immutable;

import javax.persistence.*;

@Immutable
@Data
@Entity
@Table(name = "v_about_us_lang")
public class ViewAboutUsLanguage implements IRequestData {
    @Id
    @Column(name = "image")
    private String image;
    @Column(name = "type")
    private int type;
    @Column(name = "content")
    private String content;
    @Column(name = "title")
    private String title;
    @Column(name = "language")
    private String language;
    @Column(name = "partner")
    private String partner;


    @Override
    public boolean isValid() {
        return false;
    }
}
