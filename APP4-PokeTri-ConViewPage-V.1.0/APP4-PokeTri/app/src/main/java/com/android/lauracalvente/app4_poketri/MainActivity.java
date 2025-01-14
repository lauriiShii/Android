package com.android.lauracalvente.app4_poketri;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.android.lauracalvente.app4_poketri.Adapters.PagerAdapter;
import com.android.lauracalvente.app4_poketri.Utils.Constantes;
import com.android.lauracalvente.app4_poketri.Utils.KeyBoardUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnPageChange;
import butterknife.OnTouch;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.viewPage)
    public ViewPager viewPage;

    static public String namePlayer = "";

    static public FragmentManager gestor;
    PagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        gestor = getSupportFragmentManager();
        cargarPageView();
    }

    public void cargarPageView() {
        // Instantiate a ViewPager and a PagerAdapter.
        adapter = new PagerAdapter(gestor);
        viewPage.setAdapter(adapter);
        //Empezamos con el Fragment main
        viewPage.setCurrentItem(1);
    }

    //Para poder volver al fragmento principal desde el juego y las puntuaciones.
    //Hay que poner los tag cuando se cargan los otros fragmentos
    @Override
    public void onBackPressed() {
        if (gestor.findFragmentByTag(Constantes.TAG_FRAGMENT_MAIN) != null)
            super.onBackPressed();
        else {
            viewPage.setCurrentItem(1);
        }
    }

    @OnPageChange(R.id.viewPage)
    public void onPageSelected(int position) {
        KeyBoardUtils.hideOffKeyBoard(viewPage, this);

        //Si el name no esta relleno no puede pasar al joco
        if (namePlayer.equals("") && viewPage.getCurrentItem() == 2)
            viewPage.setCurrentItem(1);

    }

}
