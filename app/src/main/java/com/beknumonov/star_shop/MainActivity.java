package com.beknumonov.star_shop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;

import com.beknumonov.star_shop.base.BaseActivity;
import com.beknumonov.star_shop.databinding.ActivityMainBinding;
import com.beknumonov.star_shop.fragments.CartFragment;
import com.beknumonov.star_shop.fragments.HomeFragment;
import com.beknumonov.star_shop.fragments.ProductsFragment;
import com.beknumonov.star_shop.fragments.ProfileFragment;
import com.beknumonov.star_shop.utils.Tab;
import com.google.android.material.navigation.NavigationBarView;

/*
    1. Create component in xml.
        1. Add Toolbar in the top
        2. Add FragmentContainerView in the middle
        3. Add BottomNavigationView in the bottom.
    2. Create Fragments.
    3. Replace or (Show and Hide) Fragments.

*/


public class MainActivity extends BaseActivity<ActivityMainBinding> {

    private Tab currentTab;

    private HomeFragment homeFragment;
    private ProductsFragment productsFragment;
    private CartFragment cartFragment;
    private ProfileFragment profileFragment;

    @Override
    protected ActivityMainBinding inflateViewBinding(LayoutInflater inflater) {
        return ActivityMainBinding.inflate(inflater);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        currentTab = Tab.HOME;
        onNavigationTabSelected(Tab.HOME);
        binding.bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.home)
                    onNavigationTabSelected(Tab.HOME);
                else if (item.getItemId() == R.id.product)
                    onNavigationTabSelected(Tab.PRODUCTS);
                else if (item.getItemId() == R.id.cart)
                    onNavigationTabSelected(Tab.CART);
                else if (item.getItemId() == R.id.profile)
                    onNavigationTabSelected(Tab.PROFILE);

                return true;
            }
        });
    }

    private void onNavigationTabSelected(Tab tab) {

        Fragment fragment = getSupportFragmentManager().findFragmentByTag(currentTab.getTag());
        if (fragment != null)
            getSupportFragmentManager().beginTransaction().hide(fragment).commit();


        currentTab = tab;



        switch (tab) {
            case HOME:
                if (homeFragment == null) {
                    homeFragment = new HomeFragment();
                    getSupportFragmentManager().beginTransaction().add(R.id.fragment_container_view, homeFragment, "HOME").commit();
                } else {
                    getSupportFragmentManager().beginTransaction().show(homeFragment).commit();
                }

                binding.tvPageTitle.setText("Home");
                break;
            case PRODUCTS:
                if (productsFragment == null) {
                    productsFragment = new ProductsFragment();
                    getSupportFragmentManager().beginTransaction().add(R.id.fragment_container_view, productsFragment, tab.getTag()).commit();
                } else {
                    getSupportFragmentManager().beginTransaction().show(productsFragment).commit();
                }
                binding.tvPageTitle.setText("Products");
                break;

            case CART:
                if (cartFragment == null) {
                    cartFragment = new CartFragment();
                    getSupportFragmentManager().beginTransaction().add(R.id.fragment_container_view, cartFragment, tab.getTag()).commit();
                } else {
                    getSupportFragmentManager().beginTransaction().show(cartFragment).commit();
                }
                binding.tvPageTitle.setText("Cart");
                break;

            case PROFILE:
                if (profileFragment == null) {
                    profileFragment = new ProfileFragment();
                    getSupportFragmentManager().beginTransaction().add(R.id.fragment_container_view, profileFragment, tab.getTag()).commit();
                } else {
                    getSupportFragmentManager().beginTransaction().show(profileFragment).commit();
                }
                binding.tvPageTitle.setText("Profile");
                break;

        }


    }
}