package com.project.pradyotprakash.rental.app.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.project.pradyotprakash.rental.app.composables.imagePicker.ImagePicker
import com.project.pradyotprakash.rental.app.composables.imagePicker.ImagePickerType
import com.project.pradyotprakash.rental.core.models.ComposeType
import com.project.pradyotprakash.rental.core.models.FieldStates

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FieldComposable(
    paddingValues: PaddingValues = PaddingValues(all = 0.dp),
    fields: State<List<FieldStates>>,
    onValueChange: (String, Int) -> Unit = { _, _ -> },
    onSelected: (Int, String) -> Unit = { _, _ -> },
) {
    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        contentPadding = PaddingValues(
            top = paddingValues.calculateTopPadding(),
            bottom = paddingValues.calculateBottomPadding(),
        )
    ) {
        items(fields.value.size) { index ->
            val field = fields.value[index]
            val filedValue = field.value.observeAsState("")
            val isSelectedValue = field.isSelected.observeAsState(false)

            when (field.composeType) {
                ComposeType.OutlinedTextField -> {
                    OutlinedTextField(
                        value = filedValue.value,
                        onValueChange = {
                            if (field.maxChar == -1) {
                                onValueChange(it, index)
                            } else {
                                if (it.length <= field.maxChar) {
                                    onValueChange(it, index)
                                }
                            }
                        },
                        label = { Text(field.label) },
                        readOnly = field.readOnly,
                        keyboardOptions = field.keyboardOptions,
                        visualTransformation = field.visualTransformation,
                        keyboardActions = field.keyboardActions,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 15.dp)
                    )
                }
                ComposeType.RadioGroup -> {
                    Card(
                        modifier = Modifier
                            .padding(horizontal = 15.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .padding(all = 10.dp)
                        ) {
                            if (field.children.isNotEmpty()) {
                                Text(
                                    field.label,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                )
                            }

                            field.children.forEach { child ->
                                when (child.composeType) {
                                    ComposeType.RadioButton -> {
                                        Row(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .clickable {
                                                    onSelected(index, child.id)
                                                },
                                            verticalAlignment = Alignment.CenterVertically,
                                        ) {
                                            RadioButton(
                                                selected = filedValue.value == child.id,
                                                onClick = {
                                                    onSelected(index, child.id)
                                                },
                                            )
                                            Text(text = child.label)
                                        }
                                    }
                                    else -> {}
                                }
                            }
                        }
                    }
                }
                ComposeType.Switch -> {
                    Card(
                        modifier = Modifier
                            .padding(horizontal = 15.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 15.dp),
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Text(text = field.label)
                            Spacer(modifier = Modifier.weight(1f))
                            Switch(
                                checked = isSelectedValue.value,
                                onCheckedChange = {
                                    onSelected(index, "")
                                },
                            )
                        }
                    }
                }
                ComposeType.MultipleImagePicker -> {
                    Card(
                        modifier = Modifier
                            .padding(horizontal = 15.dp)
                            .fillMaxWidth()
                    ) {
                        ImagePicker(
                            imagePickerViewModel = hiltViewModel(),
                            imagePickerType = ImagePickerType.MultipleImagePicker,
                            field = field,
                        )
                    }
                }
                ComposeType.RadioButton -> {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                onSelected(index, field.id)
                            },
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        RadioButton(
                            selected = filedValue.value == field.id,
                            onClick = {
                                onSelected(index, field.id)
                            },
                        )
                        Text(text = field.label)
                    }
                }
                else -> {}
            }
            Spacer(modifier = Modifier.height(10.dp))
        }

        item {
            Spacer(modifier = Modifier.height(30.dp))
        }
    }
}