package com.blapecha.reservas.view;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Slf4j
@Named
@SessionScoped
@Data
public class LocaleView implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private Locale locale;
    private String language;
    
    @PostConstruct
    public void init() {
        locale = FacesContext.getCurrentInstance().getExternalContext().getRequestLocale();
        language = locale.getLanguage();
        log.info("Idioma detectado: {}", language);
    }
    
    public String changeLanguage(String language) {
        this.language = language;
        locale = new Locale(language);
        FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
        log.info("Idioma cambiado a: {}", language);
        return null;
    }
    
    public List<String> getAvailableLanguages() {
        List<String> languages = new ArrayList<>();
        languages.add("es");
        languages.add("en");
        return languages;
    }
}