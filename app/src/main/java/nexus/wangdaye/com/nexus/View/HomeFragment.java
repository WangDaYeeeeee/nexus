package nexus.wangdaye.com.nexus.View;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import nexus.wangdaye.com.nexus.Model.ThemeColor;
import nexus.wangdaye.com.nexus.R;
import nexus.wangdaye.com.nexus.Widget.ActionAppBarLayout;
import nexus.wangdaye.com.nexus.Adapter.ViewPagerAdapter;

/**
 * Home fragment
 * */

public class HomeFragment extends Fragment
        implements View.OnClickListener, Toolbar.OnMenuItemClickListener {
    // widget
    private ActionAppBarLayout appBarLayout;
    private Toolbar toolbar;
    private RecyclerView infoList;
    private RecyclerView groupList;

    // data

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        this.initWidget(view);
        return view;
    }

    private void initWidget(View view) {
        this.appBarLayout = (ActionAppBarLayout) view.findViewById(R.id.fragment_home_appBarLayout);

        this.toolbar = (Toolbar) view.findViewById(R.id.fragment_home_toolbar);
        toolbar.setTitle(getString(R.string.app_name));
        toolbar.setNavigationIcon(R.drawable.ic_burger_menu);
        toolbar.setNavigationOnClickListener(this);
        int[] colors = new ThemeColor(getActivity()).getColor();
        toolbar.setBackgroundColor(ContextCompat.getColor(getActivity(), colors[0]));
        toolbar.inflateMenu(R.menu.fragment_home_menu);
        toolbar.setOnMenuItemClickListener(this);

        this.initPage(view);

        this.infoList = (RecyclerView) view.findViewById(R.id.fragment_home_info_recyclerView);

        this.groupList = (RecyclerView) view.findViewById(R.id.fragment_home_group_recyclerView);
    }

    @SuppressLint("InflateParams")
    private void initPage(View view) {
        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.fragment_home_tabLayout);
        List<String> titleList = new ArrayList<>();
        titleList.add(getString(R.string.dynamic));
        titleList.add(getString(R.string.group));

        ViewPager viewPager = (ViewPager) view.findViewById(R.id.fragment_home_viewPager);
        LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
        View[] pages = new View[] {
                layoutInflater.inflate(R.layout.container_home_info, null),
                layoutInflater.inflate(R.layout.container_home_group, null)
        };
        List<View> pageList = new ArrayList<>();
        pageList.add(pages[0]);
        pageList.add(pages[1]);

        ViewPagerAdapter adapter = new ViewPagerAdapter(titleList, pageList);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        int[] colors = new ThemeColor(getActivity()).getColor();
        tabLayout.setBackgroundColor(ContextCompat.getColor(getActivity(), colors[0]));
        if (ContextCompat.getColor(getActivity(), colors[0]) == ContextCompat.getColor(getActivity(), colors[2])) {
            tabLayout.setSelectedTabIndicatorColor(ContextCompat.getColor(getActivity(), android.R.color.white));
        }
    }

    @Override
    public void onClick(View v) {
        int widgetId = v.getId();

        if (widgetId == -1) {
            DrawerLayout drawer = (DrawerLayout) getActivity().findViewById(R.id.activity_main_drawer_layout);
            drawer.openDrawer(GravityCompat.START);
        }
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        int itemId = item.getItemId();

        if (itemId == R.id.home_action_search) {
            return true;
        } else if (itemId == R.id.home_action_notice) {
            return true;
        } else {
            return false;
        }
    }
}
