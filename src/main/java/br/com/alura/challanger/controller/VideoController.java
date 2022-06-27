package br.com.alura.challanger.controller;

import br.com.alura.challanger.model.Video;
import br.com.alura.challanger.model.VideoRequest;
import br.com.alura.challanger.service.VideoService;
import br.com.alura.challanger.validator.group.PutChecks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import javax.validation.groups.Default;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
@Validated
@RequestMapping("/video")
public class VideoController {


    @Autowired
    VideoService videoService;

    @GetMapping
    public ResponseEntity<Page<Video>> listarVideos(@RequestParam("page") @Min(value = 1, message = "O valor informado deve ser maior ou igual a 1") Integer page,
                                                    @RequestParam  @Min(value = 1, message = "O valor informado deve ser maior ou igual a 1") Integer qtd){
            Pageable paginacao = PageRequest.of(page-1,qtd);
            return new ResponseEntity<Page<Video>>(videoService.getVideo(paginacao), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Video> getVideoId( @PathVariable("id") @NotBlank(message = "id não pode ser nulo!") String id){
        return new ResponseEntity<Video>(videoService.getVideoId(id), HttpStatus.OK);
    }

    @PostMapping
    public  ResponseEntity<Video> inserirVideo(@Valid @RequestBody VideoRequest videoRequest){
        return new ResponseEntity<Video>(videoService.inserirVideo(videoRequest), HttpStatus.OK);
    }

    @PutMapping
    public  ResponseEntity<Video> atualizarVideo(@Validated({ Default.class, PutChecks.class }) @RequestBody VideoRequest videoRequest){
        return new ResponseEntity<Video>(videoService.atualizaVideo(videoRequest), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<String> deletarVideo(@PathVariable("id") @NotBlank(message = "id não pode ser nulo!") String id){
        return new ResponseEntity<String>(videoService.deletarVideo(id), HttpStatus.OK);
    }

}
