package ktx.vis

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.backends.lwjgl.LwjglFiles
import com.badlogic.gdx.backends.lwjgl.LwjglNativesLoader
import com.badlogic.gdx.graphics.GL20
import com.kotcrab.vis.ui.VisUI
import com.nhaarman.mockito_kotlin.mock

/**
 * Tests that require to have mocked libGDX environment must inherit from this class.
 * @author Kotcrab
 */
abstract class NeedsLibGDX {
  private companion object {
    init {
      LwjglNativesLoader.load()

      Gdx.graphics = mock()
      Gdx.app = mock()
      Gdx.gl = mock<GL20>()
      Gdx.files = LwjglFiles()
      Gdx.gl20 = Gdx.gl

      // Includes a default skin filled with styles of all Scene2D widgets:
      VisUI.load()
    }
  }
}
