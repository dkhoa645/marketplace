package com.dkhoa.marketplace.controller;

import com.dkhoa.marketplace.dto.request.ProvinceCreationRequest;
import com.dkhoa.marketplace.dto.request.ProvinceUpdateRequest;
import com.dkhoa.marketplace.dto.response.ApiResponse;
import com.dkhoa.marketplace.dto.response.ProvinceResponse;
import com.dkhoa.marketplace.service.ProvinceService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/province")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProvinceController {
    ProvinceService provinceService;

    @PostMapping
    ApiResponse<ProvinceResponse> createProvince(@RequestBody ProvinceCreationRequest request) {
        return ApiResponse.<ProvinceResponse>builder()
                .result(provinceService.createProvince(request))
                .build();
    }

    @GetMapping
    ApiResponse<List<ProvinceResponse>> getProvinces() {
        return ApiResponse.<List<ProvinceResponse>>builder()
                .result(provinceService.getAllProvince())
                .build();
    }

    @DeleteMapping("/{id}")
    ApiResponse<Void> deleteProvince(@PathVariable Long id){
        provinceService.deleteProvince(id);
        return ApiResponse.<Void>builder()
                .message("Delete province successfully")
                .build();
    }

    @PutMapping("/{id}")
    ApiResponse<ProvinceResponse> updateProvince(
            @PathVariable Long id,
            @RequestBody ProvinceUpdateRequest request) {
        return ApiResponse.<ProvinceResponse>builder()
                .result(provinceService.updateProvince(id,request))
                .build();
    }
}
