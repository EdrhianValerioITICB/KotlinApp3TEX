import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

// MenuViewModel.kt
class MenuViewModel : ViewModel() {

    private val _selectedOption = MutableLiveData<String>()
    val selectedOption: LiveData<String>
        get() = _selectedOption

    fun selectOption(option: String) {
        _selectedOption.value = option
    }
}
