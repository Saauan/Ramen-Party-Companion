package saauan.ramenpartycompanion

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import saauan.ramenpartycompanion.databinding.FragmentResultBinding

class ResultFragment : Fragment() {
    private var _binding: FragmentResultBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var players: List<Player>
    private var multiplier: Double = 1.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            players =
                (it.getSerializable(ARG_PLAYERS) as ArrayList<*>?)?.filterIsInstance<Player>()
                    ?: emptyList()
            multiplier = it.getDouble(ARG_MULTIPLIER)
        }
        if (arguments == null) {
            players = emptyList()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentResultBinding.inflate(inflater, container, false)
        players.forEach {
            val playerText = TextView(context)
            playerText.text = "${it.name} : ${it.score * multiplier}"
            playerText.textSize = 30.0F
            binding.scoreLayout.addView(playerText)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.nextButton.setOnClickListener {
            findNavController().navigate(R.id.action_resultFragment_to_FirstFragment)
        }
    }
}