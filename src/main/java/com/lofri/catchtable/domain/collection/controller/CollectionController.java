package com.lofri.catchtable.domain.collection.controller;

import com.lofri.catchtable.domain.collection.dto.CreateCollectionRequest;
import com.lofri.catchtable.domain.collection.dto.GetCollectionResponse;
import com.lofri.catchtable.domain.collection.dto.UpdateCollectionRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/collections")
public class CollectionController {


    @PostMapping()
    public ResponseEntity<?> createCollection(@Valid @RequestBody CreateCollectionRequest request) {
        return null;
    }

    @GetMapping("/{collectionId}")
    public ResponseEntity<GetCollectionResponse> getCollection(@PathVariable long collectionId) {
        return null;
    }

    @PutMapping("/{collectionId}")
    public ResponseEntity<?> updateCollection(@Valid @RequestBody UpdateCollectionRequest request) {
        return null;
    }


    @DeleteMapping("/{collectionId}")
    public ResponseEntity<?> deleteCollection(@PathVariable long collectionId) {
        return null;
    }

    @PostMapping("/{collectionId}/subscribe")
    public ResponseEntity<?> subscribeCollection(@PathVariable long collectionId) {
        return null;
    }

    @DeleteMapping("/{collectionId}/subscribe")
    public ResponseEntity<?> unsubscribeCollection(@PathVariable long collectionId) {
        return null;
    }
}
