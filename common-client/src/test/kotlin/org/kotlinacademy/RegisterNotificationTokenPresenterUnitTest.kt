package org.kotlinacademy

import org.kotlinacademy.data.FirebaseTokenType
import org.kotlinacademy.presentation.notifications.RegisterNotificationTokenPresenter
import org.kotlinacademy.presentation.notifications.RegisterNotificationTokenView
import org.kotlinacademy.respositories.NotificationRepository
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class RegisterNotificationTokenPresenterUnitTest : BaseUnitTest() {

    @BeforeTest
    fun setUp() {
        overrideNotificationRepository { _, _ -> }
    }

    @JsName("tokenSendingTest")
    @Test
    fun `Correctly sends token`() {
        // Given
        val view = RegisterNotificationTokenView()
        overrideNotificationRepository { _, _ -> /* no-op */ }
        val presenter = RegisterNotificationTokenPresenter(view, FAKE_TOKEN_TYPE)
        // When
        presenter.onRefresh(FAKE_TOKEN)
        // Then
        assertEquals(0, view.loggedErrors.size)
    }

    @JsName("errorsLoggingTest")
    @Test
    fun `When repository returns error, it is logged`() {
        // Given
        val view = RegisterNotificationTokenView()
        overrideNotificationRepository { _, _ -> throw NORMAL_ERROR }
        val presenter = RegisterNotificationTokenPresenter(view, FAKE_TOKEN_TYPE)
        // When
        presenter.onRefresh(FAKE_TOKEN)
        // Then
        assertEquals(1, view.loggedErrors.size)
        assertEquals(NORMAL_ERROR, view.loggedErrors[0])
    }

    @JsName("tokenRegisteredSettingTest")
    @Test
    fun `It is known when token is correctly registered`() {
        // Given
        val view = RegisterNotificationTokenView()
        overrideNotificationRepository { _, _ -> /* no-op */ }
        val presenter = RegisterNotificationTokenPresenter(view, FAKE_TOKEN_TYPE)
        // When
        presenter.onRefresh(FAKE_TOKEN)
        // Then
        assertTrue(view.tokenRegistered)
    }

    private fun overrideNotificationRepository(onAddFeedback: (String, FirebaseTokenType) -> Unit) {
        NotificationRepository.mock = object : NotificationRepository {
            override suspend fun registerToken(token: String, type: FirebaseTokenType) {
                onAddFeedback(token, type)
            }
        }
    }

    private fun RegisterNotificationTokenView() = object : RegisterNotificationTokenView {
        var loggedErrors = listOf<Throwable>()
        var tokenRegistered = false

        override fun setTokenRegistered(token: String) {
            tokenRegistered = true
        }

        override fun logError(error: Throwable) {
            loggedErrors += error
        }
    }

    companion object {
        const val FAKE_TOKEN = "Token"
        val FAKE_TOKEN_TYPE = FirebaseTokenType.Android
        val NORMAL_ERROR = Throwable()
    }
}