package com.beknumonov.star_shop.fragments;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.viewpager2.widget.ViewPager2;

import com.beknumonov.star_shop.adapter.BannerPagerAdapter;
import com.beknumonov.star_shop.adapter.IndicatorAdapter;
import com.beknumonov.star_shop.base.BaseFragment;
import com.beknumonov.star_shop.base.RequestCallback;
import com.beknumonov.star_shop.databinding.FragmentHomeBinding;
import com.beknumonov.star_shop.model.Banner;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Response;

public class HomeFragment extends BaseFragment<FragmentHomeBinding> {

    private ArrayList<Banner> bannerArrayList;
    private BannerPagerAdapter bannerPagerAdapter;
    private IndicatorAdapter indicatorAdapter;

    @Override
    protected FragmentHomeBinding inflateView(LayoutInflater inflater, ViewGroup parent, boolean toAttach) {
        return FragmentHomeBinding.inflate(inflater, parent, toAttach);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bannerArrayList = new ArrayList<>();
        bannerPagerAdapter = new BannerPagerAdapter(getActivity(), bannerArrayList);
        indicatorAdapter = new IndicatorAdapter(bannerArrayList.size());


    }

    @Override
    public void onStart() {
        super.onStart();
        loadBanners();
    }

    private void loadBanners() {

        Call<ArrayList<Banner>> call = parent.mainApi.getBanners();

        call.enqueue(new RequestCallback<ArrayList<Banner>>() {
            @Override
            protected void onResponseSuccess(Call<ArrayList<Banner>> call, Response<ArrayList<Banner>> response) {
                bannerArrayList.addAll(response.body());
                bannerPagerAdapter.notifyDataSetChanged();
                //indicator size change
                indicatorAdapter.setBannerListSize(bannerArrayList.size());
                indicatorAdapter.notifyDataSetChanged();
            }

            @Override
            protected void onResponseFailed(Call<ArrayList<Banner>> call, Throwable t) {

            }
        });

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.bannerViewPager.setAdapter(bannerPagerAdapter);
        binding.indicatorRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        binding.indicatorRecyclerView.setAdapter(indicatorAdapter);


        binding.bannerViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                indicatorAdapter.setSelectedPosition(position);
                indicatorAdapter.notifyDataSetChanged();
            }
        });


        new CountDownTimer(30000, 3000) {

            public void onTick(long millisUntilFinished) {

                // logic to set the EditText could go here
                int currentPosition = binding.bannerViewPager.getCurrentItem();
                currentPosition++;
                if (currentPosition < bannerArrayList.size())
                    binding.bannerViewPager.setCurrentItem(currentPosition);
                else
                    binding.bannerViewPager.setCurrentItem(0);

            }

            public void onFinish() {

            }

        }.start();


    }
}
