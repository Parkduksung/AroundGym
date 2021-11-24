package base

import androidx.constraintlayout.motion.utils.ViewState
import androidx.lifecycle.Observer
import org.mockito.Mock


abstract class ViewModelBaseTest : BaseTest() {

    @Mock
    lateinit var viewStateObserver: Observer<ViewState>
}