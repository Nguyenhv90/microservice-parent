package com.hvn.controller;

import com.hvn.model.Gallery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/gallery")
public class HomeController {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private Environment environment;

    @GetMapping("/")
    public ResponseEntity<String> home() {
        // This is useful for debugging
        // When having multiple instance of gallery service running at different ports.
        // We load balance among them, and display which instance received the request.
        return ResponseEntity.ok("Hello from Gallery Service running at port: " + environment.getProperty("local.server.port"));
    }

    @RequestMapping("/{id}")
    public Gallery getGallery(@PathVariable final int id) {
        // create gallery object
        Gallery gallery = new Gallery();
        gallery.setId(id);

        // get list of available images
        List<Object> images = restTemplate.getForObject("http://image-service/images/all", List.class);
        gallery.setImages(images);

        return gallery;
    }

    // -------- Admin Area --------
    // This method should only be accessed by users with role of 'admin' // We'll add the logic of role based auth later
    @RequestMapping("/admin")
    public String homeAdmin() {
        return "This is the admin area of Gallery service running at port: " + environment.getProperty("local.server.port");
    }
}
