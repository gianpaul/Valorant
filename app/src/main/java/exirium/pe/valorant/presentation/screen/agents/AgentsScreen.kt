package exirium.pe.valorant.presentation.screen.agents

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import exirium.pe.valorant.domain.model.Agent
import org.koin.androidx.compose.getViewModel
import kotlin.math.abs

@Composable
fun AgentsScreen(
    viewModel: AgentsViewModel = getViewModel()
) {
    val uiState by remember { viewModel.uiState }.collectAsState()

    when (uiState) {
        is AgentsState.Loading -> { /* TODO */
        }

        is AgentsState.GetAgents -> AgentList(agents = (uiState as AgentsState.GetAgents).agents)
        is AgentsState.Error -> { /* TODO */
        }
    }
}

@Composable
fun AgentList(agents: List<Agent>) {
    val listState = rememberLazyListState()
    val selectedAgent = remember { mutableStateOf(agents.first()) }
    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF1C252E))
    ) {
        val halfRowWidth = constraints.maxWidth / 2
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Spacer(modifier = Modifier.size(16.dp))

            Image(
                painter = rememberImagePainter("https://upload.wikimedia.org/wikipedia/commons/thumb/f/fc/Valorant_logo_-_pink_color_version.svg/2560px-Valorant_logo_-_pink_color_version.svg.png"),
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.15f)
            )
            Spacer(modifier = Modifier.size(16.dp))

            OutlinedText(
                text = "Choose your awesome agent",
                fontSize = 28,
                textColor = Color.White,
                outlineColor = Color.Black
            )

            Spacer(modifier = Modifier.size(16.dp))

            LazyRow(
                state = listState,
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp)
            ) {
                itemsIndexed(agents) { index, agent ->
                    val scale by remember {
                        derivedStateOf {
                            val currentItemInfo =
                                listState.layoutInfo.visibleItemsInfo.firstOrNull { it.index == index }
                                    ?: return@derivedStateOf 0.7f
                            if (currentItemInfo.offset + currentItemInfo.size / 2 >= halfRowWidth / 2 &&
                                currentItemInfo.offset + currentItemInfo.size / 2 <= 3 * halfRowWidth / 2) {
                                selectedAgent.value = agent
                            }
                            val itemHalfSize = currentItemInfo.size / 2
                            (1f - minOf(
                                1f,
                                abs(currentItemInfo.offset.toFloat() + itemHalfSize.toFloat() - halfRowWidth.toFloat()) / halfRowWidth.toFloat()
                            ) * 0.3f)
                        }
                    }
                    AgentCard(
                        agent, modifier = Modifier.graphicsLayer(
                            scaleX = scale, scaleY = scale
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.size(16.dp))

            Text(
                text = "Special Abilities", fontSize = 20.sp, color = Color.White
            )

            Spacer(modifier = Modifier.size(16.dp))

            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp)
            ) {
                items(selectedAgent.value.abilities) { abilityUrl ->
                    abilityUrl?.let {
                        AbilityCard(it)
                    }
                }
            }
        }
    }
}

@Composable
fun AbilityCard(abilityUrl: String) {
    Box(
        modifier = Modifier
            .size(50.dp)
            .background(Color(0xFF1C252E), shape = RoundedCornerShape(16.dp)),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = rememberImagePainter(abilityUrl),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.size(90.dp)
        )
    }
}

@Composable
fun AgentCard(agent: Agent, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .size(height = 400.dp, width = 250.dp)
            .background(Color(0xFFFF4654), shape = RoundedCornerShape(16.dp)),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = rememberImagePainter(agent.background),
            contentDescription = null,
            contentScale = ContentScale.FillHeight,
            modifier = Modifier.fillMaxWidth()
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = rememberImagePainter(agent.image),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(300.dp)
            )

            OutlinedText(
                text = agent.name,
                fontSize = 24,
                textColor = Color.White,
                outlineColor = Color.Black
            )
            OutlinedText(
                text = agent.role,
                fontSize = 16,
                textColor = Color.White,
                outlineColor = Color.Black
            )
        }
    }
}

@Composable
fun OutlinedText(text: String, fontSize: Int, textColor: Color, outlineColor: Color) {
    Box(contentAlignment = Alignment.Center) {
        Text(
            text = text,
            fontSize = fontSize.sp,
            color = outlineColor,
            modifier = Modifier.offset(1.dp, 1.dp)
        )
        Text(
            text = text,
            fontSize = fontSize.sp,
            color = outlineColor,
            modifier = Modifier.offset(- 1.dp, - 1.dp)
        )
        Text(
            text = text,
            fontSize = fontSize.sp,
            color = outlineColor,
            modifier = Modifier.offset(1.dp, - 1.dp)
        )
        Text(
            text = text,
            fontSize = fontSize.sp,
            color = outlineColor,
            modifier = Modifier.offset(- 1.dp, 1.dp)
        )

        // Texto principal
        Text(
            text = text, fontSize = fontSize.sp, color = textColor
        )
    }
}
