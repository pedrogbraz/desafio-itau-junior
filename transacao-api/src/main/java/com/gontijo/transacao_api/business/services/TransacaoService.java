package com.gontijo.transacao_api.business.services;

import com.gontijo.transacao_api.controller.dtos.TransacaoRequestDTO;
import com.gontijo.transacao_api.infrastructure.exceptions.UnprocessableEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransacaoService {
    private final List<TransacaoRequestDTO> listaTransacoes = new ArrayList<>();

    // Metodo para adicionar transacoes(que seria o POST /transacao)
    public void adicionarTransacoes(TransacaoRequestDTO dto) {
        log.info("Iniciada o processamento de gravar transações " + dto);
        // Exceção para que a data não possa ser sucessora a data atual
        if (dto.dataHora().isAfter(OffsetDateTime.now())) {
            log.error("Data e hora maiores que a data e hora atuais");
            throw new UnprocessableEntity("Data e hora maiores que a data e hora atuais");
        }
        // Exceção para que o valor não possa ser menor do que 0
        if (dto.valor() < 0) {
            log.error("Valor menor que 0");
            throw new UnprocessableEntity("Valor menor que 0");
        }
        listaTransacoes.add(dto);
        log.info("Transações adicionadas com sucesso");
    }

    // Metodo para limpar transações(que seria o DELETE /transacao)
    public void limparTransacoes() {
        log.info("Iniciado processamento para deletar transações");
        listaTransacoes.clear();
        log.info("Transações deletadas com sucesso");
    }

    // Metodo para buscar estatisticas
    public List<TransacaoRequestDTO> buscarTransacoes(Integer intervaloBusca) {
        log.info("Iniciadas buscas de transações por tempo " + intervaloBusca);
        OffsetDateTime dataHoraIntervalo = OffsetDateTime.now().minusSeconds(intervaloBusca);

        // Filtro para pegar apenas as transações que ocorreram nos ultimos 60 segundos
        // Peguei a lista de transacoes, chamei o stream para filtrar cada uma das transacoes e verifica se a data e hora delas, e sucessora a atual, se for posterior, ela adiciona na lista
        log.info("Retorno de transações com sucesso");
        return listaTransacoes.stream()
                .filter(transacao -> transacao.dataHora()
                    .isAfter(dataHoraIntervalo)).toList();
    }
}
