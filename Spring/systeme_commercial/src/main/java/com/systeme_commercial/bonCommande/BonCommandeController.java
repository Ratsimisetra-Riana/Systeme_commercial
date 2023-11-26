package com.systeme_commercial.bonCommande;

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
public class BonCommandeController {

    private final BonCommandeDetailRepository detailsRepo;
    private final BonCommandeHeaderRepository headerRepo;

    public BonCommandeController(BonCommandeDetailRepository detailsRepo,BonCommandeHeaderRepository headerRepo){
        this.detailsRepo = detailsRepo;
        this.headerRepo = headerRepo;
    }

    @PostMapping("/bonCommande")
	public BonCommande saveBonCommande(@RequestBody BonCommande b) {
        System.out.println(b.toString());
        b.save(this.headerRepo,this.detailsRepo);
        b.generatePdf();
        return b;
	}


}
