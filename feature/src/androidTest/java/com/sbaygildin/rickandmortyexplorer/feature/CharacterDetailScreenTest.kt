package com.sbaygildin.rickandmortyexplorer.feature

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.sbaygildin.rickandmortyexplorer.domain.model.RMCharacter
import com.sbaygildin.rickandmortyexplorer.feature.character.CharacterDetailScreen
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CharacterDetailScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun shouldDisplayCharacterDetails() {

        val mockCharacter = RMCharacter(
            id = 1,
            name = "Rick Sanchez",
            status = "Alive",
            species = "Human",
            type = "",
            gender = "Male",
            image = "",
            episode = emptyList(),
            locationName = "Earth (C-137)",
            locationUrl = ""
        )


        composeTestRule.setContent {
            CharacterDetailScreen(character = mockCharacter)
        }
        composeTestRule.onNodeWithText("Rick Sanchez").assertIsDisplayed()
        composeTestRule.onNodeWithText("Species: Human").assertIsDisplayed()
        composeTestRule.onNodeWithText("Gender: Male").assertIsDisplayed()
        composeTestRule.onNodeWithText("Location: Earth (C-137)").assertIsDisplayed()
    }
}