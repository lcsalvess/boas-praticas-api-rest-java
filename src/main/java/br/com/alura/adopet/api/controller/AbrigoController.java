package br.com.alura.adopet.api.controller;

import br.com.alura.adopet.api.dto.abrigo.AbrigoDto;
import br.com.alura.adopet.api.dto.abrigo.AbrigoDtoCadastro;
import br.com.alura.adopet.api.dto.pet.PetDto;
import br.com.alura.adopet.api.dto.pet.PetDtoCadastro;
import br.com.alura.adopet.api.exception.ValidacaoExpection;
import br.com.alura.adopet.api.model.Abrigo;
import br.com.alura.adopet.api.service.AbrigoService;
import br.com.alura.adopet.api.service.PetService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/abrigos")
public class AbrigoController {

    @Autowired
    private AbrigoService service;
    @Autowired
    private AbrigoService abrigoService;
    @Autowired
    private PetService petService;

    @GetMapping
    public ResponseEntity<List<AbrigoDto>> listar() {
        List<AbrigoDto> abrigoDtoList = service.listarAbrigos();
        return ResponseEntity.ok(abrigoDtoList);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<String> cadastrar(@RequestBody @Valid AbrigoDtoCadastro dto) {
        try {
            this.service.cadastrar(dto);
            return ResponseEntity.ok("Abrigo cadastrado com sucesso!");
        } catch (ValidacaoExpection expection) {
            return ResponseEntity.badRequest().body(expection.getMessage());
        }
    }

    @GetMapping("/{idOuNome}/pets")
    public ResponseEntity<List<PetDto>> listarPets(@PathVariable String idOuNome) {
        try {
            List<PetDto> petsPorAbrigo = service.listarPetsPorAbrigo(idOuNome);
            return ResponseEntity.ok(petsPorAbrigo);
        } catch (ValidacaoExpection exception) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{idOuNome}/pets")
    @Transactional
    public ResponseEntity<String> cadastrarPet(@PathVariable String idOuNome, @RequestBody @Valid PetDtoCadastro dto) {
        try {
            Abrigo abrigo = abrigoService.carregarAbrigo(idOuNome);
            petService.cadastrarPet(abrigo, dto);
            return ResponseEntity.ok("Pet cadastrado com sucesso!");
        } catch (ValidacaoExpection exception) {
            return ResponseEntity.notFound().build();
        }
    }
}
