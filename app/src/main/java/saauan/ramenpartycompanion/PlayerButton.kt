package saauan.ramenpartycompanion

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import saauan.ramenpartycompanion.databinding.FragmentPlayerButtonBinding


private const val ARG_PLAYER = "player"

/**
 * A simple [Fragment] subclass.
 * Use the [PlayerButton.newInstance] factory method to
 * create an instance of this fragment.
 */
class PlayerButton : Fragment() {
    private var _binding: FragmentPlayerButtonBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private var player: Player? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            player = Player(it.getString(ARG_PLAYER)!!)
        }
        println("Player : $player")
        println("Arguments : $arguments")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPlayerButtonBinding.inflate(inflater, container, false)
        binding.playerButton.text = player?.name
        updateScoreDisplay()

        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.playerButton.setOnClickListener {
            incrementScore()
        }
        binding.minusButton.setOnClickListener {
            decrementScore()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun incrementScore() {
        player?.incrementScore()
        updateScoreDisplay()
    }

    fun decrementScore() {
        if (player?.score!! > 0) {
            player?.decrementScore()
            updateScoreDisplay()
        }
    }

    fun resetScore() {
        player?.resetScore()
        updateScoreDisplay()
    }

    private fun updateScoreDisplay() {
        binding.currentScore.text = player?.score.toString().padStart(3, '0')
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @return A new instance of fragment PlayerButton.
         */
        @JvmStatic
        fun newInstance(param1: String) =
            PlayerButton().apply {
                arguments = Bundle().apply {
                    putString(ARG_PLAYER, param1)
                }
            }
    }
}