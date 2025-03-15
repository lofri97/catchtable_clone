package com.lofri.catchtable.domain.collection.controller;

import com.lofri.catchtable.common.dto.ResponseTemplate;
import com.lofri.catchtable.domain.collection.dto.CreateCollectionRequest;
import com.lofri.catchtable.domain.collection.dto.GetCollectionResponse;
import com.lofri.catchtable.domain.collection.dto.GetCollectionsResponse;
import com.lofri.catchtable.domain.collection.dto.UpdateCollectionRequest;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/collections")
public class CollectionController {


    @PostMapping()
    public ResponseTemplate<Void> createCollection(@Valid @RequestBody CreateCollectionRequest request) {
        return null;
    }

    @GetMapping()
    public ResponseTemplate<GetCollectionsResponse> getCollections(@RequestParam(name = "type", required = false) String type,
                                                                   @RequestParam(name = "user_id", required = false) String userId,
                                                                   @RequestParam(name = "page_size", defaultValue = "20") Integer pageSize,
                                                                   @RequestParam(name = "page_index", defaultValue = "1") Integer pageIndex,
                                                                   @RequestParam(name = "order_by", defaultValue = "recent") String orderBy) {
        return null;
    }

    @GetMapping("/{collectionId}")
    public ResponseTemplate<GetCollectionResponse> getCollection(@PathVariable long collectionId) {
        return null;
    }

    @PutMapping("/{collectionId}")
    public ResponseTemplate<Void> updateCollection(@Valid @RequestBody UpdateCollectionRequest request) {
        return null;
    }

    @DeleteMapping("/{collectionId}")
    public ResponseTemplate<Void> deleteCollection(@PathVariable long collectionId) {
        return null;
    }

    @PostMapping("/{collectionId}/subscribe")
    public ResponseTemplate<Void> subscribeCollection(@PathVariable long collectionId) {
        return null;
    }

    @DeleteMapping("/{collectionId}/subscribe")
    public ResponseTemplate<Void> unsubscribeCollection(@PathVariable long collectionId) {
        return null;
    }
}
