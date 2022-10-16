package com.publicissapient.configinitializer.com.publicissapient.configinitializer.presentation.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import com.publicissapient.configinitializer.presentation.theme.Purple40
import com.publicissapient.configinitializer.presentation.theme.Purple80
import com.publicissapient.configinitializer.presentation.theme.configLauncherFonts
import com.publicissapient.configinitializer.repository.model.Component
import com.publicissapient.configinitializer.repository.model.Config
import com.publicissapient.configinitializer.repository.model.ConfigListItem
import com.publicissapient.configinitializer.repository.model.SubItem
import javax.inject.Inject

class HomeWidgets @Inject constructor() {

    val args = HashMap<String, Any>()

    @Composable
    internal fun ConfigAppHomeLayout(paddingValues: PaddingValues, listItems: Config?) {
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
        ) {
            listItems?.items?.forEach { component ->
                when(component.type) {
                    Component.SWITCH -> {
                        val switchList = mutableListOf<@Composable () -> Unit>()
                        component.subItems?.forEach { switchItem ->
                            args[switchItem.id] = switchItem.value
                            switchList.add { SwitchComponent(switchItem) }
                        }
                        CardContainer(component, switchList)
                    }
                    Component.PICKER -> {
                        args[component.id] = component.subItems?.firstOrNull()?.value ?: ""
                        CardContainer(component, listOf { DropDown(component) })
                    }
                    Component.EDITTEXT -> {
                        args[component.id] = ""
                        CardContainer(component, listOf { CustomValue(component) })
                    }
                    Component.UNDEFINED -> {
                        // no-op
                    }
                }
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Preview
    @Composable
    private fun SwitchComponent(
        @PreviewParameter(SampleSubItemProvider::class) component: SubItem
    ) {
        val checkedState = remember { mutableStateOf(component.value.toBoolean()) }
        val icon: (@Composable () -> Unit) = if (checkedState.value) {
            {
                Icon(
                    imageVector = Icons.Filled.Check,
                    contentDescription = null,
                    modifier = Modifier.size(SwitchDefaults.IconSize),
                )
            }
        } else {
            {
                Icon(
                    imageVector = Icons.Filled.Clear,
                    contentDescription = null,
                    modifier = Modifier.size(SwitchDefaults.IconSize),
                )
            }
        }
        return ListItem(
            headlineText = {
                Text(
                    text = component.title,
                )
            },
            supportingText = {
                if(component.subtext == null)
                    null
                else
                    Text(
                        text = component.subtext
                    )
            },
            trailingContent = {
                Switch(
                    checked = checkedState.value,
                    enabled = true,
                    onCheckedChange = {
                        checkedState.value = it
                        args[component.id] = checkedState.value
                    },
                    thumbContent = icon
                )
            }
        )
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    private fun CardContainer(component: ConfigListItem, composable: List<@Composable () -> Unit>) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp),
            elevation = CardDefaults.cardElevation(10.dp)
        ) {
            Column {
                ElevatedSuggestionChip(
                    modifier = Modifier.padding(12.dp, 12.dp, 12.dp, 0.dp),
                    label = { Text(text = component.title, fontFamily = configLauncherFonts) },
                    icon = { Icon(
                        Icons.Filled.Info,
                        contentDescription = "Localized description",
                        Modifier.size(AssistChipDefaults.IconSize)
                    )
                    },
                    onClick = {null}
                )
                composable.forEach {
                    it.invoke()
                }
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    private fun DropDown(component: ConfigListItem) {
        val options = component.subItems
        var expanded by remember { mutableStateOf(false) }
        var selectedOptionText by remember { mutableStateOf(options?.firstOrNull()?.title) }
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded }
        ) {
            ListItem(
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth(),
                headlineText = {
                    Text(
                        selectedOptionText ?: ""
                    )
                },
                trailingContent = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) }
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
            ) {
                options?.forEach { selectionOption ->
                    DropdownMenuItem(
                        text = { ListItem(
                            headlineText = {
                                Text(
                                    text = selectionOption.title,
                                )
                            },
                            supportingText = {
                                if(selectionOption.subtext == null)
                                    null
                                else
                                    Text(
                                        text = selectionOption.value
                                    )
                            } )},
                        onClick = {
                            selectedOptionText = selectionOption.title
                            expanded = false
                            args[component.id] = selectionOption.value
                        },
                        contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                    )
                }
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    private fun CustomValue(component: ConfigListItem) {
        var text by rememberSaveable { mutableStateOf("") }
        OutlinedTextField(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth(),
            value = text,
            onValueChange = {
                text = it
                args[component.id] = text
            },
            label = { Text(component.subtext ?: component.title) },
            trailingIcon = {
                IconButton(
                    content = {
                        Icon(
                            Icons.Filled.Clear,
                            contentDescription = "Clear Text"
                        )
                    },
                    onClick = {
                        text = ""
                    }
                )
            }
        )
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    internal fun ConfigAppTopBar(title: String) {
        TopAppBar(
            title = {
                Text(
                    color = Color.White,
                    text = title
                )
            },
            colors = TopAppBarDefaults
                .smallTopAppBarColors(containerColor = Purple40)
        )
    }

    @Composable
    internal fun ConfigAppBottomBar(
        launchButtonOnClick: () -> Unit,
        buttonText: String
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Purple40)
        ) {
            Button(
                colors = ButtonDefaults.buttonColors(Purple80),
                onClick = launchButtonOnClick,
                modifier = Modifier
                    .padding(12.dp)
                    .fillMaxWidth()
            ) {
                Text(text = buttonText)
            }
        }
    }
}

class SampleSubItemProvider: PreviewParameterProvider<SubItem> {
    override val values: Sequence<SubItem>
        get() = sequenceOf(SubItem("my_switch", "my switch", null, "true"))
}