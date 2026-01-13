import org.example.feature.dog_flea.presentation.controller.DogFleaController
import org.example.feature.dog_flea.presentation.model.DogFleaModel
import org.example.feature.dog_flea.presentation.view.DogFleaView
import org.example.feature.game_of_life.presentation.controller.GameOfLifeController
import org.example.feature.game_of_life.presentation.model.GameOfLifeModel
import org.example.feature.game_of_life.presentation.view.GameOfLifeView
import org.example.feature.roulette.presentation.controller.RouletteController
import org.example.feature.roulette.presentation.model.RouletteModel
import org.example.feature.roulette.presentation.view.RouletteView
import feature.dfa.presentation.controller.DFAController
import feature.dfa.presentation.model.DFAModel
import feature.dfa.presentation.view.DFAVerifierView
import org.example.presentation.common.main_frame.controller.MainController
import org.example.presentation.common.main_frame.model.NavigationModel
import org.example.presentation.common.main_frame.view.MainFrame
import javax.swing.SwingUtilities

fun main() {
    SwingUtilities.invokeLater {
        //--- Main navigation ---
        val navigationModel = NavigationModel()
        val mainFrame = MainFrame()
        val mainController = MainController(navigationModel, mainFrame)
        mainController.init()

        //--- Game of Life ---
        val gameOfLifeModel = GameOfLifeModel()
        val gameOfLifeView = GameOfLifeView()
        GameOfLifeController(
            model = gameOfLifeModel,
            view = gameOfLifeView
        )

        //--- Dog Flea ---
        val dogFleaModel = DogFleaModel()
        val dogFleaView = DogFleaView()
        DogFleaController(
            model = dogFleaModel,
            view = dogFleaView,
        )

        //--- Roulette ---
        val rouletteModel = RouletteModel()
        val rouletteView = RouletteView()
        RouletteController(
            model = rouletteModel,
            view = rouletteView
        )

        //--- DFA ---
        val dfaModel = DFAModel()
        val dfaView = DFAVerifierView()
        DFAController(
            model = dfaModel,
            view = dfaView
        )

        //--- Connect features to the main frame ---
        mainController.addFeaturePanel(gameOfLifeView, "GameOfLife")
        mainController.addFeaturePanel(dogFleaView, "DogFlea")
        mainController.addFeaturePanel(rouletteView, "Roulette")
        mainController.addFeaturePanel(dfaView, "DFA")

        mainFrame.isVisible = true
    }
}
