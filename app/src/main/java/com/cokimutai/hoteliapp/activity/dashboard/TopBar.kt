package com.cokimutai.hoteliapp.activity.dashboard

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.cokimutai.hoteliapp.R

@Composable
@Preview
fun TopBar() {
    ConstraintLayout(
        modifier = Modifier
            .padding(WindowInsets.statusBars.asPaddingValues())
            .padding(top = 8.dp)
            .padding(horizontal = 16.dp)
            .fillMaxWidth()
    ){
        val (name, setting, notification) = createRefs()
        Image(painter = painterResource(R.drawable.settings_icon),
            contentDescription = null,
            modifier = Modifier
                .constrainAs(setting) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                }
                .clickable { }
        )
        Column(
            modifier = Modifier.constrainAs(name) {
                top.linkTo(parent.top)
                start.linkTo(setting.end)
                end.linkTo(notification.start)


            },
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            Text(
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(color = Color.Red)) {
                        append(
                            "SCRUMPTIOUS"
                        )
                    }

                    withStyle(style = SpanStyle(color = Color.Black)) {
                        append(
                            "FOOD"
                        )
                    }

                },
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
            Text(
                text = "Delicious to the core",
                color = Color.DarkGray,
                fontSize = 12.sp
            )
        }

        Image(
            painter = painterResource(R.drawable.bell_icon),
            contentDescription = null,
            modifier = Modifier
                .constrainAs(notification) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    end.linkTo(parent.end)
                }
                .clickable { }
        )


    }
}
