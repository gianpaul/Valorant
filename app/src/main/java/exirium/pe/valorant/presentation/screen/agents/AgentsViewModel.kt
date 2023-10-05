package exirium.pe.valorant.presentation.screen.agents

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import exirium.pe.valorant.domain.model.Agent
import exirium.pe.valorant.domain.usecase.GetAgentsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class AgentsViewModel(
    private val getAgentsUseCase: GetAgentsUseCase
) : ViewModel() {
    private val _uiState: MutableStateFlow<AgentsState> = MutableStateFlow(AgentsState.Loading)
    val uiState: MutableStateFlow<AgentsState> = _uiState

    init {
        getAgents()
    }

    fun onAction(action: Action) {
        when (action) {
            is Action.GetAgents -> getAgents()
        }
    }

    private fun getAgents() {
        viewModelScope.launch {
            runCatching {
                getAgentsUseCase()
            }.onSuccess { agents ->
                _uiState.value = AgentsState.GetAgents(agents)
            }.onFailure { error ->
                _uiState.value = AgentsState.Error(error.message ?: "Error getting agents")
            }
        }
    }
}

sealed interface Action {
    object GetAgents : Action
}

sealed interface AgentsState {
    object Loading : AgentsState
    data class GetAgents(val agents: List<Agent>) : AgentsState
    data class Error(val message: String) : AgentsState
}