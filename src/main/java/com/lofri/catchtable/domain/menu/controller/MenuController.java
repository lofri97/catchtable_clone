package com.lofri.catchtable.domain.menu.controller;

import com.lofri.catchtable.common.dto.ResponseTemplate;
import com.lofri.catchtable.domain.menu.dto.GetMenuResponse;
import com.lofri.catchtable.domain.menu.dto.GetMenusResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/menus")
public class MenuController {

    @GetMapping
    public ResponseTemplate<GetMenusResponse> getMenus(@RequestParam(name = "rest_id") long restId,
                                                       @RequestParam(name = "query_type", defaultValue = "simple") String queryType) {
        return null;
    }

    @GetMapping("/{menuId}")
    public ResponseTemplate<GetMenuResponse> getMenu(@PathVariable long menuId) {
        return null;
    }
}
