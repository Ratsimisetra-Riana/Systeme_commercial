package com.systeme_commercial.varianteArticle;

import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class VarianteArticleController {

    private final VarianteArticleRepository varArticleRepo;

    public VarianteArticleController(VarianteArticleRepository varArticleRepo){
        this.varArticleRepo = varArticleRepo;
    }

    @GetMapping("/varianteArticle")
	public List<VarianteArticle> getVariantesArticle() {
        return this.varArticleRepo.findAll();
	}

    @PostMapping("/varianteArticle")
	public VarianteArticle insertVariantesArticle(@RequestBody VarianteArticle v) {
        return this.varArticleRepo.save(v);
	}
}
