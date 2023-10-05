package exirium.pe.valorant.data.repository

import exirium.pe.valorant.data.remote.service.ValorantApi
import exirium.pe.valorant.domain.mapper.toAgentDomain
import exirium.pe.valorant.domain.model.Agent
import exirium.pe.valorant.domain.repository.AgentRepository

class AgentRepositoryImpl(
    private val valorantApi: ValorantApi
) : AgentRepository {

    @Throws(Exception::class)
    override suspend fun getAgents(): List<Agent> {
        val response = valorantApi.getAgents()
        if (! response.isSuccessful) throw Exception("Error getting agents")
        return response.body()?.data?.mapNotNull { characterDTO ->
            characterDTO.role?.let {
                characterDTO.toAgentDomain()
            }
        } ?: emptyList()
    }
}