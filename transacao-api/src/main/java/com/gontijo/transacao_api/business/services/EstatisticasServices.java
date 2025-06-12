package com.gontijo.transacao_api.business.services;

import com.gontijo.transacao_api.controller.dtos.EstatisticasResponseDTO;
import com.gontijo.transacao_api.controller.dtos.TransacaoRequestDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.DoubleSummaryStatistics;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class EstatisticasServices {
    public final TransacaoService transacaoService;

    // Metodo para buscar as transacoes
    public EstatisticasResponseDTO calcularEstatisticasTransacoes(Integer intervaloBusca) {
        // Metodo para buscar as transacoes que foram feitas no intervalo de tempo de 60 segundos
        log.info("Iniciada busca de estatisticas de transações pelo periodo de tempo de" + intervaloBusca);
        List<TransacaoRequestDTO> transacoes = transacaoService.buscarTransacoes(intervaloBusca);

        // Se as estatisticas estiverem vazias, elas serão todas atribuidas como 0
        if (transacoes.isEmpty()) {
            return new EstatisticasResponseDTO(0L, 0.0, 0.0, 0.0, 0.0);
        }

        // Metodo para fazer o calculo e atribuir valores para as estatisticas
        DoubleSummaryStatistics estatisticasTransacoes = transacoes.stream()
                .mapToDouble(TransacaoRequestDTO::valor).summaryStatistics();
        log.info("Estatisticas retornadas com sucesso");
        return new EstatisticasResponseDTO(estatisticasTransacoes.getCount(), estatisticasTransacoes.getSum(), estatisticasTransacoes.getAverage(), estatisticasTransacoes.getMin(), estatisticasTransacoes.getMax());
    }
}
