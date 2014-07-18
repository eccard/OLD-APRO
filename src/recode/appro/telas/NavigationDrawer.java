package recode.appro.telas;

import android.app.ActionBar;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;

public class NavigationDrawer extends FragmentActivity implements
        OnGroupClickListener, OnChildClickListener {

    private DrawerLayout mDrawerLayout;
    private ExpandableListView mDrawerList;
    private static ActionBarDrawerToggle mDrawerToggle;
    private CharSequence mDrawerTitle;
    private CharSequence mSubTitle;
    private CharSequence mTitle = "Notícias";
    private AdapterDrawerItem adapterCustomDrawer;
    public static int codigoCurso;
    public static int codigoDepartamento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_navigation_drawer);

        // Inicializando itens do Navigation Drawer
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ExpandableListView) findViewById(R.id.left_drawer);

        adapterCustomDrawer = new AdapterDrawerItem(this);

        mDrawerList.setAdapter(adapterCustomDrawer);
        mDrawerList.setOnGroupClickListener(this);
        mDrawerList.setOnChildClickListener(this);

        getActionBar().setDisplayHomeAsUpEnabled(true);

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                R.drawable.ic_navigation_drawer, R.string.drawer_open,
                R.string.drawer_close) {

            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                getActionBar().setTitle(mTitle);
                invalidateOptionsMenu(); // creates call to
                // onPrepareOptionsMenu()
            }

            public void onDrawerOpened(View drawerView) {
                getActionBar().setTitle("PUROmobile");
                invalidateOptionsMenu(); // creates call to
                // onPrepareOptionsMenu()

            }
        };

        mDrawerLayout.setDrawerListener(mDrawerToggle);

        Fragment fragment = null;

        if (savedInstanceState == null) {

            fragment = new FragmentNoticias();
            getActionBar().removeAllTabs();
            getActionBar()
                    .setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        }

        android.support.v4.app.FragmentManager frgManager = getSupportFragmentManager();
        frgManager.beginTransaction().replace(R.id.content_frame, fragment)
                .commit();
        mDrawerLayout.closeDrawer(mDrawerList);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggles
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return false;
    }

    @Override
    public boolean onChildClick(ExpandableListView parent, View v,
                                int groupPosition, int childPosition, long id) {

        // Bundle bundle;
        Fragment fragment = null;
        android.support.v4.app.FragmentManager frgManager;

        switch (groupPosition) {
            case 2:

                getActionBar().removeAllTabs();
                getActionBar()
                        .setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);

                switch (childPosition) {

                    case 0:

                        // bundle = new Bundle();
                        // bundle.putInt("codigoCurso", 1);
                        //
                        mTitle = "Ciência da Computação";
                        getActionBar().setTitle(mTitle);
                        getActionBar().setSubtitle("Cursos");

                        NavigationDrawer.codigoCurso = 1;
                        fragment = new FragmentCursoMain();

                        // fragment.setArguments(bundle);

                        frgManager = getSupportFragmentManager();
                        frgManager.beginTransaction()
                                .replace(R.id.content_frame, fragment).commit();

                        mDrawerLayout.closeDrawer(mDrawerList);
                        break;

                    case 1:

                        // bundle = new Bundle();
                        // bundle.putInt("codigoCurso", 6);
                        mTitle = "Enfermagem";
                        getActionBar().setTitle(mTitle);
                        getActionBar().setSubtitle("Cursos");

                        NavigationDrawer.codigoCurso = 6;
                        fragment = new FragmentCursoMain();

                        // fragment.setArguments(bundle);

                        frgManager = getSupportFragmentManager();
                        frgManager.beginTransaction()
                                .replace(R.id.content_frame, fragment).commit();

                        mDrawerLayout.closeDrawer(mDrawerList);
                        break;

                    case 2:

                        mTitle = "Engenharia de Produção";
                        getActionBar().setTitle(mTitle);
                        getActionBar().setSubtitle("Cursos");

                        NavigationDrawer.codigoCurso = 3;
                        fragment = new FragmentCursoMain();
                        // fragment.setArguments(bundle);

                        frgManager = getSupportFragmentManager();
                        frgManager.beginTransaction()
                                .replace(R.id.content_frame, fragment).commit();

                        mDrawerLayout.closeDrawer(mDrawerList);
                        break;

                    case 3:

                        mTitle = "Produção Cultural";
                        getActionBar().setTitle(mTitle);
                        getActionBar().setSubtitle("Cursos");

                        NavigationDrawer.codigoCurso = 2;
                        fragment = new FragmentCursoMain();
                        // fragment.setArguments(bundle);

                        frgManager = getSupportFragmentManager();
                        frgManager.beginTransaction()
                                .replace(R.id.content_frame, fragment).commit();

                        mDrawerLayout.closeDrawer(mDrawerList);
                        break;

                    case 4:

                        mTitle = "Psicologia";
                        getActionBar().setTitle(mTitle);
                        getActionBar().setSubtitle("Cursos");

                        NavigationDrawer.codigoCurso = 5;
                        fragment = new FragmentCursoMain();
                        // fragment.setArguments(bundle);

                        frgManager = getSupportFragmentManager();
                        frgManager.beginTransaction()
                                .replace(R.id.content_frame, fragment).commit();

                        mDrawerLayout.closeDrawer(mDrawerList);
                        break;

                    case 5:

                        mTitle = "Serviço Social";
                        getActionBar().setTitle(mTitle);
                        getActionBar().setSubtitle("Cursos");

                        NavigationDrawer.codigoCurso = 4;
                        fragment = new FragmentCursoMain();
                        // fragment.setArguments(bundle);

                        frgManager = getSupportFragmentManager();
                        frgManager.beginTransaction()
                                .replace(R.id.content_frame, fragment).commit();

                        mDrawerLayout.closeDrawer(mDrawerList);
                        break;

                    default:
                        break;
                }

                break;

            case 3:

                getActionBar().removeAllTabs();
                getActionBar()
                        .setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);

                switch (childPosition) {

                    case 0:

                        mTitle = "ICT";
                        getActionBar().setTitle(mTitle);
                        getActionBar().setSubtitle("Departamentos");

                        NavigationDrawer.codigoDepartamento = 4;
                        fragment = new FragmentDepartamentoMain();

                        frgManager = getSupportFragmentManager();
                        frgManager.beginTransaction()
                                .replace(R.id.content_frame, fragment).commit();

                        mDrawerLayout.closeDrawer(mDrawerList);
                        break;

                    case 1:

                        mTitle = "IHS";
                        getActionBar().setTitle(mTitle);
                        getActionBar().setSubtitle("Departamentos");

                        NavigationDrawer.codigoDepartamento = 3;
                        fragment = new FragmentDepartamentoMain();

                        frgManager = getSupportFragmentManager();
                        frgManager.beginTransaction()
                                .replace(R.id.content_frame, fragment).commit();

                        mDrawerLayout.closeDrawer(mDrawerList);
                        break;

                    case 2:

                        mTitle = "RAE";
                        getActionBar().setTitle(mTitle);
                        getActionBar().setSubtitle("Departamentos");

                        NavigationDrawer.codigoDepartamento = 5;
                        fragment = new FragmentDepartamentoMain();

                        frgManager = getSupportFragmentManager();
                        frgManager.beginTransaction()
                                .replace(R.id.content_frame, fragment).commit();

                        mDrawerLayout.closeDrawer(mDrawerList);
                        break;

                    case 3:

                        mTitle = "RCT";
                        getActionBar().setTitle(mTitle);
                        getActionBar().setSubtitle("Departamentos");

                        NavigationDrawer.codigoDepartamento = 2;
                        fragment = new FragmentDepartamentoMain();

                        frgManager = getSupportFragmentManager();
                        frgManager.beginTransaction()
                                .replace(R.id.content_frame, fragment).commit();

                        mDrawerLayout.closeDrawer(mDrawerList);
                        break;

                    case 4:

                        mTitle = "RFM";
                        getActionBar().setTitle(mTitle);
                        getActionBar().setSubtitle("Departamentos");

                        NavigationDrawer.codigoDepartamento = 1;
                        fragment = new FragmentDepartamentoMain();

                        frgManager = getSupportFragmentManager();
                        frgManager.beginTransaction()
                                .replace(R.id.content_frame, fragment).commit();

                        mDrawerLayout.closeDrawer(mDrawerList);
                        break;

                    case 5:

                        mTitle = "RIR";
                        getActionBar().setTitle(mTitle);
                        getActionBar().setSubtitle("Departamentos");

                        NavigationDrawer.codigoDepartamento = 6;
                        fragment = new FragmentDepartamentoMain();

                        frgManager = getSupportFragmentManager();
                        frgManager.beginTransaction()
                                .replace(R.id.content_frame, fragment).commit();

                        mDrawerLayout.closeDrawer(mDrawerList);
                        break;

                }

            default:
                break;
        }

        return false;
    }

    @Override
    public boolean onGroupClick(ExpandableListView parent, View v,
                                int groupPosition, long id) {

        Fragment fragment = null;
        android.support.v4.app.FragmentManager frgManager;

        switch (groupPosition) {

            case 0:

                mTitle = "O Pólo";
                getActionBar().setTitle(mTitle);

                getActionBar().removeAllTabs();
                getActionBar()
                        .setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);

                fragment = new FragmentUniversidadeInfo();
                frgManager = getSupportFragmentManager();
                frgManager.beginTransaction().replace(R.id.content_frame, fragment)
                        .commit();
                mDrawerLayout.closeDrawer(mDrawerList);
                break;

            case 1:

                mTitle = "Calendários";
                getActionBar().setTitle(mTitle);

                getActionBar().removeAllTabs();
                getActionBar()
                        .setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);

                fragment = new FragmentCalendario();
                frgManager = getSupportFragmentManager();
                frgManager.beginTransaction().replace(R.id.content_frame, fragment)
                        .commit();
                mDrawerLayout.closeDrawer(mDrawerList);
                break;

            case 4:

                mTitle = "Notícias";
                getActionBar().setTitle(mTitle);

                fragment = new FragmentNoticias();
                getActionBar().removeAllTabs();
                getActionBar()
                        .setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);

                frgManager = getSupportFragmentManager();
                frgManager.beginTransaction().replace(R.id.content_frame, fragment)
                        .commit();
                mDrawerLayout.closeDrawer(mDrawerList);

                break;

            case 5:

                mTitle = "Professores";
                getActionBar().setTitle(mTitle);

                fragment = new FragmentProfessores();
                getActionBar().removeAllTabs();
                getActionBar()
                        .setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);

                frgManager = getSupportFragmentManager();
                frgManager.beginTransaction().replace(R.id.content_frame, fragment)
                        .commit();
                mDrawerLayout.closeDrawer(mDrawerList);

                break;
            case 6:

                mTitle = "Eventos";
                getActionBar().setTitle(mTitle);

                fragment = new FragmentEventos();
                getActionBar().removeAllTabs();
                getActionBar()
                        .setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);

                frgManager = getSupportFragmentManager();
                frgManager.beginTransaction().replace(R.id.content_frame, fragment)
                        .commit();
                mDrawerLayout.closeDrawer(mDrawerList);

                break;


            default:
                break;
        }

        return false;
    }
}
