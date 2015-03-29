package com.springapp.controllers;

import com.springapp.transactional.Tiles;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by franschl on 29.03.15.
 */
@RestController
public class TileController {

    @RequestMapping(value = "tile/{lat}/{lon}", method = RequestMethod.GET)
    public ResponseEntity<byte[]> getTile(@PathVariable("lat") int lat, @PathVariable("lon") int lon) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(new MediaType("application", "zip"));
            headers.setContentDispositionFormData("download", "tile.zip");
            return new ResponseEntity<>(Tiles.getTile(lat, lon), headers, HttpStatus.OK);
        } catch (FileNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
