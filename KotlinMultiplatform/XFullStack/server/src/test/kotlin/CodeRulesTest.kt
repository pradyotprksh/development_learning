import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.ext.list.containingFiles
import com.lemonappdev.konsist.api.ext.list.interfaces
import com.lemonappdev.konsist.api.ext.list.print
import com.lemonappdev.konsist.api.ext.provider.hasAnnotationOf
import com.lemonappdev.konsist.api.verify.assertTrue
import io.ktor.resources.Resource
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
            .assertTrue { it.nameWithExtension == "Router.kt" }
    }

    @Test
    fun `test each annotated resources should have Resource name at the end`() {
        Konsist
            .scopeFromProject()
            .classes()
            .filter { it.hasAnnotationOf<Resource>() }
            .assertTrue {
                it.name.endsWith("Resource")
            }
    }

    @Test
    fun `test each feature controller classes should have a main controller class`() {
        Konsist
            .scopeFromPackage("com.pradyotprakash.xfullstack.features..")
            .packages
            .filter { pkg ->
                pkg.name.count { it == '.' } == 5 && pkg.name.endsWith("controllers")
            }
            .containingFiles
            .assertTrue {
                it.name.endsWith("Controller")
            }
    }

    @Test
    fun `test each controller interface should have Controller name at the end`() {
        Konsist
            .scopeFromPackage("com.pradyotprakash.xfullstack.features..")
            .packages
            .filter { pkg ->
                pkg.name.count { it == '.' } == 5 && pkg.name.endsWith("controllers")
            }
            .containingFiles
            .interfaces()
            .assertTrue {
                it.name.endsWith("Controller")
            }
    }

    @Test
    fun `test each controller implementation should have ControllerImplementation name at the end`() {

    }
}