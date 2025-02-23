package scisrc.mobiledev.ecommercelayout.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import scisrc.mobiledev.ecommercelayout.R
import scisrc.mobiledev.ecommercelayout.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // รับค่า menuId จาก arguments (ถ้ามี)
        val menuId = arguments?.getInt("menu_id") ?: R.id.nav_home
        updateContent(menuId)
    }

    private fun updateContent(menuId: Int) {
        binding.layoutHome.visibility = View.GONE
        binding.layoutProductList.visibility = View.GONE
        binding.layoutCart.visibility = View.GONE
        binding.layoutProfile.visibility = View.GONE
        binding.layoutWishlist.visibility = View.GONE

        when (menuId) {
            R.id.nav_home -> binding.layoutHome.visibility = View.VISIBLE
            R.id.nav_products -> binding.layoutProductList.visibility = View.VISIBLE
            R.id.nav_cart -> binding.layoutCart.visibility = View.VISIBLE
            R.id.nav_profile -> binding.layoutProfile.visibility = View.VISIBLE
            R.id.nav_favorites -> binding.layoutWishlist.visibility = View.VISIBLE
            else -> binding.layoutHome.visibility = View.VISIBLE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance(menuId: Int): HomeFragment {
            val fragment = HomeFragment()
            val args = Bundle()
            args.putInt("menu_id", menuId)
            fragment.arguments = args
            return fragment
        }
    }
}
