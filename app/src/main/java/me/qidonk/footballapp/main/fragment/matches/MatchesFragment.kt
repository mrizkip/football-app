package me.qidonk.footballapp.main.fragment.matches


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.*
import kotlinx.android.synthetic.main.fragment_matches.*
import me.qidonk.footballapp.R
import me.qidonk.footballapp.main.adapter.MatchesViewPagerAdapter

class MatchesFragment : Fragment() {

    companion object {
        fun newInstance(): Fragment {
            return MatchesFragment()
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
