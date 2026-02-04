package com.cokimutai.hoteliapp.activity.dashboard

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.cokimutai.hoteliapp.activity.BaseActivity
import com.cokimutai.hoteliapp.domain.BannerModel
import com.cokimutai.hoteliapp.ui.theme.HoteliAppTheme
import com.cokimutai.hoteliapp.viewModel.MainViewModel

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MainScreen()
        }
    }
}

    @Composable
    fun MainScreen(
        viewModel: MainViewModel = viewModel()
    ) {
        val banners by viewModel.banners.observeAsState(emptyList())
        val categories by viewModel.categories.observeAsState(emptyList())
        val showBannerLoading = banners.isEmpty()
        val showCategoryLoading = banners.isEmpty()

    Scaffold(
        bottomBar = { HotBottomBar()}
         ) { paddingValues ->
         LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues = paddingValues)
            ){
             item { TopBar()}
             item { Banner(banners, showBannerLoading) }
             item { Search() }
             item { CategorySection(categories, showCategoryLoading) }

            }
         }
    }


