package com.cokimutai.hoteliapp.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.cokimutai.hoteliapp.domain.BannerModel
import com.cokimutai.hoteliapp.domain.CategoryModel
import com.cokimutai.hoteliapp.domain.FoodModel
import com.cokimutai.hoteliapp.repository.MainRepository


class MainViewModel : ViewModel() {
    private val repository = MainRepository()

    val banners: LiveData<List<BannerModel>> = repository.loadBanner()
    val categories: LiveData<List<CategoryModel>> = repository.loadCategory()

    private val _selectedCategoryId = MutableLiveData<String>()
    // Loading state
    private val _isLoadingFiltered = MutableLiveData(false)
    val isLoadingFiltered: LiveData<Boolean> = _isLoadingFiltered

    val filteredFoods: LiveData<List<FoodModel>> = _selectedCategoryId.switchMap { categoryId ->
        _isLoadingFiltered.value = true
        repository.loadFiltered(categoryId).also {
            // This will update when data is received
            it.observeForever { foods ->
                if (foods.isNotEmpty()) {
                    _isLoadingFiltered.value = false
                }
            }
        }
    }

    fun selectCategory(categoryId: String) {
        if (_selectedCategoryId.value != categoryId) {
            _isLoadingFiltered.value = true
            _selectedCategoryId.value = categoryId
        }
    }
}
