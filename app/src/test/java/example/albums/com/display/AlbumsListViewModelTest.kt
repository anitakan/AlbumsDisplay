package example.albums.com.display

import android.content.Context
import com.android.volley.VolleyError
import com.nhaarman.mockito_kotlin.whenever
import junit.framework.TestCase.assertTrue
import org.junit.Assert.assertFalse
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Matchers
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import org.powermock.api.mockito.PowerMockito
import org.powermock.api.mockito.PowerMockito.whenNew
import org.powermock.core.classloader.annotations.PrepareForTest
import org.powermock.modules.junit4.PowerMockRunner

@RunWith(PowerMockRunner::class)
@PrepareForTest(VolleyStringRequest::class, Util::class)
class AlbumsListViewModelTest {
    private lateinit var subject: AlbumsListViewModel
    @Mock
    private lateinit var volleyStringRequestMock: VolleyStringRequest
    @Mock
    private lateinit var contextMock: Context
    @Mock
    private lateinit var callbackMock: AlbumsListViewModel.Callback
    @Mock
    private  lateinit var errorMock:VolleyError

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        PowerMockito.mock(VolleyStringRequest::class.java)
        whenever(errorMock.message).thenReturn("error")
        whenNew(VolleyStringRequest::class.java).withNoArguments().thenReturn(volleyStringRequestMock)
        subject = AlbumsListViewModel()
    }

    @Test
    internal fun itShouldMakeProgressToFalseOnErrorResponse() {
        subject.setCallback(callbackMock)

        subject.onErrorResponse(errorMock)

        assertFalse(subject.showProgress.get())
    }

    @Test
    fun itShouldMakeErrorFlagToTrue() {
        subject.setCallback(callbackMock)

        subject.onErrorResponse(errorMock)

        assertTrue(subject.showError.get())
        verify(callbackMock).updateErrorMessage(Matchers.anyString())
    }

    @Test
    fun itShouldUpdateUIOnResponseReceived() {
        subject.setCallback(callbackMock)

        subject.onResponse(emptyList())

        assertFalse(subject.showProgress.get())
        verify(callbackMock).updateUI(emptyList())
    }

}