package com.beknumonov.star_shop.activities;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.beknumonov.star_shop.adapter.ProductPagerAdapter;
import com.beknumonov.star_shop.base.BaseActivity;
import com.beknumonov.star_shop.base.RequestCallback;
import com.beknumonov.star_shop.databinding.ActivityProductDetailsBinding;
import com.beknumonov.star_shop.dialog.MyDialogs;
import com.beknumonov.star_shop.dialog.OptionDialog;
import com.beknumonov.star_shop.model.CartRequest;
import com.beknumonov.star_shop.model.CartResponse;
import com.beknumonov.star_shop.model.ColorOption;
import com.beknumonov.star_shop.model.Option;
import com.beknumonov.star_shop.model.Product;
import com.beknumonov.star_shop.model.Image;
import com.beknumonov.star_shop.model.SizeOption;

import java.util.ArrayList;
import java.util.function.Consumer;

import retrofit2.Call;
import retrofit2.Response;



/*

    XML:
        ScrollView
            ViewPager Image Size 1:1
            TextView -Brand
            TextView -Product name minLine: 2
            LinearLayout : Horizontal
                TextView - Price ----- TextView Original Price
            Color Selection (TextView)
            Size Selection (TextView)
            Details: TextView (Multiline)
        LinearLayout - Bottom Panel (Horizontal)
            TextView : Add to Cart
            TextView : Buy now.

    Class:
        Get product_id from intent


*/


public class ProductDetailsActivity extends BaseActivity<ActivityProductDetailsBinding> {

    private Product product;

    private ProductPagerAdapter adapter;
    private ArrayList<Image> imageArrayList;
    private ColorOption selectedColorOption;
    private SizeOption selectedSizeOption;

    private ArrayList<SizeOption> filterSizeOptionArrayList = new ArrayList<>();

    @Override
    protected ActivityProductDetailsBinding inflateViewBinding(LayoutInflater inflater) {
        return ActivityProductDetailsBinding.inflate(inflater);
    }

    @Override
    protected boolean hasBackButton() {
        return true;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int productId = getIntent().getIntExtra("product_id", 0);
        imageArrayList = new ArrayList<>();
        adapter = new ProductPagerAdapter(this, imageArrayList);
        binding.productViewPager.setAdapter(adapter);
        if (productId != 0) {
            // load Product details
            loadProductDetails(productId);
        }


        binding.tvColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OptionDialog optionDialog = new OptionDialog();
                optionDialog.setSelectedColorOption(selectedColorOption);
                optionDialog.setColorOptionArrayList(product.getColorOptions());
                optionDialog.setOptionItemSelectedListener(new OptionDialog.OptionItemSelectListener() {
                    @Override
                    public void onColorItemSelected(ColorOption colorOption) {
                        // this will be invoked
                        optionDialog.dismiss();
                        selectedColorOption = colorOption;
                        binding.ivColorRight.setVisibility(View.GONE);
                        binding.tvSelectedColor.setVisibility(View.VISIBLE);
                        binding.tvSelectedColor.setText(colorOption.getTitle());
                        selectedSizeOption = null;


                        binding.tvSelectedSize.setVisibility(View.GONE);
                        binding.ivSizeRight.setVisibility(View.VISIBLE);

                        // filter size options with color.

                        // Product 1
                        /*
                         *  Color: Red, Blue, Grey
                         *  Size: M, L, XL
                         *  Options: 1. Red - M, 2. Red - L
                         *           3. Blue - L, 4. Blue -XL
                         *           5. Grey - M.
                         *
                         *  User -> Color: Red
                         *          Size: M, L
                         *
                         * */

                        filterSizeOptionArrayList.clear();
                        product.getOptionArrayList().forEach(new Consumer<Option>() {
                            @Override
                            public void accept(Option option) {
                                if (option.getColorOption().getId() == selectedColorOption.getId()) {
                                    filterSizeOptionArrayList.add(option.getSizeOption());
                                }
                            }
                        });


                    }

                    @Override
                    public void onSizeItemSelected(SizeOption sizeOption) {

                    }
                });

                optionDialog.show(getSupportFragmentManager(), "color_dialog");
            }
        });

        binding.plusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int value = Integer.parseInt(binding.tvQuantity.getText().toString());
                if (value < 10)
                    value++; // value=value+1

                binding.tvQuantity.setText(String.valueOf(value));

            }
        });
        binding.minusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int value = Integer.parseInt(binding.tvQuantity.getText().toString());
                if (value > 1)
                    value--; // value=value-1

                binding.tvQuantity.setText(String.valueOf(value));
            }
        });

        binding.addProductToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // product id
                // quantity
                // option id.

                // options:
                //    option 1: Navi, M.
                //    option 2: Red, L.
                //    option 3: Yellow, XL.

                int quantity = Integer.parseInt(binding.tvQuantity.getText().toString());

                // selectedColorOption, selectedSizeOption;

                if (selectedSizeOption == null || selectedColorOption == null) {
                    MyDialogs.showNoticeDialog(getSupportFragmentManager(), "Please select options first");
                    return;
                }


                Option selectedOption = null;
                for (Option option : product.getOptionArrayList()) {
                    if (option.getColorOption().getId() == selectedColorOption.getId() && option.getSizeOption().getId() == selectedSizeOption.getId()) {
                        selectedOption = option;
                    }
                }

                if (selectedOption != null) {

                    CartRequest cartRequest = new CartRequest(product.id, quantity, selectedOption.getId());

                    Call<CartResponse> call = mainApi.addProductToCart(cartRequest);
                    call.enqueue(new RequestCallback<CartResponse>() {
                        @Override
                        protected void onResponseSuccess(Call<CartResponse> call, Response<CartResponse> response) {
                            MyDialogs.showNoticeDialog(getSupportFragmentManager(), "Product is added to cart!");
                        }

                        @Override
                        protected void onResponseFailed(Call<CartResponse> call, Throwable t) {

                        }
                    });

                }

            }
        });

        binding.tvSize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (selectedColorOption != null) {
                    OptionDialog optionDialog = new OptionDialog();
                    optionDialog.setSizeOptionArrayList(filterSizeOptionArrayList);
                    optionDialog.setOptionItemSelectedListener(new OptionDialog.OptionItemSelectListener() {
                        @Override
                        public void onColorItemSelected(ColorOption colorOption) {

                        }

                        @Override
                        public void onSizeItemSelected(SizeOption sizeOption) {
                            // this will be invoked
                            optionDialog.dismiss();
                            selectedSizeOption = sizeOption;
                            binding.tvSelectedSize.setVisibility(View.VISIBLE);
                            binding.ivSizeRight.setVisibility(View.GONE);
                            binding.tvSelectedSize.setText(selectedSizeOption.getTitle());

                        }
                    });
                    optionDialog.show(getSupportFragmentManager(), "size_dialog");
                } else {
                    MyDialogs.showNoticeDialog(getSupportFragmentManager(), "Please select color first.");
                }
            }
        });

    }


    private void loadProductDetails(int productId) {

        Call<Product> call = mainApi.getProductDetails(productId);

        call.enqueue(new RequestCallback<Product>() {
            @Override
            protected void onResponseSuccess(Call<Product> call, Response<Product> response) {
                product = response.body();
                createProductUI();
            }

            @Override
            protected void onResponseFailed(Call<Product> call, Throwable t) {

            }
        });

    }


    private void createProductUI() {
        if (product != null) {
            imageArrayList.addAll(product.getImages());
            adapter.notifyDataSetChanged();


            binding.productBrand.setText(product.getBrand());
            binding.productTitle.setText(product.getTitle());
            binding.productDetails.setText(product.getDescription());
            binding.productPriceCurrent.setText(product.getPriceCurrentAsString());
            binding.productPriceOriginal.setText(product.getPriceOriginalAsString());
        }
    }
}
