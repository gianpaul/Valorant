package exirium.pe.valorant.domain.model

data class Agent(
    val image: String,
    val name: String,
    val role: String,
    val background: String,
    val abilities: List<String>
)