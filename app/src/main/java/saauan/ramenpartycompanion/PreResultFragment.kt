package saauan.ramenpartycompanion

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import saauan.ramenpartycompanion.databinding.FragmentPreResultBinding
import java.math.RoundingMode
import java.text.DecimalFormat

private const val ARG_PLAYERS = "players"

class PreResultFragment : Fragment() {

    private var _binding: FragmentPreResultBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var players: List<Player>
    private var currentMultiplier: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            players = (it.getSerializable(ARG_PLAYERS) as ArrayList<*>).filterIsInstance<Player>()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentPreResultBinding.inflate(inflater, container, false)
        changeMultiplier(0.0)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.preResultMinusButton.setOnClickListener {
            changeMultiplier(currentMultiplier - 1)
        }
        binding.preResultPlusButton.setOnClickListener {
            changeMultiplier(currentMultiplier + 1)
        }
        binding.nextButton.setOnClickListener {
            val newBundle = Bundle()
            newBundle.putSerializable("players", players as ArrayList<Player>)
            newBundle.putDouble("score_multiplier", currentMultiplier)
            findNavController().navigate(R.id.action_preResultFragment_to_resultFragment)
        }
    }

    private fun changeMultiplier(newMultiplier: Double) {
        val df = DecimalFormat("#.##")
        df.roundingMode = RoundingMode.CEILING
        currentMultiplier = newMultiplier
        binding.scoreMultiplier.setText(df.format(newMultiplier))
    }
}