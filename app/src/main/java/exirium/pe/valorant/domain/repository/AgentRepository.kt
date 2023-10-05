package exirium.pe.valorant.domain.repository

import exirium.pe.valorant.domain.model.Agent

interface AgentRepository {

    suspend fun getAgents(): List<Agent>
}