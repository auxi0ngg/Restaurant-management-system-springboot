package com.auxiongg.restaurant.menu;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.auxiongg.restaurant.menu.dtos.MenuItemDto;
import com.auxiongg.restaurant.menu.dtos.UpdateSpecialRequest;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/menu")
@Tag(name = "Menu")
public class MenuController {
    private final MenuService menuService;

    @GetMapping
    @Operation(summary = "Any user can view the menu")
    public ResponseEntity<List<MenuItemDto>> getAllItems() {
        return ResponseEntity.ok(menuService.getAllItems());
    }

    @PreAuthorize("hasRole('CHEF')")
    @PostMapping("/special")
    @Operation(summary = "A chef can update or add a special to the menu")
    public ResponseEntity<MenuItemDto> createOrUpdateSpecial(
            @Valid @RequestBody UpdateSpecialRequest request
    ) {
        return ResponseEntity.ok(menuService.createOrUpdateSpecial(request.getName(), request.getPrice(), request.getDescription()));
    }

}
