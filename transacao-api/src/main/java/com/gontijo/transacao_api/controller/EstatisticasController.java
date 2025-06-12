package com.gontijo.transacao_api.controller;

import com.gontijo.transacao_api.business.services.EstatisticasServices;
import com.gontijo.transacao_api.controller.dtos.EstatisticasResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/estatistica")
@RequiredArgsConstructor
public class EstatisticasController {
    private final EstatisticasServices estatisticasServices;

    @GetMapping
    @Operation(description = "Endpoint responsável por buscar estatisticas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca efetuada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro na busca de estatisticas"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor"),
    })
    // Metodo para buscar estatisticas
    public ResponseEntity<EstatisticasResponseDTO> buscarEstatisticas(
            @RequestParam(value = "intervaloBuscar", required = false, defaultValue = "60") Integer intervaloBusca) {
        return ResponseEntity.ok(estatisticasServices.calcularEstatisticasTransacoes(intervaloBusca));
    }
}
