package com.chamados.chamados.service;

import com.chamados.chamados.domain.chamados.Chamado;
import com.chamados.chamados.domain.imagens.Imagem;
import com.chamados.chamados.domain.imagens.ImagemPostResponseDTO;
import com.chamados.chamados.domain.imagens.ImagemResponseDTO;
import com.chamados.chamados.mapper.ImagemMapper;
import com.chamados.chamados.repositories.ChamadoRepository;
import com.chamados.chamados.repositories.ImagemRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class ImagemService {

    @Autowired
    private AWSService awsService;

    @Autowired
    private ChamadoRepository chamadoRepository;

    @Autowired
    private ImagemRepository imagemRepository;

    @Autowired
    private ImagemMapper mapper;

    @Value("${aws.bucket.name}")
    private String _bucketName;

    public ImagemPostResponseDTO addImg(UUID chamadoId, MultipartFile file){
        String urlImagem = null;
        try {
            if(file.isEmpty()){
                throw new RuntimeException("Selecione uma imagem");
            }

            Chamado chamado = chamadoRepository.findById(chamadoId)
                    .orElseThrow(()-> new IllegalArgumentException("Chamado não encontrado"));

            Imagem imagem = mapper.toEntity(file.getOriginalFilename());
            imagem.setChamado(chamado);
            imagemRepository.save(imagem);

            File newFile = this.convertMultiPartToFile(file);
            this.awsService.putFile(chamadoId,_bucketName,file.getOriginalFilename(),newFile);
            newFile.delete();

            return mapper.toPostDTOResponse(imagem);
        } catch (Exception e){
            throw new RuntimeException("Erro ao fazer upload da imagem: "+e.getMessage());
        }
    }

    public List<ImagemResponseDTO> getAllImages(UUID chamadoId){
        List<Imagem> imagems = imagemRepository.findByChamadoId(chamadoId);
        List<ImagemResponseDTO> dtos = mapper.ToDTOList(imagems);

        dtos.forEach(dto -> {
            String urlAssigned = awsService.getUrlAssinada(_bucketName,dto.getNome());

            dto.setUrlImg(urlAssigned);
        });

        return dtos;
    }

    public void removeImagem(UUID imagemID){
        try {
            Imagem imagem = imagemRepository.findById(imagemID)
                    .orElseThrow(()-> new IllegalArgumentException("Imagem não encontrada"));

            if(imagem == null){
                throw new EntityNotFoundException("Imagem com ID " + imagemID + " não encontrada" );
            }

            String key = imagem.getChamado().getId().toString() + "/" + imagem.getNome();

            imagemRepository.deleteById(imagemID);
            this.awsService.deleteFile(_bucketName, key);
        } catch (Exception e){
            throw new RuntimeException("Erro ao remover a imagem " + e.getMessage());
        }
    }

    private File convertMultiPartToFile(MultipartFile multipartFile) throws IOException {

        File convFile = new File(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(multipartFile.getBytes());
        fos.close();

        return convFile;
    }
}
