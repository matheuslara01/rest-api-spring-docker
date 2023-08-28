package br.com.incode.demodocker.application.generics;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.incode.demodocker.application.dtos.ResponseDTO;
import br.com.incode.demodocker.infrastructure.util.BaseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

public abstract class ControllerGeneric<T, ID extends Serializable> {
    
    @Autowired
    private BaseService<T, ID> baseService;

    @GetMapping("/findAll")
    @SecurityRequirement(name = "bearerAuth")
    @Operation(summary = "Get all records")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Success in the request", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Object.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid Parameter", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseDTO.class)) }),
            @ApiResponse(responseCode = "403", description = "Not Authorized", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseDTO.class)) }),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseDTO.class)) }),
            @ApiResponse(responseCode = "504", description = "Timeout", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseDTO.class)) }), })
    public List<T> findAll() {
        return baseService.findAll();
    }

    @GetMapping("/findById/{id}")
    @SecurityRequirement(name = "bearerAuth")
    @Operation(summary = "Get Record by ID")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Success in the request", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Object.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid Parameter", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseDTO.class)) }),
            @ApiResponse(responseCode = "403", description = "Not Authorized", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseDTO.class)) }),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseDTO.class)) }),
            @ApiResponse(responseCode = "504", description = "Timeout", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseDTO.class)) }), })
    public T findById(@Parameter(description = "Object ID") @PathVariable ID id) {
        return baseService.findById(id);
    }

    @PostMapping("/save")
    @SecurityRequirement(name = "bearerAuth")
    @Operation(summary = "Save Object")
    @ApiResponses(value = { @ApiResponse(responseCode = "201", description = "Object created", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Object.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid Parameter", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseDTO.class)) }),
            @ApiResponse(responseCode = "403", description = "Not Authorized", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseDTO.class)) }),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseDTO.class)) }),
            @ApiResponse(responseCode = "504", description = "Timeout", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseDTO.class)) }), })
    public T saveReturnEntity(@RequestBody T entity) {
        return baseService.saveReturnEntity(entity, "Error when saving!");
    }

    @DeleteMapping("/delete/{id}")
    @SecurityRequirement(name = "bearerAuth")
    @Operation(summary = "Delete Object")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Deleted successfully", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Object.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid Parameter", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseDTO.class)) }),
            @ApiResponse(responseCode = "403", description = "Not Authorized", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseDTO.class)) }),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseDTO.class)) }),
            @ApiResponse(responseCode = "504", description = "Timeout", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseDTO.class)) }), })
    public ResponseDTO deleteById(@PathVariable ID id) {
        return baseService.deleteById(id, "Error deleting!");
    }
}
