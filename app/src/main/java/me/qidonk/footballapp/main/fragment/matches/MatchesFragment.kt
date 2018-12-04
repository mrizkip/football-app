package me.qidonk.footballapp.main.fragment.matches


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.support.v7.widget.SearchView
import android.view.*
import kotlinx.android.synthetic.main.fragment_matches.*
import me.qidonk.footballapp.R
import me.qidonk.footballapp.main.SearchMatchActivity
import me.qidonk.footballapp.main.adapter.MatchesViewPagerAdapter
import org.jetbrains.anko.startActivity

class MatchesFragment : Fragment() {

    companion object {
        fun newInstance(): Fragment {
            val fragment = MatchesFragment()
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_matches, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        setupViewPager(matches_viewPager)
        matches_tabLayout.setupWithViewPager(matches_viewPager)

    }

    private fun setupViewPager(matches_viewPager: ViewPager) {
        val adapter = MatchesViewPagerAdapter(childFragmentManager)
        val fragment1 = NextMatchFragment.newInstance()
        val fragment2 = LastMatchFragment.newInstance()

        adapter.addFragment(fragment1)
        adapter.addFragment(fragment2)

        matches_viewPager.adapter = adapter
    }


}
