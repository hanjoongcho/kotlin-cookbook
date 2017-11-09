package io.github.hanjoongcho.com.squareup.retrofit2

import org.junit.Assert.assertEquals
import org.junit.Test

class SimpleServiceTest {

    @Test fun getFirstContributorLoginValueTest() {
        assertEquals(getFirstContributorLoginValue("hanjoongcho", "aaf-easyphotomap"), "hanjoongcho")
    }

    @Test fun getLanguageTest() {
        assertEquals(getLanguage("hanjoongcho", "aaf-easyphotomap"), "Kotlin")
    }

}
