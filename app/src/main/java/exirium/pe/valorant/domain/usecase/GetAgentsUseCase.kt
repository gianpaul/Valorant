package exirium.pe.valorant.domain.usecase

import exirium.pe.valorant.domain.repository.AgentRepository

class GetAgentsUseCase(
    private val agentRepository: AgentRepository
) {
    suspend operator fun invoke() = agentRepository.getAgents()
}