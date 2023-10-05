package exirium.pe.valorant.data.remote.response

data class CharacterDTO(
    val uuid: String,
    val displayName: String,
    val description: String,
    val developerName: String,
    val characterTags: List<String>?,
    val displayIcon: String,
    val displayIconSmall: String,
    val bustPortrait: String,
    val fullPortrait: String,
    val fullPortraitV2: String,
    val killfeedPortrait: String,
    val background: String,
    val backgroundGradientColors: List<String>,
    val assetPath: String,
    val isFullPortraitRightFacing: Boolean,
    val isPlayableCharacter: Boolean,
    val isAvailableForTest: Boolean,
    val isBaseContent: Boolean,
    val role: RoleDTO,
    val abilities: List<AbilityDTO>,
    val voiceLine: String?
)
