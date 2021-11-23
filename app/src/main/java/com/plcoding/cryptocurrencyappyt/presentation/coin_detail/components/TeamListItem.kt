package com.plcoding.cryptocurrencyappyt.presentation.coin_detail.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import com.plcoding.cryptocurrencyappyt.data.remote.dto.Team

@Composable
fun  TeamListItem(
    team: Team,
    modifier: Modifier
) {
    Column(
        modifier = Modifier,
        verticalArrangement = Arrangement.Center


    ) {
        Text(
            text = team.name,
            style = MaterialTheme.typography.h4
        )

        Text(
            text = team.position,
            style = MaterialTheme.typography.body2,
            fontStyle = FontStyle.Italic
        )
            }
}