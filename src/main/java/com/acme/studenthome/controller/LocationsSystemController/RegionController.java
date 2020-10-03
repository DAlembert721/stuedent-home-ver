package com.acme.studenthome.controller.LocationsSystemController;

import com.acme.studenthome.domain.model.LocationsSystem.Region;
import com.acme.studenthome.domain.service.LocationsSystemService.LocationService;
import com.acme.studenthome.resource.LocationsSystem.RegionResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class RegionController {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private LocationService locationService;

    @Operation(summary = "Get Regions",
            description = "Get All Regions by Page",
            tags = "regions")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "All regions returned",
                    content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/regions")
    public Page<RegionResource> getAllRegions(Pageable pageable) {
        Page<Region> regionPage = locationService.getAllRegions(pageable);
        List<RegionResource> resources = regionPage.getContent()
                .stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }


     private RegionResource convertToResource(Region entity) {
        return mapper.map(entity, RegionResource.class);
    }


}