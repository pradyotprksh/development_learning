import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.ext.list.containingFiles
import com.lemonappdev.konsist.api.ext.list.print
import com.lemonappdev.konsist.api.verify.assertTrue
import org.junit.Test

class CodeRulesTest {
    @Test
    fun `test each features package will have a Router file`() {
        Konsist
            .scopeFromPackage("com.pradyotprakash.xfullstack.features..")
            .packages
            .filter { pkg ->
                pkg.name.count { it == '.' } == 4
            }
            .containingFiles
            .print { it.nameWithExtension }
            .assertTrue { it.nameWithExtension == "Router.kt" }
    }
}