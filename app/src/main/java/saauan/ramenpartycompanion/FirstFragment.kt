package saauan.ramenpartycompanion

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.fragment.findNavController
import saauan.ramenpartycompanion.databinding.FragmentFirstBinding


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val playerButtons: MutableList<PlayerButton> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            addButton("Paul")
            addButton("Soufian")
            addButton("Zoé")
            addButton("Jo'")
            addButton("Tristan")
        }
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    fun addButton(name: String) {
        val fragmentManager: FragmentManager = childFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        val fragment = PlayerButton().apply {
            arguments = bundleOf(
                "player" to name,
            )
        }
        this.playerButtons.add(fragment)
        fragmentTransaction.add(R.id.button_layout, fragment)
        fragmentTransaction.commit()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonFirst.setOnClickListener {
            val newBundle = Bundle()
            newBundle.putSerializable("players", getAllPlayers() as ArrayList<Player>)
            findNavController().navigate(R.id.action_FirstFragment_to_preResultFragment, newBundle)
        }
        binding.buttonReset.setOnClickListener {
            val builder: AlertDialog.Builder = AlertDialog.Builder(context)
            builder.setCancelable(true)
            builder.setTitle("Êtes vous certains de vouloir faire cela ?")
            builder.setMessage("Les score seront tous remit à zéro. Cette opération ne peut pas être défaite.")
            builder.setPositiveButton("Confirmer") { _, _ ->
                this.playerButtons.forEach { it.resetScore() }
            }
            builder.setNegativeButton(
                android.R.string.cancel
            ) { _, _ -> }

            val dialog: AlertDialog = builder.create()
            dialog.show()
        }
    }

    fun getAllPlayers(): List<Player> {
        return playerButtons.map { playerFragmentToPlayer(it) }
    }

    fun playerFragmentToPlayer(fragment: PlayerButton): Player {
        return fragment.player!!
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}