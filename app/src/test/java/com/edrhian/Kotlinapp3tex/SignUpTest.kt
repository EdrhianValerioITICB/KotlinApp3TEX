
import com.edrhian.kotlinapp3tex.SignUp
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mock

//@RunWith(AndroidJUnit4::class)
class SignUpTest {

    @Mock
    private lateinit var signUp: SignUp

    @Before
    fun setup() {
        signUp = SignUp()
    }

    @Test
    fun testIsValidMail_ValidEmail_ReturnsTrue() {
        val validEmails = listOf("test@example.com", "user123@gmail.com", "john.doe@hotmail.com")

        for (email in validEmails) {
            assertTrue(signUp.isValidMail(email))
        }
    }

    @Test
    fun testIsValidMail_InvalidEmail_ReturnsFalse() {
        val invalidEmails = listOf("invalidemail", "user@domain", "user@domain.", "user@.com")

        for (email in invalidEmails) {
            assertFalse(signUp.isValidMail(email))
        }
    }

    @Test
    fun testIsValidPassword_ValidPassword_ReturnsTrue() {
        val validPasswords = listOf("Password123456", "MySecret123456")

        for (password in validPasswords) {
            assertTrue(signUp.isValidPassword(password))
        }
    }

    @Test
    fun testIsValidPassword_InvalidPassword_ReturnsFalse() {
        val invalidPasswords = listOf("weakpassword", "12345678", "password", "Short1", "NoNumbers")

        for (password in invalidPasswords) {
            assertFalse(signUp.isValidPassword(password))
        }
    }
}
