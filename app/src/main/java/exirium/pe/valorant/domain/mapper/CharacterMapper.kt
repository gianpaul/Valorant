package exirium.pe.valorant.domain.mapper

import exirium.pe.valorant.data.remote.response.CharacterDTO
import exirium.pe.valorant.domain.model.Agent

fun CharacterDTO.toAgentDomain () = Agent(
    image = this.fullPortrait?:"",
    name = this.displayName,
    role = this.role?.displayName?:"",
    background = this.background?:"",
    abilities = this.abilities?.map { it.displayIcon } ?: emptyList()
)