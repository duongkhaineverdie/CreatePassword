package com.thucthantinh.createpassword.presentation.ui.home

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.thucthantinh.createpassword.R
import com.thucthantinh.createpassword.presentation.ui.theme.CreatePasswordTheme
import com.thucthantinh.createpassword.utils.Constants
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(navController: NavHostController) {
    val homeViewModel: HomeViewModel = koinViewModel()
    val uiState by homeViewModel.uiState.collectAsState()

    val context = LocalContext.current
    HomeScreen(
        modifier = Modifier.fillMaxSize(),
        onValueLengthChange = homeViewModel::onValueChangeLength,
        onClickGeneratePassword = homeViewModel::generatePassword,
        actionIncludeDigits = homeViewModel::actionIncludeDigits,
        actionIncludeUppercase = homeViewModel::actionIncludeUppercase,
        actionIncludeLowercase = homeViewModel::actionIncludeLowercase,
        actionIncludeSpecialChars = homeViewModel::actionIncludeSpecialChars,
        includeLowercase = uiState.includeLowercase,
        includeUppercase = uiState.includeUppercase,
        includeDigits = uiState.includeDigits,
        includeSpecialChars = uiState.includeSpecialChars,
        lengthPassword = uiState.passwordLength?.toString() ?: "",
        isErrorField = uiState.isErrorInput,
        generatedPassword = uiState.passwordGenerated,
        onClickCopy = {
            Constants.copyToClipboard(it, context)
            Toast.makeText(context, "Copied!", Toast.LENGTH_SHORT).show()
        },
        onClickSavePassword = homeViewModel::savePassword,
        passwords = uiState.passwords,
    )
}

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    lengthPassword: String = "",
    onClickGeneratePassword: () -> Unit,
    onValueLengthChange: (String) -> Unit,
    actionIncludeLowercase: (Boolean) -> Unit,
    actionIncludeUppercase: (Boolean) -> Unit,
    actionIncludeDigits: (Boolean) -> Unit,
    actionIncludeSpecialChars: (Boolean) -> Unit,
    includeLowercase: Boolean = true,
    includeUppercase: Boolean = true,
    includeDigits: Boolean = true,
    includeSpecialChars: Boolean = true,
    isErrorField: Boolean = true,
    generatedPassword: String = "",
    onClickCopy: (String) -> Unit,
    onClickSavePassword: (String) -> Unit,
    passwords: MutableList<String> = arrayListOf()
) {
    val localFocusManager = LocalFocusManager.current
    Column(
        modifier
            .systemBarsPadding()
            .background(Color.Gray.copy(0.2f))
            .pointerInput(Unit) {
                detectTapGestures(onTap = {
                    localFocusManager.clearFocus()
                })
            }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    vertical = 10.dp,
                    horizontal = 20.dp
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(id = R.string.app_name),
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

        }
        Spacer(modifier = Modifier.height(20.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp),
            verticalArrangement = Arrangement.spacedBy(5.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Row(modifier = Modifier.fillMaxWidth()) {

                OutlinedTextField(
                    value = lengthPassword,
                    onValueChange = onValueLengthChange,
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text(text = "Password length", fontSize = 16.sp) },
                    isError = isErrorField,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number
                    ),
                    supportingText = {
                        if (isErrorField) Text(
                            text = "Input 6 -> 16",
                            fontSize = 14.sp
                        )
                    }
                )
            }

            Column(
                modifier = Modifier.fillMaxWidth(),
            ) {

                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Lower Text",
                        style = MaterialTheme.typography.titleMedium
                    )
                    Checkbox(
                        checked = includeLowercase,
                        onCheckedChange = actionIncludeLowercase,
                    )
                }
                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Upper Text",
                        style = MaterialTheme.typography.titleMedium
                    )
                    Checkbox(
                        checked = includeUppercase,
                        onCheckedChange = actionIncludeUppercase,
                    )
                }

                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Number",
                        style = MaterialTheme.typography.titleMedium
                    )
                    Checkbox(
                        checked = includeDigits,
                        onCheckedChange = actionIncludeDigits,
                    )
                }
                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Special Char",
                        style = MaterialTheme.typography.titleMedium
                    )
                    Checkbox(
                        checked = includeSpecialChars,
                        onCheckedChange = actionIncludeSpecialChars,
                    )
                }
            }

            Button(onClick = onClickGeneratePassword) {
                Icon(Icons.Filled.Check, contentDescription = "Generate Password")
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Generate Password")
            }

            if (generatedPassword.isNotBlank()) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(IntrinsicSize.Min)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Button(
                            modifier = Modifier
                                .fillMaxHeight(),
                            shape = RoundedCornerShape(0.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.Gray.copy(0.2f),
                                contentColor = Color.Black
                            ),
                            contentPadding = PaddingValues(),
                            onClick = { onClickSavePassword(generatedPassword) }) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_download),
                                contentDescription = null
                            )
                        }
                        // Display the generated password (consider security implications)
                        Text(
                            modifier = Modifier
                                .weight(1f)
                                .padding(horizontal = 10.dp, vertical = 5.dp),
                            text = generatedPassword,
                            textAlign = TextAlign.Start,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis,
                            fontSize = 20.sp
                        )
                        Button(
                            modifier = Modifier
                                .fillMaxHeight(),
                            shape = RoundedCornerShape(0.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.Gray.copy(0.2f),
                                contentColor = Color.Black
                            ),
                            contentPadding = PaddingValues(),
                            onClick = { onClickCopy(generatedPassword) }) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_copy),
                                contentDescription = null
                            )
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .background(Color.Gray.copy(0.1f))
                .padding(bottom = 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier.padding(vertical = 5.dp),
                text = "Save passwords",
                style = MaterialTheme.typography.titleLarge
            )
            HorizontalDivider(
                color = Color.Gray.copy(0.5f)
            )
            if (passwords.isNotEmpty()) {
                LazyColumn {
                    items(passwords.reversed()) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(IntrinsicSize.Min),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                modifier = Modifier.padding(horizontal = 20.dp),
                                fontSize = 18.sp,
                                text = it
                            )
                            Button(
                                modifier = Modifier
                                    .fillMaxHeight(),
                                shape = RoundedCornerShape(0.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color.Gray.copy(0.2f),
                                    contentColor = Color.Black
                                ),
                                contentPadding = PaddingValues(),
                                onClick = { onClickCopy(generatedPassword) }) {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_copy),
                                    contentDescription = null
                                )
                            }
                        }
                        HorizontalDivider(
                            color = Color.Gray.copy(0.5f)
                        )
                    }
                }
            } else {
                Text(modifier = Modifier.padding(top = 20.dp), text = "Empty List")
            }
        }

    }


}

@Composable
@Preview(showSystemUi = true)
fun HomeScreenPreview() {
    CreatePasswordTheme {
        HomeScreen(
            modifier = Modifier.fillMaxSize(),
            onClickGeneratePassword = {/* no-op */ },
            onValueLengthChange = {/* no-op */ },
            actionIncludeDigits = {/* no-op */ },
            actionIncludeLowercase = {/* no-op */ },
            actionIncludeUppercase = {/* no-op */ },
            actionIncludeSpecialChars = {/* no-op */ },
            isErrorField = false,
            generatedPassword = "wwwwwwwwwwwwwwww",
            onClickCopy = {/* no-op */ },
            onClickSavePassword = {/* no-op */ },
            passwords = arrayListOf("iuoiet", "oiwjtwwwwww")
        )
    }
}