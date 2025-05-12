package com.chamados.chamados.controller;

import com.chamados.chamados.domain.chamados.Chamado;
import com.chamados.chamados.domain.chamados.ChamadoRequestDTO;
import com.chamados.chamados.domain.imagens.Imagem;
import com.chamados.chamados.domain.imagens.ImagemPostResponseDTO;
import com.chamados.chamados.domain.imagens.ImagemResponseDTO;
import com.chamados.chamados.service.ChamadoService;
import com.chamados.chamados.service.ImagemService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/chamado")
@Tag(name = "Chamado")
public class ChamadoController {

    @Autowired
    private ChamadoService chamadoService;
    @Autowired
    private ImagemService imagemService;

    @PostMapping()
    public ResponseEntity<Chamado> addOrAlterChamado(@RequestBody @Valid ChamadoRequestDTO body){
        Chamado newChamado = this.chamadoService.createOrUpdateChamado(body);
        return  ResponseEntity.ok(newChamado);
    }

    @GetMapping("")
    public ResponseEntity<List<Chamado>> getAllChamado(){
        List<Chamado> chamado = this.chamadoService.getAll();

        return ResponseEntity.ok(chamado);
    }

    @DeleteMapping("{chamadoId}")
    public ResponseEntity<Void> removeChamado(@PathVariable("chamadoId") UUID chamadoId){
        this.chamadoService.deleteChamadoId(chamadoId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("{chamadoId}")
    public ResponseEntity<Chamado> getByIdChamado(@PathVariable("chamadoId") UUID chamadoId){
        Chamado chamado = this.chamadoService.getById(chamadoId);
        return ResponseEntity.ok(chamado);
    }

    @GetMapping("{chamadoId}/anexos/imagem")
    public ResponseEntity<List<ImagemResponseDTO>> getAllImagens(@PathVariable("chamadoId") UUID chamadoId){
        List<ImagemResponseDTO> imagems = this.imagemService.getAllImages(chamadoId);
        return ResponseEntity.ok(imagems);
    }

    @PostMapping(value = "{chamadoId}/anexo/imagem", consumes = "multipart/form-data")
    public ResponseEntity<ImagemPostResponseDTO> addImagem(@PathVariable("chamadoId") UUID chamadoId,
                                                           @RequestParam(name = "file") MultipartFile file){
        ImagemPostResponseDTO imagem = this.imagemService.addImg(chamadoId,file);
        return ResponseEntity.ok(imagem);
    }

    @DeleteMapping("/anexo/imagem/{imagemId}")
    public ResponseEntity<Void> removeImagem(@PathVariable("imagemId") UUID imagemId){
        this.imagemService.removeImagem(imagemId);
        return ResponseEntity.ok().build();
    }
}
