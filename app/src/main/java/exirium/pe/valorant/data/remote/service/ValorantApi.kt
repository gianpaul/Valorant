package exirium.pe.valorant.data.remote.service

import exirium.pe.valorant.data.remote.response.AgentDTO
import retrofit2.Response
import retrofit2.http.GET

interface ValorantApi {

    @GET("agents")
    suspend fun getAgents(): Response<AgentDTO>

}