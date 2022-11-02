package com.project.pradyotprakash.rental.app.composables.field

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.BackdropScaffold
import androidx.compose.material.BackdropValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.rememberBackdropScaffoldState
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.project.pradyotprakash.rental.app.composables.NetworkImageComposable
import com.project.pradyotprakash.rental.app.composables.imagePicker.ImagePicker
import com.project.pradyotprakash.rental.app.composables.imagePicker.ImagePickerType
import com.project.pradyotprakash.rental.app.composables.location.LocationPicker
import com.project.pradyotprakash.rental.app.localization.TR
import com.project.pradyotprakash.rental.core.models.ComposeType
import com.project.pradyotprakash.rental.core.models.FieldStates
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun FieldComposable(
    fields: State<List<FieldStates>>,
    onValueChange: (Int, String) -> Unit = { _, _ -> },
    appBarText: @Composable () -> Unit = {},
    appBarNavigationIcon: ImageVector? = null,
    appBarNavigationIconAction: () -> Unit = {},
    bottomBar: @Composable () -> Unit = {},
) {
    var pageState by remember { mutableStateOf<FieldPageState>(FieldPageState.Normal) }
    val scaffoldState = rememberBackdropScaffoldState(
        BackdropValue.Revealed
    )
    val scope = rememberCoroutineScope()

    val openLocation: (state: FieldStates) -> Unit = { state ->
        pageState = FieldPageState.Location(state)
        scope.launch { scaffoldState.conceal() }
    }
    val closeSheet = {
        pageState = FieldPageState.Normal
        scope.launch { scaffoldState.reveal() }
    }

    BackdropScaffold(
        scaffoldState = scaffoldState,
        peekHeight = 0.dp,
        headerHeight = 0.dp,
        backLayerBackgroundColor = MaterialTheme.colorScheme.background,
        frontLayerBackgroundColor = MaterialTheme.colorScheme.background,
        gesturesEnabled = false,
        appBar = {
            TopAppBar(
                title = appBarText,
                navigationIcon = {
                    appBarNavigationIcon?.let {
                        IconButton(
                            onClick = appBarNavigationIconAction
                        ) {
                            Icon(
                                imageVector = appBarNavigationIcon,
                                contentDescription = appBarNavigationIcon.name,
                            )
                        }
                    }
                },
                colors = TopAppBarDefaults.smallTopAppBarColors()
            )
        },
        backLayerContent = {
            Box {
                LazyColumn(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(top = 10.dp)
                ) {
                    items(fields.value.size) { index ->
                        val field = fields.value[index]
                        val filedValue = field.value.observeAsState("")
                        val locationValue = field.locationDetails.observeAsState(null)
                        val isSelectedValue = field.isSelected.observeAsState(false)

                        when (field.composeType) {
                            ComposeType.OutlinedTextField -> {
                                OutlinedTextField(
                                    value = filedValue.value,
                                    onValueChange = {
                                        if (field.maxChar == -1) {
                                            onValueChange(index, it)
                                        } else {
                                            if (it.length <= field.maxChar) {
                                                onValueChange(index, it)
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
                                                                onValueChange(index, child.id)
                                                            },
                                                        verticalAlignment = Alignment.CenterVertically,
                                                    ) {
                                                        RadioButton(
                                                            selected = filedValue.value == child.id,
                                                            onClick = {
                                                                onValueChange(index, child.id)
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
                                                onValueChange(index, "")
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
                                        imagePickerType = ImagePickerType.MultipleImagePicker,
                                        field = field,
                                    )
                                }
                            }
                            ComposeType.SingleImagePicker -> {
                                Card(
                                    modifier = Modifier
                                        .padding(horizontal = 15.dp)
                                        .fillMaxWidth()
                                ) {
                                    ImagePicker(
                                        imagePickerType = ImagePickerType.SingleImagePicker,
                                        field = field,
                                    )
                                }
                            }
                            ComposeType.RadioButton -> {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clickable {
                                            onValueChange(index, field.id)
                                        },
                                    verticalAlignment = Alignment.CenterVertically,
                                ) {
                                    RadioButton(
                                        selected = filedValue.value == field.id,
                                        onClick = {
                                            onValueChange(index, field.id)
                                        },
                                    )
                                    Text(text = field.label)
                                }
                            }
                            ComposeType.ImagePreview -> {
                                NetworkImageComposable(
                                    imageUrl = filedValue.value,
                                    size = 100.dp,
                                    cornerSize = 10.dp,
                                    error = field.errorImageId,
                                )
                            }
                            ComposeType.LocationPicker -> {
                                Card(
                                    modifier = Modifier
                                        .padding(horizontal = 15.dp)
                                        .fillMaxWidth()
                                        .clickable {
                                            openLocation(field)
                                        }
                                ) {
                                    val locationName = locationValue.value?.display_name ?: ""

                                    Row(
                                        modifier = Modifier.padding(15.dp),
                                        verticalAlignment = Alignment.CenterVertically,
                                    ) {
                                        Column(
                                            modifier = Modifier.weight(1.0f)
                                        ) {
                                            Text(text = field.label)
                                            Spacer(modifier = Modifier.height(5.dp))
                                            Text(
                                                text = locationName.ifEmpty { TR.pleaseSearchLocation },
                                                style = MaterialTheme.typography.bodyMedium,
                                            )
                                        }
                                        Spacer(modifier = Modifier.width(5.dp))
                                        Icon(
                                            imageVector = Icons.Default.KeyboardArrowRight,
                                            contentDescription = Icons.Default.KeyboardArrowRight.name
                                        )
                                    }
                                }
                            }
                            else -> {}
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                    }

                    item {
                        Spacer(modifier = Modifier.height(50.dp))
                    }
                }
                Box(modifier = Modifier.align(Alignment.BottomCenter)) {
                    bottomBar()
                }
            }
        },
        frontLayerContent = {
            when (pageState) {
                is FieldPageState.Location -> {
                    (pageState as? FieldPageState.Location)?.fieldStates?.let { state ->
                        LocationPicker(
                            field = state,
                            closeAction = {
                                closeSheet()
                            }
                        )
                    }
                }
                else -> {}
            }
        },
    )

    BackHandler {
        if (scaffoldState.isConcealed) {
            closeSheet()
        } else {
            appBarNavigationIconAction()
        }
    }
}